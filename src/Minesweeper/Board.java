package Minesweeper;
class Board {
  private int width;
  private int height;
  private boolean[][] mines;
  private boolean[][] revealed;
  private boolean[][] flagged;
  private int[][] adjacentMines;
  Board(int width, int height, int mines) {
    this.width = width;
    this.height = height;
    this.mines = new boolean[width][height];
    this.revealed = new boolean[width][height];
    this.flagged = new boolean[width][height];
    this.adjacentMines = new int[width][height];
    setMines(mines);
  }
  private int countAdjacentMines(int x, int y) {
    int count = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i >= 0 && i < width && j >= 0 && j < height) {
          if (mines[i][j]) {
            count++;
          }
        }
      }
    }
    return count;
  }
  public void flag(int x, int y) {
    flagged[x][y] = true;
  }
  public int getAdjacentMines(int x, int y) {
    return adjacentMines[x][y];
  }
  public int getHeight() {
    return height;
  }
  public int getWidth() {
    return width;
  }
  public boolean isFlagged(int x, int y) {
    return flagged[x][y];
  }
  public boolean isMine(int x, int y) {
    return mines[x][y];
  }
  public boolean isRevealed(int x, int y) {
    return revealed[x][y];
  }
  public boolean isWin() {
    boolean win = true;
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        win &= (mines[x][y] ^ revealed[x][y]);
      }
    }
    return win;
  }
  public void reveal(int x, int y) {
    revealed[x][y] = true;
    if (adjacentMines[x][y] == 0) {
      for (int i = x - 1; i <= x + 1; i++) {
        for (int j = y - 1; j <= y + 1; j++) {
          if (i >= 0 && i < width && j >= 0 && j < height) {
            if (!revealed[i][j]) {
              reveal(i, j);
            }
          }
        }
      }
    }
  }
  private void setMines(int mines) {
    while (mines > 0) {
      int x = (int) (Math.random() * width);
      int y = (int) (Math.random() * height);
      if (!this.mines[x][y]) {
        this.mines[x][y] = true;
        mines--;
      }
    }
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (!this.mines[x][y]) {
          adjacentMines[x][y] = countAdjacentMines(x, y);
        }
      }
    }
  }
  public void unflag(int x, int y) {
    flagged[x][y] = false;
  }
}
