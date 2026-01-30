package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable {
  Player player;
  Camera camera;
  Scene currentScene;

  static double deltaTime;

  public Canvas() {
    player = new Player();
    camera = new Camera(player, this);
    currentScene = new Level1(player, camera);
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLACK);
  }

  @Override
  public void run() {
    long lastTime = System.nanoTime();

    while (true) {
      long now = System.nanoTime();
      deltaTime = (now - lastTime) / 1_000_000_000.0;
      lastTime = now;

      update();
      repaint();

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void update() {
    currentScene.update();
    player.update();
    camera.update();
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    currentScene.draw(g);

    Toolkit.getDefaultToolkit().sync();
  }

  void keyPress(KeyEvent e) {
    player.sendKeyPress(e);
  }

  void keyReleas(KeyEvent e) {
    player.sendKeyReleas(e);
  }

  void mouseHandler(MouseEvent e) {
    System.out.println(e.getXOnScreen());
  }

}
