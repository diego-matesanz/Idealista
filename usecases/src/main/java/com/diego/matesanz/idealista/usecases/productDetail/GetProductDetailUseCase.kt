package com.diego.matesanz.idealista.usecases.productDetail

import com.diego.matesanz.idealista.data.repositories.ProductRepository
import com.diego.matesanz.idealista.domain.models.ProductDetail
import org.koin.core.annotation.Factory

@Factory
class GetProductDetailUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke(propertyCode: String): ProductDetail =
        productRepository.getProductDetail(propertyCode)
}
