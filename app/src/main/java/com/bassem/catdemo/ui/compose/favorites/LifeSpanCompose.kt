package com.bassem.catdemo.ui.compose.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bassem.catdemo.R
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun LifeSpanTextCompose(){
    LifeSpanText(averageLifespan = 5.5)
}

@Composable
fun LifeSpanText(averageLifespan: Double?) {
    if (averageLifespan != null) {
        Text(
            text = stringResource(
                R.string.average_lifespan_of_favorites_years,
                String.format(Locale.getDefault(), "%.1f", averageLifespan)
            ),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = dimensionResource(id = R.dimen.default_padding)
                )
                .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

}