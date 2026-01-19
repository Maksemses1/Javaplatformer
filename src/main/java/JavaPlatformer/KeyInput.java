package JavaPlatformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyInput extends KeyAdapter {
  Canvas canvas;

  KeyInput(Canvas c) {
    canvas = c;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    canvas.keyPress(e);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    canvas.keyReleas(e);
  }
}
