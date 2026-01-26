package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

class Player extends GameObject {
  boolean canJump = false;
  boolean keyW = false;
  boolean keyA = false;
  boolean keyS = false;
  boolean keyD = false;

  ArrayList<CollidePOJO> touchedColliders;

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
    for (GameObject obj : scene.getGameObjectList()) {
      if (obj == this)
        continue;

      boolean horizontalOverlap = (x >= obj.x - width && x <= obj.x + obj.width);
      boolean verticalOverlap = (potentialY + height > obj.y && y < obj.y + obj.height);

      if (horizontalOverlap && verticalOverlap) {
        if (Math.abs(obj.y - (potentialY + height)) <= 3) {
          touchedColliders.add(new CollidePOJO(obj, "top"));
        }
        if (Math.abs(y - (obj.y + obj.height)) <= 3) {
          touchedColliders.add(new CollidePOJO(obj, "bottom"));
        }
        touchedColliders.add(new CollidePOJO(obj, ""));
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
