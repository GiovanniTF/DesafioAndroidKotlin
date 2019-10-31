package br.com.giovanni.desafioandroidkotlinapp.models

import com.google.gson.annotations.SerializedName

data class Owner (

    @SerializedName("login")
    var login : String,

    @SerializedName("avatar_url")
    var avatar_url : String

)