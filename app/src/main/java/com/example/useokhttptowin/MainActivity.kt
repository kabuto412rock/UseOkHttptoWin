package com.example.useokhttptowin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {
    val KuRequest = KuRequest()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonApiUrl = ""
        KuRequest.getJson(jsonApiUrl, )

    }

}
