package com.diego.matesanz.idealista.data.datasource

import com.diego.matesanz.idealista.domain.models.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {
    fun getSavedProducts(): Flow<List<ProductItem>>
    suspend fun saveProduct(product: ProductItem)
    suspend fun deleteProduct(propertyCode: String)
}
