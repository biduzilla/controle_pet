package com.ricky.controle_pet.presentation.form.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ricky.controle_pet.R
import com.ricky.controle_pet.ui.theme.ErrorLight

@Composable
fun TextFieldCompose(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    @StringRes label: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    icon: ImageVector? = null,
    ime: ImeAction = ImeAction.Next,
    onChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onChange(it) },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = ime
        ),
        leadingIcon = if (icon != null) {
            {
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(id = label)
                )
            }
        } else {
            null
        },
        supportingText = {
            if (isError) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.campo_obrigatorio),
                    color = ErrorLight,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldComposePrev() {
    TextFieldCompose(
        value = "value",
        isError = true,
        label = R.string.cadastrar_animal
    ) {

    }
}