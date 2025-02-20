package com.diego.matesanz.idealista.framework.remote

import com.diego.matesanz.idealista.framework.remote.models.ProductItemResponse
import retrofit2.http.GET

interface ProductsService {

    @GET("list.json")
    suspend fun getProducts(): List<ProductItemResponse>
}
