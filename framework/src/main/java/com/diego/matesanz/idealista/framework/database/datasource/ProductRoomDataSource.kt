package com.diego.matesanz.idealista.framework.database.datasource

import com.diego.matesanz.idealista.data.datasource.ProductLocalDataSource
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.framework.database.dao.ProductItemDao
import com.diego.matesanz.idealista.framework.database.mappers.toDomainModel
import com.diego.matesanz.idealista.framework.database.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRoomDataSource(
    private val productItemDao: ProductItemDao,
): ProductLocalDataSource {

    override fun getSavedProducts(): Flow<List<ProductItem>> =
        productItemDao.getSavedProducts().map { products -> products.map { it.toDomainModel() } }

    override suspend fun saveProduct(product: ProductItem) = productItemDao.saveProduct(product.toEntity())

    override suspend fun deleteProduct(propertyCode: String) = productItemDao.deleteProduct(propertyCode)
}
