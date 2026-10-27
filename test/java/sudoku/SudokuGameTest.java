package sudoku;

import org.junit.jupiter.api.Test;
import sudoku.strategy.PracticeMoveStrategy;
import sudoku.strategy.StandardMoveStrategy;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGameTest {
    BoardFactory factory = new BoardFactory();

    @Test
    void validMoveIsPlaced() {
        SudokuGame game = new SudokuGame(factory.createSampleBoard(), new StandardMoveStrategy());
        assertTrue(game.makeMove(0, 2, 4));
        assertEquals(4, game.getBoard().getValue(0, 2));
    }

    @Test
    void givenCellCannotBeChangedOrCleared() {
        SudokuGame game = new SudokuGame(factory.createSampleBoard(), new StandardMoveStrategy());
        assertFalse(game.makeMove(0, 0, 1));
        assertFalse(game.clearCell(0, 0));
        assertEquals(5, game.getBoard().getValue(0, 0));
    }

    @Test
    void standardStrategyRejectsRowColumnAndBoxConflicts() {
        SudokuGame game = new SudokuGame(factory.createSampleBoard(), new StandardMoveStrategy());
        assertFalse(game.makeMove(0, 2, 5)); // row
        assertFalse(game.makeMove(3, 1, 9)); // column
        assertFalse(game.makeMove(1, 1, 8)); // box
    }

    @Test
    void practiceStrategyAllowsConflictButDoesNotMarkBoardComplete() {
        SudokuGame game = new SudokuGame(factory.createSampleBoard(), new PracticeMoveStrategy());
        assertTrue(game.makeMove(0, 2, 5));
        assertFalse(game.getBoard().isComplete());
    }

    @Test
    void observerReceivesChangesAndStopsAfterRemoval() {
        SudokuGame game = new SudokuGame(factory.createSampleBoard(), new StandardMoveStrategy());
        AtomicInteger count = new AtomicInteger();
        sudoku.observers.GameObserver observer = (board, row, col, value) -> {
            assertEquals(0, row); assertEquals(2, col); assertEquals(value, board.getValue(row, col));
            count.incrementAndGet();
        };
        game.addObserver(observer);
        assertTrue(game.makeMove(0, 2, 4));
        assertFalse(game.makeMove(0, 2, 5));
        assertTrue(game.clearCell(0, 2));
        game.removeObserver(observer);
        assertTrue(game.makeMove(0, 2, 4));
        assertEquals(2, count.get());
    }

    @Test
    void invalidCluesAndOutOfRangeValuesAreRejected() {
        int[][] clues = new int[9][9]; clues[0][0] = 1; clues[0][1] = 1;
        assertThrows(IllegalArgumentException.class, () -> new Board(clues));
        SudokuGame game = new SudokuGame(factory.createEmptyBoard(), new StandardMoveStrategy());
        assertFalse(game.makeMove(0, 0, 10));
        assertThrows(IllegalArgumentException.class, () -> game.makeMove(9, 0, 1));
    }

    @Test
    void solvedBoardIsComplete() {
        Board board = new Board(new int[][] {
            {5,3,4,6,7,8,9,1,2}, {6,7,2,1,9,5,3,4,8}, {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3}, {4,2,6,8,5,3,7,9,1}, {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4}, {2,8,7,4,1,9,6,3,5}, {3,4,5,2,8,6,1,7,9}
        });
        assertTrue(board.isComplete());
    }
}
