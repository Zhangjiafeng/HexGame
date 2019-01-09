package com;


/**
 * Created by CYQ on 2017/7/9.
 */
public class HexGame {

    public static void setPlayer(GameObject game) {
        if (game.player1Type == 0)
            game.player1 = new HumanObject(1, game);
        else
            game.player1 = new AIObject(1, game);

        if (game.player2Type == 0)
            game.player2 = new HumanObject(2, game);
        else
            game.player2 = new AIObject(2, game);
    }

    public static void main(String[] args) {
        GameObject game = new GameObject(11);
        setPlayer(game);
        game.run();
    }
}
