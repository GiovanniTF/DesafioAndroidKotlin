package br.com.giovanni.desafioandroidkotlinapp.api

import com.google.gson.annotations.SerializedName

data class Posts(

    @SerializedName("id")
    var id: Int,

    @SerializedName("full_name")
    var full_name: String,

    @SerializedName("owner")
    var owner: Owner,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("forks")
    var forks: Int,

    @SerializedName("stargazers_count")
    var stargazers_count: Int

)