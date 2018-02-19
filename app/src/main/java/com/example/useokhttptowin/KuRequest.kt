package com.example.useokhttptowin

import android.os.Build
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync

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

    // 發送Get請求一個Json檔案並以JsonArray方式讀取&處理
    fun handleJsonArray(url: String, handler: ((jsonArray: JsonArray)->Unit)) {
        doAsync {
            val jsonStr = get(url)
            val jsonArray = gson.fromJson(jsonStr, JsonArray::class.java)
            handler(jsonArray)
        }
    }

    fun getJsonArray(url: String): JsonArray {
        val jsonStr = get(url)
        val jsonArray = gson.fromJson(jsonStr, JsonArray::class.java)
        return jsonArray
    }
}
