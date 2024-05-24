package com.ricky.controle_pet.presentation.form.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ricky.controle_pet.domain.enums.EnumWithValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Enum<T>> DropdownCompose(
    modifier: Modifier = Modifier,
    label: String,
    list: Array<T>,
    onChange: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = label, onValueChange = {},
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