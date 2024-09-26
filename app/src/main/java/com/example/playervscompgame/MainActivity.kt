package com.example.playervscompgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.playervscompgame.ui.theme.PlayerVsCompGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayerVsCompGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    playerVsComp()
                }
            }
        }
    }
}

@Composable

fun playerVsComp() {

    var playerHp by remember { mutableStateOf(100) }
    var computerHp by remember { mutableStateOf(100) }
    val playerAttackRandomNumber = (0..10).random()
    val computerAttackRandomNumber = (0..10).random()
    val healingRandomNumber = (0..5).random()
    val computerChoice = (1..2).random()
    var winner = ""


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) { // HP Bar.
            Row {
                Text("Player HP : $playerHp")
                Spacer(modifier = Modifier.width(46.dp))
                Text("Computer HP : $computerHp")
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Attack Button
            Button(onClick = {
                computerHp -= playerAttackRandomNumber
                when (computerChoice) {
                    1 -> playerHp -= computerAttackRandomNumber
                    2 -> computerHp += healingRandomNumber
                }

            }) {
                Text("ATTACK")

                //Healing Button
            }
            Button(onClick = {
                playerHp += healingRandomNumber
                when (computerChoice) {
                    1 -> playerHp -= computerAttackRandomNumber
                    2 -> computerHp += healingRandomNumber
                }

            }) {
                Text("HEALING")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Winner
            if (playerHp <= 0) {
                winner = "COMPUTER!!!"
            }
            if (computerHp <= 0) {
                winner = "PLAYER!!!"
            }
            Text("Winner: $winner")
        }
}


@Preview(showBackground = true)
@Composable
fun playerVsCompPreview() {
    playerVsComp()
}