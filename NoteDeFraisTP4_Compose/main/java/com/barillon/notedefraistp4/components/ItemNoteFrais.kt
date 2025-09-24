package com.barillon.notedefraistp4.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

    @Composable
    fun ItemNoteFrais(
        nom: String,
        type: String,
        montant: Double,
        statut: String,
        onClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = nom, style = MaterialTheme.typography.bodyMedium)
            Text(text = type, style = MaterialTheme.typography.bodyMedium)
            Text(text = "${montant}€", style = MaterialTheme.typography.bodyMedium)
            Text(text = statut, style = MaterialTheme.typography.bodySmall)
        }
    }

