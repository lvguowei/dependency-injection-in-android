package com.techyourchance.dagger2course.screens.common.viewmodels

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject
import javax.inject.Provider


class ViewModelFactory @Inject constructor(
  private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
  private val savedStateRegistryOwner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

  override fun <T : ViewModel?> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ): T {

    val viewModel =
      providers[modelClass]?.get() ?: throw RuntimeException("Unsupported ViewModel ${modelClass}")

    if (viewModel is SavedStateViewModel) {
      viewModel.init(handle)
    }

    return viewModel as T
  }
}

