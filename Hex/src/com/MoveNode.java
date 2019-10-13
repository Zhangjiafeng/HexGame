package com;

public class MoveNode {
	private  int x;
	private int y;
	private int team;
	private int moveNumber;
	public MoveNode(int x, int y, int team, int moveNumber){
		this.setX(x);
		this.setY(y);
		this.setTeam(team);
		this.setMoveNumber(moveNumber);
		
	}
	protected void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	protected void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	protected void setTeam(int team) {
		this.team = team;
	}
	public int getTeam() {
		return team;
	}
	public int getMoveNumber() {
		return moveNumber;
	}
	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}
}