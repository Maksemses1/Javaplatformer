package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.List;

class Player extends GameObject {
  boolean canJump = false;
  boolean keyW = false;
  boolean keyA = false;
  boolean keyS = false;
  boolean keyD = false;

  List<CollidePOJO> touchedColliders;

  Scene scene;

  int coinsValue = 0;

  HUD hud;

  int jumpImpulse = 0;

  Player() {
    x = 0;
    y = 0;
    width = 20;
    height = 60;
    hud = new HUD();
    hud.setCoins(coinsValue);
  }

  @Override
  public void draw(Graphics g) {
    Color prevColor = g.getColor();
    g.setColor(Color.RED);
    g.fillRect(x, y, width, height);
    g.setColor(prevColor);
  }

  void update() {
    checkColliders();
    gravity();
    jump();
    move();
    onCollide();
  }

  void jump() {
    List<CollidePOJO> platforms = filterColliders("platform");
    for (CollidePOJO platform : platforms) {
      if (platform.side == "bottom") {
        jumpImpulse = 0;
      }
    }
    if (jumpImpulse > 0) {
      jumpImpulse -= 5;
      canJump = false;
    }
    y = (int) (y - (jumpImpulse * Canvas.deltaTime));
  }

  int i = 0;

  void gravity() {
    int potentialY = (int) (y + (300 * Canvas.deltaTime));
    boolean down = true;
    List<CollidePOJO> platforms = filterColliders("platform");
    for (CollidePOJO platform : platforms) {
      if (platform.side == "top") {
        y = platform.gameObject.y - height;
        down = false;
        break;
      }
    }
    if (!down) {
      canJump = true;
    } else {
      y = potentialY;
      canJump = false;
    }
  }

  void move() {
    int potentialX = (int) (x + ((keyA ? -1 : (keyD ? 1 : 0)) * 300 * Canvas.deltaTime));

    List<CollidePOJO> platforms = filterColliders("platform");
    for (CollidePOJO platform : platforms) {
      if (platform.side == "left" && potentialX >= x) {
        x = platform.gameObject.x - width;
      } else if (platform.side == "right" && potentialX <= x) {
        x = platform.gameObject.x + platform.gameObject.width;
      } else {
        x = potentialX;
      }
    }
    if (platforms.isEmpty())
      x = potentialX;

  }

  void onCollide() {
    List<CollidePOJO> coins = filterColliders("coin");
    if (coins != null) {
      for (CollidePOJO collidePOJO : coins) {

        scene.removeObject(collidePOJO.gameObject);
        hud.setCoins(++coinsValue);
      }
    }
  }

  void checkColliders() {
    touchedColliders = new ArrayList<>();
    int potentialY = (int) (y + (300 * Canvas.deltaTime));
    int potentialX = (int) (x + ((keyA ? -1 : (keyD ? 1 : 0)) * 300 * Canvas.deltaTime));
    for (GameObject obj : scene.getGameObjectList()) {
      if (obj == this)
        continue;

      boolean horizontalOverlap = (x >= obj.x - width && x <= obj.x + obj.width);
      boolean verticalOverlap = (potentialY + height > obj.y && y < obj.y + obj.height);

      int top = Math.abs(obj.y - (potentialY + height));
      int bottom = Math.abs(y - (obj.y + obj.height));
      int left = Math.abs(obj.x - (potentialX + width));
      int right = Math.abs((obj.x + obj.width) - x);

      if (horizontalOverlap && verticalOverlap) {
        if (top <= bottom && top <= left && top <= right)
          touchedColliders.add(new CollidePOJO(obj, "top"));
        else if (bottom <= top && bottom <= left && bottom <= right)
          touchedColliders.add(new CollidePOJO(obj, "botton"));
        else if (left <= bottom && left <= top && left <= right)
          touchedColliders.add(new CollidePOJO(obj, "left"));
        else if (right <= bottom && right <= left && right <= top)
          touchedColliders.add(new CollidePOJO(obj, "right"));
      }
    }
  }

  List<CollidePOJO> filterColliders(String tag) {
    return touchedColliders
        .stream()
        .filter(obj -> obj.gameObject.hasTag(tag))
        .collect(Collectors.toList());

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
