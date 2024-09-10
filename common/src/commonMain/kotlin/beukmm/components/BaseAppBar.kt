package beukmm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary700
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_arrow_back_white
import beukmm.common.generated.resources.ic_unfavorite_white
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BaseAppBar(
    title: String = "",
    leftIcon: DrawableResource = Res.drawable.ic_arrow_back_white,
    rightIcon: DrawableResource = Res.drawable.ic_unfavorite_white,
    onLeftIconClick: () -> Unit = { },
    onRightIconClick: () -> Unit = { },
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(Primary700),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Image(
            painterResource(leftIcon),
            null,
            modifier = Modifier.size(24.dp)
                .clickable { onLeftIconClick.invoke() },
        )
        Text(
            text = title,
            modifier = Modifier.weight(1f),
        )
        Image(
            painterResource(rightIcon),
            null,
            modifier = Modifier.size(24.dp)
                .clickable { onRightIconClick.invoke() },
        )
    }
}