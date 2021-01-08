package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {

  val activityComponent: ActivityComponent by lazy {
    (application as MyApplication).appComponent.newActivityComponentBuilder()
      .activity(this)
      .build()
  }

  protected val presentationComponent by lazy {
    activityComponent.newPresentationComponent(PresentationModule(this))
  }
}
