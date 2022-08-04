package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.abilities.Ability;
import model.world.Champion;
import engine.Game;

public class FirstPanel extends JPanel implements ActionListener {
	
	
	
			
private Frame frame;
private JLabel l1;
private JLabel l2;
private JTextField N1;
private JTextField N2;
private JButton Start;
private JComboBox<String>champion1; 
private JComboBox<String>champion2;
private JComboBox<String>champion3;
private JComboBox<String>champion4;
private JComboBox<String>champion5;
private JComboBox<String>champion6;
private JTextArea a1;
private JTextArea a2;
private JTextArea a3;
private JTextArea a4;
private JTextArea a5;
private JTextArea a6;
private JButton show;
private JButton show2;

ArrayList<Champion>mylist=Game.getAvailableChampions();

public FirstPanel(Frame frame){
	
	String[]r= new String[mylist.size()+1];
			r[0]="Select Champion";
			for(int i=1;i<mylist.size();i++){
				r[i]=mylist.get(i-1).getName();
			}
			String[]s= new String[mylist.size()+1];
			s[0]="Select Leader";
			for(int i=1;i<mylist.size();i++){
				s[i]=mylist.get(i-1).getName();
			}		
	this.frame=frame;
	this.setLayout(null);
	l1=new JLabel("Player 1 Name");
	l1.setBounds(30,30,200,50);
	l1.setFont(new Font("verdana",Font.PLAIN,18));
	this.add(l1);
	N1=new JTextField();
	N1.setBounds(250,40,200,30);
	this.add(N1);
	l2=new JLabel("Player 2 Name");
	l2.setFont(new Font("verdana",Font.PLAIN,18));
	l2.setBounds(30,400,200,50);
	this.add(l2);
	N2=new JTextField();
	N2.setBounds(250,410,200,30);
	this.add(N2);
	String[]x={"Select Leader"};
	String[]y={"Select Champion"};
	champion1=new JComboBox<String>(s);
	champion1.setBounds(600,70,120,25);
	this.add(champion1);
	show=new JButton("show");
	show.setBounds(600,100,120,25);
	show.addActionListener(this);
	this.add(show);
	champion2=new JComboBox<String>(r);
	champion2.setBounds(300,100,120,25);
	this.add(champion2);
	champion3=new JComboBox<String>(r);
	champion3.setBounds(900,100,120,25);
	this.add(champion3);
	champion4=new JComboBox<String>(s);
	champion4.setBounds(600,450,120,25);
	this.add(champion4);
	show2=new JButton("show");
	show2.setBounds(600,480,120,25);
	show2.addActionListener(this);
	this.add(show2);
	champion5=new JComboBox<String>(r);
	champion5.setBounds(300,480,120,25);
	this.add(champion5);
	champion6=new JComboBox<String>(r);
	champion6.setBounds(900,480,120,25);
	this.add(champion6);
	a1=new JTextArea();
	a1.setBounds(560,150,200,200);
	a1.setEditable(false);
	this.add(a1);
	a2=new JTextArea();
	a2.setBounds(260,150,200,200);
	a2.setEditable(false);
	this.add(a2);
	a3=new JTextArea();
	a3.setBounds(860,150,200,200);
	a3.setEditable(false);
	this.add(a3);
	a4=new JTextArea();
	a4.setBounds(260,530,200,200);
	a4.setEditable(false);
	this.add(a4);
	a5=new JTextArea();
	a5.setBounds(560,530,200,200);
	a5.setEditable(false);
	this.add(a5);
	a6=new JTextArea();
	a6.setBounds(860,530,200,200);
	a6.setEditable(false);
	this.add(a6);
	Start=new JButton("Start");
	Start.setBounds(1100,400,120,25);
	Start.addActionListener(this);
	this.add(Start);}
	public String Champion1Info(int i){
		for(Ability a:Game.getAvailableAbilities()){
			System.out.println(a.getName());
		}
	Champion c1=mylist.get(i);
	System.out.println(c1.getAbilities());
	System.out.println(c1.getAbilities().get(0));
	System.out.println(c1.getAbilities().get(0).getName());
	String q="Name: " + c1.getName() + "\n" + "Damage: " + c1.getAttackDamage() + "\n" + "Range: " + 
	c1.getAttackRange() + "\n" + "Health: " + c1.getMaxHP() + "\n" + "Speed: " + c1.getSpeed() + 
	"\n" + "Turn's Action Points: " + c1.getMaxActionPointsPerTurn() +"\n" + "Mana: " +c1.getMana()+
	"\n" +"Ability 1) "+ c1.getAbilities().get(0).getName()+
	"\n"+"Ability 2) "+ c1.getAbilities().get(1).getName()+
	"\n"+"Ability 3) "+ c1.getAbilities().get(2).getName();
	return q;
	}
	


	public void actionPerformed(ActionEvent AE) {
		ArrayList<Integer>index=new ArrayList<Integer>(6);
	
	if (AE.getSource()==show){
		if(champion1.getSelectedIndex()!=0 && champion2.getSelectedIndex()!=0 
				&& champion3.getSelectedIndex()!=0){
		a1.setText(Champion1Info(champion1.getSelectedIndex()-1));
		a2.setText(Champion1Info(champion2.getSelectedIndex()-1));
		a3.setText(Champion1Info(champion3.getSelectedIndex()-1));
		}
		else
			JOptionPane.showMessageDialog(this, "Player 1 must 3 Champions","Error",JOptionPane.ERROR_MESSAGE);
	}
if (AE.getSource()==show2){
	if(champion5.getSelectedIndex()!=0 && champion4.getSelectedIndex()!=0 
			&& champion6.getSelectedIndex()!=0){
		a4.setText(Champion1Info(champion5.getSelectedIndex()-1));
		a5.setText(Champion1Info(champion4.getSelectedIndex()-1));
		a6.setText(Champion1Info(champion6.getSelectedIndex()-1));
	}
	else
		JOptionPane.showMessageDialog(this, "Player 2 must 3 Champions","Error",JOptionPane.ERROR_MESSAGE);

}
	
		int i;
			 if(AE.getSource()==Start){
				 i=0;
				 if(champion1.getSelectedIndex()!=0 && champion2.getSelectedIndex()!=0 
							&& champion3.getSelectedIndex()!=0 && champion5.getSelectedIndex()!=0 && champion4.getSelectedIndex()!=0 
							&& champion6.getSelectedIndex()!=0){
			if (N1.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Player 1 must have name","Error",JOptionPane.ERROR_MESSAGE); }
			else if(!(N1.getText().equals(""))){
				i++;
			}
		
		 if(N2.getText().equals("")){
				JOptionPane.showMessageDialog(this, "Player 2 must have name","Error",JOptionPane.ERROR_MESSAGE); i++;
			}
		else if(!(N2.getText().equals(""))){
			i++;
		}	
			
		if(!(index.contains(champion1.getSelectedIndex()))){
			index.add(Integer.valueOf(champion1.getSelectedIndex())); i++;}
		 else if(index.contains(champion1.getSelectedIndex())){
			 JOptionPane.showMessageDialog(this, "Champions must be Different","Error",JOptionPane.ERROR_MESSAGE);
		 }
		  if(!(index.contains(champion2.getSelectedIndex()))){
			index.add(Integer.valueOf(champion2.getSelectedIndex())); i++;}
		 else if(index.contains(champion2.getSelectedIndex())){
			 JOptionPane.showMessageDialog(this, "Champions must be Different","Error",JOptionPane.ERROR_MESSAGE);
		 }
		   if(!(index.contains(champion3.getSelectedIndex()))){
			index.add(Integer.valueOf(champion3.getSelectedIndex())); i++;}
		 else if(index.contains(champion3.getSelectedIndex())){
			 JOptionPane.showMessageDialog(this, "Champions must be Different","Error",JOptionPane.ERROR_MESSAGE);
		 }
		   if(!(index.contains(champion4.getSelectedIndex()))){
			index.add(Integer.valueOf(champion4.getSelectedIndex())); i++;}
		 else if(index.contains(champion4.getSelectedIndex())){
			 JOptionPane.showMessageDialog(this, "Champions must be Different","Error",JOptionPane.ERROR_MESSAGE);
		 }
		   if(!(index.contains(champion5.getSelectedIndex()))){
			index.add(Integer.valueOf(champion5.getSelectedIndex())); i++;}
		 else if (index.contains(champion5.getSelectedIndex())){
		   JOptionPane.showMessageDialog(this, "Champions must be Different","Error",JOptionPane.ERROR_MESSAGE);
		 }
		  if(!(index.contains(champion6.getSelectedIndex()))){
			index.add(Integer.valueOf(champion6.getSelectedIndex())); i++;
			}
		  else if (index.contains(champion6.getSelectedIndex())){
			 JOptionPane.showMessageDialog(this, "Champions must be Different","Error",JOptionPane.ERROR_MESSAGE);
		 }
		  if(i==8)
		  frame.boardPanel(N1.getText(), N2.getText(), champion1.getSelectedIndex()-1, champion2.getSelectedIndex()-1,
					champion3.getSelectedIndex()-1, champion4.getSelectedIndex()-1, 
					champion5.getSelectedIndex()-1, champion6.getSelectedIndex()-1);
		 		 }
				 else 
					 JOptionPane.showMessageDialog(this, "Players must select Champions","Error",JOptionPane.ERROR_MESSAGE);
				  
			 }
			 

	
	}
}
	


