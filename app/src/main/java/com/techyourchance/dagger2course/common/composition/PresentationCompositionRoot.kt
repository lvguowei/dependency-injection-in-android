package com.techyourchance.dagger2course.common.composition

import com.techyourchance.dagger2course.questions.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.viewsmvc.ViewMvcFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {

  private val layoutInflater
    get() = activityCompositionRoot.layoutInflater

  private val supportFragmentManager
    get() = activityCompositionRoot.supportFragmentManager

  private val stackOverflowApi
    get() = activityCompositionRoot.stackOverflowApi


  val viewMvcFactory
    get() = ViewMvcFactory(layoutInflater)

  val dialogsNavigator
    get() = DialogsNavigator(supportFragmentManager)

  val screensNavigator
    get() = activityCompositionRoot.screensNavigator

  val fetchQuestionsUseCase
    get() = FetchQuestionsUseCase(stackOverflowApi)

  val fetchQuestionDetailsUseCase
    get() = FetchQuestionDetailsUseCase(stackOverflowApi)
}
