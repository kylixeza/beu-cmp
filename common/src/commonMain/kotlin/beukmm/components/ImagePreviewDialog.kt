package beukmm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import beukmm.theme.White
import beukmm.util.customKamelConfig
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.CloseCircle
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig

@Composable
fun ImagePreviewDialogue(
    modifier: Modifier = Modifier,
    image: String = "",
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.85f)
                .padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center
        ) {

            CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                KamelImage(
                    asyncPainterResource(image),
                    contentDescription = "Review image",
                    contentScale = ContentScale.Fit,
                    modifier = modifier.fillMaxSize(
                        fraction = 0.9f
                    ).padding(12.dp)
                )
            }

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClick = { onDismissRequest() }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = EvaIcons.Fill.CloseCircle,
                    contentDescription = null,
                    tint = White,
                )
            }
        }

    }
}

@Composable
fun ImagePreviewDialogue(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.85f),
            contentAlignment = Alignment.Center
        ) {

            Image(
                modifier = modifier.fillMaxSize(fraction = 0.95f).padding(12.dp),
                bitmap = image,
                contentDescription = "Review image",
                contentScale = ContentScale.Fit
            )

            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClick = { onDismissRequest() }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = EvaIcons.Fill.CloseCircle,
                    contentDescription = null,
                    tint = White,
                )
            }
        }
    }
}