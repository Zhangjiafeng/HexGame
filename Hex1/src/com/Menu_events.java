package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu_events implements ActionListener {
    private GameObject game;

    Menu_events(GameObject game) {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == game.menuItemUndo) {
            GameAction.undo(game);
        }
    }
}
