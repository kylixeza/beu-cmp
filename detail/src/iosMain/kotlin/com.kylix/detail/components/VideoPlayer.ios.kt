package com.kylix.detail.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.currentItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.CoreGraphics.CGRect
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSURL
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCATransactionDisableActions
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String,
    onVideoFinished: () -> Unit
) {

    val player = remember { AVPlayer(uRL = NSURL.URLWithString(url)!!) }
    val playerLayer = remember { AVPlayerLayer() }
    val avPlayerViewController = remember { AVPlayerViewController() }

    avPlayerViewController.player = player
    avPlayerViewController.showsPlaybackControls = true

    playerLayer.player = player

    NSNotificationCenter.defaultCenter.addObserverForName(
        name = AVPlayerItemDidPlayToEndTimeNotification,
        `object` = player.currentItem,
        queue = null
    ) { _ ->
        onVideoFinished()
    }

    UIKitView(
        modifier = modifier,
        factory = {
            val playerContainer = UIView()
            avPlayerViewController.setVideoGravity(AVLayerVideoGravityResizeAspectFill)
            playerContainer.addSubview(avPlayerViewController.view)
            playerContainer
        },
        onResize = { view: UIView, rect: CValue<CGRect> ->
            CATransaction.begin()
            CATransaction.setValue(true, kCATransactionDisableActions)
            view.layer.setFrame(rect)
            playerLayer.setFrame(rect)
            avPlayerViewController.view.layer.frame = rect
            CATransaction.commit()
        },
        update = {
            player.play()
            avPlayerViewController.player?.play()
        },
        onRelease = {
            player.pause()
            avPlayerViewController.player?.pause()
            playerLayer.player = null
        }
    )

}