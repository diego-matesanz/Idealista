package com.diego.matesanz.idealista.usecases

import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.domain.models.ProductItem

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(): List<ProductItem> = productRepository.getProducts()
}
