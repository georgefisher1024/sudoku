package sudoku;

import sudoku.observers.GameObserver;
import sudoku.strategy.MoveStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SudokuGame {
    private final Board board;
    private final MoveStrategy moveStrategy;
    private final List<GameObserver> observers = new ArrayList<>();

    public SudokuGame(Board board, MoveStrategy moveStrategy) {
        this.board = Objects.requireNonNull(board);
        this.moveStrategy = Objects.requireNonNull(moveStrategy);
    }

    public Board getBoard() { return board; }

    public void addObserver(GameObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    public void removeObserver(GameObserver observer) { observers.remove(observer); }

    public boolean makeMove(int row, int col, int value) {
        if (board.isGiven(row, col) || !moveStrategy.isValidMove(board, row, col, value)) return false;
        if (board.getValue(row, col) == value) return true;
        board.setValue(row, col, value);
        notifyObservers(row, col, value);
        return true;
    }

    public boolean clearCell(int row, int col) {
        if (board.isGiven(row, col)) return false;
        if (board.getValue(row, col) == 0) return true;
        board.setValue(row, col, 0);
        notifyObservers(row, col, 0);
        return true;
    }

    private void notifyObservers(int row, int col, int value) {
        for (GameObserver observer : new ArrayList<>(observers)) {
            observer.cellChanged(board, row, col, value);
        }
    }
}
