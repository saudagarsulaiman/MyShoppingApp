package com.tasks.myshoppingapp.data.model

data class ProductsModel(
    var data:  ProductsData
)

data class  ProductsData(
    var products: ArrayList<ProductDetails>
)

data class ProductDetails(
    var id: String,
    var brand: String,
    var name: String,
    var productDesc: String,
    var price: String,
    var offerPrice: String,
    var productUrl: String
)