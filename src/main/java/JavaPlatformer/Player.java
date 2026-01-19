package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class Player extends GameObject {
  final int width = 20;
  final int height = 60;

  boolean keyW = false;
  boolean keyA = false;
  boolean keyS = false;
  boolean keyD = false;

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

  void update() {
    x += (keyA ? -1 : (keyD ? 1 : 0)) * 300 * Canvas.deltaTime;
  }

  void sendKeyPress(KeyEvent e) {
    int key = e.getKeyCode();
    // AD
    if (key == 65) {
      keyA = true;
    } else if (key == 68) {
      keyD = true;
    }
  }

  void sendKeyReleas(KeyEvent e) {
    int key = e.getKeyCode();
    // AD
    if (key == 65) {
      keyA = false;
    } else if (key == 68) {
      keyD = false;
    }
  }
}
