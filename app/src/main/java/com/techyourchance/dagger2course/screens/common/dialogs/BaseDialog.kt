package com.techyourchance.dagger2course.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

open class BaseDialog : DialogFragment() {

  protected val presentationComponent by lazy {
    (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
      PresentationModule(this)
    )
  }
}
