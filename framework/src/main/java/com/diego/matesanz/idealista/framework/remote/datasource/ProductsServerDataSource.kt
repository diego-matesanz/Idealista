package com.diego.matesanz.idealista.framework.remote.datasource

import com.diego.matesanz.idealista.data.datasource.ProductRemoteDataSource
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.framework.remote.ProductsService
import com.diego.matesanz.idealista.framework.remote.mappers.toDomainModel

class ProductsServerDataSource(
    private val productsService: ProductsService,
) : ProductRemoteDataSource {

    override suspend fun getProducts(): List<ProductItem> =
        productsService.getProducts().map { it.toDomainModel() }

    override suspend fun getProductDetail(propertyCode: String): ProductDetail =
        productsService.getProductDetail().toDomainModel()
}
