package JavaPlatformer;

class Trigger extends GameObject {
  Trigger(int x, int y, int height, int width) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    colliders.add("trigger");
  }
}
