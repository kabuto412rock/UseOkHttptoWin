package com.example.useokhttptowin

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonArray
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_view.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by guzongjia on 2018/2/18.
 */
class MyAdapter(val jsonArray: JsonArray): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        Log.d("實驗", "getItemCount")

        return jsonArray.size()
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        Log.d("實驗", "onBindViewHolder")

        holder?.apply {
            itemView.onClick {

            }
            val oneSiteInformation = jsonArray.get(position)!!.asJsonObject
            itemView.AQI_textview.text = "AQI指數：" + oneSiteInformation.get("AQI").asString
            itemView.site_textview.text = oneSiteInformation.get("SiteName").asString
            itemView.status_textview.text = oneSiteInformation.get("Status").asString

            val countyName = oneSiteInformation.get("County").asString
            val countyIconUrl = CITY_MAP[countyName]
            Picasso.with(itemView.context).load(countyIconUrl).into(itemView.city_icon_view)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        Log.d("實驗", "onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellRow = layoutInflater.inflate(R.layout.card_view, parent, false)
        return CustomViewHolder(cellRow)
    }


}
class CustomViewHolder(view: View): RecyclerView.ViewHolder(view)
