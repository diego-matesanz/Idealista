package com.diego.matesanz.idealista.usecases

import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.domain.models.ProductItem

class ToggleProductFavoriteUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productItem: ProductItem) {
        if (productItem.isSaved) {
            productRepository.deleteProduct(productItem.propertyCode)
        } else {
            productRepository.saveProduct(productItem.copy(isSaved = true))
        }
    }
}
