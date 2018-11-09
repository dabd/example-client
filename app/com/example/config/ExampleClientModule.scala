package com.example.config

import com.google.inject.AbstractModule

class ExampleClientModule extends AbstractModule {
  override def configure(): Unit =
    bind(classOf[SchedulerStarter]).asEagerSingleton()
}
