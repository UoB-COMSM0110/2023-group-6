import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sun.audio.*; 
public class table extends JPanel implements Runnable{
		LinkedList<boule> b=new LinkedList<boule>();
		ArrayList<Integer> bouleSurTableAvant= new ArrayList<Integer>();
		ArrayList<Integer> bouleSurTableApres= new ArrayList<Integer>();
		int nbCollision;
		JLabel ee;
		int x1,y1;
		int x0,y0;
		double t;
		int t0;
		double a;
		boolean[][] listeCollision;
		double[][] listeT;
		long instantClic;
		long instantRelache;
		long duree;
		boolean clic;
		int premiereToucheDeBlanc;
		boolean shouldRestart;
		int valeurDeForce;
		boolean joueur;
		int nbFauteJoueur1;
		int nbFauteJoueur2;
		boolean gagne;
		public table (LinkedList<boule> b){ //CREER LA TABLE 
			clic=false;
			x0=0;
			y0=-35;
			this.b=b;
			this.setLayout(null);
			this.setBounds(0,0,1392,767);
			this.setBackground(Color.white);
			ee = new JLabel();
			ee.setIcon(new ImageIcon("./table.png"));
			ee.setBounds(x0,y0,1392,767);
			this.add(ee);
			t = 10;
			t0 = 3;
			a = 0.35;
			premiereToucheDeBlanc=-1;
			shouldRestart=false;
			nbCollision=1;
			joueur=false;
			gagne=false;
			for(int i=0;i<b.size();i++){
				if(b.get(i).estRentree==false)
				bouleSurTableAvant.add(i);
			}
		}
		
		public void restart(){
			shouldRestart=true;
			b.get(0).estRentree=false;
			if(joueur)
			nbFauteJoueur1++;
			else
			nbFauteJoueur2++;
		}
		public void paint(Graphics g) {//DESSINER LES BOULES
			super.paint(g);
			
			for (int i = 0;i<b.size();i++){
				b.get(i).paint(g);
			}
			
			if(clic&&!bouleBouge(b)&&!shouldRestart){
				duree=System.currentTimeMillis()-this.instantClic;
			
				g.setColor(Color.green);//BARRE DE FORCE PROPORTIONNELLE À LA VITESSE INITIALE FOURNIE
				valeurDeForce=valeurDeForcetan(duree);
				g.fillRect(1276,0,89,valeurDeForce);
			}
			
			int xB=(int) b.get(0).pos.x;
			int yB=(int) b.get(0).pos.y;
			
			
			if(!shouldRestart&&!bouleBouge(b)){
				Graphics2D g2d = (Graphics2D) g.create();
				Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
				g2d.setStroke(dashed);
				g2d.setColor(Color.black);
				g2d.drawLine(x1+x0, y1-35, xB, yB);
				g2d.drawOval(x1+x0-20,y1-35-20,40,40);
				g2d.dispose();
				duree=0;
			}
			if(shouldRestart&&!bouleBouge(b)){
				g.setColor(Color.white);
				g.drawOval(x1+x0-20,y1-20-35,40,40);
			}
			Graphics2D gkd = (Graphics2D) g.create();
			gkd.setColor(Color.white);
			if(joueur==false&&!bouleBouge(b)){
				if(b.get(5).estRentree||(nbFauteJoueur2>=3)){
					gkd.drawString("Joueur 1 gagne!", 100,100);
					gagne=true;
				}			
				else
				gkd.drawString("Joueur 1 joue", 100,100);
				
			} 
			if(joueur==true&&!bouleBouge(b)){
				if(b.get(9).estRentree||(nbFauteJoueur1>=3)){
					gkd.drawString("Joueur 2 gagne!", 100,100);		
					gagne=true;
				}	
				else
				gkd.drawString("Joueur 2 joue", 100,100);				
			}
			gkd.drawString("Joueur 1 : "+nbFauteJoueur1+" Faute(s)", 100,120);
			gkd.drawString("Joueur 2 : "+nbFauteJoueur2+" Faute(s)", 100,140);
		}
	


		public void run() { // SEQUENCE D'ANIMATION
			
			
			while(!gagne){
				
					
				
				
					
					
				
				//COLLISION
				listeCollision=creerListeCollision(b);
				if(estTouchee(listeCollision)){
					for(int i=0;i<b.size();i++){
						if(listeCollision[0][i]&&premiereToucheDeBlanc==-1){
							premiereToucheDeBlanc=i;
							nbCollision++;
						}
					}
					collision(b,listeCollision);
				}
				
				
				ArrayList<Integer> bouleSurTableApres= new ArrayList<Integer>();
				for(int i=0;i<b.size();i++){
					if(b.get(i).estRentree==false)
					bouleSurTableApres.add(i);
				}
				
				//REGLES
				if(!shouldRestart){
					if((bouleSurTableApres.get(0)!=0)&&!bouleBouge(b)){
						this.restart();
					} else {
						if(premiereToucheDeBlanc!=bouleSurTableAvant.get(1)&&premiereToucheDeBlanc!=-1){
							this.restart();
							} else {
								if(nbCollision==0&&bouleBouge(b)==false)
								this.restart();
							
								}
							}
						}
					
				
				
				
				vecteur[] vx0=new vecteur[b.size()];
				for (int i = 0;i<b.size();i++){						
					
					b.get(i).pos.x+=b.get(i).v.x*t0/1000;//DEPLACEMENT	
					b.get(i).pos.y+=b.get(i).v.y*t0/1000;	
					
					vx0[i]=b.get(i).v;                 
					if(b.get(i).v.norme!=0){//ACCELERATION
						b.get(i).v=b.get(i).v.moins(b.get(i).v.multiplier(a/b.get(i).v.norme));
						b.get(i).v.x = b.get(i).v.x-a*Math.cos(b.get(i).v.arg);
						b.get(i).v.y = b.get(i).v.y-a*Math.sin(b.get(i).v.arg);
					}
					if(b.get(i).v.composant(vx0[i])<=0)
					b.get(i).v= new vecteur();                 
					//REBONDISSEMENT
					if(b.get(i).pos.x<=(71+x0+20)&&b.get(i).v.x<0&&b.get(i).pos.y>(149-35)&&b.get(i).pos.y<(624-35))
					{b.get(i).v.x=(-1)*b.get(i).v.x;
					/*try {  //effets sonores
                        AudioStream audioStream = new AudioStream(getClass().getResourceAsStream("bounce.wav"));
                        AudioPlayer.player.start(audioStream);
						} catch (Exception ex) {
                        Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
					}*/
					}
					if(b.get(i).pos.x>=(1209+x0-20)&&b.get(i).v.x>0&&b.get(i).pos.y>(149-35)&&b.get(i).pos.y<(624-35))
					{b.get(i).v.x=(-1)*b.get(i).v.x;
					/*try {  //effets sonores
                        AudioStream audioStream = new AudioStream(getClass().getResourceAsStream("bounce.wav"));
                        AudioPlayer.player.start(audioStream);
					} catch (Exception ex) {
                        Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
					}*/
					
					}
					if(b.get(i).pos.y<=(98-35+20)&&b.get(i).v.y<0&&((b.get(i).pos.x>(103+x0)&&b.get(i).pos.x<(590+x0))||(b.get(i).pos.x>(690+x0)&&b.get(i).pos.x<(1176+x0))))
					{b.get(i).v.y=(-1)*b.get(i).v.y;
					/*try {  //effets sonores
                        AudioStream audioStream = new AudioStream(getClass().getResourceAsStream("bounce.wav"));
                        AudioPlayer.player.start(audioStream);
					} catch (Exception ex) {
                        Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
					}*/
					}
					if(b.get(i).pos.y>=(671-35-20)&&b.get(i).v.y>0&&((b.get(i).pos.x>(103+x0)&&b.get(i).pos.x<(590+x0))||(b.get(i).pos.x>(690+x0)&&b.get(i).pos.x<(1176+x0))))
					{b.get(i).v.y=(-1)*b.get(i).v.y;
					/*try {  //effets sonores
                        AudioStream audioStream = new AudioStream(getClass().getResourceAsStream("bounce.wav"));
                        AudioPlayer.player.start(audioStream);
						} catch (Exception ex) {
                        Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
					}*/
					}
						
					//ARRONDIS
					int[] vv={51,110,51,110,1170,1231,1231,1170,590,690,590,690};
					int[] ww={149,78,624,691,78,149,624,691,78,78,691,691};
					for(int j=0;j<12;j++){
						if(b.get(i).distance(vv[j]+x0,ww[j]-35)<40){
							vecteur newv = new vecteur(b.get(i).v.norme,Math.PI-b.get(i).v.arg+2*Math.atan2(b.get(i).pos.y-(ww[j]-35),b.get(i).pos.x-(vv[j]+x0)),1);
							b.get(i).v=newv;
						}
					}
					//TROUS
					int[] xx={60,1220,60,1220,640,640};
					int[] yy={100,100,670,670,80,690};
					for(int j=0;j<6;j++){
						if(b.get(i).distance(xx[j]+x0,yy[j]-35)<30){
							b.get(i).pos.x=10000;
							b.get(i).v=new vecteur();
							b.get(i).estRentree=true;
						}
					}
				}
					if(bouleBouge(b)==false){
						premiereToucheDeBlanc=-1;
						
					}
					
					
				
				
				
				try {
					Thread.sleep(t0);// LE TEMPS DE RAFRAICHIR
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}
		
	
	public static void collision(LinkedList<boule> b,boolean[][] liste){ //merci supprimer static pour activer la fonctionnalité audio
		for (int i = 0;i<b.size();i++){
			for (int j = i+1;j<b.size();j++){
				if(liste[i][j]){
					double d =b.get(i).r-b.get(i).distance(b.get(j))/2;
					vecteur axe1 = b.get(i).axe(b.get(j));
					double rua = axe1.norme;
					axe1 = axe1.multiplier(1/rua);
					vecteur d1 = axe1.multiplier(d);
					
					b.get(i).pos.x-=d1.x;
					b.get(i).pos.y-=d1.y;
					b.get(j).pos.x+=d1.x;
					b.get(j).pos.y+=d1.y;
					
					vecteur v1x=b.get(i).vitesse1(b.get(j));
					vecteur v1y=b.get(i).vitesse2(b.get(j));
					vecteur v2x=b.get(j).vitesse1(b.get(i));
					vecteur v2y=b.get(j).vitesse2(b.get(i));
					b.get(i).v=v1y.plus(v2x).plus(v1x.multiplier(0.05));
					b.get(j).v=v2y.plus(v1x).plus(v2x.multiplier(0.05));
					b.get(i).attenuation(Math.sqrt(0.9));
					b.get(j).attenuation(Math.sqrt(0.9));
					/*try {
                        AudioStream audioStream = new AudioStream(getClass().getResourceAsStream("bounce.wav"));
                        AudioPlayer.player.start(audioStream);
					} catch (Exception ex) {
                        Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
					}*/
				}
			}
		}
	}
	public static boolean[][] creerListeCollision(LinkedList<boule> b){
		boolean[][] listeCollision1 = new boolean[b.size()][b.size()];
		for (int i = 0;i<b.size();i++){
			for (int j = i+1;j<b.size();j++){
				if(i!=j)
				listeCollision1[i][j]=b.get(i).estTouchee(b.get(j));
			}
		}
		return listeCollision1;
	}
	

	public static boolean estTouchee(boolean[][] liste){
		boolean booo = false;
		for (int i = 0;i<liste.length;i++){
			for (int j = i+1;j<liste[0].length;j++){
				if(liste[i][j])
				booo=true;
			}	
		}
		return booo;
	}
	
	public static boolean bouleBouge(LinkedList<boule> b){
		boolean state=false;
		for(boule bille : b){
			if(bille.v.norme!=0){
				state=true;
				break;
			}
		}
		return state;
	}
	public static int valeurDeForcetan(long duree){
		int d=(int)(duree*0.5);
		if((d/767)%2==0)
		d=(d%767); 
		if((d/767)%2==1)
		d=(767-(d%767));
		return d;
	}

}
