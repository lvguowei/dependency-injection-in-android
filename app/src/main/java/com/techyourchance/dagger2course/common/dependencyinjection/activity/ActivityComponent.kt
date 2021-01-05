package com.techyourchance.dagger2course.common.dependencyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

  /**
   * if the Module does not has any parameters,
   * we can omit them here
   *
   * But for clarity purpose, we keep them anyway
   */
  fun newPresentationComponent(): PresentationComponent

  @Subcomponent.Builder
  interface Builder {
    /**
     * Binds an Activity directly to the component, not going through modules
     */
    @BindsInstance
    fun activity(activity: AppCompatActivity): Builder

    fun activityModule(activityModule: ActivityModule): Builder

    fun build(): ActivityComponent
  }
}
