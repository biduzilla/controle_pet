package com.ricky.controle_pet.presentation.pets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.controle_pet.R
import com.ricky.controle_pet.domain.enums.SexoEnum
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.utils.byteArrayToBitmap
import com.ricky.controle_pet.utils.calculateAgeAndMonths
import com.ricky.controle_pet.utils.formatterLocalDate
import java.time.LocalDate

@Composable
fun PetInfoItem(
    modifier: Modifier = Modifier,
    animal: Animal,
    onClick: (Animal) -> Unit
) {
    val foto = animal.foto?.let {
        val bitmap = byteArrayToBitmap(it)
        return@let BitmapPainter(bitmap.asImageBitmap())
    } ?: run {
        painterResource(id = R.drawable.blue_dog)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(animal) },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart,
                    painter = foto, contentDescription = animal.nome
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = animal.nome,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = buildString {
                            append(animal.sexo.value)
                            append(" | ")
                            append(animal.especie.value)
                        },
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Icon(
                            imageVector = Icons.Default.Pets, contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Red
                        )
                        Text(
                            text = animal.raca,
                            modifier = Modifier
                                .padding(
                                    start = 8.dp,
                                    end = 12.dp
                                ),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                SexoTag(sexo = animal.sexo)
                Text(
                    text = calculateAgeAndMonths(animal.nascimento),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun SexoTag(sexo: SexoEnum, modifier: Modifier = Modifier) {
    val color = if (sexo == SexoEnum.MACHO) Color.Blue else Color.Red

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(color = color.copy(alpha = .2f))
    ) {
        Text(
            text = sexo.value,
            modifier = Modifier.padding(
                start = 12.dp,
                top = 4.dp,
                end = 12.dp,
                bottom = 6.dp
            ),
            color = color,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Preview
@Composable
private fun PetInfoItemPrev() {

    PetInfoItem(
        animal = Animal(
            raca = "raca",
            nome = "nome",
            nascimento = LocalDate.now()
        )
    ) {

    }
}