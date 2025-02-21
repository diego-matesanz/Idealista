package com.diego.matesanz.idealista.ui.common.components.multimediaPager

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddLocation
import androidx.compose.material.icons.outlined.EmergencyRecording
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.diego.matesanz.idealista.R
import com.diego.matesanz.idealista.domain.models.ProductDetail
import com.diego.matesanz.idealista.domain.models.ProductItem
import com.diego.matesanz.idealista.ui.theme.Black
import com.diego.matesanz.idealista.ui.theme.White

@Composable
fun MultimediaPager(
    multimedia: ProductItem.Multimedia,
    modifier: Modifier = Modifier,
    isExpandedSize: Boolean = false,
    imageShape: Shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
) {
    val multimediaPagerState = rememberMultimediaPagerState(
        multimedia = multimedia,
        isExpandedSize = isExpandedSize,
    )
    MultimediaPager(
        multimediaPagerState = multimediaPagerState,
        imageShape = imageShape,
        modifier = modifier,
    )
}

@Composable
fun MultimediaPager(
    multimedia: ProductDetail.Multimedia,
    modifier: Modifier = Modifier,
    isExpandedSize: Boolean = false,
    imageShape: Shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
) {
    val multimediaPagerState = rememberMultimediaPagerState(
        multimedia = multimedia,
        isExpandedSize = isExpandedSize,
    )
    MultimediaPager(
        multimediaPagerState = multimediaPagerState,
        imageShape = imageShape,
        modifier = modifier,
    )
}

@Composable
private fun MultimediaPager(
    multimediaPagerState: MultimediaPagerState,
    imageShape: Shape,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        ImagesPager(multimediaPagerState = multimediaPagerState, imageShape = imageShape)
        MultimediaIconsRow(multimediaPagerState = multimediaPagerState)
    }
}

@Composable
private fun ImagesPager(
    multimediaPagerState: MultimediaPagerState,
    imageShape: Shape,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.5F / 1)
            .clip(shape = imageShape),
    ) {
        HorizontalPager(
            state = multimediaPagerState.pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            AsyncImage(
                model = multimediaPagerState.getPageImageModel(page),
                contentDescription = multimediaPagerState.getPageImageDescription(page),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
        Text(
            text = multimediaPagerState.getPositionText(),
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
private fun MultimediaIconsRow(
    multimediaPagerState: MultimediaPagerState,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        if (multimediaPagerState.hasImages()) {
            MultimediaItemIcon(
                imageVector = Icons.Outlined.Image,
                contentDescription = "Images",
                text = if (multimediaPagerState.isExpandedSize) stringResource(
                    R.string.number_of_images,
                    multimediaPagerState.multimedia.images.size
                ) else null,
            )
        }
        if (multimediaPagerState.hasVideo()) {
            MultimediaItemIcon(
                imageVector = Icons.Outlined.EmergencyRecording,
                contentDescription = "Video",
                text = if (multimediaPagerState.isExpandedSize) stringResource(
                    R.string.number_of_videos,
                    multimediaPagerState.multimedia.images.size // videos size
                ) else null,
            )
        }
        if (multimediaPagerState.hasMap()) {
            MultimediaItemIcon(
                imageVector = Icons.Outlined.AddLocation,
                contentDescription = "Map",
                text = if (multimediaPagerState.isExpandedSize) stringResource(R.string.map) else null,
            )
        }
    }
}

@Composable
private fun MultimediaItemIcon(
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    text: String? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable(
                onClick = { },
                role = Role.Button,
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(2.dp),
            )
            .padding(8.dp),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
        text?.let {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
