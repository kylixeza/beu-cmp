package beukmm.util

import beukmm.theme.Error50
import beukmm.theme.Error900
import beukmm.theme.Primary50
import beukmm.theme.Primary900
import beukmm.theme.Success50
import beukmm.theme.Success900

fun String.cardDifficultyColor() = when (this) {
    "Easy" -> Success50
    "Medium" -> Primary50
    "Hard" -> Error50
    else -> Primary50
}

fun String.textDifficultyColor() = when (this) {
    "Easy" -> Success900
    "Medium" -> Primary900
    "Hard" -> Error900
    else -> Primary900
}
