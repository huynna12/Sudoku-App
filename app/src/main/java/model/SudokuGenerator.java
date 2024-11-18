package model;

public class SudokuGenerator {
    private SudokuBoard board;
    private static final int EASY = 43;
    private static final int MEDIUM = 47;
    private static final int HARD = 52;

    public SudokuGenerator(SudokuBoard board, String level){
        this.board = board;
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

        for(int i = 0; i < level; i++){

        }
    }
}
