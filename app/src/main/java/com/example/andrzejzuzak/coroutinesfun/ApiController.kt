package com.example.andrzejzuzak.coroutinesfun

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineName
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit

object ApiController {

    const val POST_COROUTINE_NAME = "post"

    val service: ApiService = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .build()
            .create(ApiService::class.java)

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