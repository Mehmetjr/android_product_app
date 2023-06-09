package com.example.mehmet_can_gunduz_hw6.models

data class Product(
    val id : Long,
    val title : String,
    val description : String,
    val price : String,
    val discountPercentage : String,
    val rating : String,
    val stock : String,
    val brand : String,
    val category: String,
    val thumbnail : String,
    val images : List<String>)
