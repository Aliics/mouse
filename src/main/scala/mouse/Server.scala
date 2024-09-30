package mouse

import mouse.errors.ParseError
import org.slf4j.Logger

import java.net.{ServerSocket, Socket, URI}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

/**
 * Create the [[Server]] with the specified [[Route]]s.
 *
 * To actually to running the server, invoke the [[run]] method with the port you want to listen on.
 *
 * {{{
 *     Server(
 *       routes(
 *         Method.Get / "hello" / "world" -> greetWorld,
 *       ) *
 *     ).runBlocking(port = 8080)
 * }}}
 *
 * @param routes Request handlers bound to specified endpoints.
 * @param logger A logger implementation is required, this is for important debugging information.
 * @param x$3    To handle everything asynchronously, an [[ExecutionContext]] must be provided.
 */
class Server(routes: Route*)(using logger: Logger)(using ExecutionContext):
  /**
   * Wraps the [[run]] method using [[Await]].
   * This is the method that most servers will want to use.
   * Both exist to allow for multiple server bindings and control.
   *
   * @param port Socket port to bind.
   */
  def runBlocking(port: Int): Unit =
    Await.result(run(port), Duration.Inf)

  /**
   * Run the [[Server]] asynchronously on the specified [[port]].
   *
   * @param port Socket port to bind.
   */
  //noinspection ScalaWeakerAccess
  def run(port: Int): Future[Unit] =
    Future(ServerSocket(port)).flatMap(listen)

  /**
   * Recursively accept connections and offload the handling to the [[handle]] function.
   * Runs asynchronously. The handling is done asynchronously as well, and the result is not awaited.
   *
   * @param ss [[ServerSocket]] bound.
   * @return
   */
  private def listen(ss: ServerSocket): Future[Unit] =
    for
      conn <- Future(ss.accept())
      _ = handle(conn) // Future is not awaited. Executed asynchronously.
      _ <- listen(ss) // Listen for the next request.
    yield ()

  private def handle(conn: Socket): Future[Unit] =
    Request(conn.getInputStream).flatMap:
      case Right(req) =>
        given Request = req

        for
          route <- Future:
            routes.find(_.matcher(
              req.method,
              URI `create` req.uri.getPath,
            ))
          resp <- route.fold
            // No route was found. 404.
              (Future(Response.NotFound()))
            // Pass the request onto the handler. We found the route!
              (_.handler(req).transformWith:
                case Success(r) => Future(r)
                case Failure(e) => Future:
                  // Log the error and respond with a 500, so the caller knows something went awry.
                  // We don't want to necessarily give the caller details, though, so let's respond vaguely.
                  logger.error(s"Unhandled error thrown. Request to ${req.uri} failed.", e)
                  Response.InternalServerError(body = "Request handling failed.")
              )

          // Respond and close.
          _ <- Future:
            resp.writeToStream(conn.getOutputStream)
            conn.close()
        yield ()
      case Left(ParseError(message)) =>
        // Write the error message back to the response.
        // These are parsing errors, not handling errors.
        Future:
          conn.getOutputStream.write(s"ParseError: $message".getBytes)
