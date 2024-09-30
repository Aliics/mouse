package mouse

import mouse.errors.ParseError

import java.io.InputStream
import java.net.{ServerSocket, Socket}
import scala.concurrent.{ExecutionContext, Future}

class Server(routes: Route*)(using ExecutionContext):
  def run(port: Int): Future[Unit] =
    for
      ss <- Future(ServerSocket(port))
      _ <- listen(ss)
    yield ()

  private def listen(ss: ServerSocket): Future[Unit] =
    for
      conn <- Future(ss.accept())
      _ <- handle(conn)
      _ <- listen(ss)
    yield ()

  private def handle(conn: Socket): Future[Unit] =
    Request(conn.getInputStream).flatMap:
      case Right(req) =>
        for
          route <- Future(routes.find(_.matcher(req.method, req.uri)))
          resp <- route.fold(
            // No route was found. 404.
            Future(Response(
              version = req.version,
              status = Status.NotFound,
              headers = Map.empty,
              body = InputStream.nullInputStream,
            ))
          )(_.handler(req))

          // Respond and close.
          _ <- Future:
            resp.writeToStream(conn.getOutputStream)
            conn.close()
        yield ()
      case Left(ParseError(message)) =>
        Future:
          conn.getOutputStream.write(message.getBytes)
