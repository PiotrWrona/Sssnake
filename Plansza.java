import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Tworzy graficzny interfejs gry
 * 
 * @version 1.1
 * 
 */


public class Plansza extends Gra{

	public Image zdjecieObrazu;
	public Graphics grafikaGry;
	
	public final static int szerokoscPlanszy = 400;
	public final static int wysokoscPlanszy = 300;

	
	/**
	 * Konstruuje ekran gry
	 */
	public Plansza(){
		
		JFrame okno = new JFrame("Sssnake");
		setBounds(0,0,szerokoscPlanszy,wysokoscPlanszy);
		addKeyListener(this);
		
		JPanel panel = (JPanel) okno.getContentPane();
		panel.setPreferredSize(new Dimension(szerokoscPlanszy,wysokoscPlanszy));
		panel.setLayout(null);
		panel.add(this);

		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setResizable(false);
		okno.setBounds(100,20,szerokoscPlanszy+6,wysokoscPlanszy+28);
		okno.setVisible(true);
		
		zdjecieObrazu = createImage (szerokoscPlanszy,wysokoscPlanszy);
		grafikaGry = zdjecieObrazu.getGraphics();
	}
	
	/**
	 * Rysyje elementy na ekranie
	 */
	public void paint(Graphics grafika){		
		
		grafikaGry.setColor(Color.black);
		grafikaGry.fillRect(0,0,szerokoscPlanszy,wysokoscPlanszy);
		
		grafikaGry.setColor(Color.green);
		grafikaGry.fillOval(czesciWeza.get(czesciWeza.size()-1).xGlowa,czesciWeza.get(czesciWeza.size()-1).yGlowa,20,20);
		
		grafikaGry.setColor(Color.yellow);
		for (int i=1; i < czesciWeza.size()-1; i++){
			int x = czesciWeza.get(i).xGlowa;
			int y = czesciWeza.get(i).yGlowa;
			grafikaGry.fillOval(x,y,20,20);
		}
	
		grafikaGry.setColor(Color.white);
		if (Gra.jestJedzenie1){
			grafikaGry.fillOval(Gra.x1,Gra.y1,20,20);
		}	
		if (Gra.jestJedzenie2){
			grafikaGry.fillOval(Gra.x2,Gra.y2,20,20);
		}
		if (Gra.jestJedzenie3){
			grafikaGry.fillOval(Gra.x3,Gra.y3,20,20);
		}	
		grafikaGry.setColor(Color.red);
		if (Gra.jestJedzenie4){
			grafikaGry.fillOval(Gra.x4,Gra.y4,20,20);
		}
				
		grafika.drawImage(zdjecieObrazu,0,0,this);
		
	}
	
	public void update(Graphics grafika){
		paint(grafika);
	}
	
}
