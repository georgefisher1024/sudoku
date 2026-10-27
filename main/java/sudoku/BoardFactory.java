package sudoku;

public class BoardFactory {
    // A fixed sample puzzle makes the starter project and tests reproducible.
    public Board createSampleBoard() {
        return new Board(new int[][] {
            {5,3,0,0,7,0,0,0,0}, {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0}, {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1}, {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0}, {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        });
    }

    public Board createEmptyBoard() { return new Board(new int[Board.SIZE][Board.SIZE]); }
}
