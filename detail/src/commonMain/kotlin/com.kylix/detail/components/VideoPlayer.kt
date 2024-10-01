package com.kylix.detail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Deprecated(
    message = "Use VideoPlayerView instead",
    replaceWith = ReplaceWith("VideoPlayerView"),
    level = DeprecationLevel.WARNING
)
@Composable
expect fun VideoPlayer(
    modifier: Modifier,
    url: String,
    onVideoFinished: () -> Unit
)