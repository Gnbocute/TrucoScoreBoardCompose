package br.edu.ifsp.scl.sc3046664.trucoscoreboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3046664.trucoscoreboardcompose.ui.theme.TrucoScoreBoardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TrucoScoreBoardComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    TrucoScoreBoardComposeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TrucoScoreBoardComposeScreen(modifier: Modifier = Modifier) {

    var team1Score by remember { mutableIntStateOf(0) }
    var team2Score by remember { mutableIntStateOf(0) }

    var team1Name by remember { mutableStateOf("Nós") }
    var team2Name by remember { mutableStateOf("Eles") }

    var winner by remember { mutableStateOf<String?>(null) }

    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            text = {
                Text(dialogMessage)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            OutlinedTextField(
                value = team1Name,
                onValueChange = { team1Name = it },
                singleLine = true
            )

            Text(
                text = team1Score.toString(),
                fontSize = 48.sp
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Button(
                    enabled = team1Score < 12 && winner == null,
                    onClick = {

                        team1Score++

                        if (team1Score == 11) {
                            dialogMessage = "$team1Name está na mão de 11!"
                            showDialog = true
                        }

                        if (team1Score == 12) {
                            winner = team1Name
                            dialogMessage = "$team1Name venceu!"
                            showDialog = true
                        }
                    }
                ) {
                    Text("+1")
                }

                Button(
                    enabled = team1Score < 11 && winner == null,
                    onClick = {

                        team1Score = minOf(team1Score + 3, 12)

                        if (team1Score == 11) {
                            dialogMessage = "$team1Name está na mão de 11!"
                            showDialog = true
                        }

                        if (team1Score == 12) {
                            winner = team1Name
                            dialogMessage = "$team1Name venceu!"
                            showDialog = true
                        }
                    }
                ) {
                    Text("+3")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            contentAlignment = Alignment.Center
        ) {

            HorizontalDivider()

            Button(
                onClick = {
                    team1Score = 0
                    team2Score = 0
                    winner = null
                    showDialog = false
                }
            ) {
                Text("Reiniciar")
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            OutlinedTextField(
                value = team2Name,
                onValueChange = { team2Name = it },
                singleLine = true
            )

            Text(
                text = team2Score.toString(),
                fontSize = 48.sp
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Button(
                    enabled = team2Score < 12 && winner == null,
                    onClick = {

                        team2Score++

                        if (team2Score == 11) {
                            dialogMessage = "$team2Name está na mão de 11!"
                            showDialog = true
                        }

                        if (team2Score == 12) {
                            winner = team2Name
                            dialogMessage = "$team2Name venceu!"
                            showDialog = true
                        }
                    }
                ) {
                    Text("+1")
                }

                Button(
                    enabled = team2Score < 11 && winner == null,
                    onClick = {

                        team2Score = minOf(team2Score + 3, 12)

                        if (team2Score == 11) {
                            dialogMessage = "$team2Name está na mão de 11!"
                            showDialog = true
                        }

                        if (team2Score == 12) {
                            winner = team2Name
                            dialogMessage = "$team2Name venceu!"
                            showDialog = true
                        }
                    }
                ) {
                    Text("+3")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrucoScoreBoardComposePreview() {
    TrucoScoreBoardComposeTheme {
        TrucoScoreBoardComposeScreen()
    }
}