package com.kylix.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import beukmm.theme.Primary500
import beukmm.theme.White
import compose.icons.FeatherIcons
import compose.icons.feathericons.Search

@Composable
fun HomeAppbar(
    modifier: Modifier = Modifier,
    greeting: String = "",
    onSearchClick: () -> Unit = { }
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .background(Primary500)
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 12.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            greeting,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold,
                color = White
            )
        )
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onSearchClick
        ) {
            Icon(
                FeatherIcons.Search, null,
                tint = White
            )
        }
    }
}