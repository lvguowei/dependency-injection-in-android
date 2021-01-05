package com.techyourchance.dagger2course.common.service

import android.app.Service
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.service.ServiceComponent
import com.techyourchance.dagger2course.common.dependencyinjection.service.ServiceModule

abstract class BaseService : Service() {

  val serviceComponent: ServiceComponent by lazy {
    (application as MyApplication).appComponent.newServiceComponent(ServiceModule(this))
  }
}
