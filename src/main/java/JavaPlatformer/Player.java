package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class Player extends GameObject {
  final int width = 20;
  final int height = 60;

  Player() {
    x = 0;
    y = 0;
  }

  @Override
  public void draw(Graphics g) {
    Color prevColor = g.getColor();
    g.setColor(Color.RED);
    g.fillRect(x, y, width, height);
    g.setColor(prevColor);
  }

  void sendKey(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == 87) { // W
      y -= 20;
    } else if (key == 65) {
      x -= 20;
    } else if (key == 83) {
      y += 20;
    } else if (key == 68) {
      x += 20;
    }
  }
}
