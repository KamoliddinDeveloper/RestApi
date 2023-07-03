package com.example.restapi.models

data class MyPostTodoResponse(

    val bajarildi: Boolean,
    val batafsil: String,
    val id: Int,
    val oxirgi_muddat: String,
    val sana: String,
    val sarlavha: String,
    val zarurlik: String
)