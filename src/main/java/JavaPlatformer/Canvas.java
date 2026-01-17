package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable {
  Player player;
  Camera camera;
  ArrayList<GameObject> gameObjects = new ArrayList<>();
  boolean running = true;

  public Canvas() {
    player = new Player();
    camera = new Camera(player, this);
    gameObjects.add(new Grass(1000, 1000));
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLACK);
  }

  @Override
  public void run() {
    long lastTime = System.nanoTime();

    while (running) {
      long now = System.nanoTime();
      double deltaTime = (now - lastTime) / 1_000_000_000.0;
      lastTime = now;

      update(deltaTime);
      repaint();

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void update(double deltaTime) {
    camera.update();
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    AffineTransform oldTransform = g2d.getTransform();

    g.translate(-camera.x, -camera.y);
    for (GameObject obj : gameObjects) {
      obj.draw(g2d);
    }

    player.draw(g2d);

    g2d.setTransform(oldTransform);

    // TODO

    Toolkit.getDefaultToolkit().sync();
  }

  void keyHandler(KeyEvent e) {
    player.sendKey(e);
  }

  void mouseHandler(MouseEvent e) {
    System.out.println(e.getXOnScreen());
    // TODO
  }

}
