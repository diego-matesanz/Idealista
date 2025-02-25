package com.diego.matesanz.idealista.ui.screens.productDetail

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.diego.matesanz.idealista.R
import com.diego.matesanz.idealista.data.Result
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.ui.common.components.buttons.SecondaryButton
import com.diego.matesanz.idealista.ui.common.components.multimediaPager.MultimediaPager
import com.diego.matesanz.idealista.ui.common.components.propertyType.mapPropertyType
import com.diego.matesanz.idealista.ui.common.utils.formatIntegerWithDots
import com.diego.matesanz.idealista.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsState()

    when (state.value) {
        is Result.Loading -> {
            /* TODO Add loader */
        }

        is Result.Error -> {
            /* TODO Add error */
        }

        is Result.Success -> {
            val product = (state.value as Result.Success).data
            Screen {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = product.propertyComment,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            },
                            colors = TopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                scrolledContainerColor = MaterialTheme.colorScheme.background,
                                navigationIconContentColor = MaterialTheme.colorScheme.secondary,
                                titleContentColor = MaterialTheme.colorScheme.onBackground,
                                actionIconContentColor = MaterialTheme.colorScheme.secondary,
                            ),
                            windowInsets = WindowInsets(
                                top = 0.dp,
                                bottom = 0.dp,
                            ),
                        )
                    },
                    modifier = modifier,
                ) { padding ->
                    Box(
                        modifier = Modifier.padding(padding)
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .background(color = MaterialTheme.colorScheme.surface),
                            contentPadding = PaddingValues(bottom = 72.dp),
                        ) {
                            item {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier
                                        .background(color = MaterialTheme.colorScheme.background)
                                        .padding(bottom = 16.dp),
                                ) {
                                    MultimediaPager(
                                        multimedia = product.multimedia,
                                        isExpandedSize = true,
                                        imageShape = RectangleShape
                                    )
                                    PropertyTitle(title = product.propertyComment)
                                    PricingAndSize(product = product)
                                    ActionButtonsRow()
                                    AddNoteButton()
                                    PublisherComment(product = product)
                                }
                            }
                            item {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(color = MaterialTheme.colorScheme.background)
                                        .padding(16.dp),
                                ) {
                                    BasicCharacteristics(product = product)
                                    EnergyCertification(product = product)
                                }
                            }
                        }
                        ContactButtons(modifier = Modifier.align(Alignment.BottomCenter))
                    }
                }
            }
        }
    }
}

@Composable
private fun PropertyTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
private fun PricingAndSize(
    product: ProductDetail,
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
                    append(product.priceInfo.amount.toInt().formatIntegerWithDots())
                }
                withStyle(SpanStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)) {
                    append(product.priceInfo.currencySuffix)
                }
            },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = stringResource(
                R.string.size,
                product.moreCharacteristics.constructedArea.toInt().formatIntegerWithDots()
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun ActionButtonsRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ActionButton(
            imageVector = Icons.Default.FavoriteBorder,
            text = stringResource(R.string.save_button),
        )
        ActionButton(
            imageVector = Icons.Outlined.Delete,
            text = stringResource(R.string.remove_button),
        )
        ActionButton(
            imageVector = Icons.Outlined.Share,
            text = stringResource(R.string.share_button),
        )
    }
}

@Composable
private fun ActionButton(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable(
            onClick = { },
            role = Role.Button,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = text,
            tint = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
private fun AddNoteButton(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .clickable(
                onClick = { },
                role = Role.Button,
            )
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiary)
            .padding(16.dp),
        text = stringResource(R.string.add_note_button),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onTertiary,
    )
}

@Composable
private fun PublisherComment(
    product: ProductDetail,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(R.string.publisher_comment),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Min),
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    ) {
                        append(stringResource(R.string.see_comment))
                        append(" ")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        )
                    ) {
                        append(mapCountry(product.country))
                    }
                },
                style = MaterialTheme.typography.titleMedium,
            )
            VerticalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            )
            Text(
                text = stringResource(R.string.translations_button),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
        Text(
            text = product.propertyComment,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = if (isExpanded) Int.MAX_VALUE else 5,
            overflow = TextOverflow.Clip,
            modifier = Modifier.animateContentSize(),
        )
        Text(
            text = stringResource(R.string.see_whole_comment),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable(
                onClick = { isExpanded = !isExpanded },
                role = Role.Button,
            ),
        )
    }
}

private fun mapCountry(countryCode: String): String = when (countryCode) {
    "es" -> "Español (Original)"
    else -> countryCode
}

@Composable
private fun BasicCharacteristics(
    product: ProductDetail,
    modifier: Modifier = Modifier,
) {
    val characteristics = listOf(
        mapPropertyType(product.homeType),
        stringResource(R.string.floor_number, product.moreCharacteristics.floor),
        stringResource(R.string.constructed_area, product.moreCharacteristics.constructedArea),
        stringResource(R.string.number_of_rooms, product.moreCharacteristics.roomNumber),
        stringResource(R.string.number_of_bathrooms, product.moreCharacteristics.bathNumber),
    )
    CharacteristicsList(
        characteristics = characteristics,
        title = stringResource(R.string.basic_characteristics),
        modifier = modifier,
    )
}

@Composable
private fun EnergyCertification(
    product: ProductDetail,
    modifier: Modifier = Modifier,
) {
    val characteristics = listOf(
        product.energyCertification.energyConsumption.type.uppercase(),
    )
    CharacteristicsList(
        characteristics = characteristics,
        title = product.energyCertification.title,
        modifier = modifier,
    )
}

@Composable
private fun CharacteristicsList(
    characteristics: List<String>,
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onBackground,
        )
        characteristics.forEach {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.titleMedium.fontSize)) {
                        append(" • ")
                    }
                    withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)) {
                        append(it)
                    }
                },
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun ContactButtons(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        ) {
            SecondaryButton(
                text = stringResource(R.string.chat_button),
                imageVector = Icons.AutoMirrored.Filled.Chat,
                onClick = {},
                modifier = Modifier.weight(1f),
            )
            SecondaryButton(
                text = stringResource(R.string.call_button),
                imageVector = Icons.Filled.Phone,
                onClick = {},
                modifier = Modifier.weight(1f),
            )
        }
    }
}
