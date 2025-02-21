package com.diego.matesanz.idealista.ui.screens.productList.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddLocation
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.EmergencyRecording
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.diego.matesanz.idealista.R
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.domain.models.ProductItem.Multimedia.Image
import com.diego.matesanz.idealista.ui.common.utils.formatIntegerWithDots
import com.diego.matesanz.idealista.ui.screens.productList.mocks.productMock
import com.diego.matesanz.idealista.ui.theme.Black
import com.diego.matesanz.idealista.ui.theme.IdealistaTheme
import com.diego.matesanz.idealista.ui.theme.White

@Composable
fun ProductItem(
    product: ProductItem,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(4.dp))
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
private fun MultimediaPager(multimedia: ProductItem.Multimedia) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ImagesPager(images = multimedia.images)
        MultimediaIconsRow(multimedia = multimedia)
    }
}

@Composable
private fun ImagesPager(images: List<Image>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5F / 1)
            .clip(shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    ) {
        val pageCount = Int.MAX_VALUE
        val offset = pageCount / 2
        val initialPage = offset - (offset % images.size)
        val pagerState = rememberPagerState(initialPage = initialPage) { pageCount }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            AsyncImage(
                model = images[page % images.size].url,
                contentDescription = images[page % images.size].tag,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        Text(
            text = "${(pagerState.currentPage % images.size) + 1}/${images.size}",
            style = MaterialTheme.typography.bodyMedium,
            color = White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .background(
                    color = Black.copy(alpha = 0.5F),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@Composable
private fun MultimediaIconsRow(multimedia: ProductItem.Multimedia) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        if (multimedia.hasImages()) {
            MultimediaItemIcon(
                imageVector = Icons.Outlined.Image,
                contentDescription = "Images",
            )
        }
        if (multimedia.hasVideo()) {
            MultimediaItemIcon(
                imageVector = Icons.Outlined.EmergencyRecording,
                contentDescription = "Video",
            )
        }
        if (multimedia.hasMap()) {
            MultimediaItemIcon(
                imageVector = Icons.Outlined.AddLocation,
                contentDescription = "Map",
            )
        }
    }
}

@Composable
private fun MultimediaItemIcon(
    imageVector: ImageVector,
    contentDescription: String,
) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Black,
                shape = RoundedCornerShape(2.dp),
            ),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}

@Composable
private fun PropertyTypeAndAddress(product: ProductItem) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
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
private fun mapPropertyType(propertyType: String): String {
    return when (propertyType) {
        "flat" -> stringResource(R.string.property_type_flat)
        else -> propertyType
    }
}

@Composable
private fun PricingAndSize(product: ProductItem) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize)) {
                    append(product.priceInfo.price.amount.toInt().formatIntegerWithDots())
                }
                withStyle(SpanStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)) {
                    append(product.priceInfo.price.currencySuffix)
                }
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Black,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.number_of_rooms, product.rooms),
                style = MaterialTheme.typography.bodyMedium,
                color = Black,
            )
            Text(
                text = stringResource(
                    R.string.size,
                    product.size.toInt().formatIntegerWithDots()
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = Black,
            )
        }
    }
}

@Composable
private fun ActionButtons() {
    Row(
        modifier = Modifier
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
                imageVector = Icons.Outlined.Favorite,
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
