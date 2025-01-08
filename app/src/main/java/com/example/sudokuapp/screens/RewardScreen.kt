package com.example.sudokuapp.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RewardScreen(
    score: Int,
    time: Int,
    result: Boolean,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (result) "Congratulations, You Won!" else "Game Over",
            fontSize = 24.sp,
            color = if (result) Color.Green else Color.Red,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Score: $score",
            fontSize = 20.sp
        )
        val minutes = time / 60
        val seconds = time % 60
        Text(
            text = String.format("Time: %02d:%02d", minutes, seconds),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Play Again",
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(8.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                color = Color.White
            )
            Text(
                text = "Exit",
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(Screen.Detail.route)
                    },
                color = Color.White
            )
        }
    }
}

