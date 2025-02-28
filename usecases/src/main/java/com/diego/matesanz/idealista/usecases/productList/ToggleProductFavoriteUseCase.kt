package com.diego.matesanz.idealista.usecases.productList

import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.domain.models.ProductItem
import org.koin.core.annotation.Factory

@Factory
class ToggleProductFavoriteUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(productItem: ProductItem) {
        if (productItem.isSaved) {
            productRepository.deleteProduct(productItem.propertyCode)
        } else {
            productRepository.saveProduct(productItem.copy(isSaved = true))
        }
    }
}
