package beukmm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary700
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_arrow_back_white
import beukmm.common.generated.resources.ic_unfavorite_white
import beukmm.theme.White
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BaseAppBar(
    title: String = "",
    leftIcon: DrawableResource = Res.drawable.ic_arrow_back_white,
    leftIconTint: Color = LocalContentColor.current. copy(alpha = LocalContentAlpha. current),
    rightIcon: DrawableResource? = Res.drawable.ic_unfavorite_white,
    rightIconTint: Color = LocalContentColor.current. copy(alpha = LocalContentAlpha. current),
    onLeftIconClick: () -> Unit = { },
    onRightIconClick: () -> Unit = { },
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(Primary700)
            .padding(vertical = 16.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = { onLeftIconClick.invoke() },
        ) {
            Icon(
                painterResource(leftIcon),
                null,
                tint = leftIconTint
            )
        }
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h5.copy(
                color = White
            ),
            textAlign = TextAlign.Center
        )

        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = { if (rightIcon != null) onRightIconClick() },
        ) {
            if (rightIcon != null) {
                Icon(
                    painterResource(rightIcon),
                    null,
                    tint = rightIconTint
                )
            }
        }
    }
}