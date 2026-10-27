package sudoku.strategy;

import sudoku.Board;

// Practice mode accepts conflicts so that a player can investigate mistakes.
public class PracticeMoveStrategy implements MoveStrategy {
    @Override
    public boolean isValidMove(Board board, int row, int col, int value) {
        return value >= 1 && value <= 9;
    }
}
