package com.techyourchance.dagger2course.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreensNavigator

class ActivityCompositionRoot(
  private val activity: AppCompatActivity,
  private val appCompositionRoot: AppCompositionRoot
) {

  val screensNavigator: ScreensNavigator by lazy {
    ScreensNavigator(activity)
  }

  val layoutInflater
    get() = activity.layoutInflater

  val supportFragmentManager
    get() = activity.supportFragmentManager

  val stackOverflowApi: StackoverflowApi
    get() = appCompositionRoot.stackOverflowApi
}
