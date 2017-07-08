package com.example.andrzejzuzak.coroutinesfun

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFirstPost()
    }

    fun getFirstPost() {
        ApiController.getPost(1, this::onSuccess, this::onFailure)
    }

    private fun onSuccess(postDto: PostDto?) {
        postDto?.let {
            runOnUiThread {
                textView.text = "${postDto.userId}"
            }
        }
    }

    private fun onFailure(errorMsg: String?) {
        errorMsg?.let {
            runOnUiThread {
                textView.text = errorMsg
            }
        }
    }

}
