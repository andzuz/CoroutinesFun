package com.example.andrzejzuzak.coroutinesfun

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val POST_ID_PATH_PARAM = "postId"
        const val POST_ENDPOINT = "posts/{$POST_ID_PATH_PARAM}"
    }

    @GET(POST_ENDPOINT)
    fun getPost(@Path(POST_ID_PATH_PARAM) postId: Int): Call<PostDto>

}