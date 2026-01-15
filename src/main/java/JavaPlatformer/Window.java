package JavaPlatformer;

import javax.swing.JFrame;

class Window extends JFrame {
  Canvas canvas;

  Window() {
    setBounds(500, 100, 500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    canvas = new Canvas();
    add(canvas);
  }

  void start() {
    setVisible(true);
    canvas.start();
  }
}
