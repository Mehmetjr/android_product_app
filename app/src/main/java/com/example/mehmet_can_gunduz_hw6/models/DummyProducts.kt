package com.example.mehmet_can_gunduz_hw6.models

data class DummyProducts (
    val products: List<Product>,
    val total : Long,
    val skip : Long,
    val limit : Long)