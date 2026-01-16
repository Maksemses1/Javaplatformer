package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable {
  Player player;
  boolean running = true;

  public Canvas() {
    player = new Player();
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
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    player.draw(g);

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
