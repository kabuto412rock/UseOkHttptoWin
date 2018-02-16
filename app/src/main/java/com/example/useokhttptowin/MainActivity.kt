package com.example.useokhttptowin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testJsonUrl = "http://jsonplaceholder.typicode.com/Users"
        val kuRequest = KuRequest()
        doAsync {
            val jsonStr = kuRequest.get(testJsonUrl)
            println("jsonStr = $jsonStr")
        }
    }


}
