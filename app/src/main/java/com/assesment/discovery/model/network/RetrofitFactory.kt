package com.assesment.discovery.model.network

import androidx.annotation.NonNull
import com.assesment.discovery.BuildConfig
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This class is used to configure Retrofit, OkHttp and Jackson together
 * **/
object RetrofitFactory {

    private var retrofit: Retrofit? = null

    private val retrofitClient: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!
        }

    private val okHttpClient: OkHttpClient
        @NonNull
        get() {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(httpLoggingInterceptor)
            builder.connectTimeout(60, TimeUnit.SECONDS) // connect timeout
            builder.readTimeout(60, TimeUnit.SECONDS)    // socket timeout
            builder.retryOnConnectionFailure(true)
            return builder.build()
        }

    private val objectMapper: ObjectMapper
        @NonNull
        get() {
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false)
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true)
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
            objectMapper.registerModule(
                KotlinModule(
                    512,
                    nullToEmptyCollection = true,
                    nullToEmptyMap = true
                )
            )
            return objectMapper
        }


    private val httpLoggingInterceptor: HttpLoggingInterceptor
        @NonNull
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            return httpLoggingInterceptor
        }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofitClient.create(serviceClass)
    }

}