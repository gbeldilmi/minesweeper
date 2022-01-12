package Minesweeper;
class Minesweeper extends javax.swing.JFrame {
  private Board board;
  private Level level;
  private javax.swing.JButton[][] buttons;
  Minesweeper(Level level) {
    this.level = level;
    initComponents();
  }
  private void buttonClicked(java.awt.event.MouseEvent evt) {
    int x = 0;
    int y = 0;
    for (int i = 0; i < board.getWidth(); i++) {
      for (int j = 0; j < board.getHeight(); j++) {
        if (buttons[i][j] == evt.getSource()) {
          x = i;
          y = j;
        }
      }
    }
    buttonClicked(x, y, evt.getButton());
  }
  private void buttonClicked(int x, int y, int mouseButton) {
    if (buttons[x][y].isEnabled()){
      if (mouseButton == java.awt.event.MouseEvent.BUTTON1) {
        if (!board.isFlagged(x, y)) {
          buttons[x][y].setEnabled(false);
          board.reveal(x, y);
          if (board.isMine(x, y)) {
            buttons[x][y].setText("X");
            gameOver();
          } else {
            if (board.getAdjacentMines(x, y) == 0) {
              buttons[x][y].setText("");
              for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                  if (i >= 0 && i < board.getWidth() && j >= 0 && j < board.getHeight() && (i != x || j != y)) {
                    buttonClicked(i, j, java.awt.event.MouseEvent.BUTTON1);
                  }
                }
              }
            } else {
              buttons[x][y].setText(Integer.toString(board.getAdjacentMines(x, y)));
            }
          }
        }
      } else if (mouseButton == java.awt.event.MouseEvent.BUTTON3) {
        if (!board.isRevealed(x, y)) {
          if (board.isFlagged(x, y)) {
            buttons[x][y].setText("");
            board.unflag(x, y);
          } else {
            buttons[x][y].setText("F");
            board.flag(x, y);
          }
        }
      }
    }
    if (board.isWin()) {
      gameWon();
    }
  }
  private void initComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Minesweeper");
    setLocationRelativeTo(null);
    initGrid();
  }
  private void initGrid() {
    board = new Board(level.getWidth(), level.getHeight(), level.getMines());
    getContentPane().removeAll();
    getContentPane().setLayout(new java.awt.GridLayout(board.getHeight(), board.getWidth()));
    buttons = new javax.swing.JButton[board.getWidth()][board.getHeight()];
    for (int x = 0; x < board.getWidth(); x++) {
      for (int y = 0; y < board.getHeight(); y++) {
        buttons[x][y] = new javax.swing.JButton();
        buttons[x][y].setText("");
        buttons[x][y].addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            buttonClicked(evt);
          }
        });
        getContentPane().add(buttons[x][y]);
      }
    }
    pack();
    invalidate();
    validate();
    repaint();
  }
  private void gameOver() {
    for (int i = 0; i < board.getWidth(); i++) {
      for (int j = 0; j < board.getHeight(); j++) {
        if (board.isMine(i, j)) {
          buttons[i][j].setText("X");
          buttons[i][j].setEnabled(false);
        }
      }
    }
    javax.swing.JOptionPane.showMessageDialog(this, "Game Over");
    initGrid();
  }
  private void gameWon() {
    javax.swing.JOptionPane.showMessageDialog(this, "You Win!");
    level.up();
    initGrid();
  }
}
