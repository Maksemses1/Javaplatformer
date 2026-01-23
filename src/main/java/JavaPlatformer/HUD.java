package JavaPlatformer;

import java.awt.Graphics;
import java.util.ArrayList;

class HUD {

  ArrayList<HUDElement> elements = new ArrayList<>();
  HUDElement coins;

  HUD() {
    coins = new HUDElement(50, 50, "Coins: 0");
    elements.add(coins);
  }

  void draw(Graphics g) {
    for (HUDElement element : elements) {
      element.draw(g);
    }
  }

  void setCoins(int coins) {
    this.coins.setValue("Coins: " + coins);
  }
}
