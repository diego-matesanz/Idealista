package com.diego.matesanz.idealista.ui.screens.productDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diego.matesanz.idealista.data.Result
import com.diego.matesanz.idealista.data.stateAsResultIn
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.usecases.productDetail.GetProductDetailUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProductDetailViewModel(
    private val propertyCode: String,
    private val getProductDetailUseCase: GetProductDetailUseCase,
) : ViewModel() {

    val state: StateFlow<Result<ProductDetail>> =
        flow { emit(getProductDetailUseCase(propertyCode)) }
            .stateAsResultIn(viewModelScope)
}
