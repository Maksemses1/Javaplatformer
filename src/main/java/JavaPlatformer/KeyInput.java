package JavaPlatformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyInput extends KeyAdapter {
  public void keyPressed(KeyEvent e) {
    System.out.println(e.getKeyCode());
  }
}
