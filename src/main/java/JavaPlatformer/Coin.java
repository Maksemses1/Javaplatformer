package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;

class Coin extends GameObject {
  Color col = Color.YELLOW;

  Coin(int x, int y) {
    colliders.add("coin");
    width = 50;
    height = 50;
    this.x = x;
    this.y = y;
  }

  @Override
  void draw(Graphics g) {
    g.setColor(col);
    g.fillRect(x, y, width, height);
  }
}
