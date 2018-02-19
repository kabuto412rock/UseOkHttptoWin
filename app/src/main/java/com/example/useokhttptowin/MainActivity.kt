package com.example.useokhttptowin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private val kuRequest = KuRequest()
    private val openDataUrl = "http://opendata2.epa.gov.tw/AQI.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    fun setupView() {

        // 如果layout會改變，那就用這個固定size改進效能
        myRecycleView.setHasFixedSize(true)

        // 這樣recycle view的佈局就會成線性。
        mLayoutManager = LinearLayoutManager(this)
        myRecycleView.layoutManager = mLayoutManager

        doAsync {

            val jsonArrayFromOpenData: JsonArray = kuRequest.getJsonArray(openDataUrl)
            var citySet = mutableSetOf<String>()
            for (oneJson in jsonArrayFromOpenData) {
                val jsonObject = oneJson.asJsonObject
                val currentObjectCounty = jsonObject.get("County").asString
                citySet.add(currentObjectCounty)
            }
            Log.d("citySet", citySet.toString())
            val myAdapter = MyAdapter(jsonArrayFromOpenData)
            // 指定我們recycle view的adapter
            uiThread {
                myRecycleView.adapter = myAdapter
            }

        }
    }

}
