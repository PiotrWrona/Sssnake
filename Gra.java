import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Klasa odpowiedzialna za mechanikê gry
 * 
 * @version 1.1
 *
 */
public class Gra extends Canvas implements Runnable, KeyListener{


	static ArrayList<Waz> czesciWeza = new ArrayList<Waz>();
	
	public boolean ruchLewo, ruchGora, ruchDol;
	public boolean ruchPrawo = true;
	
	public int xGlowaTemp = 80;
	public int yGlowaTemp = 80;
	public boolean wazRosnie;
	
	public static boolean czySaSciany = true;
	private boolean start;
	public int punkty;
	public int czasGry;
	public static int szybkoscGry = 300;
	public boolean koniecGry = false;
	
	public static boolean jestJedzenie1, jestJedzenie2, jestJedzenie3, jestJedzenie4;	
	public static int x1,x2,x3,x4,y1,y2,y3,y4;

	
	
	public void run(){
		
		try {
			click(120,50);
		} catch (AWTException e1) {
			System.out.println("Autoklik nie zadzialal");
			e1.printStackTrace();
		}
		
		czesciWeza.add(new Waz(xGlowaTemp-80,yGlowaTemp));
		czesciWeza.add(new Waz(xGlowaTemp-60,yGlowaTemp));
		czesciWeza.add(new Waz(xGlowaTemp-40,yGlowaTemp));
		czesciWeza.add(new Waz(xGlowaTemp-20,yGlowaTemp));
		czesciWeza.add(new Waz(xGlowaTemp,yGlowaTemp));
				
		repaint();
		while(!start){} //---- start = "spacja"
		
		
		while(true){
			
			
			if (wazRosnie){
				if (ruchLewo){					
					xGlowaTemp-=20;
				}
				if (ruchPrawo){
					xGlowaTemp+=20;
				}
				if (ruchGora){
					yGlowaTemp-=20;
				}
				if (ruchDol){
					yGlowaTemp+=20;
				}
				czesciWeza.add(new Waz(xGlowaTemp,yGlowaTemp));
				wazRosnie = false;
			}				
			else{
				if (ruchLewo) xGlowaTemp-=20;
				if (ruchPrawo) xGlowaTemp+=20;
				if (ruchGora) yGlowaTemp-=20;
				if (ruchDol) yGlowaTemp+=20;
				czesciWeza.add(new Waz(xGlowaTemp,yGlowaTemp));
				czesciWeza.remove(0);
				
			}
			
			
			if (xGlowaTemp < 0 || xGlowaTemp >= Plansza.szerokoscPlanszy || yGlowaTemp < 0 || yGlowaTemp >= Plansza.wysokoscPlanszy){
				if (czySaSciany){
					JOptionPane.showMessageDialog(null, "Koniec Gry\nTwoj wynik: " + punkty);
					koniecGry = true;
				}
				else{
					if (ruchLewo) xGlowaTemp=Plansza.szerokoscPlanszy-20;
					if (ruchPrawo) xGlowaTemp=0;
					if (ruchGora) yGlowaTemp=Plansza.wysokoscPlanszy-20;
					if (ruchDol) yGlowaTemp=0;
					czesciWeza.add(new Waz(xGlowaTemp,yGlowaTemp));
					czesciWeza.remove(0);
				}
			}
			
			
			if (czasGry%40==0){
				Random r = new Random();
				x1 = r.nextInt(Plansza.szerokoscPlanszy/20) * 20;
				x2 = r.nextInt(Plansza.szerokoscPlanszy/20) * 20;
				x3 = r.nextInt(Plansza.szerokoscPlanszy/20) * 20;
				x4 = r.nextInt(Plansza.szerokoscPlanszy/20) * 20;
				y1 = r.nextInt(Plansza.wysokoscPlanszy/20) * 20;
				y2 = r.nextInt(Plansza.wysokoscPlanszy/20) * 20;
				y3 = r.nextInt(Plansza.wysokoscPlanszy/20) * 20;
				y4 = r.nextInt(Plansza.wysokoscPlanszy/20) * 20;
							
				jestJedzenie1 = true;
				jestJedzenie2 = true;
				jestJedzenie3 = true;
				jestJedzenie4 = true;
			}
			
			
			if (xGlowaTemp == x1 && yGlowaTemp == y1){
				punkty+=100;
				jestJedzenie1 = false;
				wazRosnie = true;
			}
			if (xGlowaTemp == x2 && yGlowaTemp == y2){
				punkty+=100;
				jestJedzenie2 = false;
				wazRosnie = true;
			}
			if (xGlowaTemp == x3 && yGlowaTemp == y3){
				punkty+=100;
				jestJedzenie3 = false;
				wazRosnie = true;
			}
			if (xGlowaTemp == x4 && yGlowaTemp == y4){
				punkty+=300;
				jestJedzenie4 = false;
				wazRosnie = true;
			}

			
			for(int i=0; i<czesciWeza.size()-1; i++){
				if(xGlowaTemp == czesciWeza.get(i).xGlowa && yGlowaTemp == czesciWeza.get(i).yGlowa ){
					JOptionPane.showMessageDialog(null, "Koniec Gry\nTwoj wynik: " + punkty);
					koniecGry = true;
				}
			}
			

			repaint();
			
			
			if (czasGry%30==0 && szybkoscGry>50) szybkoscGry-=5;
			try {
				Thread.sleep(szybkoscGry);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			czasGry++;
			if (koniecGry == true) break;
		}
		
	
	}

	
	public static void click(int x, int y) throws AWTException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);    
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
/**
 * Obs³uga Windows'owych klawiszy w grze (strza³ek oraz spacji)
 */
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == 37){
			ruchLewo = true;
			ruchPrawo = false;
			ruchGora = false;
			ruchDol = false;
		}
		if (key.getKeyCode() == 39){
			ruchLewo = false;
			ruchPrawo = true;
			ruchGora = false;
			ruchDol = false;
		}
		if (key.getKeyCode() == 38){
			ruchLewo = false;
			ruchPrawo = false;
			ruchGora = true;
			ruchDol = false;
		}
		if (key.getKeyCode() == 40){
			ruchLewo = false;
			ruchPrawo = false;
			ruchGora = false;
			ruchDol = true;
		}
		if (key.getKeyCode() == 32) start=true;	
	}
	public void keyReleased(KeyEvent key) {	
		if (key.getKeyCode() == 32) start=false;
	}
	public void keyTyped(KeyEvent key) {
	}
	
	
}
