package sudoku.observers;

import sudoku.Board;

public class ConsoleObserver implements GameObserver {
    @Override
    public void cellChanged(Board board, int row, int col, int value) {
        System.out.println("Cell (" + row + ", " + col + ") changed to " + value);
    }
}
