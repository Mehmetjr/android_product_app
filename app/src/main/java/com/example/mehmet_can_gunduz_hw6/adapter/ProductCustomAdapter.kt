package com.example.mehmet_can_gunduz_hw6.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mehmet_can_gunduz_hw6.R
import com.example.mehmet_can_gunduz_hw6.models.DummyProducts
import com.example.mehmet_can_gunduz_hw6.models.Product
import retrofit2.Call

class ProductCustomAdapter(private val context: Activity, private val list : List<Product>
) : ArrayAdapter<Product>(context,R.layout.custom_list_item,list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rootView = context.layoutInflater.inflate(R.layout.custom_list_item,null,true)
        val img_list_product = rootView.findViewById<ImageView>(R.id.img_list_product)
        val txt_list_title = rootView.findViewById<TextView>(R.id.txt_list_title)
        val txt_list_brand = rootView.findViewById<TextView>(R.id.txt_list_brand)
        val txt_list_price = rootView.findViewById<TextView>(R.id.txt_list_price)

        val value = list.get(position)

        txt_list_title.text = value.title
        txt_list_brand.text = value.brand
        txt_list_price.text = value.price

        Glide
            .with(rootView)
            .load(value.thumbnail)
            .into(img_list_product)

        return rootView
    }
}