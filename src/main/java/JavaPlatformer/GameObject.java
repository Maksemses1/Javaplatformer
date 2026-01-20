package JavaPlatformer;

import java.awt.Graphics;

abstract class GameObject {
  int x, y;
  int width, height;
  boolean isCollide = false;

  void draw(Graphics g) {
    g.drawRect(x, y, 0, 0);
  }
}
