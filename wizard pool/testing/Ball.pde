class Ball{
  private int size = 44;
  // co-ords
  private int x;
  private int y;
  // direction of travel
  private float i;
  private float j;
  // velocity
  private float vel;
  // -1 = unassigned, 0 = white
  private int owner;
  //colour
  private int[] col = {150,150,150};
  
  // constructor
  Ball(int xstart, int ystart){
    x = xstart;
    y = ystart;
    i = 0; j = 0; vel = 0; owner = -1;
  }
  
  void draw(){
    this.move();
    fill(col[0],col[1],col[2]);
    ellipse(x,y,size,size);
  }
  
  void move(){
    checkCollisions();
    x += (int) round(i*vel);
    y += (int) round(j*vel);
  }
  
  void checkCollisions(){
    //bottom
    if(y + (int) round(j*vel) + size/2 > 1086){
      j = -j;
    }
    //right
    if(x + (int) round(i*vel) + size/2 > 1907){
      i = -i;
    }
    //top
    if(y + (int) round(j*vel) - size/2 < 313){
      j = -j;
    }
    //left
    if(x + (int) round(i*vel) - size/2 < 289){
      i = -i;
    }
    //balls
    for(Ball ball : blist){
      //(a,b) vector from home ball's next pos to foreign ball
      int a = ball.getX()-(this.getX()+(int) round(i*vel));
      int b = ball.getY()-(this.getY()+(int) round(j*vel));
      float dist = sqrt(a*a+b*b);
      if( dist < size && ball != this){
        //convert a,b to unit vector for direction of hit ball
        // NEEDS CHANGING FOR WHEN BOTH BALLS ARE MOVING
        ball.setDirection(a/dist,b/dist);
        ball.setVel(this.getVel()*0.75);
      }
    }
  }
  
  
  // GET/SET FUNCS
  void setVel(float newVel){ vel = newVel; }
  float getVel(){ return vel; }
  void setDirection(float newi, float newj){ i=newi; j=newj; }
  void setPos(int newx, int newy) { x = newx; y= newy; }
  int getX(){ return x; }
  int getY(){ return y; }
  void setCol(int r, int g, int b){ col[0]=r; col[1]=g; col[2]=b; }
  
  
}
  
