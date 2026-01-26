package JavaPlatformer;

class Level1 extends Scene {
  Level1(Player player, Camera camera) {
    super(player, camera);
    gameObjects.add(new Grass(-20, 400, 500, 50));
    gameObjects.add(new Grass(30, 200, 100, 50));
    gameObjects.add(new Grass(600, 350, 300, 50));
    gameObjects.add(new Coin(90, 310));
    player.setScene(this);
  }

}
