package beukmm.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary500
import beukmm.util.customKamelConfig
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.CloseCircle
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig

@Composable
fun NetworkFlexboxImages(
    images: List<String>
) {
    val density = LocalDensity.current

    var rowWidthDp by remember { mutableStateOf(0.dp) }
    val itemSpacing by remember { mutableStateOf(8.dp) }

    var selectedImage by remember { mutableStateOf("") }
    var showImagePreview by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                rowWidthDp = with(density) { coordinates.size.width.toDp() }
            },
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        val itemSize = (rowWidthDp - (itemSpacing * 2)) / 3
        images.forEach {
            Card(
                modifier = Modifier
                    .size(itemSize)
                    .aspectRatio(1f)
                    .clickable {
                        selectedImage = it
                        showImagePreview = true
                    },
                shape = RoundedCornerShape(8.dp)
            ) {
                CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                    KamelImage(
                        asyncPainterResource(it),
                        contentDescription = "Review image",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }

    if (showImagePreview) {
        ImagePreviewDialogue(
            image = selectedImage,
            onDismissRequest = { showImagePreview = false }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocalFlexboxImages(
    images: List<ImageBitmap>,
    onDeleteImage: (ImageBitmap) -> Unit = {},
) {
    val density = LocalDensity.current

    var rowWidthDp by remember { mutableStateOf(0.dp) }
    val itemSpacing by remember { mutableStateOf(8.dp) }

    var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var showImagePreview by remember { mutableStateOf(false) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                rowWidthDp = with(density) { coordinates.size.width.toDp() }
            },
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        val itemSize = (rowWidthDp - (itemSpacing * 2)) / 3

        items(
            images,
            key = { images.indexOf(it) },
        ) {
            Card(
                modifier = Modifier
                    .size(itemSize)
                    .aspectRatio(1f)
                    .animateItemPlacement()
                    .clickable {
                        selectedImage = it
                        showImagePreview = true
                    },
                shape = RoundedCornerShape(8.dp)
            ) {
                Box (
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        bitmap = it,
                        contentDescription = "Review image",
                        contentScale = ContentScale.Crop
                    )

                    Icon(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .clickable { onDeleteImage(it) },
                        imageVector = EvaIcons.Fill.CloseCircle,
                        contentDescription = null,
                        tint = Primary500,
                    )
                }
            }
        }
    }

    if (showImagePreview) {
        ImagePreviewDialogue(
            image = selectedImage!!,
            onDismissRequest = { showImagePreview = false }
        )
    }
}