package Minesweeper;
public class Main {
  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Minesweeper(new Level(0)).setVisible(true);
      }
    });
  }
}
