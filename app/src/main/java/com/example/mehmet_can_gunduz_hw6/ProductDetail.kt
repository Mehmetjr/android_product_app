package com.example.mehmet_can_gunduz_hw6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProductDetail : AppCompatActivity() {

    lateinit var img_product : ImageView
    lateinit var txt_title : TextView
    lateinit var txt_brand : TextView
    lateinit var txt_rating : TextView
    lateinit var txt_stock : TextView
    lateinit var txt_description : TextView
    lateinit var txt_price : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        img_product = findViewById(R.id.img_product)
        txt_title = findViewById(R.id.txt_title)
        txt_brand = findViewById(R.id.txt_brand)
        txt_rating = findViewById(R.id.txt_rating)
        txt_stock = findViewById(R.id.txt_stock)
        txt_description = findViewById(R.id.txt_description)
        txt_price = findViewById(R.id.txt_price)

        txt_title.text = ProductList.productDetail.title
        txt_brand.text = ProductList.productDetail.brand
        txt_rating.text = ProductList.productDetail.rating
        txt_stock.text = ProductList.productDetail.stock
        txt_description.text = ProductList.productDetail.description
        txt_price.text = ProductList.productDetail.price


        Glide
            .with(this)
            .load(ProductList.productDetail.thumbnail)
            .into(img_product)
    }
}