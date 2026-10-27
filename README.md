# Sudoku 
## Design patterns

- **Strategy** : 
    - `StandardMoveStrategy` enforces row, column, and box rules for numbers 1-9
      - for example: if user wants to place a 1 in a box, but there is already a 1, then the move will not be allowed and an error message will be produced.
    - `PracticeMoveStrategy` allows placement of any number even if conflicts exist; this is for user to practice/test their move guesses
- **Observer** : a game maintains observers and notifies them after a cell actually changes. `ConsoleObserver` demonstrates a subscriber; a future graphical view can implement the same interface. Rejected moves and unchanged cells do not trigger notifications.
- **Factory** : constructs sudoku boards
  - makes empty board, sample board
  - Could be used to make easy, medium, hard games