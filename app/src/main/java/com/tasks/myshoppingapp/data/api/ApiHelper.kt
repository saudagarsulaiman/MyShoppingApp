package com.tasks.myshoppingapp.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getProducts() = apiService.getProducts()

    suspend fun getProfile() = apiService.getProfile()
}
