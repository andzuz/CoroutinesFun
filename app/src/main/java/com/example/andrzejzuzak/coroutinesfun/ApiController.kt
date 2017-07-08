package com.example.andrzejzuzak.coroutinesfun

import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineName
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiController {

    const val POST_COROUTINE_NAME = "post"

    val service: ApiService

    init {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        service = Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
    }

    fun getPost(postId: Int,
                success: (PostDto?) -> Unit,
                failure: (String?) -> Unit) {
        val call = service.getPost(postId = postId)

        launch(CommonPool + CoroutineName(POST_COROUTINE_NAME)) {
            val postResponse = call.execute()

            if (postResponse.isSuccessful) {
                success(postResponse.body())
            } else {
                failure(postResponse.errorBody()?.string())
            }
        }
    }

}