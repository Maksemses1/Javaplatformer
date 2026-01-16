package JavaPlatformer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseInput extends MouseAdapter {
  public void mousePressed(MouseEvent e) {
    System.out.println(e.getWhen());
  }
}
