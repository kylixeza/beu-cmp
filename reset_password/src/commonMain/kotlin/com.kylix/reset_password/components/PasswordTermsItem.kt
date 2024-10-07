package com.kylix.reset_password.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.reset_password.generated.resources.Res
import beukmm.reset_password.generated.resources.ic_password_term_fail
import beukmm.reset_password.generated.resources.ic_password_term_pass
import org.jetbrains.compose.resources.painterResource

@Composable
fun PasswordConstraintItem(
    modifier: Modifier = Modifier,
    isCorrect: Boolean = false,
    terms: String = ""
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(if (isCorrect) Res.drawable.ic_password_term_pass else Res.drawable.ic_password_term_fail),
            modifier = Modifier.size(24.dp),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = terms,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}