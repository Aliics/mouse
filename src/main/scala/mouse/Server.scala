package mouse

import mouse.errors.ParseError

import java.net.{ServerSocket, Socket}
import scala.concurrent.{ExecutionContext, Future}

class Server(routes: Route*)(using ExecutionContext):
  /**
   * Run the [[Server]] asynchronously on the specified [[port]].
   *
   * @param port Socket port to bind.
   * @return
   */
  def run(port: Int): Future[Unit] =
    for
      ss <- Future(ServerSocket(port))
      _ <- listen(ss)
    yield ()

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
          route <- Future(routes.find(_.matcher(req.method, req.uri)))
          resp <- route.fold
            // No route was found. 404.
              (Future(Response.NotFound()))
            // Pass the request onto the handler. We found the route!
              (_.handler(req))

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
