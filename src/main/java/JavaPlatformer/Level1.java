package JavaPlatformer;

class Level1 extends Scene {
  Level1(Player player, Camera camera) {
    super(player, camera);
    gameObjects.add(new Grass(-20, 400, 500, 50));
    gameObjects.add(new Grass(300, 300, 100, 50) {
      int move = 0;
      boolean right = true;
      int startX = x;

      void update() {
        if (right)
          x = startX + move++;
        else
          x = startX + move--;
        if (move == 100 || move == 0)
          right = !right;
      }
    });

    gameObjects.add(new Grass(600, 350, 300, 50));
    gameObjects.add(new Coin(90, 310));
    player.setScene(this);
  }

}
