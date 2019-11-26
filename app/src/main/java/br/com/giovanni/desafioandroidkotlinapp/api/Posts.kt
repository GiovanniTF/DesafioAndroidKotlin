package br.com.giovanni.desafioandroidkotlinapp.api

import com.google.gson.annotations.SerializedName

data class Posts(

    @SerializedName("id")
    val id: Int,

    @SerializedName("full_name")
    val full_name: String,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("forks")
    val forks: Int,

    @SerializedName("stargazers_count")
    val stargazers_count: Int

)