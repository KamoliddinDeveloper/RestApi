package com.example.restapi.utils

import java.util.Date

@Suppress("UNREACHABLE_CODE")
data class Resource<out T>(val  status: Status, val data:T?, val message: String?){

    companion object{
        fun <T>succsess(data:T):Resource<T>{
            return Resource(Status.SUCCSESS, data, null)
        }
        fun <T>error(message: String?):Resource<T>{
            return Resource(Status.ERROR, null, message)
        }
        fun <T>loading(message: String?):Resource<T>{
            return Resource(Status.LOADING, null, message)
        }
    }
}
