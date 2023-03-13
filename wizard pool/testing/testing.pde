PImage img;

Lines lines = new Lines();
Stick stick = new Stick();
Ball ball1 = new Ball(1300,600);
Ball ball2 = new Ball(1100,850);
Ball ball3 = new Ball(700,750);
Ball ball4 = new Ball(350,400);
public Ball[] blist = { ball1, ball2, ball3, ball4};
public Ball[] potted = {};
Pocket pocket1 = new Pocket(266,293, 70);
Pocket pocket2 = new Pocket(1940,293, 70);
Pocket pocket3 = new Pocket(1940,1116, 70);
Pocket pocket4 = new Pocket(266,1116, 70);
Pocket pocket5 = new Pocket(1100,288, 50);
Pocket pocket6 = new Pocket(1100,1111, 50);
public Pocket[] plist = { pocket1, pocket2, pocket3, pocket4, pocket5, pocket6};

// runs once at start
void setup(){
  // set screen size (w,h)
  size(2200,1300);
  background(250,250,250);
  img = loadImage("table.jpeg");
  image(img,0,50,width,height);

  // set fill colour
  fill(40,150,100);
  
  //set outline colour
  stroke(250,250,50);
  
  fill(250,250,250);
  // draw circle (x,y,w,h)
  ellipse(900,650,44,44);

  // draw rectangle (x,y,w,h)
  //rect(300,300,250,350);
  ball1.setDirection(0.6,0.8);
  ball1.setVel(14);
}

//runs every frame in a loop
void draw(){
  // System.out.println(frameRate);
  //image(img,0,50,width,height);
  fill(50,200,70);
  rect(261,288,1935-261,1111-288);
  lines.draw();
  stick.draw(900, 650);
  fill(250,250,250);
  ellipse(900,650,44,44);
  for(Ball ball : blist){
    ball.draw();
  }
  for(Pocket pocket : plist){
    pocket.draw();
  }
  checkGameStatus();
}

int ballInPocket() {
  for(Pocket pocket : plist){
    for (int i = 1; i < blist.length; i++){
      float dx = b.getX() - pocket.x;
      float dy = b.getY() - pocket.y;
      float distance = sqrt(dx*dx + dy*dy);
      if (distance <= pocket.r +10){
        System.out.println("Ball in pocket");
        // return ball number
        return i;
      }
      else {
        return 0;
      }
    }
  }
  return 0;
}

void checkGameStatus() {
  // check if all balls are in pockets
  for (Ball ball : blist){
    // if return value is not null, ball is in pocket remove from blist
    blist.remove(ballInPocket());
  }
}

void mouseClicked(){
  System.out.println("Co-ords=" + mouseX +","+mouseY);
}
