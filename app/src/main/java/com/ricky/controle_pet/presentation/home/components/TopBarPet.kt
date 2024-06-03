package com.ricky.controle_pet.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.utils.byteArrayToBitmap
import com.ricky.controle_pet.utils.calculateAgeAndMonths

@Composable
fun TopBarPet(
    modifier: Modifier = Modifier,
    animal: Animal,
    label: String,
    isDark: Boolean,
    onVoltar: () -> Unit,
    onClick: () -> Unit,
    onChangeTheme: (Boolean) -> Unit
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(onClick = { onVoltar.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineMedium
                        .copy(fontWeight = FontWeight.Bold)
                )

                Switch(
                    checked = isDark,
                    onCheckedChange = { onChangeTheme(it) },
                    thumbContent = {
                        if (isDark) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.LightMode,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 32.dp)
            ) {
                Card(shape = CircleShape,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { onClick.invoke() }) {
                    Image(
                        bitmap = byteArrayToBitmap(animal.foto!!).asImageBitmap(),
                        contentDescription = animal.nome,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = animal.nome,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = calculateAgeAndMonths(animal.nascimento),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

    }
}