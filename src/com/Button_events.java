package com;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Button_events implements ActionListener {
    private int x, y;
    private GameObject game;

    Button_events(int x, int y, GameObject game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub


        if (game != null && game.gamePiece[x][y].getTeam() == 0)
            GameAction.setPiece(new Point(x, y), game);


    }


}
