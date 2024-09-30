package beukmm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_arrow_back_black
import beukmm.theme.Black
import beukmm.theme.White
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SecondaryAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    titleColor: Color = Black,
    leftIcon: DrawableResource? = Res.drawable.ic_arrow_back_black,
    leftIconTint: Color = Black,
    background: Color = White,
    onLeftIconClick: () -> Unit = {  },
    content: @Composable () -> Unit = { }
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(background)
            .padding(vertical = 16.dp, horizontal = 24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            if (leftIcon != null) {
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
                Spacer(modifier = Modifier.width(16.dp))
            }
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = titleColor
                ),
                textAlign = TextAlign.Start
            )
        }

        content()
    }
}