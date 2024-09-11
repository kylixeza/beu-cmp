package beukmm.base

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.stack.StackEvent
import cafe.adriel.voyager.transitions.ScreenTransition

private val EnterScales = 1.1f to 0.95f
private val ExitScales = EnterScales.second to EnterScales.first

@OptIn(ExperimentalVoyagerApi::class)
class ScaleTransition : ScreenTransition {

    override fun enter(lastEvent: StackEvent): EnterTransition {
        val (initialScale, targetScale) = when (lastEvent) {
            StackEvent.Pop -> ExitScales
            else -> EnterScales
        }
        return scaleIn(
            initialScale = initialScale,
        )
    }

    override fun exit(lastEvent: StackEvent): ExitTransition {
        val (initialScale, targetScale) = when (lastEvent) {
            StackEvent.Pop -> ExitScales
            else -> EnterScales
        }
        return scaleOut(
            targetScale = targetScale
        )
    }
}