package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

class Player extends GameObject {
  boolean canJump = false;
  boolean keyW = false;
  boolean keyA = false;
  boolean keyS = false;
  boolean keyD = false;

  int jumpImpulse = 0;

  Player() {
    x = 0;
    y = 0;
    width = 20;
    height = 60;
    isCollide = true;
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
    gravity();
    jump();
  }

  void jump() {
    if (jumpImpulse > 0) {
      jumpImpulse -= 5;
      canJump = false;
    }
    y = (int) (y - (jumpImpulse * Canvas.deltaTime));
  }

  void gravity() {
    int potentialY = (int) (y + (300 * Canvas.deltaTime));
    boolean down = true;
    for (GameObject obj : Canvas.gameObjects) {
      if (obj.isCollide)
        if ((x >= obj.x - width && x <= obj.x + obj.width)
            &&
            potentialY + height > obj.y && y < obj.y) {
          down = false;
          break;
        }
    }
    if (down)
      y = potentialY;
    else
      canJump = true;
  }

  void sendKeyPress(KeyEvent e) {
    int key = e.getKeyCode();
    // AD
    if (key == 65) {
      keyA = true;
    } else if (key == 68) {
      keyD = true;
    } else if (key == 32) {
      if (canJump) {
        jumpImpulse = 600;
      }
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
