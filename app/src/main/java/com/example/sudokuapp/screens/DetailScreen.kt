package com.example.sudokuapp.screens

import SudokuBoardDisplay
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*   //mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import model.SudokuBoard
import model.SudokuGenerator

@Composable
fun DetailScreen(navController: NavController) {
    var showDifficultyDialog by remember { mutableStateOf(false) }
    var sudokuBoard by remember { mutableStateOf<SudokuBoard?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(100.dp),
                    text = "Sudoku",
                    fontWeight = FontWeight.Bold, // Use FontWeight
                    fontSize = 40.sp,
                    style = MaterialTheme.typography.headlineLarge
                )
                ExpandableCard(
                    title = "Instruction",
                    content = """
                        Goal: Fill the 9x9 grid so each row, column, and 3x3 box contains the numbers 1 to 9, with no repeats.

                        Rules:
                        - Every **row**, **column**, and **3x3 box** must have the numbers 1 to 9.
                        - Some numbers are already given to help you.

                        How to play:
                        - Start by looking for rows, columns, or boxes with few missing numbers.
                        - Use the process of elimination to figure out where numbers go.

                        Tips:
                        - If stuck, look for cells where only one number can fit.
                        - Focus on completing one row, column, or box at a time.
                    """.trimIndent()
                )
                ExpandableCard(
                    title = "Did You Know?",
                    content = "Sudoku was invented in 1979 by Howard Garns, an American architect."
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 150.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NewGameButton(onClick = { showDifficultyDialog = true })
                ContinueGameButton(onClick = {
                    sudokuBoard = sudokuBoard ?: SudokuBoard() // Start with an empty board if none exists
                })
            }
        }
    }

// Display difficulty selection dialog
    if (showDifficultyDialog) {
        DifficultySelectionDialog(
            onDismissRequest = { showDifficultyDialog = false },
            onDifficultySelected = { difficulty ->
                // Navigate to the SudokuBoardScreen with the selected difficulty
                showDifficultyDialog = false
                navController.navigate("sudokuBoard/$difficulty")
            }
        )
    }
}

@Composable
fun DifficultySelectionDialog(
    onDismissRequest: () -> Unit,
    onDifficultySelected: (String) -> Unit
) {
    androidx.compose.ui.window.Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Difficulty",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Button(
                    onClick = { onDifficultySelected("Easy") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Easy")
                }

                Button(
                    onClick = { onDifficultySelected("Medium") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Medium")
                }

                Button(
                    onClick = { onDifficultySelected("Hard") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hard")
                }
            }
        }
    }
}

@Composable
fun NewGameButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(text = "New Game", fontSize = 19.sp, modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun ContinueGameButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(text = "Continue Game", fontSize = 19.sp, modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun ExpandableCard(title: String, content: String) {
    var expandedState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Add spacing between cards
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = { expandedState = !expandedState },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier
                        .rotate(rotateState)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            if (expandedState) {
                Text(
                    text = if (content.isNotEmpty()) content else "No content available.",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
//    DetailScreen()
}