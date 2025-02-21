package com.diego.matesanz.idealista.data.repositories

import com.diego.matesanz.idealista.data.datasource.ProductRemoteDataSource
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem

class ProductRepository(
    private val remoteDataSource: ProductRemoteDataSource,
) {
    suspend fun getProducts(): List<ProductItem> = remoteDataSource.getProducts()

    suspend fun getProductDetail(propertyCode: String): ProductDetail =
        remoteDataSource.getProductDetail(propertyCode)
}
