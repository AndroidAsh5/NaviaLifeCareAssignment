package com.karexpert.navialifecareassignment.core

data class Resource<out T>(val status: Utills, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Utills.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Utills.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Utills.LOADING, data, null)
        }

    }

}