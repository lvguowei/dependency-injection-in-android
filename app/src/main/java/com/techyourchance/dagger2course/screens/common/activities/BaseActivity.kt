package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.common.composition.ActivityCompositionRoot
import com.techyourchance.dagger2course.common.composition.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

  val activityCompositionRoot by lazy {
    ActivityCompositionRoot(
      this,
      (application as MyApplication).appCompositionRoot
    )
  }

  protected val compositionRoot by lazy {
    PresentationCompositionRoot(activityCompositionRoot)
  }
}
