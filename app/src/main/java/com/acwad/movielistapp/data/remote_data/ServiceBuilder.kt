package com.acwad.movielistapp.data.remote_data

import com.acwad.movielistapp.Utils.constant
import com.acwad.movielistapp.data.MoviesApiClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ServiceBuilder {

    operator fun invoke(): MoviesApiClient {
          val interceptor = Interceptor { chain ->
            val url =
                chain.request().url().newBuilder().addQueryParameter("api_key", constant.apiKey)
                    .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
        // we are creating a networking client using OkHttp and add our authInterceptor.
          val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

          fun getRetrofit(): Retrofit {
            return Retrofit.Builder().client(apiClient)
                .baseUrl(constant.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())

                .build()
        }

        var ServiceApi: MoviesApiClient = getRetrofit().create(MoviesApiClient::class.java)

        return  ServiceApi

    }
}