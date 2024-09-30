package com.kylix.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary500
import com.kylix.profile.model.Setting
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemSetting(
    modifier: Modifier = Modifier,
    setting: Setting,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(setting.icon),
            contentDescription = setting.name,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = setting.name,
            modifier = Modifier.padding(horizontal = 16.dp).weight(1f)
        )
        Icon(
            FeatherIcons.ArrowRight,
            null,
            tint = Primary500
        )
    }

}