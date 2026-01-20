package JavaPlatformer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class HUDElement extends GameObject {
  String value;
  Color color = Color.WHITE;

  HUDElement(int x, int y, String startValue) {
    this.x = x;
    this.y = y;
    this.value = startValue;
  }

  @Override
  void draw(Graphics g) {
    g.setColor(color);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
    g.drawString(value, x, y);
  }
}
