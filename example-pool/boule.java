import java.awt.*;
import javax.swing.*;
public class boule {
	public vecteur pos;  //position
	public vecteur v;  //vitesse
	public double r;  //rayon
	public boolean estRentree;
	public int numero;
	public Color couleur;
	//CONSTRUCTEUR
	public boule(double a, double b){
		pos = new vecteur(a,b);
		v=new vecteur();
		r = 20.0;
		estRentree=false;
		couleur = new Color((int) (Math.random()*200),(int) (Math.random()*200),(int) (Math.random()*200));
	}
	public boule(double a, double b,double c,double d){
		pos = new vecteur(a,b);
		v=new vecteur(c,d);
		r = 20.0;
		estRentree=false;
		couleur = new Color((int) (Math.random()*200),(int) (Math.random()*200),(int) (Math.random()*200));
	}
	public boule(int i,double a, double b,double c,double d,Color x){
		pos = new vecteur(a,b);
		v=new vecteur(c,d);
		r = 20.0;
		estRentree=false;
		couleur = x;
		numero=i;
	}
	public boule(vecteur pos) {
		this.pos=pos;
		v=new vecteur();
		r = 20.0;
		estRentree=false;
	}
	
	//ASSIGNATION DE VITESSE
	public void vitesse(double a, double b){
		this.v=new vecteur(a,b);
	}
	//DISTANCE ET VERIFICATION DE COLLISION
	public double distance(boule b){
		return Math.sqrt((this.pos.x-b.pos.x)*(this.pos.x-b.pos.x)+(this.pos.y-b.pos.y)*(this.pos.y-b.pos.y));
	}
	public double distance(int a,int b){// !!!!!!!!!!!!!!!!!!!!!!!!!
		return Math.sqrt((this.pos.x-a)*(this.pos.x-a)+(this.pos.y-b)*(this.pos.y-b));
	}
	public boolean estTouchee(boule b){
		return this.distance(b)*this.distance(b)<=4*r*r;
	}
	
	//CALCUL DE VITESSES FINALES
	public vecteur axe(boule b){ //vecteur de l'axe traversant les centres des 2 boules
		vecteur a = new vecteur(b.pos.x-this.pos.x,b.pos.y-this.pos.y);
		return a;
	}
	public vecteur vitesse1(boule b){ //vitesse finale de la boule cognee//composante suivant x
		vecteur v1 = new vecteur(this.v.composant(this.axe(b)),this.axe(b).arg,0);
		return v1;
	}
	public vecteur vitesse2(boule b){ //vitesse finale de la boule cognante//composante suivant y
		return this.v.moins(this.vitesse1(b));
	}
	
	//PAINT
	public void paint(Graphics g){
		g.setColor(couleur);
        g.fillOval((int)(Math.round(this.pos.x))-(int)(r),(int)(Math.round(this.pos.y))-(int)(r),2*(int)(r),2*(int)(r));
        g.setColor(Color.black);
        g.fillOval((int)(Math.round(this.pos.x))-1,(int)(Math.round(this.pos.y))-1,2,2);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Serif",Font.PLAIN,20));
        if(couleur==Color.black)
        g2.setColor(Color.white);
        g2.drawString(numero+"", Math.round(this.pos.x)-5, Math.round(this.pos.y)+7);
        
	}
	
	public String toString(){
		return "v = "+this.v.toString()+"p = "+this.pos.toString();
	}
	
	public void attenuation(double k){
		this.v=this.v.multiplier(k);
	}
	
	public void nouvCoord(){
		this.pos.x+=0;
		this.pos.y-=35;
	}
	
}


