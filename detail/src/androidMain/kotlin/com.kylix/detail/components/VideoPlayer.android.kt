package com.kylix.detail.components

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.AspectRatioFrameLayout.ResizeMode
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String,
    onVideoFinished: () -> Unit
) {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
        }
    }

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_ENDED) {
                    onVideoFinished()
                }
            }
        }
        player.addListener(listener)

        onDispose {
            player.removeListener(listener)
            player.release()
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
                this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            }
        }
    )
}