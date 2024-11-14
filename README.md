# Mouse - HTTP Server + Client Library

[![mouse Scala version support](https://index.scala-lang.org/aliics/mouse/mouse/latest.svg)](https://index.scala-lang.org/aliics/mouse/mouse)

Small, simple, and lightweight HTTP server and client library with no dependencies.

Using Postman as a client, the HTTP server responds within 2-3ms, which is faster than many popular alternatives.

# Motivation

Other HTTP libraries had the issue of having a large dependency tree and a lot of setup to make even the most basic of
endpoints. The Scala ecosystem really needed an easy-to-use and simple library with no fuss, and that's what
**mouse is**!

**Our goals:**

- Easy to use
- Simple to reason about
- Quick to install
- Great performance
- Works from prototype to production

# Get Started

In your build.sbt:

```scala
libraryDependencies ++= Seq(
  // ...
  "io.github.aliics" %% "mouse" % "0.6.0",
  // ...
)
```

mouse's layout is very simple, there are only two imports you will need:

```scala
import mouse.* // For our Server, Client, etc. 
import mouse.types.* // For our Request, Response, etc.
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
Client("localhost", port = 8080)
  .getBlocking("hello/alex") // GET Request was made, and we awaited the Response.
  .textBlocking() // The body of the response.
```

All HTTP method helper methods have a "blocking" wrapper for convenience. Generally, you'll want to use the async ones
(`getBlocking` -> `get`).

Full examples exists in the [examples](/examples) directory.

# Contributing

Get your [fork](https://github.com/Aliics/mouse/fork) and make a [pull request](https://github.com/Aliics/mouse/pulls)!
We'll go from there.
