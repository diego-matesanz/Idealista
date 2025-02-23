package com.diego.matesanz.idealista.ui.common.components.propertyType

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.diego.matesanz.idealista.R

@Composable
fun mapPropertyType(propertyType: String): String {
    return when (propertyType) {
        "flat" -> stringResource(R.string.property_type_flat)
        else -> propertyType
    }
}
