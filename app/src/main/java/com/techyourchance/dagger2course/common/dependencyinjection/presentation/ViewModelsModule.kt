package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import androidx.lifecycle.ViewModel
import com.techyourchance.dagger2course.common.dependencyinjection.ViewModelKey
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel
import com.techyourchance.dagger2course.screens.viewmodel.MyViewModel2
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

  @Binds
  @ViewModelKey(MyViewModel::class)
  @IntoMap
  abstract fun myViewModel(myViewModel: MyViewModel): ViewModel

  @Binds
  @ViewModelKey(MyViewModel2::class)
  @IntoMap
  abstract fun myViewModel2(myViewModel2: MyViewModel2): ViewModel
}
