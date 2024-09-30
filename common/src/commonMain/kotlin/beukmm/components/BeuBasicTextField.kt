package beukmm.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import beukmm.theme.Neutral300
import beukmm.theme.Primary500
import beukmm.theme.White

@Composable
fun BeuBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    minSize: Dp = Dp.Unspecified,
    maxSize: Dp = Dp.Unspecified,
    label: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Color.Black),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField -> innerTextField() }
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, if (value.isNotBlank()) Primary500 else Neutral300, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(White)
            .padding(16.dp)
            .heightIn(min = minSize, max = maxSize),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        onTextLayout = onTextLayout,
        interactionSource = interactionSource,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                if (leadingIcon != null) {
                    Box(
                        modifier.weight(1f)
                    ) { leadingIcon.invoke() }
                }

                Box(
                    modifier.weight(8f)
                ) { innerTextField() }

                if (trailingIcon != null) {
                    Box(
                        modifier.weight(1f)
                    ) { trailingIcon.invoke() }
                }
            }
            Row(
                verticalAlignment = Alignment.Top,
            ) {
                if (leadingIcon != null) {
                    Box(
                        modifier.weight(1f)
                    ) { leadingIcon.invoke() }
                }

                Box(
                    modifier.weight(8f)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = label,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.LightGray
                            ),
                        )
                    }
                }

                if (trailingIcon != null) {
                    Box(
                        modifier.weight(1f)
                    ) { trailingIcon.invoke() }
                }
            }
        }
    )
}