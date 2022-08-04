package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.world.Champion;
import engine.Game;
import engine.Player;

public class Frame extends JFrame{
private BoardPanel boardpanel;
private FirstPanel firstpanel;
String playerName;
String playerName2;

Player p1;
Player p2;

Game game;
public Frame(){
	  

	try{
	    Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");

	
	firstpanel=new FirstPanel(this);
	this.getContentPane().add(firstpanel);
	
	this.setTitle("MCU WAR");
	this.setSize(1500,750);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	catch(IOException e){
		e.printStackTrace();
	}
}
 public String getPlayerName() {
	return playerName;
}
public void setPlayerName(String playerName) {
	this.playerName = playerName;
}
public String getPlayerName2() {
	return playerName2;
}
public void setPlayerName2(String playerName2) {
	this.playerName2 = playerName2;
}
public Player getP1() {
	return p1;
}
public void setP1(Player p1) {
	this.p1 = p1;
}
public Player getP2() {
	return p2;
}
public void setP2(Player p2) {
	this.p2 = p2;
}
public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}
public void boardPanel(String s1,String s2,int ch1,int ch2,int ch3, int ch4, int ch5, int ch6){
	p1=new Player(s1);
	p2=new Player(s2);
	 p1.getTeam().add(Game.getAvailableChampions().get(ch1));
	 p1.getTeam().add(Game.getAvailableChampions().get(ch2));
	 p1.getTeam().add(Game.getAvailableChampions().get(ch3));
	 p2.getTeam().add(Game.getAvailableChampions().get(ch4));
	 p2.getTeam().add(Game.getAvailableChampions().get(ch5));
	 p2.getTeam().add(Game.getAvailableChampions().get(ch6));
	 
	 p1.setLeader(Game.getAvailableChampions().get(ch1));
	 p2.setLeader(Game.getAvailableChampions().get(ch4));
	 
	  game=new Game(p1,p2);
	 this.getContentPane().removeAll();
	boardpanel=new BoardPanel(this);
	this.getContentPane().add(boardpanel);
	this.revalidate();
	this.repaint();
 }
	

public static void main(String[] args){
	Frame f=new Frame();
}

}