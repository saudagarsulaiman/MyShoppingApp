package com.tasks.myshoppingapp.data.repository

import com.tasks.myshoppingapp.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getProducts() = apiHelper.getProducts()

    suspend fun getProfile() = apiHelper.getProfile()
}