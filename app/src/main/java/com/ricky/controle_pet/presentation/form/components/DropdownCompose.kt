package com.ricky.controle_pet.presentation.form.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.controle_pet.R
import com.ricky.controle_pet.domain.enums.EnumWithValue
import com.ricky.controle_pet.domain.enums.SexoEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> DropdownCompose(
    modifier: Modifier = Modifier,
    value: String,
    @StringRes label: Int,
    list: Array<T>,
    onChange: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                value = value, onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                })
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEach { item ->
                    val itemAsEnumWithValue = item as? EnumWithValue
                    val text = itemAsEnumWithValue?.value ?: item.name
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            onChange(item)
                            expanded = false
                        })
                }
            }
        }
    }
}

@Preview
@Composable
private fun DropdownComposePrev() {
    DropdownCompose(
        label = R.string.sexo,
        value = "teste",
        list = SexoEnum.entries.toTypedArray()
    ) {
    }
}