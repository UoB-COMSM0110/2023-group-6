class Stick{
  int resting_distance = 40;
  int stick_length = 200;
 void draw(int x, int y){
    float i = mouseX -x;
    float j = mouseY -y;
    float magnitude = sqrt(i*i+j*j);
    i /= magnitude;
    j /= magnitude;
    fill(30,30,30);
    strokeWeight(16);
    line((int) x+i*resting_distance, (int) y+j*resting_distance, (int) x+i*resting_distance+i*stick_length, (int) y+j*resting_distance+j*stick_length);
    strokeWeight(1);
 }
}
