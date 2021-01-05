package com.techyourchance.dagger2course.common.dependencyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.ScreensNavigatorImpl
import com.techyourchance.dagger2course.screens.common.viewsmvc.ScreensNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

  @ActivityScope
  @Binds
  abstract fun screensNavigator(screensNavigator: ScreensNavigatorImpl): ScreensNavigator

  companion object {

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = activity.layoutInflater

    @Provides
    fun supportFragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
  }
}
