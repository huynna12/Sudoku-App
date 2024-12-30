import android.util.Log
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
import kotlinx.coroutines.delay
import model.SudokuBoard
import model.SudokuGenerator

@Composable
fun SudokuBoardScreen(difficulty: String?) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Debug the received difficulty
        Log.d("SudokuBoardScreen", "Received difficulty: $difficulty")

        // Generate the Sudoku board based on the difficulty
        val validDifficulty = difficulty ?: "Easy"

        // Generate the Sudoku board and log the result
        val board = SudokuBoard()
        val generator = SudokuGenerator(board, validDifficulty)
        val sudokuBoard = generator.getBoard()

        Log.d("SudokuBoardScreen", "Generated Board: $sudokuBoard")

        // Display the Sudoku board
        displayTime()

        SudokuBoardDisplay(sudokuBoard = sudokuBoard)

        displayAvailableNumbers(board = sudokuBoard)


    }
}


@Composable
fun SudokuBoardDisplay(
    sudokuBoard: SudokuBoard,
    onCellClick: ((row: Int, col: Int) -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .background(Color.Black) // Outer border for the grid
            .padding(3.dp) // Padding for subsquare borders
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
                            isHighlighted = (r / 3 + c / 3) % 2 == 0, // Alternate block colors
                            onClick = { onCellClick?.invoke(r, c) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SudokuCell(value: Int, isHighlighted: Boolean, onClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .size(45.dp)
            .background(
                color = if (isHighlighted) Color(0xFFEDEDED) else Color.White, // Subsquare coloring
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = .5.dp,
                color = Color.Black // Cell borders
            )
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
fun displayAvailableNumbers(board: SudokuBoard){
    val totalNumbers = board.totalNumbers()
    Row(){
        for((index, num) in totalNumbers.withIndex()){
            if(num != 9){
                Text(text = (index + 1).toString(), Modifier.padding(horizontal = 13.dp), fontSize = 30.sp)
            }
            else {
                 Text(text = " ", Modifier.padding(horizontal = 20.dp))
            }
        }
    }
}

@Composable
fun displayTime() {
    // Track the elapsed time in seconds
    var elapsedTime by remember { mutableIntStateOf(0) }

    // Start the timer
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L) // Wait for 1 second
            elapsedTime += 1
        }
    }

    // Format time as mm:ss
    val minutes = elapsedTime / 60
    val seconds = elapsedTime % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    // Display the timer
    Text(
        text = timeFormatted,
        color = Color.Black,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(16.dp) // Padding from the edges
    )
}

@Preview
@Composable
fun SudokuGameScreenPreview(){
    val board = SudokuBoard()
    val generator = SudokuGenerator(board, "Easy")
    val sudokuBoard = generator.getBoard()
    SudokuBoardDisplay(sudokuBoard = sudokuBoard)

//    displayAvailableNumbers(sudokuBoard)
}