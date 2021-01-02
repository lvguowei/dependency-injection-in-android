package com.techyourchance.dagger2course.questions

import com.techyourchance.dagger2course.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionDetailsUseCase(private val stackOverflowApi: StackoverflowApi) {

  sealed class Result {
    class Success(val questionWithBody: QuestionWithBody) : Result()
    object Failure : Result()
  }

  suspend fun fetchQuestionDetails(questionId: String): Result {
    return withContext(Dispatchers.IO) {
      try {
        val response = stackOverflowApi.questionDetails(questionId)
        if (response.isSuccessful && response.body() != null) {
          return@withContext Result.Success(response.body()!!.question)
        } else {
          return@withContext Result.Failure
        }
      } catch (t: Throwable) {
        if (t !is CancellationException) {
          return@withContext Result.Failure
        } else {
          throw t
        }
      }
    }
  }
}
