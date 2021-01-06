package com.techyourchance.dagger2course.screens.common.viewsmvc


interface ScreensNavigator {

  fun navigateBack()

  fun toQuestionDetails(questionId: String)

  fun toViewModel()
}
