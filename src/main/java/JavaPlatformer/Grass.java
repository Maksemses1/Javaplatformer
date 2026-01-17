package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;

class Grass extends GameObject {
  final int WIDTH;
  final int HEIGHT;
  final Color col = new Color(57, 112, 6);

  Grass(int width, int height) {
    this.HEIGHT = height;
    this.WIDTH = width;
  }

  public void draw(Graphics g) {
    Color prevColor = g.getColor();
    g.setColor(col);
    g.fillRect(x, y, WIDTH, HEIGHT);
    g.setColor(prevColor);
  }
}
