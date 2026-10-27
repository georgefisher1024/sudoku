package sudoku.strategy;

import sudoku.Board;

public interface MoveStrategy {
    boolean isValidMove(Board board, int row, int col, int value);
}
