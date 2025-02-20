package com.diego.matesanz.idealista.framework.remote.datasource

import com.diego.matesanz.idealista.data.datasource.ProductRemoteDataSource
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.framework.remote.ProductsService
import com.diego.matesanz.idealista.framework.remote.mappers.toDomainModel

class ProductsServerDataSource(
    private val productsService: ProductsService,
) : ProductRemoteDataSource {

    override suspend fun getProducts(): List<ProductItem> =
        productsService.getProducts().map { it.toDomainModel() }
}
