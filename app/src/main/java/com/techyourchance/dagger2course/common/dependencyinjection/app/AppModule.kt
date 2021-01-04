package com.techyourchance.dagger2course.common.dependencyinjection.app

import android.app.Application
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val app: Application) {

  @Provides
  fun application() = app

  @ApplicationScope
  @Provides
  fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  @ApplicationScope
  @Provides
  fun stackOverflowApi(retrofit: Retrofit): StackoverflowApi =
    retrofit.create(StackoverflowApi::class.java)
}
