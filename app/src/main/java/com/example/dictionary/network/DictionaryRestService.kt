package com.example.dictionary.network

import com.example.dictionary.model.Base
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.TimeUnit

interface DictionaryRestService {
    companion object {
        val instance: DictionaryRestService by lazy {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()
                        val requestBuilder = original.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Request-Type", "Android")
                            .addHeader("Content-Type", "application/json")
                            .addHeader(
                                "x-rapidapi-host",
                                "mashape-community-urban-dictionary.p.rapidapi.com"
                            )
                            .addHeader(
                                "x-rapidapi-key",
                                "bb13610f65msh7ef236003435eccp18fbb3jsn6c51f468afe2"
                            )
                        val request = requestBuilder.build()
                        return chain.proceed(request)
                    }
                })
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(DictionaryRestService::class.java)
        }
    }
    @GET("/define")
    fun getTerm(@Query("term") term: String): Single<Base>
}