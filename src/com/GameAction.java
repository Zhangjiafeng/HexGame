package com;

import java.awt.*;

public class GameAction {
	private GameAction(){}
	
	public static synchronized boolean checkWinPlayer(int team, GameObject game) {
		if(team==1){
			for (int i = 0; i < game.gridSize; i++) {
				if (PolygonButton.checkWinTeam(1, game.gridSize, i, game.gamePiece)) {
					System.out.println("Player1 wins");
					checkedFlagReset(game);
					String path=PolygonButton.findShortestPath(1, game.gridSize, i, game.gamePiece);
					PolygonButton.colorPath(game.gridSize,i,path,game);
					return true;
				}
			}
			return false;
		}
		else{
			for (int i = 0; i < game.gridSize; i++) {
				if (PolygonButton.checkWinTeam( 2, i, game.gridSize, game.gamePiece)) {
					System.out.println("Player2 wins");
					checkedFlagReset(game);
					String path=PolygonButton.findShortestPath(2, i, game.gridSize, game.gamePiece);
					PolygonButton.colorPath(i,game.gridSize,path,game);
					return true;
				}
			}
			return false;
		}
	}

	public static void checkedFlagReset(GameObject game) {
		for (int x = game.gridSize - 1; x >= 0; x--) {
			for (int y = game.gridSize - 1; y >= 0; y--) {
				game.gamePiece[x][y].checkedflage = false;
			}
		}
	}
	
	public static void setPiece(Point p, GameObject game) {
		getPlayer(game.currentPlayer, game).setMove(new GameAction(),p);
	}
	
	private static void setTeam(int t,int x,int y, GameObject game) {
		game.moveList.makeMove(x, y, t, game.moveNumber);
		game.gamePiece[x][y].setTeam(t,game);
		game.moveNumber++;
	}
	
	public static boolean makeMove(Player player, int team, Point hex, GameObject game){
		if(player!=null && game.gamePiece[hex.x][hex.y].getTeam() == 0){
			setTeam(team,hex.x,hex.y,game);
			System.out.println("Player"+team+"'s move:("+hex.x+","+hex.y+")");
			return true;
		}
		return false;
	}
	
	public static Player getPlayer(int i, GameObject game){
		if(i==1){
			return game.player1;
		}
		else if(i==2){
			return game.player2;
		}
		else{
			return null;
		}
	}

	public static void undo(GameObject game){
		if(game.moveNumber>1&&game.checkForRunning()){
			checkedFlagReset(game);

			//Remove the piece from the board and the movelist
			MoveNode lastMoveNode = game.moveList.thisMoveNode;
			game.gamePiece[lastMoveNode.getX()][lastMoveNode.getY()].setTeam(0,game);
			game.moveList = game.moveList.previousMove;
			game.moveNumber--;

			boolean p1 = game.player1 instanceof HumanObject;
			boolean p2 = game.player2 instanceof HumanObject;

			if(game.currentPlayer==1 && p1){//It's a human's turn
				game.player2.undoCalled();//Tell the other player we're going back a turn

				if(!p2){//If the other person isn't a human, undo again
					if(game.moveNumber>1){
						lastMoveNode = game.moveList.thisMoveNode;
						game.gamePiece[lastMoveNode.getX()][lastMoveNode.getY()].setTeam(0,game);
						game.moveList = game.moveList.previousMove;
						game.moveNumber--;
					}
				}

			}
			else if(game.currentPlayer==1 && !p1){
					game.player1.undoCalled();

			}
			else if(game.currentPlayer==2 && p2){
				game.player1.undoCalled();

				//If the other person isn't a (local) human
				if(!p1){
					//Undo again
					if(game.moveNumber>1){
						lastMoveNode = game.moveList.thisMoveNode;
						game.gamePiece[lastMoveNode.getX()][lastMoveNode.getY()].setTeam(0,game);
						game.moveList = game.moveList.previousMove;
						game.moveNumber--;
					}

				}

			}
			else if(game.currentPlayer==2 && !p2){
					game.player2.undoCalled();
			}


		}

	}

}