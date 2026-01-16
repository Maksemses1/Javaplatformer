package JavaPlatformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyInput extends KeyAdapter {
  Canvas canvas;

  KeyInput(Canvas c) {
    canvas = c;
  }

  public void keyPressed(KeyEvent e) {
    canvas.keyHandler(e);
  }
}
