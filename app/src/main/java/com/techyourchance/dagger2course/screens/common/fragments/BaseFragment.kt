package com.techyourchance.dagger2course.screens.common.fragments

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.DaggerPresentationComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {
  protected val presentationComponent by lazy {
    DaggerPresentationComponent.builder()
      .activityComponent((requireActivity() as BaseActivity).activityComponent)
      .presentationModule(PresentationModule())
      .build()
  }
}
