
class Pocket {
    public float x, y;
    public float r;


  Pocket(float x, float y, float r) {
      this.x = x;
      this.y = y;
      this.r = r;
  }

  void draw() {
      fill(300, 100, 100);
      ellipse(x, y, r*2, r*2);
  }
}
