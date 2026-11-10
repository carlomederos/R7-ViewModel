package com.carlo_mederos.viewmodel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlo_mederos.viewmodel.R
import com.carlo_mederos.viewmodel.ui.theme.ViewModelTheme

@Composable
fun StartScreen(onStartClick: (String) -> Unit, modifier: Modifier = Modifier) {
    var playerName by rememberSaveable { mutableStateOf("") }
    val start = { onStartClick(playerName.trim().ifEmpty { "Jugador" }) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.app_name), style = MaterialTheme.typography.displaySmall)
        Text(stringResource(R.string.start_instructions), style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(
            value = playerName,
            onValueChange = { playerName = it },
            singleLine = true,
            label = { Text(stringResource(R.string.player_name)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { start() }),
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = start, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.start_game))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    ViewModelTheme { StartScreen(onStartClick = {}) }
}
