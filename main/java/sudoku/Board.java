package sudoku;

public class Board {
    public static final int SIZE = 9;
    private final int[][] values = new int[SIZE][SIZE];
    private final boolean[][] given = new boolean[SIZE][SIZE];

    public Board(int[][] clues) {
        if (clues == null || clues.length != SIZE) {
            throw new IllegalArgumentException("Board must have 9 rows");
        }
        for (int row = 0; row < SIZE; row++) {
            if (clues[row] == null || clues[row].length != SIZE) {
                throw new IllegalArgumentException("Each row must have 9 columns");
            }
            for (int col = 0; col < SIZE; col++) {
                int value = clues[row][col];
                if (value < 0 || value > 9) {
                    throw new IllegalArgumentException("Values must be between 0 and 9");
                }
                values[row][col] = value;
                given[row][col] = value != 0;
            }
        }
        // Validate the complete board after copying it, so later clues are checked too.
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = values[row][col];
                if (value != 0 && !isAllowed(row, col, value)) {
                    throw new IllegalArgumentException("Clues conflict");
                }
            }
        }
    }

    public int getValue(int row, int col) {
        checkPosition(row, col);
        return values[row][col];
    }

    public boolean isGiven(int row, int col) {
        checkPosition(row, col);
        return given[row][col];
    }

    public boolean isAllowed(int row, int col, int value) {
        checkPosition(row, col);
        if (value < 1 || value > 9) return false;
        for (int i = 0; i < SIZE; i++) {
            if (i != col && values[row][i] == value) return false;
            if (i != row && values[i][col] == value) return false;
        }
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != row || c != col) && values[r][c] == value) return false;
            }
        }
        return true;
    }

    // Package-private: SudokuGame controls changes and observer notifications.
    void setValue(int row, int col, int value) {
        checkPosition(row, col);
        if (given[row][col]) throw new IllegalArgumentException("Cannot change a given cell");
        if (value < 0 || value > 9) throw new IllegalArgumentException("Values must be between 0 and 9");
        values[row][col] = value;
    }

    public boolean isComplete() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                int value = values[row][col];
                if (value == 0 || !isAllowed(row, col, value)) return false;
            }
        }
        return true;
    }

    private void checkPosition(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Row and column must be between 0 and 8");
        }
    }
}
