package com.example.mehmet_can_gunduz_hw6.services

import com.example.mehmet_can_gunduz_hw6.models.DummyProducts
import com.example.mehmet_can_gunduz_hw6.models.JWTData
import com.example.mehmet_can_gunduz_hw6.models.JWTUser
import com.example.mehmet_can_gunduz_hw6.models.Product
import retrofit2.Call
import retrofit2.http.*

interface DummyServices {


    @POST("/auth/login")
    fun login(@Body jwtUser : JWTUser) : Call<JWTData>

    @GET("products?limit=10")
    fun products() : Call<DummyProducts>


    @GET("products/search")
    fun searchProducts(@Query("q") query: String):Call<DummyProducts>
}