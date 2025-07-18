package model;

import java.util.Random;

public class SudokuBoard {
    private int[][] board;
    private static final Random random = new Random();
    private  static final int[] numbers = new int[]{1,2,3,4,5,6,7,8,9};
    /**
     * Create a standard 9x9 SUDOKU board
     */
    public SudokuBoard() {
        board = new int[9][9];
        fillBoard();
    }

    public SudokuBoard(int[][] thisBoard){
        board = thisBoard;
    }


    /**
     * Return the value of the cell at given row and column
     * @param r -   given row
     * @param c -   given column
     * @return  the given value of the cell at given row and column
     */
    public int getCell(int r, int c){
        return board[r][c];
    }

    /**
     * Replace the value of the cell at given row and column with the given value
     * @param r -   given row
     * @param c -   given column
     * @param value - given value
     */
    public void setCell(int r, int c, int value){
        board[r][c] = value;
    }

    /**
     * Clear all values in the board
     */
    public void clear(){
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                board[r][c] = 0;
            }
        }
    }

    /**
     * Return the board
     * @return the board
     */
    public int[][] getBoard(){
        return board;
    }

    /**
     * Return true if the value can be placed at given column and row, false otherwise
     * @param row   -   given row
     * @param col   -   given column
     * @param value -   given value
     * @return true if the value can be placed at given column and row, false otherwise
     */
    boolean isValid(int row, int col, int value){
        //check the value in given row and column
            // Check the value in the given row and column
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == value || board[i][col] == value) {
                    return false;
                }
            }

            // Check the value in the sub-square (3x3 grid)
            int startRow = row / 3 * 3;
            int startCol = col / 3 * 3;
            for (int r = startRow; r < startRow + 3; r++) {
                for (int c = startCol; c < startCol + 3; c++) {
                    if (board[r][c] == value) {
                        return false;
                    }
                }
            }
            return true;
    }

    private void fillBoard(){
        fillCell(0,0);
    }

    private boolean fillCell(int row, int col){
        if(row == 9){
            return true;    //filled all the rows
        }

        if(col == 9){
            return fillCell(row + 1, 0);  //reset the col and move down to a new row
        }
        shuffleArray(numbers);
        for(int curNum : numbers){
            if(isValid(row, col, curNum)){
                board[row][col] = curNum;

                if(fillCell(row, col + 1)){
                    return true;
                }

                board[row][col] = 0;
            }
        }
        return false;
    }

    private void shuffleArray(int[] array){
        int index, temp;
        for(int i = array.length - 1; i > 0; i--){
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public String toString(){
       String s = "";
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                s +=  board[r][c] + " ";
            }
            s += "\n ";
        }

        return s;
    }

    /**
     * Return an array of numbers that is in the board
     * @return an array of numbers that is in the board, index acted as the number the the value that
     * that index is the total numbers of the number on the board
     */
    public int[] totalNumbers(){
        int[] numbersLeft = new int[9];
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if(board[r][c] != 0){
                    numbersLeft[board[r][c] - 1]++;
                }
            }
        }
        return numbersLeft;
    }

    public SudokuBoard copy(){
        int[][] newBoard = new int[9][9];
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                newBoard[r][c] = board[r][c];
            }
        }

        return new SudokuBoard(newBoard);
    }
}


