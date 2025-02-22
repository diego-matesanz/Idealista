package com.diego.matesanz.idealista.data.datasource

import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem

interface ProductRemoteDataSource {
    suspend fun getProducts(): List<ProductItem>
    suspend fun getProductDetail(propertyCode: String): ProductDetail
}
