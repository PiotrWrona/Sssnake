import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * Tworzy ekran powitalny w grze, gdzie mo¿emy wybraæ:
 * <ul>
 *   <li>poziom trudnosci gry (szybkosc gry)</li>
 *   <li>oraz okresliæ, czy uderzenie w œcianê koñczy grê.</li>
 * </ul>
 * 
 * @version 1.0
 *
 */
public class Powitalny extends JFrame implements ActionListener{


	private JButton graj;
	private JLabel poziomPredkosciTxt, scianyTxt;
	private ButtonGroup poziomPredkosciButtonGroup;
	private JCheckBox scianyTxtCheckbox;
	private JRadioButton wolneRB, srednieRB, szybkieRB; 
	
	
	static boolean gra;
	/**
	 * Konstruuje ekran powitalny
	 */
	public Powitalny(){
		setSize(300,350);
		setTitle("Sssnake");
		setLayout(null);
		
		poziomPredkosciTxt = new JLabel("Wybierz poziom trudnosci");	
		poziomPredkosciTxt.setBounds(20, 20, 300, 40);
		add(poziomPredkosciTxt);
		
		poziomPredkosciButtonGroup = new ButtonGroup();
		wolneRB = new JRadioButton("pooowloolnie", false);
		wolneRB.setBounds(20, 60, 150, 20);
		poziomPredkosciButtonGroup.add(wolneRB);
		add(wolneRB);
		wolneRB.addActionListener(this);
		srednieRB = new JRadioButton("srednio", true);
		srednieRB.setBounds(20, 80, 150, 20);
		poziomPredkosciButtonGroup.add(srednieRB);
		add(srednieRB);
		srednieRB.addActionListener(this);
		szybkieRB = new JRadioButton("sz..bko", false);
		szybkieRB.setBounds(20, 100, 150, 20);
		poziomPredkosciButtonGroup.add(szybkieRB);
		add(szybkieRB);
		szybkieRB.addActionListener(this);

		
		scianyTxt = new JLabel("Czy usun¹æ ograniczenia œcian?");	
		scianyTxt.setBounds(20, 150, 300, 40);
		add(scianyTxt);
		scianyTxtCheckbox = new JCheckBox("Tak");
		scianyTxtCheckbox.setBounds(20, 180, 80, 40);
		add(scianyTxtCheckbox);
		scianyTxtCheckbox.addActionListener(this);
		
		
		graj = new JButton("GRAJ !");
		graj.setBounds(100, 250, 100, 40);
		add(graj);
		graj.addActionListener(this);
	}
	

	
	static Powitalny oknoP = new Powitalny();
	public static void main(String[] args) {
		
		oknoP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		oknoP.setBounds(100,20,300,350);
		oknoP.setVisible(true);
		
		while (!gra);
			
		Plansza plansza = new Plansza();	
		plansza.run();
		
	}

	/**
	 * Obs³uga zdarzeñ wyboru poziomu trudnoœci oraz uderzenia wê¿a w œcianê
	 */
	public void actionPerformed(ActionEvent e) {
		
		Object zdarzenie = e.getSource();
		if (zdarzenie == graj){
			oknoP.setVisible(false);
			gra = true;	
		}
		if (zdarzenie == scianyTxtCheckbox){
			if (Gra.czySaSciany) Gra.czySaSciany = false;
			else Gra.czySaSciany = true;
		}
		if (zdarzenie == wolneRB) Gra.szybkoscGry = 600;
		if (zdarzenie == srednieRB) Gra.szybkoscGry = 300;
		if (zdarzenie == szybkieRB) Gra.szybkoscGry = 100;
	
	}
}
