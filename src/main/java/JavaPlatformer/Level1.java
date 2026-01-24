package JavaPlatformer;

class Level1 extends Scene {
  Level1(Player player, Camera camera) {
    super(player, camera);
    gameObjects.add(new Grass(-20, 400, 1000, 100));
    gameObjects.add(new Coin(90, 310));
    player.setScene(this);
  }

}
