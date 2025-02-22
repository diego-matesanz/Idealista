package com.diego.matesanz.idealista.ui.screens.productList.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diego.matesanz.idealista.R
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.ui.common.components.multimediaPager.MultimediaPager
import com.diego.matesanz.idealista.ui.common.components.propertyType.mapPropertyType
import com.diego.matesanz.idealista.ui.common.utils.formatIntegerWithDots
import com.diego.matesanz.idealista.ui.screens.productList.mocks.productMock
import com.diego.matesanz.idealista.ui.theme.Black
import com.diego.matesanz.idealista.ui.theme.IdealistaTheme

@Composable
fun ProductItem(
    product: ProductItem,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = Black.copy(alpha = 0.1f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick(product.propertyCode) },
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        MultimediaPager(multimedia = product.multimedia)
        PropertyTypeAndAddress(product = product)
        PricingAndSize(product = product)
        ActionButtons()
    }
}

@Composable
private fun PropertyTypeAndAddress(
    product: ProductItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = if (product.address.isNotBlank()) {
                stringResource(
                    R.string.property_type_and_address,
                    mapPropertyType(product.propertyType),
                    product.address,
                )
            } else {
                product.propertyType
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = stringResource(
                R.string.municipality_and_province,
                product.municipality,
                product.province,
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Light),
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun PricingAndSize(
    product: ProductItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.SemiBold,
                    )
                ) {
                    append(product.priceInfo.price.amount.toInt().formatIntegerWithDots())
                }
                withStyle(SpanStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)) {
                    append(product.priceInfo.price.currencySuffix)
                }
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.number_of_rooms_compact, product.rooms),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = stringResource(
                    R.string.size,
                    product.size.toInt().formatIntegerWithDots()
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun ActionButtons(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChatBubbleOutline,
                    contentDescription = "Contact",
                    tint = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = stringResource(R.string.contact_button),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Outlined.Call,
                    contentDescription = "Call",
                    tint = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = stringResource(R.string.call_button),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.secondary,
            )
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
@Preview
fun ProductItemPreview() {
    IdealistaTheme {
        ProductItem(
            product = productMock,
            onClick = {},
        )
    }
}
