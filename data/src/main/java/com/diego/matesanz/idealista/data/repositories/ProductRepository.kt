package com.diego.matesanz.idealista.data.repositories

import com.diego.matesanz.idealista.data.datasource.ProductLocalDataSource
import com.diego.matesanz.idealista.data.datasource.ProductRemoteDataSource
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource,
) {

    private val savedProducts: Flow<List<ProductItem>> = localDataSource.getSavedProducts()

    suspend fun getProducts(): List<ProductItem> = remoteDataSource.getProducts()

    suspend fun getProductDetail(propertyCode: String): ProductDetail =
        remoteDataSource.getProductDetail(propertyCode)
}
