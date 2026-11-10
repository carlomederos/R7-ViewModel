package com.carlo_mederos.viewmodel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlo_mederos.viewmodel.R
import com.carlo_mederos.viewmodel.ui.theme.ViewModelTheme

@Composable
fun ResultScreen(
    playerName: String,
    score: Int,
    wordCount: Int,
    onPlayAgain: () -> Unit,
    onExitToStart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.congratulations), style = MaterialTheme.typography.displaySmall)
        Text(stringResource(R.string.result_player, playerName), style = MaterialTheme.typography.titleLarge)
        Text(stringResource(R.string.you_scored, score), style = MaterialTheme.typography.headlineMedium)
        Text(stringResource(R.string.words_played, wordCount), style = MaterialTheme.typography.bodyLarge)
        Button(onClick = onPlayAgain, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.play_again))
        }
        OutlinedButton(onClick = onExitToStart, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.change_player))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ViewModelTheme {
        ResultScreen("Carlo", 160, 10, {}, {})
    }
}
