package com;

public class MoveList  {
	public MoveNode thisMoveNode;
	public MoveList previousMove;
	public MoveList(){
		
	}
	
	public MoveList(int x, int y, int teamNumber, int moveNumber){
		thisMoveNode = new MoveNode(x,y,teamNumber, moveNumber);
	}
	
	public MoveList(MoveList oldMove, int x, int y, int teamNumber, int moveNumber){
		thisMoveNode = new MoveNode(x,y,teamNumber, moveNumber);
		previousMove=oldMove;
	}
	public MoveList(MoveList oldMove, MoveNode thisMoveNode){
		this.thisMoveNode = thisMoveNode;
		previousMove=oldMove;
	}
	public MoveNode getmove(){
		return thisMoveNode;
	}
	/* do not use makeMove might not work with
	 * base cases and is not tested*/
	public void makeMove(int x, int y, int teamNumber, int moveNumber){
		previousMove=new MoveList(previousMove, thisMoveNode);
		thisMoveNode = new MoveNode(x, y, teamNumber, moveNumber);
	}

}