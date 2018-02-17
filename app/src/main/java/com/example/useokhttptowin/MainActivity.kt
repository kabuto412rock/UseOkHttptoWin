package com.example.useokhttptowin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.TextView
import com.example.useokhttptowin.R.id.textview_show_json
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 讓textview能有scroll bar能往下滑
        textview_show_json.movementMethod = ScrollingMovementMethod()

        btn_gson_test.onClick {
            val testJsonUrl = "http://jsonplaceholder.typicode.com/Users"
            val kuRequest = KuRequest()
            doAsync {
                val jsonStr = kuRequest.get(testJsonUrl)

                jsonStr?.let {
                    handleJsonstr(it, textview_show_json)
                }
            }
        }

        btn_clear_textview.onClick {
            textview_show_json.text = ""
        }
    }



}

class User(val name: String,
           val email: String,
           val address: UserAddress,
           val company: UserComany)
class UserAddress(val city: String)
class UserComany(val name: String)

fun Context.handleJsonstr(jsonStr: String, textView: TextView) {
    val gson = Gson()
    val userArray = gson.fromJson(jsonStr, JsonArray::class.java)
    val stringBuilder = StringBuilder()
    for (user in userArray) {
        val userObj = user.asJsonObject

        val userId = userObj.get("id")
        val userCityAddress = userObj.getAsJsonObject("address").get("city")
        val userCompanyName = userObj.getAsJsonObject("company").get("name")
        val userName = userObj["name"]

        stringBuilder.append("$userId. 名稱:$userName, 城市:$userCityAddress, 公司:$userCompanyName\n\n")
    }

    runOnUiThread {
        textView.text = stringBuilder.toString()
    }

}