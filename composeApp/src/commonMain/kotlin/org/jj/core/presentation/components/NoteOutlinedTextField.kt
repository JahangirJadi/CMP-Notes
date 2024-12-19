package org.jj.core.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import org.jj.core.presentation.Yellow

@Composable
fun NoteOutlinedTextField(
    modifier: Modifier = Modifier,
    value:String,
    onValueChanged:(String)->Unit,
    hint: String,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = false
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Yellow,
            cursorColor = Yellow
        ),
        modifier = modifier,
        isError = error!=null,
        placeholder = {
            Text(hint)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        singleLine = singleLine

    )

}