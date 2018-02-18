package com.example.useokhttptowin

import android.os.Build
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.Request

class KuRequest {
    private val client = OkHttpClient()
    private val gson = Gson()

    // 發送一個get請求
    fun get(url: String): String? {
        val request = Request.Builder()
                .url(url)
                .build()
        val response = client.newCall(request).execute()
        return response.body()?.string()
    }

    fun <T>getJson(url: String, classOfT: Class<T>): T {
        val jsonStr = get(url)
        return gson.fromJson(jsonStr, classOfT)

    }
}
