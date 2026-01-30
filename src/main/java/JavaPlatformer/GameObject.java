package JavaPlatformer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

abstract class GameObject {
  int x, y;
  int width, height;
  List<String> colliders = new ArrayList<>();

  boolean hasTag(String str) {
    return colliders.contains(str);
  }

  void update() {
  };

  void draw(Graphics g) {
  };
}
