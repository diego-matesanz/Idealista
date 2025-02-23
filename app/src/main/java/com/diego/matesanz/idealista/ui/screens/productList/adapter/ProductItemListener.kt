package com.diego.matesanz.idealista.ui.screens.productList.adapter

import com.diego.matesanz.idealista.domain.models.ProductItem

interface ProductItemListener {
    fun onProductClick(propertyCode: String)
    fun onFavoriteClick(product: ProductItem)
}
