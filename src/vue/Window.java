package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.Performance;
import model.myTable;
import bdd.BDD;


public class Window extends JFrame{

	private JPanel jPanCenter;
	private JPanel jPanNord;
	private JTable table;
	private JTextArea t1;
	private JTextArea t2;
	private JComboBox jc1;
	

	class EcouteurBounton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String event = e.getActionCommand();
			BDD bd = new BDD();
			ArrayList<String> parcours = bd.getParours();
			if(event.equals("Ajouter")){
				if(t1.getText().equals("") || t2.getText().equals("")){
					JOptionPane jOP = new JOptionPane();
					jOP.showMessageDialog(null, "Soit la date ou le temps n'as pas été indiqué!", "Attention", JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(jc1.getSelectedItem().equals("Selection")){
						JOptionPane jOP = new JOptionPane();
						jOP.showMessageDialog(null, "Veuillez séléctionner un parcour!", "Attention", JOptionPane.WARNING_MESSAGE);
					}
					else{
						int nb = bd.addPerformance(t2.getText(),t1.getText() , jc1.getSelectedIndex());
						if(nb != 0){
							Object[] donnee = new Object[]
						            { parcours.get(jc1.getSelectedIndex()-1),t1.getText() ,t2.getText()};
							((myTable) table.getModel()).addRow(donnee);
						}
					}
				}
			}
		}
		
		
	}
	
	public Window(){
		this.setTitle("Runing progresse");
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initisalise();
		this.setVisible(true);
	}
	
	
	public void initisalise(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(this.getPanelNord(),BorderLayout.NORTH);
		//c.add(this.getPanelWest(),BorderLayout.WEST);
		c.add(this.getPanelCenter(),BorderLayout.CENTER);
		//c.add(this.getPanelEast(),BorderLayout.EAST);
		//c.add(this.getPanelSouth(),BorderLayout.SOUTH);
		
	}


	private JPanel getPanelNord() {
		// TODO Auto-generated method stub
		this.jPanNord = new JPanel();
		GridLayout gl = new GridLayout(1, 6);
		gl.setHgap(5);
		jPanNord.setLayout(gl);
		jPanNord.setBorder(BorderFactory.createTitledBorder("Ajouter activité"));
		Border border = jPanNord.getBorder();
		BDD bd = new BDD();
		ArrayList<String> parcours = bd.getParours();
		JLabel l1 = new JLabel("Temps:");
		jPanNord.add(l1);
		t1 = new JTextArea();
		t1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jPanNord.add(t1);
		JLabel l2 = new JLabel("Date:");
		jPanNord.add(l2);
		t2 = new JTextArea();
		t2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jPanNord.add(t2);
		jc1 = new JComboBox<>();
		jc1.addItem("Selection");
		for(int i = 0; i<parcours.size(); i++){
			jc1.addItem(parcours.get(i));
		}
		jPanNord.add(jc1);
		JButton b1 = new JButton("Ajouter");
		b1.addActionListener(new EcouteurBounton());
		jPanNord.add(b1);
		return jPanNord;
	}


	private JPanel getPanelCenter() {
		// TODO Auto-generated method stub
		this.jPanCenter = new JPanel();
		jPanCenter.setLayout(new GridLayout());
		BDD bd = new BDD();
		ArrayList<Performance> per = bd.getPerformance();
		String title[] = {"Parcoure","Temps", "Date"};
		String data[][] = new String[per.size()][title.length];
		for(int i = 0; i < per.size(); i++){
			data[i][0] = per.get(i).getParcours();
			data[i][1] = per.get(i).getTemps();
			data[i][2] = per.get(i).getDate();
		}
		myTable mT = new myTable(data, title);
		table = new JTable(mT);
		table.setAutoCreateRowSorter(true);	
		JScrollPane  scroll = new JScrollPane(table);
		scroll.setSize(750, 600);
		jPanCenter.add(scroll);
		return jPanCenter;
	}
}
