package com.tasks.myshoppingapp.data.api

import com.tasks.myshoppingapp.data.model.ProductsModel
import com.tasks.myshoppingapp.data.model.ProfileModel
import retrofit2.http.GET

interface ApiService {

    @GET("bc09a745-4346-4025-9611-9da76366dbbc")
    suspend fun getProducts(): ProductsModel

    @GET("aaf97364-eedc-46a5-8f9e-56eb4b3cedd2")
    suspend fun getProfile(): ProfileModel
}