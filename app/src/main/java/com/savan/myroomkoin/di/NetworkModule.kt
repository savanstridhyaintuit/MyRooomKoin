package com.savan.myroomkoin.di

import android.content.Context
import com.savan.myroomkoin.BuildConfig
import com.savan.myroomkoin.api.services.ApiHelper
import com.savan.myroomkoin.api.services.ApiService
import com.savan.myroomkoin.api.services.Api_Impl
import com.savan.myroomkoin.repository.MainRepository
import com.savan.myroomkoin.utils.NetworkHelper
import com.savan.myroomkoin.viewmodel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), "https://jsonplaceholder.typicode.com/") }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> {
        return@single Api_Impl(get())
    }
    single {MainRepository(get())}

    viewModel {
        MainViewModel(get(), get())
    }
}


private fun provideNetworkHelper(context: Context) = NetworkHelper(context)
private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)



private val loadNetworkFeatures by lazy {
    loadKoinModules(listOf(networkModule))

}

fun injectNetworkModule() = loadNetworkFeatures