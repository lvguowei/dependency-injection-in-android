package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import kotlinx.coroutines.*

class QuestionsListFragment : BaseFragment(), QuestionsListViewMvc.Listener {

  private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
  private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase
  private lateinit var dialogsNavigator: DialogsNavigator
  private lateinit var screensNavigator: ScreensNavigator

  private lateinit var viewMvc: QuestionsListViewMvc

  private var isDataLoaded = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase
    dialogsNavigator = compositionRoot.dialogsNavigator
    screensNavigator = compositionRoot.screensNavigator
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewMvc = compositionRoot.viewMvcFactory.newQuestionsListViewMvc(container)
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
}
