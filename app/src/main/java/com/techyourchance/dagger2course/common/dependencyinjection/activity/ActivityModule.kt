package com.techyourchance.dagger2course.common.dependencyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
  private val activity: AppCompatActivity
) {

  @Provides
  fun activity() = activity

  @Provides
  @ActivityScope
  fun screensNavigator(activity: AppCompatActivity) = ScreensNavigator(activity)

  @Provides
  fun layoutInflater(activity: AppCompatActivity) = activity.layoutInflater

  @Provides
  fun supportFragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
}
