package com.example.mehmet_can_gunduz_hw6.models

data class JWTData(
    val id : Long,
    val username : String,
    val email: String,
    val firstname : String,
    val lastname : String,
    val gender : String,
    val image: String,
    val token : String)
