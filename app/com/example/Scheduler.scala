package com.example

import java.util.UUID
import java.util.concurrent.TimeUnit
import akka.actor.{ActorSystem, Cancellable}
import javax.inject.Inject
import play.api.Logger
import play.api.libs.ws.WSClient
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.FiniteDuration
import scala.util.{Failure, Success}

class Scheduler @Inject()(actorSystem: ActorSystem, wsClient: WSClient) {

  def startScheduler(interval: FiniteDuration): Cancellable =
    actorSystem.scheduler.schedule(FiniteDuration(1, TimeUnit.SECONDS), interval) {
      (1 to 500).foreach { i =>
        val latency = 10
        val id      = UUID.randomUUID()
        Logger.info(s"Starting a call nr $i with latency of $latency, id=$id")
        wsClient
          .url(s"https://localhost:443/hello-world/$latency?requestId=$id")
          .get
          .onComplete {
            case Success(response) =>
              if (response.status == 200) Logger.info(s"Got a response for call id=$id")
              else Logger.info(s"Got non-200 response for call id=$id, status=${response.status}")
            case Failure(exception) => Logger.info(s"Failure getting response for call id=$id", exception)
          }
      }
    }

}
