package com;

import java.awt.*;
import java.util.LinkedList;

//import com.net.MoveListener;



public class HumanObject implements Player {
	private Color color;
	private int team;
//	private LinkedList<Point> hex = new LinkedList<Point>();
    private Point hex;
	private GameObject game;
	
	public HumanObject(int team, GameObject game) {
		this.team=team;
		this.game=game;
		this.setColor(Color.red);
		hex=new Point(-1,-1);
	}
	
	@Override
	public void getPlayerTurn() {
		while (true) {
			while (hex.equals(new Point(-1,-1))) {
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (GameAction.makeMove(this,  team, hex, game)) {
				hex=new Point(-1,-1);
				break;
			}
		}
	}

	@Override
	public void undoCalled() {

	}



	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setMove(Object o, Point hex) {
		if(o instanceof GameAction && game.currentPlayer==team){
			this.hex=hex;
		}
	}
}