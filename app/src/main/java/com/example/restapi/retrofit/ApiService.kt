package com.example.restapi.retrofit

import com.example.restapi.models.MyPostTodoResponse
import com.example.restapi.models.MyPostTodoReuest
import com.example.restapi.models.MyTodo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("plan")
    suspend fun getAllTodo():List<MyTodo>

    @POST("plan/")
    suspend fun addTodo(@Body myPostTodoReuest: MyPostTodoReuest) : MyPostTodoResponse
}