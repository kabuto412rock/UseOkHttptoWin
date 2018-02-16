package com.example.useokhttptowin

import okhttp3.OkHttpClient
import okhttp3.Request

class KuRequest {
    private val client = OkHttpClient()

    // 發送一個get請求
    fun get(url: String): String? {
        val request = Request.Builder()
                .url(url)
                .build()

        val response = client.newCall(request).execute()
        return response.body()?.string()
    }

}