package Minesweeper;
class Level {
  private int width;
  private int height;
  private int mines;
  Level(int level) {
    switch (level) {
      case 1:
        this.width = 10;
        this.height = 10;
        this.mines = 10;
        break;
      case 2:
        this.width = 15;
        this.height = 15;
        this.mines = 40;
        break;
      case 3:
        this.width = 20;
        this.height = 20;
        this.mines = 99;
        break;
      case 4:
        this.width = 100;
        this.height = 100;
        this.mines = 999;
        break;
      default:
        this.width = 20;
        this.height = 20;
        this.mines = (int) (Math.random() * 90) + 10;
    }
  }
  Level(int width, int height, int mines) {
    this.width = width;
    this.height = height;
    this.mines = mines;
  }
  public int getHeight() {
    return height;
  }
  public int getMines() {
    return mines;
  }
  public int getWidth() {
    return width;
  }
  public void up() {
    if (mines < 0.9 * width * height) {
      mines += 10;
    }
  }
}
