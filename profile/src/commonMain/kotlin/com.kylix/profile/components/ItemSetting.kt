package com.kylix.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.profile.generated.resources.Res
import beukmm.profile.generated.resources.ic_arrow_forward_ios
import beukmm.theme.Primary500
import com.kylix.profile.model.Setting
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemSetting(
    modifier: Modifier = Modifier,
    setting: Setting,
) {

    Row(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 6.dp),
    ) {
        Image(
            painter = painterResource(setting.icon),
            contentDescription = setting.name
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