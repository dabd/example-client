package com.example.config

import com.example.Scheduler
import javax.inject.{Inject, Singleton}

@Singleton
class SchedulerStarter @Inject()(scheduler: Scheduler) {
  import scala.concurrent.duration._

  scheduler.startScheduler(1 seconds)
}
