package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class BoardPanel extends JPanel implements ActionListener {

	private Frame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
    private JButton triangle;
    private JButton triangleright;
    private JButton triangleleft;
    private JButton triangledown;
    private JButton trianglea;
    private JButton trianglearight;
    private JButton trianglealeft;
    private JButton triangleadown;
    private JButton triangleb;
    private JButton trianglebdown;
    private JButton trianglebleft;
    private JButton trianglebright;
	private JButton[][]Labels;
	private JLabel playersname;
	private JLabel playersname2;
	private JLabel move;
	private JLabel Attack;
	private JButton Cast;
	private JComboBox<String>Abilities ;
	private Direction direction= Direction.DOWN;
	private JTextArea Info;
	private JButton nextturn;
	private JButton leader;
	private JComboBox abilities;
	private JTextArea playerinfo;
	private JTextArea playerinfo2;
	private Damageable selectedChampion;
	private int selectedChampionI=-1;
	private int selectedChampionJ=-1;
	private JList turns;
	private JLabel playersleaders;
	private JLabel playersleaders2;
	ArrayList<String> g=new ArrayList<String>();
	public BoardPanel(Frame frame){
		this.frame=frame;
		this.setLayout(null);
		panel1=new JPanel();
		panel1.setLayout(new GridLayout(5,5));
		panel1.setBounds(10, 10, 600, 500);
		playersname=new JLabel("First Player: "+ frame.getGame().getFirstPlayer().getName());
		playersname.setBounds(110, 550, 200, 25);
		playersname.setFont(new Font("Serif",Font.BOLD,15));
		playersname2=new JLabel("Second Player: "+ frame.getGame().getSecondPlayer().getName());
		playersname2.setBounds(280, 550, 200, 25);
		playersname2.setFont(new Font("Serif",Font.BOLD,15));
		this.add(playersname);
		this.add(playersname2);
		Labels=new JButton[5][5];
		playersleaders=new JLabel("First Player's Leader: "+ frame.getGame().getFirstPlayer().getLeader().getName());
		playersleaders2=new JLabel("Second Player's Leader: "+ frame.getGame().getSecondPlayer().getLeader().getName());
		playersleaders.setBounds(630, 590, 300, 25);
		playersleaders.setFont(new Font("Serif",Font.BOLD,15));
		this.add(playersleaders);
		playersleaders2.setBounds(630, 630, 300, 25);
		playersleaders2.setFont(new Font("Serif",Font.BOLD,15));
		this.add(playersleaders2);
		for(int j=0;j<5;j++){
			for(int i=0;i<5;i++){
				Labels[i][j]=new JButton();
				Labels[i][j].setFont(new Font("Serif",Font.PLAIN,11));
				Labels[i][j].addActionListener(this);
				
				panel1.add(Labels[i][j]);
				
				Labels[i][j].setVisible(true);
			}
			
		}
		this.add(panel1);
		String []ar={"turns:- ", frame.getGame().getturn().get(0),frame.getGame().getturn().get(1),frame.getGame().getturn().get(2),frame.getGame().getturn().get(3),
				frame.getGame().getturn().get(4),frame.getGame().getturn().get(5)};
		turns=new JList(ar);
		
		turns.setBounds(450, 550, 130, 130);
		this.add(turns);
		nextturn= new JButton("End Turn") ;
		nextturn.setBounds(200,580,150,50);
		nextturn.setFont(new Font("Serif",Font.BOLD,20));
		this.add(nextturn);
		nextturn.addActionListener(this);
		leader= new JButton("Leader Ability") ;
		leader.setBounds(200,630,150,50);
		leader.setFont(new Font("Serif",Font.BOLD,15));
		this.add(leader);
		leader.addActionListener(this);
		Cast=new JButton("CAST");
		Cast.setFont(new Font("Serif",Font.PLAIN,20));
		Cast.setBounds(805,472,150,50);
		abilities=new JComboBox();
		abilities.setBounds(900,600,150,20);
		this.add(abilities);
		triangle=new JButton ("^");
		triangle.setBounds(830,60,90,40);
		triangle.setFont(new Font("Serif",Font.BOLD,30));
		this.add(triangle);
		triangleright=new JButton (">");
		triangleright.setBounds(970,120,70,40);
		triangleright.setFont(new Font("Serif",Font.BOLD,30));
		this.add(triangleright);
		triangleleft=new JButton ("<");
		triangleleft.setBounds(720,120,70,40);
		triangleleft.setFont(new Font("Serif",Font.BOLD,30));
		this.add(triangleleft);
		triangledown=new JButton ("V");
		triangledown.setBounds(830,180,90,40);
		triangledown.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(triangledown);
		trianglea=new JButton ("^");
		trianglea.setBounds(830,240,90,40);
		trianglea.setFont(new Font("Serif",Font.BOLD,30));
		this.add(trianglea);
		trianglearight=new JButton (">");
		trianglearight.setBounds(970,300,70,40);
		trianglearight.setFont(new Font("Serif",Font.BOLD,30));
		this.add(trianglearight);
		trianglealeft=new JButton ("<");
		trianglealeft.setBounds(720,300,70,40);
		trianglealeft.setFont(new Font("Serif",Font.BOLD,30));
		this.add(trianglealeft);
		triangleadown=new JButton ("V");
		triangleadown.setBounds(830,360,90,40);
		triangleadown.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(triangleadown);
		triangleb=new JButton ("^");
		triangleb.setBounds(830,420,90,40);
		triangleb.setFont(new Font("Serif",Font.BOLD,30));
		this.add(triangleb);
		trianglebright=new JButton (">");
		trianglebright.setBounds(970,480,70,40);
		trianglebright.setFont(new Font("Serif",Font.BOLD,30));
		this.add(trianglebright);
		trianglebleft=new JButton ("<");
		trianglebleft.setBounds(720,480,70,40);
		trianglebleft.setFont(new Font("Serif",Font.BOLD,30));
		this.add(trianglebleft);
		trianglebdown=new JButton ("V");
		trianglebdown.setBounds(830,540,90,40);
		trianglebdown.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(trianglebdown);
		move=new JLabel("MOVE");
		move.setBounds(850,120, 150,50);
		move.setFont(new Font("Serif",Font.PLAIN,20));
		Attack=new JLabel("ATTACK");
		Attack.setBounds(840,300, 150,50);
		Attack.setFont(new Font("Serif",Font.PLAIN,20));
		playerinfo=new JTextArea();
		playerinfo.setEditable(false);
	    playerinfo.setText(displayplayerinfo());
		JScrollPane sp=new JScrollPane(playerinfo);
		sp.setBounds(1100,50,200,300);
		playerinfo2=new JTextArea();
		playerinfo2.setEditable(false);
		JScrollPane ps=new JScrollPane(playerinfo2);
		ps.setBounds(1100,400,200,250);
		this.add(sp);
		this.add(ps);
		this.add(Cast);
		this.add(move);
		this.add(Attack);
		triangle.addActionListener(this);
		trianglea.addActionListener(this);
		triangleb.addActionListener(this);
		triangledown.addActionListener(this);
		triangleadown.addActionListener(this);
		trianglebdown.addActionListener(this);
		triangleleft.addActionListener(this);
		trianglealeft.addActionListener(this);
		trianglebleft.addActionListener(this);
		triangleright.addActionListener(this);
		trianglearight.addActionListener(this);
		trianglebright.addActionListener(this);
		Cast.addActionListener(this);
		playersname=new JLabel("First Player: " + frame.getGame().getFirstPlayer().getName());
		
		Board();
		abiltyCombo();
		playerinfo.setText(displayplayerinfo());
	}
	public void Board(){
		for (int j=0;j<5; j++){
			for (int i=0;i<5; i++){		
			if(frame.getGame().getBoard()[i][j] instanceof Champion){
				Champion c=(Champion)frame.getGame().getBoard()[i][j];
				if(c==frame.getGame().getCurrentChampion()){
					Labels[i][j].setBackground(Color.GRAY);
					//Border border=BorderFactory.createLineBorder(Color.YELLOW);
					//Labels[i][j].setBorder(border);
				}
				else if(frame.getGame().getFirstPlayer().getTeam().contains(c)){
					
					Labels[i][j].setBackground(Color.BLUE);
					Labels[i][j].setForeground(Color.WHITE);
				}
				else {
					Labels[i][j].setBackground(Color.RED);
					Labels[i][j].setForeground(Color.BLACK);
				}
				Labels[i][j].setText(c.getName());
				Labels[i][j].setFont(new Font("Serif",Font.PLAIN,15));
			}
			else if(frame.getGame().getBoard()[i][j] instanceof Cover){
				Cover C=(Cover)frame.getGame().getBoard()[i][j];
				Labels[i][j].setBackground(Color.WHITE);
			}
			else{
				Labels[i][j].setBackground(Color.BLACK);
				Labels[i][j].setText("");
			//	Border border=BorderFactory.createLineBorder(Color.WHITE);
				//Labels[i][j].setBorder(border);
			}
					
		}
		}
		this.validate();
		this.repaint();
	}
	public void abiltyCombo(){
		abilities.removeAllItems();
		abilities.addItem(frame.getGame().getCurrentChampion().getAbilities().get(0).getName());
		abilities.addItem(frame.getGame().getCurrentChampion().getAbilities().get(1).getName());
		abilities.addItem(frame.getGame().getCurrentChampion().getAbilities().get(2).getName());
		
	}
	
	public  String displayplayerinfo(){
		String S="Current Player: ";
		if(frame.getGame().getFirstPlayer().getTeam().contains(frame.getGame().getCurrentChampion())){
			S=S+ frame.getGame().getFirstPlayer().getName()+"\n";
		}
		else if(frame.getGame().getSecondPlayer().getTeam().contains(frame.getGame().getCurrentChampion())){
			S=S +frame.getGame().getSecondPlayer().getName()+"\n";
		}
		Champion c=frame.getGame().getCurrentChampion();
		Ability a=c.getAbilities().get(0);
		Ability b=c.getAbilities().get(1);
		Ability d=c.getAbilities().get(2);
		S=S+"Name: " + c.getName() + "\n";
		if(c instanceof Hero){
			S=S+"Type: Hero"+"\n";
		}
		if(c instanceof Villain){
			S=S+"Type: Villain"+"\n";
		}
		if(c instanceof AntiHero){
			S=S+"Type: AntiHero"+"\n";
		}
				S=S+ "Damage: " + c.getAttackDamage() + "\n" + "Range: " + 
				c.getAttackRange() + "\n" + "Health: "+c.getCurrentHP()+"/" + c.getMaxHP() + "\n" + "Speed: " + c.getSpeed() + 
				"\n" + "Turn's Action Points: " + c.getMaxActionPointsPerTurn() +"\n" +"Current Action Points: " + c.getCurrentActionPoints()+"\n" + "Mana: " +c.getMana()+
				"\n" +"Ability 1) "+ a.getName()+"\n";
				if(a instanceof DamagingAbility){
					DamagingAbility dab=(DamagingAbility) a;
					S=S+"Type: Damaging Ability"+"\n"+"Damaging Amount: "+dab.getDamageAmount()+"\n";
				}
				if(a instanceof HealingAbility){
					HealingAbility dab=(HealingAbility) a;
					S=S+"Type: Healing Ability"+"\n"+"Healing Amount: "+dab.getHealAmount()+"\n";
				}
				
				if(a instanceof CrowdControlAbility){
					CrowdControlAbility dab=(CrowdControlAbility) a;
					S=S+"Type: Crowd Control Ability"+"\n"+"Effect Applied: "+ dab.getEffect().getName()+"\n";
				}
				S=S+"Mana cost: "+ a.getManaCost() +"\n"+ "Cooldown: "+a.getCurrentCooldown()+"\n"+"Base Cooldown: "+
				a.getBaseCooldown()+"\n"+"Cast range: "+a.getCastRange() +"\n"+"Cast Area: "+a.getCastArea()+"\n"+
				"Action Points: "+a.getRequiredActionPoints()+"\n"+"Ability 2) "+ b.getName()+ "\n";
				if(b instanceof DamagingAbility){
					DamagingAbility dab=(DamagingAbility) b;
					S=S+"Type: Damaging Ability"+"\n"+"Damaging Amount: "+dab.getDamageAmount()+"\n";
				}
				if(b instanceof HealingAbility){
					HealingAbility dab=(HealingAbility) b;
					S=S+"Type: Healing Ability"+"\n"+"Healing Amount: "+dab.getHealAmount()+"\n";
				}
				if(b instanceof CrowdControlAbility){
					CrowdControlAbility dab=(CrowdControlAbility) b;
					S=S+"Type: Crowd Control Ability"+"\n"+"Effect Applied: "+ dab.getEffect().getName()+"\n";
				}
				S=S+"Mana cost: "+ b.getManaCost() +"\n"+ "Cooldown: "+b.getCurrentCooldown()+"\n"
				+"Base Cooldown: "+ b.getBaseCooldown()+"\n"+"Cast range: "+b.getCastRange() +"\n"+"Cast Area: "+b.getCastArea()+"\n"+
						"Action Points: "+b.getRequiredActionPoints()+"\n"+"Ability 3) "+ d.getName()+ "\n";
				if(d instanceof DamagingAbility){
					DamagingAbility dab=(DamagingAbility) d;
					S=S+"Type: Damaging Ability"+"\n"+"Damaging Amount: "+dab.getDamageAmount()+"\n";
				}
				if(d instanceof HealingAbility){
					HealingAbility dab=(HealingAbility) d;
					S=S+"Type: Healing Ability"+"\n"+"Healing Amount: "+dab.getHealAmount()+"\n";
				}
				if(d instanceof CrowdControlAbility){
					CrowdControlAbility dab=(CrowdControlAbility) d;
					S=S+"Type: Crowd Control Ability"+"\n"+"Effect Applied: "+ dab.getEffect().getName()+"\n";
				}
				S=S+"Mana cost: "+ d.getManaCost() +"\n"+ "Cooldown: "+d.getCurrentCooldown()+"\n"+
						"Base Cooldown: "+ d.getBaseCooldown()+"\n"+"Cast range: "+d.getCastRange() +"\n"+"Cast Area: "+d.getCastArea()+"\n"+
						"Action Points: "+d.getRequiredActionPoints()+"\n";
		
		for(int i=0;i<frame.getGame().getCurrentChampion().getAppliedEffects().size();i++){
			Effect e=c.getAppliedEffects().get(i);
			S=S+"Effect "+(i+1)+") "+e.getName()+"\n";
			S=S+"Duration: " + e.getDuration()+" turns"+"\n";
		}
		this.validate();
		this.repaint();
		return S;
		
	}
public void displayplayerinfo2(Damageable d){
	if(d instanceof Champion){	
		Champion c=(Champion)d;
		String S="Name: "+c.getName()+"\n";
		if(c instanceof Hero){
			S=S+"Type: Hero"+"\n";
		}
		if(c instanceof Villain){
			S=S+"Type: Villain"+"\n";
		}
		if(c instanceof AntiHero){
			S=S+"Type: AntiHero"+"\n";
		}
		S=S+"Damage: "+c.getAttackDamage()+"\n"+"Range: "+c.getAttackRange()+"\n"+"Health: "+c.getCurrentHP()+"/" + c.getMaxHP()
				+"\n"+"Mana: "+c.getMana()+"\n"+"Action Points: "+c.getCurrentActionPoints()+"\n"+"Speed: "+c.getSpeed()+"\n"+
					"Condition: "+c.getCondition()+"\n"+"Ability 1) "+c.getAbilities().get(0).getName()
					+"\n"+"Ability 2) "+c.getAbilities().get(1).getName()
					+"\n"+"Ability 3) "+c.getAbilities().get(2).getName()+"\n";
		for(int i=0;i<c.getAppliedEffects().size();i++){
			Effect e=c.getAppliedEffects().get(i);
			S=S+"Effect "+(i+1)+") "+e.getName()+"\n";
			S=S+"Duration: " + e.getDuration()+" turns"+"\n";
		}
			playerinfo2.removeAll();
			playerinfo2.setText(S);	}
	else if(d instanceof Cover){
		Cover c=(Cover) d;
		String G="Health Points: "+ c.getCurrentHP();
		playerinfo2.removeAll();
		playerinfo2.setText(G);
	}
	this.repaint();
	this.revalidate();
}
	@Override
	public void actionPerformed(ActionEvent p) {
		for(int j=0;j<5;j++){
			for(int i=0;i<5;i++){
				if(p.getSource()==Labels[i][j]){
					
					selectedChampionI=i;
					selectedChampionJ=j;
					break;
				}
			
	}
		}
	for(int j=0;j<5;j++){
		for(int i=0;i<5;i++){
			if(p.getSource()==Labels[i][j]){
				
					selectedChampion= (Damageable) frame.getGame().getBoard()[i][j];
					if(selectedChampion!=null){
						displayplayerinfo2(selectedChampion);
					}
		}
			}
		}
	
	
	
		if (p.getSource()==triangledown){
		try{
			frame.getGame().move(Direction.RIGHT);
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|UnallowedMovementException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	if (p.getSource()==triangleright){
		try{
			frame.getGame().move(Direction.UP);
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|UnallowedMovementException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	if (p.getSource()==triangle){
		try{
			frame.getGame().move(Direction.LEFT);
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|UnallowedMovementException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	if (p.getSource()==triangleleft){
		try{
			frame.getGame().move(Direction.DOWN);
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|UnallowedMovementException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	if (p.getSource()==triangleadown){
		try{
			frame.getGame().attack(Direction.RIGHT);
			if(selectedChampion!=null){
				displayplayerinfo2(selectedChampion);
			}
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	if (p.getSource()==trianglearight){
		try{
			frame.getGame().attack(Direction.UP);
			if(selectedChampion!=null){
				displayplayerinfo2(selectedChampion);
			}
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	if (p.getSource()==trianglea){
		try{
			frame.getGame().attack(direction.LEFT);
			if(selectedChampion!=null){
				displayplayerinfo2(selectedChampion);
			}
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	if (p.getSource()==trianglealeft){
		try{
			frame.getGame().attack(Direction.DOWN);
			if(selectedChampion!=null){
				displayplayerinfo2(selectedChampion);
			}
			Board();
			playerinfo.setText(displayplayerinfo());
		}catch(NotEnoughResourcesException|ChampionDisarmedException|InvalidTargetException h){
			JOptionPane.showMessageDialog(this, h.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	if(p.getSource()==nextturn){
		frame.getGame().endTurn();
		Board();
		playerinfo.setText(displayplayerinfo());
		abiltyCombo();
	}
	
		
			
			Ability a=frame.getGame().getCurrentChampion().getAbilities().get(abilities.getSelectedIndex());
			try{
				if(p.getSource()==Cast){
			if(a.getCastArea()==AreaOfEffect.SINGLETARGET && selectedChampionI!=-1){
		frame.getGame().castAbility(a, selectedChampionI,selectedChampionJ);
			selectedChampionI=-1;
			selectedChampionJ=-1;
		}
			else if(a.getCastArea()!=AreaOfEffect.SINGLETARGET && a.getCastArea()!=AreaOfEffect.DIRECTIONAL){
				frame.getGame().castAbility(a);
				if(selectedChampion!=null){
					displayplayerinfo2(selectedChampion);
				}
			}
			Board();
			playerinfo.setText(displayplayerinfo());
			abiltyCombo();
				}
			if(a.getCastArea()==AreaOfEffect.DIRECTIONAL){
				if(p.getSource()==triangleb){
					frame.getGame().castAbility(a, Direction.LEFT);
					if(selectedChampion!=null){
						displayplayerinfo2(selectedChampion);
					}
					Board();
					playerinfo.setText(displayplayerinfo());
				}
				else if(p.getSource()==trianglebdown){
					frame.getGame().castAbility(a, Direction.RIGHT);
					if(selectedChampion!=null){
						displayplayerinfo2(selectedChampion);
					}
					Board();
					playerinfo.setText(displayplayerinfo());
				}
				else if(p.getSource()==trianglebright){
					frame.getGame().castAbility(a, Direction.UP);
					if(selectedChampion!=null){
						displayplayerinfo2(selectedChampion);
					}
					Board();
					playerinfo.setText(displayplayerinfo());
				}
				else if(p.getSource()==trianglebleft){
					frame.getGame().castAbility(a, Direction.DOWN);
					if(selectedChampion!=null){
						displayplayerinfo2(selectedChampion);
					}
					Board();
					playerinfo.setText(displayplayerinfo());
				}
		
			abiltyCombo();
			}
		}catch(NotEnoughResourcesException|AbilityUseException|
				InvalidTargetException|CloneNotSupportedException|NumberFormatException h1){
		JOptionPane.showMessageDialog(this, h1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
			try{
				if(p.getSource()==leader){
				frame.getGame().useLeaderAbility();
				if(selectedChampion!=null){
					displayplayerinfo2(selectedChampion);
				}
				Board();
				playerinfo.setText(displayplayerinfo());
			}
				
			}catch(LeaderNotCurrentException| LeaderAbilityAlreadyUsedException f1){
				JOptionPane.showMessageDialog(this, f1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
	
	
	if(frame.getGame().checkGameOver()!=null){
		JOptionPane.showMessageDialog(this,frame.getGame().checkGameOver().getName()+" is the WINNER", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	frame.setVisible(false);
	}
	
	
	
	this.revalidate();
	this.repaint();
	
	}

}
