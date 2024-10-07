package com.bassem.catdemo.ui.compose.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bassem.catdemo.utils.getListOfTemperament

@Composable
fun ChipCompose(temperament: String) {
    val temperamentList = temperament.getListOfTemperament()
    temperamentList.forEach {
        FilterChip(
            selected = false,
            onClick = { },
            label = { Text(text = it) },
            modifier = Modifier.padding(vertical = 4.dp)
        )

    }
}