package br.com.giovanni.desafioandroidkotlinapp.models

import com.google.gson.annotations.SerializedName

data class Posts (

    @SerializedName("id")
    var id : Int,

    @SerializedName("name")
    var name : String,

    @SerializedName("description")
    var description : String

)