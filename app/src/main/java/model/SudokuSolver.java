package model;
//Feed a sudoku to this class, end it will solve the sudoku
public class SudokuSolver {
    private SudokuBoard board;
    private  static final int[] num = new int[]{1,2,3,4,5,6,7,8,9};
    public SudokuSolver(SudokuBoard board){
        this.board = board;
    }
    public int[] findEmpty(int startR, int startC) {
        for(int r = startR; r < 9; r++){
            for(int c = (r == startR ? startC : 0); c < 9; c++){
               if(board.getCell(r,c) == 0){
                   return new int[]{r,c};
               }
            }
        }
        return null; //no more empty spot
    }

    public int getSolution(){
        return getSolutionHelper(0,0);
    }

    private int getSolutionHelper(int row, int col){
        int[] empty = findEmpty(row, col);

        if(empty == null){
            return 1;
        }

        row = empty[0];
        col = empty[1];
        int sol = 0;

        for(int curNum : num){
            if(board.isValid(row, col, curNum)){
                board.setCell(row, col, curNum);
                sol += getSolutionHelper(row, col);
                board.setCell(row, col, 0);
            }

            if(sol > 1){
                break;
            }
        }
        return sol;
    }

    public SudokuBoard getCompleteBoard(){
        getSolution();
        return board;
    }
}
