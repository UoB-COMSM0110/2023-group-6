PImage img;

Lines lines = new Lines();
Stick stick = new Stick();

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
}

void mouseClicked(){
  System.out.println("Co-ords=" + mouseX +","+mouseY);
}
