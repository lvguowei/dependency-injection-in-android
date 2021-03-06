package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import com.techyourchance.dagger2course.screens.common.viewsmvc.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.viewsmvc.ViewMvcFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionsListFragment : BaseFragment(), QuestionsListViewMvc.Listener {

  private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

  @Inject
  lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase

  @Inject
  lateinit var dialogsNavigator: DialogsNavigator

  @Inject
  lateinit var screensNavigator: ScreensNavigator

  @Inject
  lateinit var viewMvcFactory: ViewMvcFactory

  private lateinit var viewMvc: QuestionsListViewMvc

  private var isDataLoaded = false

  override fun onCreate(savedInstanceState: Bundle?) {
    presentationComponent.inject(this)
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewMvc = viewMvcFactory.newQuestionsListViewMvc(container)
    return viewMvc.rootView
  }

  override fun onStart() {
    super.onStart()
    viewMvc.registerListener(this)
    if (!isDataLoaded) {
      fetchQuestions()
    }
  }

  override fun onStop() {
    super.onStop()
    viewMvc.unregisterListener(this)
    coroutineScope.coroutineContext.cancelChildren()
  }

  private fun fetchQuestions() {
    coroutineScope.launch {
      viewMvc.showProgressIndication()
      try {
        when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
          is FetchQuestionsUseCase.Result.Success -> {
            viewMvc.bindQuestions(result.questions)
            isDataLoaded = true
          }
          is FetchQuestionsUseCase.Result.Failure -> {
            onFetchFailed()
          }
        }
      } finally {
        viewMvc.hideProgressIndication()
      }
    }
  }

  private fun onFetchFailed() {
    dialogsNavigator.showServerErrorDialog()
  }


  override fun onRefreshClicked() {
    fetchQuestions()
  }

  override fun onQuestionClicked(clickedQuestion: Question) {
    screensNavigator.toQuestionDetails(clickedQuestion.id)
  }

  override fun onViewModelClicked() {
    screensNavigator.toViewModel()
  }
}
