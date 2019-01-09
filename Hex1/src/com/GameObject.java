package com;

import javax.swing.*;

public class GameObject {
    final int gridSize;
    JMenuItem menuItemUndo;
    int moveNumber;
    MoveList moveList;
    int currentPlayer;
    Player player1;
    Player player2;
    int player1Type = 0;
    int player2Type = 1;
    PolygonButton[][] gamePiece;
    private boolean run = true;

    GameObject(int gridSize) {
        JFrame frame = new JFrame("HexGame");
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu operateMenu = new JMenu("操作");
        menuItemUndo = new JMenuItem("撤回");
        menuItemUndo.addActionListener(new Menu_events(this));
        operateMenu.add(menuItemUndo);
        menuBar.add(operateMenu);
        this.gridSize = gridSize;
        gamePiece = new PolygonButton[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++) {
                gamePiece[i][j] = new PolygonButton();
                gamePiece[i][j].x = i;
                gamePiece[i][j].y = j;
                gamePiece[i][j].setTeam(0, this);
                gamePiece[i][j].setBounds(22 * j + 44 * i + 0, 0 + j * 37, 50, 50);
                gamePiece[i][j].addActionListener(new Button_events(i, j, this));
                frame.add(gamePiece[i][j]);
            }
        moveNumber = 1;
        moveList = new MoveList();
        currentPlayer = 1;
        run = true;
        frame.setLayout(null);
        frame.setSize(740, 510);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean checkForWinner() {
        GameAction.checkedFlagReset(this);
        if (GameAction.checkWinPlayer(1, this)) {
            run = false;
        } else if (GameAction.checkWinPlayer(2, this)) {
            run = false;
        }

        return run;
    }

    public void run() {
        while (run) {//Loop the game
            if (checkForWinner()) {
                GameAction.getPlayer(currentPlayer, this).getPlayerTurn();
            }

            currentPlayer = (currentPlayer % 2) + 1;
        }
        System.out.println("Game over");
    }
    public boolean checkForRunning(){
        return run;
    }

}




