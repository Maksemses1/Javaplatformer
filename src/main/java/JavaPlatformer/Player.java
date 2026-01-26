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

  Scene scene;

  int coins = 0;

  HUD hud;

  int jumpImpulse = 0;

  Player() {
    x = 0;
    y = 0;
    width = 20;
    height = 60;
    hud = new HUD();
    hud.setCoins(coins);
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

    GameObject platform = checkCollision("platform");
    if (platform != null) {
      y = platform.y - height;
      canJump = true;
    } else {
      y = potentialY;
      canJump = false;
    }

    GameObject coin = checkCollision("coin");

    if (coin != null) {
      scene.removeObject(coin);
      hud.setCoins(++coins);
    }
  }

  GameObject checkCollision(String colliderTag) {
    int potentialY = (int) (y + (300 * Canvas.deltaTime));
    for (GameObject obj : scene.getGameObjectList()) {
      if (obj == this)
        continue;

      if (obj.hasTag(colliderTag)) {

        boolean horizontalOverlap = (x >= obj.x - width && x <= obj.x + obj.width);
        boolean verticalOverlap = (potentialY + height > obj.y && y < obj.y + obj.height);

        if (horizontalOverlap && verticalOverlap) {
          return obj;
        }
      }
    }
    return null;
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

  void setScene(Scene scene) {
    this.scene = scene;
  }
}
