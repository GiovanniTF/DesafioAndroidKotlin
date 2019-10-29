package br.com.giovanni.desafioandroidkotlinapp.models

import com.google.gson.annotations.SerializedName

data class ApiResponse<T> (
    @SerializedName ("items")
    val response: List<T>
)