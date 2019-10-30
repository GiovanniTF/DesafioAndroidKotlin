package br.com.giovanni.desafioandroidkotlinapp.models

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("login")
    var login : String
)