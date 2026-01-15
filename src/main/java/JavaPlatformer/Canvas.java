package JavaPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable {

  double x = 0;
  double y = 0;

  int width = 50;
  int height = 50;

  double speedX = 150.0;
  double speedY = 150.0;

  boolean running = false;

  public Canvas() {
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLACK);
  }

  public void start() {
    running = true;
    new Thread(this).start();
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

  // Логика обновления теперь зависит от времени
  private void update(double deltaTime) {
    x += speedX * deltaTime;
    y += speedY * deltaTime;

    if (x + width >= getWidth() || x < 0) {
      speedX = -speedX;
    }
    if (y + height >= getHeight() || y < 0) {
      speedY = -speedY;
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.RED);
    g.fillRect((int) x, (int) y, width, height);

    Toolkit.getDefaultToolkit().sync();
  }
}
