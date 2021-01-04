package com.techyourchance.dagger2course.common.dependencyinjection.activity

import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

  /**
   * if the Module does not has any parameters,
   * we can ommit them here
   *
   * But for clarity purpose, we keep them anyway
   */
  fun newPresentationComponent(): PresentationComponent
}
