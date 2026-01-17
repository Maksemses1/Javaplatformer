package JavaPlatformer;

import javax.swing.JPanel;

class Camera extends GameObject {
  JPanel canvas;
  Player player;

  Camera(Player player, JPanel canvas) {
    this.player = player;
    this.canvas = canvas;
  }

  void update() {
    float targetX = player.x - canvas.getWidth() / 2;
    float targetY = player.y - canvas.getHeight() / 2;

    x += (targetX - x) * 0.05f;
    y += (targetY - y) * 0.05f;
  }
}
