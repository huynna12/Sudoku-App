import android.util.Log
import android.util.Log.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import model.SudokuBoard
import model.SudokuSolver

//@Composable
//fun SudokuBoardScreen(
//    board: SudokuBoard,
//    navController: NavController
//) {
//    var sudokuBoard by remember { mutableStateOf(board) }
//    var selectedRow by remember { mutableIntStateOf(-1) }
//    var selectedCol by remember { mutableIntStateOf(-1) }
//    var errorMessage by remember { mutableStateOf("") }
//    var errors by remember { mutableIntStateOf(0) }
//    var elapsedTime by remember { mutableIntStateOf(0) }
//    var score by remember { mutableIntStateOf(0)}
//    var strike by remember { mutableIntStateOf(1) }
//    var isTimerRunning by remember { mutableStateOf(true) }
//
//    val completedBoard = remember {
//        SudokuSolver(board.copy()).getCompleteBoard()
//    }
//
//    LaunchedEffect(isTimerRunning) {
//        while (isTimerRunning) {
//            delay(1000L)
//            elapsedTime++
//        }
//    }
//
//    Column(
//        Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(){
//            // Display elapsed time
//            val minutes = elapsedTime / 60
//            val seconds = elapsedTime % 60
//            val timeFormatted = String.format("%02d:%02d", minutes, seconds)
//            Text(
//                text = "Time: $timeFormatted",
//                color = Color.Black,
//                fontSize = 18.sp,
//                modifier = Modifier.padding(16.dp)
//            )
//
//            Text(
//                text = "Score: $score",
//                color = Color.Black,
//                fontSize = 18.sp,
//                modifier = Modifier.padding(16.dp)
//            )
//
//            Text(
//                text = "Errors: $errors",
//                color = Color.Black,
//                fontSize = 18.sp,
//                modifier = Modifier.padding(16.dp)
//            )
//        }
//        SudokuBoardDisplay(
//            sudokuBoard = sudokuBoard,
//            onCellClick = { row, col ->
//                selectedRow = row
//                selectedCol = col
//            }
//        )
//
//        if (selectedRow != -1 && selectedCol != -1) {
//            editCellDialog(
//                row = selectedRow,
//                col = selectedCol,
//                sudokuBoard = sudokuBoard,
//                onBoardUpdate = { updatedBoard ->
//                    sudokuBoard = updatedBoard
//                    errorMessage = ""
//                    selectedRow = -1
//                    selectedCol = -1
//                    score += 12 * strike++
//                },
//                completedBoard = completedBoard,
//                onError = { error ->
//                    errorMessage = error
//                    errors++
//                }
//            )
//        }
//
//        if (errorMessage.isNotEmpty()) {
//            Text(
//                text = errorMessage,
//                color = Color.Red,
//                fontSize = 18.sp,
//                modifier = Modifier.padding(16.dp)
//            )
//            strike = 1
//        }
//
//
//        if (isEndGame(sudokuBoard, errors)) {
//            isTimerRunning = false // Stop the timer
//            val result = errors < 3
//            // Navigate to RewardScreen with the final elapsed time
//            navController.navigate("reward/${score}/${elapsedTime}/${result}")
//        }
//        else {
//            displayAvailableNumbers(sudokuBoard)
//        }
//    }
//}

@Composable
fun SudokuBoardScreen(
    board: SudokuBoard,
    navController: NavController
) {
    var sudokuBoard by remember { mutableStateOf(board) }
    var selectedRow by remember { mutableIntStateOf(-1) }
    var selectedCol by remember { mutableIntStateOf(-1) }
    var errorMessage by remember { mutableStateOf("") }
    var errors by remember { mutableIntStateOf(0) }
    var elapsedTime by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var strike by remember { mutableIntStateOf(1) }
    var isTimerRunning by remember { mutableStateOf(true) }

    val completedBoard = remember {
        SudokuSolver(board.copy()).getCompleteBoard()
    }

    // Control timer behavior
    LaunchedEffect(isTimerRunning) {
        while (isTimerRunning) {
            delay(1000L)
            elapsedTime++
        }
    }

    // Check for end game condition
    val gameEnded = isEndGame(sudokuBoard, errors)

    // Navigate to reward screen when the game ends
    if (gameEnded) {
        // Stop the timer before navigating
        isTimerRunning = false

        val result = errors < 3
        LaunchedEffect(gameEnded) {
            // Delay navigation to ensure UI updates before transitioning
            delay(500L)
            navController.navigate("reward/${score}/${elapsedTime}/${result}")
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            // Display elapsed time, score, and errors
            val minutes = elapsedTime / 60
            val seconds = elapsedTime % 60
            val timeFormatted = String.format("%02d:%02d", minutes, seconds)

            Text(text = "Time: $timeFormatted", color = Color.Black, fontSize = 18.sp, modifier = Modifier.padding(16.dp))
            Text(text = "Score: $score", color = Color.Black, fontSize = 18.sp, modifier = Modifier.padding(16.dp))
            Text(text = "Errors: $errors", color = Color.Black, fontSize = 18.sp, modifier = Modifier.padding(16.dp))
        }

        SudokuBoardDisplay(
            sudokuBoard = sudokuBoard,
            onCellClick = { row, col ->
                selectedRow = row
                selectedCol = col
            }
        )

        if (selectedRow != -1 && selectedCol != -1) {
            editCellDialog(
                row = selectedRow,
                col = selectedCol,
                sudokuBoard = sudokuBoard,
                onBoardUpdate = { updatedBoard ->
                    sudokuBoard = updatedBoard
                    errorMessage = ""
                    selectedRow = -1
                    selectedCol = -1
                    score += 12 * strike++
                },
                completedBoard = completedBoard,
                onError = { error ->
                    errorMessage = error
                    errors++
                }
            )
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, fontSize = 18.sp, modifier = Modifier.padding(16.dp))
            strike = 1
        }

        // Display available numbers (only when the game isn't finished)
        if (!gameEnded) {
            displayAvailableNumbers(sudokuBoard)
        }
    }
}

@Composable
fun SudokuBoardDisplay(
    sudokuBoard: SudokuBoard,
    onCellClick: (row: Int, col: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color.Black)
            .padding(3.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (r in 0 until 9) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (c in 0 until 9) {
                        SudokuCell(
                            value = sudokuBoard.getCell(r, c),
                            isHighlighted = (r / 3 + c / 3) % 2 == 0,
                            onClick = { onCellClick(r, c) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SudokuCell(
    value: Int,
    isHighlighted: Boolean,
    onClick: (() -> Unit)?
) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(
                color = if (isHighlighted) Color(0xFFEDEDED) else Color.White,
                shape = RoundedCornerShape(4.dp)
            )
            .border(0.5.dp, Color.Black)
            .clickable { onClick?.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (value == 0) "" else value.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun editCellDialog(
    row: Int,
    col: Int,
    sudokuBoard: SudokuBoard, // Directly pass the current board state
    onBoardUpdate: (SudokuBoard) -> Unit, // Callback to update the board state
    completedBoard: SudokuBoard,
    onError: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Select a number for cell ($row, $col):", fontSize = 18.sp)
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (num in 1..9) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(4.dp))
                        .clickable {
                            val correctMove = validateMove(row, col, num, completedBoard)

                            if (correctMove) {
                                // Create an updated copy of the board
                                val updatedBoard = sudokuBoard.copy().apply {
                                    setCell(row, col, num)
                                }

                                // Update the board state via the callback
                                onBoardUpdate(updatedBoard)
                            } else {
                                onError("Wrong number! Try again.")
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = num.toString(),
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun displayAvailableNumbers(board: SudokuBoard) {
    val totalNumbers = board.totalNumbers()
    Row {
        for ((index, num) in totalNumbers.withIndex()) {
            if (num != 9) {
                Text(
                    text = (index + 1).toString(),
                    Modifier.padding(horizontal = 13.dp),
                    fontSize = 30.sp
                )
            } else {
                Text(text = " ", Modifier.padding(horizontal = 20.dp))
            }
        }
    }
}

@Composable
fun displayTime() {
    var elapsedTime by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L)
            elapsedTime += 1
        }
    }

    val minutes = elapsedTime / 60
    val seconds = elapsedTime % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    Text(
        text = timeFormatted,
        color = Color.Black,
        fontSize = 18.sp,
        modifier = Modifier.padding(16.dp)
    )
}

fun validateMove(
    row: Int,
    col: Int,
    num: Int,
    completedBoard: SudokuBoard
): Boolean {
    return completedBoard.getCell(row, col) == num
}

//Helper method to stop the game when the user is done

@Composable
fun isEndGame(board: SudokuBoard, errors: Int): Boolean {
    return errors == 3 || board.totalNumbers().all { it == 9 }
}


@Preview
@Composable
fun SudokuGameScreenPreview() {
}