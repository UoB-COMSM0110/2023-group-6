import javax.swing.*;
import java.util.LinkedList; 
import java.awt.event.*;
import java.awt.*;

public class billard extends JFrame implements MouseMotionListener,MouseListener{
	JPanel background;
	table t;
	LinkedList<boule> b;
    public billard (){
	

		
		setSize(1365,767);//1276
        setLocation(0,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//CREER L'INTERFACE
       
        LinkedList<boule> b=new LinkedList<boule>();
        boule c0 = new boule(0,355,386,0,0,Color.white);
        boule c1 = new boule(1,924,386,0,0,Color.decode("#FDEC1C"));
        boule c2 = new boule(5,924+35.507,386-20.5,0,0,Color.decode("#D08600"));
        boule c3 = new boule(3,924+35.507,386+20.5,0,0,Color.decode("#008000"));
        boule c4 = new boule(4,924+35.507*2,386-20.5*2,0,0,Color.decode("#800080"));
        boule c5 = new boule(9,924+35.507*2,386,0,0,Color.decode("#00FFFF"));//9
        boule c6 = new boule(6,924+35.507*2,386+20.5*2,0,0,Color.decode("#926A07"));
        boule c7 = new boule(7,924+35.507*3,386-20.5,0,0,Color.black);
        boule c8 = new boule(8,924+35.507*3,386+20.5,0,0,Color.decode("#FF0021"));
        boule c9 = new boule(2,924+35.507*4,386,0,0,Color.blue);//2
        //CREER des BOULES
        b.add(c0);
        b.add(c1);
        b.add(c9);
        b.add(c3);
        b.add(c4);
        b.add(c2);
        b.add(c6);
        b.add(c7);
        b.add(c8);
        b.add(c5);
        for(boule bou : b){
			bou.nouvCoord();
		}
        background = new JPanel();
        t = new table(b);
		
		t.ee.addMouseMotionListener(this);
		t.ee.addMouseListener(this);
		
        
        

        background.setLayout(null);
        background.setBounds(0,0,1365,767);
        background.setBackground(Color.red);
        
        background.add(t);
        this.add(background);
		this.setVisible(true);
		
		Thread seq = new Thread(t); //INITIER LA SEQUENCE
		seq.start();
	}
		
				
			

			
			public void mouseDragged(MouseEvent e) {
				t.x1=e.getX();
				t.y1=e.getY();
			}
			public void mouseMoved(MouseEvent e) {
					t.x1=e.getX();
					t.y1=e.getY();
					System.out.println("("+t.x1+","+t.y1+")");
			}
			
			public void mousePressed(MouseEvent e) {
				if(t.clic==false){
					t.instantClic=System.currentTimeMillis();
					t.clic=true;
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				t.instantRelache=System.currentTimeMillis();
				if(!bouleBouge(t.b)){
					t.duree=t.instantRelache-t.instantClic;
					t.bouleSurTableAvant.clear();
					for(int i=0;i<t.b.size();i++){
						if(t.b.get(i).estRentree==false)
						t.bouleSurTableAvant.add(i);
					}
				}
				t.clic=false;
				t.x1=e.getX();
				t.y1=e.getY();
				
				if(!bouleBouge(t.b)&&!t.shouldRestart){
					t.b.get(0).v=new vecteur(2.5*t.valeurDeForce,Math.atan2(e.getY()-35-t.b.get(0).pos.y,e.getX()+t.x0-t.b.get(0).pos.x),1);
					t.nbCollision=0;
					
					t.joueur=!t.joueur;
				}
			}
			public void mouseClicked(MouseEvent e){
				t.x1=e.getX();
				t.y1=e.getY();
				if(bouleBouge(t.b)==false){
				if(t.shouldRestart&&(e.getClickCount() == 2)&&t.x1>(71+20)&&t.y1>(98-20)&&t.y1<(671-20)&&t.x1<355){
					t.b.get(0).pos.x=t.x1;
					t.b.get(0).pos.y=t.y1-35;
					t.b.get(0).v=new vecteur();
					t.shouldRestart=false;
					t.premiereToucheDeBlanc=-1;
					t.nbCollision=1;
				}
				}
				if(t.gagne){
					this.dispose();
					new billard();
				}
			}
			
			public void mouseEntered(MouseEvent e){
				
			}
			public void mouseExited(MouseEvent e){
			
			}
		
		public static void main (String[] args) {
			new billard();
		}
		
	
		public static boolean bouleBouge(LinkedList<boule> b){
		boolean rouge=false;
		for(boule bille : b){
			if(bille.v.norme!=0){
				rouge=true;
				break;
			}
		}
		return rouge;
	}
	
}



