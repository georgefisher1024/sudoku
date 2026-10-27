package sudoku.observers;

import sudoku.Board;

public interface GameObserver {
    void cellChanged(Board board, int row, int col, int value);
}
