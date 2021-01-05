package com.techyourchance.dagger2course.common.dependencyinjection.app

import android.app.Application
import com.techyourchance.dagger2course.common.dependencyinjection.Retrofit1
import com.techyourchance.dagger2course.common.dependencyinjection.Retrofit2
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.networking.UrlProvider
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
  fun urlProvider() = UrlProvider()

  @ApplicationScope
  @Provides
  @Retrofit1
  fun retrofit1(urlProvider: UrlProvider): Retrofit = Retrofit.Builder()
    .baseUrl(urlProvider.getBaseUrl1())
    .addConverterFactory(GsonConverterFactory.create())
    .build()


  @ApplicationScope
  @Provides
  @Retrofit2
  fun retrofit2(urlProvider: UrlProvider): Retrofit = Retrofit.Builder()
    .baseUrl(urlProvider.getBaseUrl2())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  @ApplicationScope
  @Provides
  fun stackOverflowApi(@Retrofit1 retrofit: Retrofit): StackoverflowApi =
    retrofit.create(StackoverflowApi::class.java)
}
