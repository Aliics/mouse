# Mouse - HTTP Server + Client Library

Small, simple, minimal HTTP server and client library with no dependencies.
Since it isn't doing too much in the background, it should also be pretty darn fast.
On my machine, a query to a simple query to a nop GET request can be as fast as **5ms** with cURL.
This is on-par or as fast as other popular HTTP libraries in a variety of languages.

# Usage

In your build.sbt:

```scala
libraryDependencies ++= Seq(
  // ...
  "io.github.aliics" %% "mouse" % "0.5.0",
  // ...
)
```

Setting up a [Server](./src/main/scala/mouse/Server.scala) is as easy as providing
some [Routes](./src/main/scala/mouse/Route.scala) and a port! Then calling `run` or `runBlocking`.

```scala
Server(
  routes(
    Method.Get / "hello" / "world" -> greetWorld,
    // ... More routes
  ) *
).runBlocking(port = 8080)
```

A [Client](./src/main/scala/mouse/Client.scala) is even easier!

```scala
Client("localhost", 8080).getBlocking("hello/alex")
```

All HTTP method helper methods have a "blocking" wrapper for convenience. Generally, you'll want to use the async ones
(`getBlocking` -> `get`).
