package com.techyourchance.dagger2course.common.dependencyinjection.app

import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityModule
import com.techyourchance.dagger2course.common.dependencyinjection.service.ServiceComponent
import com.techyourchance.dagger2course.common.dependencyinjection.service.ServiceModule
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {
  fun newActivityComponentBuilder(): ActivityComponent.Builder
  fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent
}
