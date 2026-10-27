package sudoku.strategy;

import sudoku.Board;

public class StandardMoveStrategy implements MoveStrategy {
    @Override
    public boolean isValidMove(Board board, int row, int col, int value) {
        return board.isAllowed(row, col, value);
    }
}
