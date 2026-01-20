package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;

class Grass extends GameObject {
  final Color col = new Color(57, 112, 6);

  Grass(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    isCollide = true;
  }

  public void draw(Graphics g) {
    Color prevColor = g.getColor();
    g.setColor(col);
    g.fillRect(x, y, width, height);
    g.setColor(prevColor);
  }
}
