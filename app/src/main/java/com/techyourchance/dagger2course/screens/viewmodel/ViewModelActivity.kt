package com.techyourchance.dagger2course.screens.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.toolbar.MyToolbar
import com.techyourchance.dagger2course.screens.common.viewmodels.ViewModelFactory
import com.techyourchance.dagger2course.screens.common.viewsmvc.ScreensNavigator
import javax.inject.Inject

class ViewModelActivity : BaseActivity() {

  @Inject
  lateinit var screensNavigator: ScreensNavigator

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  lateinit var viewModel: MyViewModel

  lateinit var viewModel2: MyViewModel2

  private lateinit var toolbar: MyToolbar

  override fun onCreate(savedInstanceState: Bundle?) {
    presentationComponent.inject(this)
    super.onCreate(savedInstanceState)

    setContentView(R.layout.layout_view_model)

    toolbar = findViewById(R.id.toolbar)
    toolbar.setNavigateUpListener {
      screensNavigator.navigateBack()
    }

    viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
    viewModel2 = ViewModelProvider(this, viewModelFactory).get(MyViewModel2::class.java)

    viewModel.questions.observe(this, Observer {
      Toast.makeText(this, "fetched ${it.size}", Toast.LENGTH_LONG).show()
    })
  }

  companion object {
    fun start(context: Context) {
      val intent = Intent(context, ViewModelActivity::class.java)
      context.startActivity(intent)
    }
  }
}
