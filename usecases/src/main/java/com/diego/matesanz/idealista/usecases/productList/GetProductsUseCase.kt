package com.diego.matesanz.idealista.usecases.productList

import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.domain.models.ProductItem
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetProductsUseCase(private val productRepository: ProductRepository) {
    operator fun invoke(): Flow<List<ProductItem>> = productRepository.getProducts()
}
