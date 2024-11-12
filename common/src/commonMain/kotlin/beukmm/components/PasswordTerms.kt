package beukmm.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_password_term_fail
import beukmm.common.generated.resources.ic_password_term_pass
import beukmm.models.PasswordTermsConstraint
import beukmm.models.PasswordTermsType
import org.jetbrains.compose.resources.painterResource

@Composable
fun PasswordTerms(
    modifier: Modifier = Modifier,
    password: String,
    allFulfilled: (Boolean) -> Unit = {}
) {

    val passwordTerms = mutableStateListOf(
        PasswordTermsConstraint(PasswordTermsType.MINIMUM_8_CHARACTERS, "8 characters minimum"),
        PasswordTermsConstraint(PasswordTermsType.ONE_WORD_ONE_CHARACTER, "At least has 1 letter and 1 number"),
        PasswordTermsConstraint(PasswordTermsType.ONE_SPECIAL_CHARACTER, "At least has 1 special character"),
    )

    LaunchedEffect(password) {
        passwordTerms.forEachIndexed { index, terms ->
            val isFulfilled = when (terms.type) {
                PasswordTermsType.MINIMUM_8_CHARACTERS -> password.length >= 8
                PasswordTermsType.ONE_WORD_ONE_CHARACTER -> password.matches("^(?=.*[A-Za-z])(?=.*\\d).+\$".toRegex())
                PasswordTermsType.ONE_SPECIAL_CHARACTER -> password.matches("^(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+\$".toRegex())
            }
            passwordTerms[index] = terms.copy(isFulfilled = isFulfilled)
        }
        allFulfilled(passwordTerms.all { it.isFulfilled })
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(passwordTerms) { item ->
            PasswordTermsItem(
                isCorrect = item.isFulfilled,
                terms = item.description
            )
        }
    }
}

@Composable
private fun PasswordTermsItem(
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