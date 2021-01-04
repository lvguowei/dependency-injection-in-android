package com.techyourchance.dagger2course.screens.questiondetails

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.questions.QuestionWithBody
import com.techyourchance.dagger2course.screens.common.imageloader.ImageLoader
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewsmvc.BaseViewMvc

class QuestionDetailsViewMvc(
  layoutInflater: LayoutInflater,
  private val imageLoader: ImageLoader,
  parent: ViewGroup?
) : BaseViewMvc<QuestionDetailsViewMvc.Listener>(
  layoutInflater,
  parent,
  R.layout.layout_question_details
) {

  interface Listener {
    fun navigateUp()
  }

  private var toolbar: MyToolbar
  private var swipeRefresh: SwipeRefreshLayout
  private var txtQuestionBody: TextView
  private var imgUser: ImageView
  private var txtUserName: TextView

  init {
    txtQuestionBody = findViewById(R.id.txt_question_body)

    // init toolbar
    toolbar = findViewById(R.id.toolbar)
    toolbar.setNavigateUpListener {

      for (listener in listeners) {
        listener.navigateUp()
      }
    }

    // init pull-down-to-refresh (used as a progress indicator)
    swipeRefresh = findViewById(R.id.swipeRefresh)
    swipeRefresh.isEnabled = false

    imgUser = findViewById(R.id.img_user)
    txtUserName = findViewById(R.id.txt_user_name)
  }


  fun bindQuestionWithBody(questionWithBody: QuestionWithBody) {
    val questionBody = questionWithBody.body
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      txtQuestionBody.text = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
    } else {
      @Suppress("DEPRECATION")
      txtQuestionBody.text = Html.fromHtml(questionBody)
    }
    imageLoader.loadImage(questionWithBody.owner.imageUrl, imgUser)
    txtUserName.text = questionWithBody.owner.name
  }

  fun showProgressIndication() {
    swipeRefresh.isRefreshing = true
  }

  fun hideProgressIndication() {
    swipeRefresh.isRefreshing = false
  }

}
