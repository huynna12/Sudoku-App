package model;

import java.util.Random;
public class SudokuGenerator {
    private SudokuBoard board;
    private SudokuSolver solver;
    private static final Random random = new Random();
    private static final int EASY = 43;
    private static final int MEDIUM = 47;
    private static final int HARD = 52;

    public SudokuGenerator(SudokuBoard board, String level){
        this.board = board;
        solver = new SudokuSolver(board);
        switch(level){
            case "easy":
                generateBoard(EASY);
                break;
            case "medium":
                generateBoard(MEDIUM);
                break;
            case "hard":
                generateBoard(HARD);
                break;
        }
    }

    public void generateBoard(int level){
        int removed = 0;

        while(removed < level){
            int r = random.nextInt(9);
            int c = random.nextInt(9);

            if(board.getCell(r,c) == 0){
                continue;
            }

            int oldNum = board.getCell(r, c);

            board.setCell(r, c, 0);
            if (solver.getSolution() != 1){
                board.setCell(r, c, oldNum);
            }
            else {
                removed++;
            }
        }
    }

    public SudokuBoard getBoard(){
        return board;
    }
}
