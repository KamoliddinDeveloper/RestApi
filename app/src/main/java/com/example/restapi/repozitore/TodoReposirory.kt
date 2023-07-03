package com.example.restapi.repozitore

import com.example.restapi.models.MyPostTodoReuest
import com.example.restapi.retrofit.ApiService

class TodoReposirory(val apiService: ApiService) {
    suspend fun getAllTodo() = apiService.getAllTodo()
    suspend fun addTodo(myPostTodoReuest: MyPostTodoReuest) = apiService.addTodo(myPostTodoReuest)
}