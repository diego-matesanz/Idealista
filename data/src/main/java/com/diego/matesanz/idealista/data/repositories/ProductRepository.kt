package com.diego.matesanz.idealista.data.repositories

import com.diego.matesanz.idealista.data.datasource.ProductLocalDataSource
import com.diego.matesanz.idealista.data.datasource.ProductRemoteDataSource
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class ProductRepository(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource,
) {

    private val savedProducts: Flow<List<ProductItem>> = localDataSource.getSavedProducts()

    fun getProducts(): Flow<List<ProductItem>> =
        savedProducts.transform { savedProducts ->
            val remoteProducts = remoteDataSource.getProducts()
            emit(remoteProducts.map { product ->
                savedProducts.find { it.propertyCode == product.propertyCode } ?: product
            })
        }

    suspend fun getProductDetail(propertyCode: String): ProductDetail =
        remoteDataSource.getProductDetail(propertyCode)

    suspend fun saveProduct(product: ProductItem) = localDataSource.saveProduct(product)

    suspend fun deleteProduct(propertyCode: String) = localDataSource.deleteProduct(propertyCode)
}
