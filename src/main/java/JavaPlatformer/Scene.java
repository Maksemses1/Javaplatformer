package JavaPlatformer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

abstract class Scene {
  ArrayList<GameObject> gameObjects = new ArrayList<>();
  Player player;
  Camera camera;

  Scene(Player player, Camera camera) {
    this.player = player;
    this.camera = camera;
  }

  void removeObject(GameObject obj) {
    gameObjects.remove(obj);
  }

  ArrayList<GameObject> getGameObjectList() {
    return gameObjects;
  }

  void update() {
    for (GameObject obj : gameObjects) {
      obj.update();
    }
  }

  void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    AffineTransform oldTransform = g2d.getTransform();

    g.translate(-camera.x, -camera.y);
    for (GameObject obj : gameObjects) {
      obj.draw(g2d);
    }

    player.draw(g2d);
    g2d.setTransform(oldTransform);
    player.hud.draw(g2d);
  }
}
