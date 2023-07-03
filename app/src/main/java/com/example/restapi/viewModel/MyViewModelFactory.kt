package com.example.restapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restapi.repozitore.TodoReposirory

class MyViewModelFactory(val todoReposirory: TodoReposirory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TodoViewModel::class.java)){
            return TodoViewModel(todoReposirory) as T
        }
        throw IllegalAccessException("Error")
    }
}