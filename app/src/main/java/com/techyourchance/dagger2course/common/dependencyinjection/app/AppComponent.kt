package com.techyourchance.dagger2course.common.dependencyinjection.app

import android.app.Application
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

  fun application(): Application

  fun stackOverflowApi(): StackoverflowApi
}
