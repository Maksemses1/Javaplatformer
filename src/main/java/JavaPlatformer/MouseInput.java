package JavaPlatformer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseInput extends MouseAdapter {
  Canvas canvas;

  MouseInput(Canvas c) {
    canvas = c;
  }

  public void mousePressed(MouseEvent e) {
    canvas.mouseHandler(e);
  }
}
