package JavaPlatformer;

import java.awt.Graphics;
import java.util.ArrayList;

abstract class GameObject {
  int x, y;
  int width, height;
  ArrayList<String> colliders = new ArrayList<>();

  boolean hasTag(String str) {
    return colliders.contains(str);
  }

  void draw(Graphics g) {
    g.drawRect(x, y, 0, 0);
  }
}
