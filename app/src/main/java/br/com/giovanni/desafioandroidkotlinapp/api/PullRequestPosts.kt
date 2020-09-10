package br.com.giovanni.desafioandroidkotlinapp.api

import com.google.gson.annotations.SerializedName

data class PullRequestPosts(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("user")
    val user: User,

    @SerializedName("head")
    val head: Head

)

data class User(
    @SerializedName("avatar_url")
    var avatar_url: String,

    @SerializedName("login")
    var login: String

)

data class Head(
    @SerializedName("repo")
    val repo: Repo?
)

data class Repo(
    @SerializedName("description")
    val description: String,

    @SerializedName("full_name")
    val full_name: String
)