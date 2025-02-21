package com.diego.matesanz.idealista.ui.common.components.multimediaPager

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.domain.models.ProductItem.Multimedia.Image

class MultimediaPagerState(
    val multimedia: MultimediaPagerModel,
    val isExpandedSize: Boolean,
    val pagerState: PagerState,
) {

    fun getPageImageModel(page: Int): String =
        multimedia.images[page % multimedia.images.size].url

    fun getPageImageDescription(page: Int): String =
        multimedia.images[page % multimedia.images.size].let { it.localizedName ?: it.tag }

    fun getPositionText(): String =
        "${(pagerState.currentPage % multimedia.images.size) + 1}/${multimedia.images.size}"

    fun hasImages(): Boolean = multimedia.images.isNotEmpty()

    fun hasVideo(): Boolean = false // multimedia.videos.isNotEmpty()

    fun hasMap(): Boolean = true // multimedia.ubication != null
}

data class MultimediaPagerModel(
    val images: List<Image>,
) {
    data class Image(
        val url: String,
        val tag: String,
        val localizedName: String? = null,
        val multimediaId: Int? = null,
    )
}

private fun ProductItem.Multimedia.toPagerModel(): MultimediaPagerModel =
    MultimediaPagerModel(
        images = images.map { it.toPagerModel() }
    )

private fun Image.toPagerModel(): MultimediaPagerModel.Image =
    MultimediaPagerModel.Image(
        url = url,
        tag = tag,
    )

private fun ProductDetail.Multimedia.toPagerModel(): MultimediaPagerModel =
    MultimediaPagerModel(
        images = images.map { it.toPagerModel() }
    )

private fun ProductDetail.Multimedia.Image.toPagerModel(): MultimediaPagerModel.Image =
    MultimediaPagerModel.Image(
        url = url,
        tag = tag,
        localizedName = localizedName,
        multimediaId = multimediaId
    )

@Composable
fun rememberMultimediaPagerState(
    multimedia: ProductItem.Multimedia,
    isExpandedSize: Boolean,
): MultimediaPagerState {
    return rememberMultimediaPagerState(
        multimedia = multimedia.toPagerModel(),
        isExpandedSize = isExpandedSize,
    )
}

@Composable
fun rememberMultimediaPagerState(
    multimedia: ProductDetail.Multimedia,
    isExpandedSize: Boolean,
): MultimediaPagerState {
    return rememberMultimediaPagerState(
        multimedia = multimedia.toPagerModel(),
        isExpandedSize = isExpandedSize,
    )
}

@Composable
private fun rememberMultimediaPagerState(
    multimedia: MultimediaPagerModel,
    isExpandedSize: Boolean,
) : MultimediaPagerState {
    val pageCount = Int.MAX_VALUE
    val offset = pageCount / 2
    val initialPage = offset - (offset % multimedia.images.size)
    val pagerState = rememberPagerState(initialPage = initialPage) { pageCount }

    return remember {
        MultimediaPagerState(
            multimedia = multimedia,
            isExpandedSize = isExpandedSize,
            pagerState = pagerState,
        )
    }
}
