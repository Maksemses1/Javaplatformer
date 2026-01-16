package JavaPlatformer;

import javax.swing.JFrame;

class Window extends JFrame {
  Canvas canvas;
  KeyInput kInput;
  MouseInput mInput;

  Window() {
    setBounds(500, 100, 500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    canvas = new Canvas();
    kInput = new KeyInput(canvas);
    mInput = new MouseInput(canvas);
    addKeyListener(kInput);
    addMouseListener(mInput);
    add(canvas);
    setFocusable(true);
    setFocusableWindowState(true);
  }

  void start() {
    new Thread(canvas).start();
    setVisible(true);
  }
}
