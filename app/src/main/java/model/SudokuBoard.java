package model;

import java.util.Random;

public class SudokuBoard {
    private int[][] board;
    private static final Random random = new Random();
    private  static final int[] num = new int[]{1,2,3,4,5,6,7,8,9};
    /**
     * Create a standard 9x9 SUDOKU board
     */
    public SudokuBoard() {
        board = new int[9][9];
        fillBoard();
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
        for(int i = 0; i < 9; i++){
            if((i != col && board[row][i] == value) || (i != row && board[col][i] == value)){
                return false;
            }
        }

        //check the value in sub-square (3x3 grid)
        int startRow = row/3 * 3;
        int startCol = col/3 * 3;
        for(int r = startRow; r < startRow + 3; r++){
            for(int c = startCol; c < startCol + 3; c++){
                if(r!= row && c!=col && board[r][c] == value){
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
            fillCell(row + 1, 0);  //reset the col and move down to a new row
        }
        shuffleArray(num);
        for(int curNum : num){
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

    public void printBoard(){
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }
}
