public class vecteur {
	public double x;
	public double y;
	public double norme;
	public double arg; // en rad 
	public vecteur(double a, double b){
		x=a;
		y=b;
		norme=Math.pow(a*a+b*b,0.5);
		arg=Math.atan2(b,a);
	}
	public vecteur(){
		x=0;
		y=0;
		norme=0;
		arg=0;
	}
	public vecteur(double norme, double arg,int i){
		x=norme*Math.cos(arg);
		y=norme*Math.sin(arg);
		this.norme=norme;
		this.arg=arg;
	}
	
	public vecteur moins(vecteur v){ // vecteur p=this-v
		vecteur p=new vecteur(this.x-v.x,this.y-v.y);
		return p;
	}
	public vecteur plus(vecteur v){ // vecteur p=this+v
		vecteur p=new vecteur(this.x+v.x,this.y+v.y);
		return p;
	}
	public vecteur multiplier(double a){
		vecteur p=new vecteur(a*this.x,a*this.y);
		return p;
	}
	
	public double composant(vecteur v){
		return (this.x*v.x+this.y*v.y)/v.norme;
	}
	public String toString(){
		return "("+this.x+","+this.y+")";
	}

}
