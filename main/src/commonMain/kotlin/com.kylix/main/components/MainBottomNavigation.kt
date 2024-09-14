package com.kylix.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary500
import beukmm.theme.Primary700
import beukmm.theme.White
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Home
import compose.icons.feathericons.User

@Composable
fun RowScope.TabNavigationItem(
    tab: Tab,
) {

    val tabNavigator = LocalTabNavigator.current

    BottomNavItem(
        modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        icon = tab.options.icon!!,
        label = tab.options.title,
        isSelected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab }
    )
}

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val sizeDp by animateFloatAsState(
        targetValue = if (isSelected) 48f else 42f,
        animationSpec = tween(durationMillis = 300)
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Primary700.copy(alpha = 0.1f) else Color.Transparent,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = modifier
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            modifier = Modifier
                .size(sizeDp.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                ),
            onClick = onClick,
        ) {
            Icon(
                painter = icon,
                contentDescription = label,
                tint = if (isSelected) Primary700 else Color.LightGray,
            )
        }

        AnimatedVisibility(
            visible = isSelected
        ) {
            Text(
                text = label,
                color = Primary700,
                style = MaterialTheme.typography.body2
            )
        }
    }
}
