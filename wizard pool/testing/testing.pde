PImage img;

Lines lines = new Lines();
Stick stick = new Stick();
Ball ball1 = new Ball(1300,600);
Ball ball2 = new Ball(1100,850);
Ball ball3 = new Ball(700,750);
Ball ball4 = new Ball(350,400);
public Ball[] blist = { ball1, ball2, ball3, ball4};

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
  ball1.setVel(7.0);
}

//runs every frame in a loop
void draw(){
  System.out.println(frameRate);
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
}

void mouseClicked(){
  System.out.println("Co-ords=" + mouseX +","+mouseY);
}
