package com;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PolygonButton extends JButton{
	int x;
	int y;
	int team=0;//队伍标志
    boolean checkedflage = false;
    Shape shape;

    PolygonButton() {
  super();
        Dimension size = getPreferredSize();


  size.width=size.height=Math.max(size.width,size.height);

  setPreferredSize(size);
  setContentAreaFilled(false);
 }
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        if(getModel().isArmed()){
            g.setColor(Color.LIGHT_GRAY);
        }else{
            g.setColor(getBackground());

        }
        int xCenter=getWidth()/2;
        int yCenter=getHeight()/2;
        int radius=(int)(getHeight()/2);
        Polygon polygon=new Polygon();
        polygon.addPoint(yCenter,xCenter+radius);
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*Math.PI/6)) );
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*2*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*2*Math.PI/6)));
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*3*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*3*Math.PI/6)));
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*4*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*4*Math.PI/6)));
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*5*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*5*Math.PI/6)));

        g.fillPolygon(polygon);



        super.paintComponents(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.black);

        int xCenter=getWidth()/2;
        int yCenter=getHeight()/2;
        int radius=(int)(getHeight()/2);
        Polygon polygon=new Polygon();
        polygon.addPoint(yCenter,xCenter+radius);
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*Math.PI/6)) );
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*2*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*2*Math.PI/6)));
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*3*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*3*Math.PI/6)));
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*4*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*4*Math.PI/6)));
        polygon.addPoint((int)(yCenter-radius*Math.sin(2*5*Math.PI/6)),(int)(xCenter+radius*Math.cos(2*5*Math.PI/6)));
        g.drawPolygon(polygon);

    }
    public boolean contains(int x, int y) {
        if((shape==null)||(!shape.getBounds().equals(getBounds()))){

            shape=new Ellipse2D.Float(0,0,getWidth(),getHeight());
        }

        return shape.contains(x,y);
    }

    public static boolean checkSpot(int team, int x, int y) {
        if (team == 1 && x == 0) {
            return true;
        }
        if (team == 2 && y == 0) {
            return true;
        }
        return false;
    }



    public boolean checkpiece(int team, int x, int y, PolygonButton[][] gamePeace) { //used for checking victory conditions
        if (team == this.team && !checkedflage) {
            checkedflage = !checkedflage;
            if (checkSpot(team, x, y) || checkWinTeam(team, x, y, gamePeace)) {
//				objectColor = Color.GREEN;
                return true;
            }
        }
        return false;
    }

    public static boolean checkWinTeam(int team, int x, int y, PolygonButton[][] gamePeace) { //used for checking victory condition
        if (y < gamePeace.length && x - 1 >= 0
                && gamePeace[x - 1][y].checkpiece(team, x - 1, y, gamePeace)) {
            return true;
        }
        if (y < gamePeace.length && x + 1 < gamePeace.length
                && gamePeace[x + 1][y].checkpiece(team, x + 1, y, gamePeace)) {
            return true;
        }
        if (x < gamePeace.length && y - 1 >= 0
                && gamePeace[x][y - 1].checkpiece(team, x, y - 1, gamePeace)) {
            return true;
        }
        if (x < gamePeace.length && y + 1 < gamePeace.length
                && gamePeace[x][y + 1].checkpiece(team, x, y + 1, gamePeace)) {
            return true;
        }
        if (y + 1 < gamePeace.length
                && x - 1 >= 0
                && gamePeace[x - 1][y + 1].checkpiece(team, x - 1, y + 1,
                gamePeace)) {
            return true;
        }
        if (y - 1 < gamePeace.length
                && x + 1 < gamePeace.length
                && y - 1 >= 0
                && gamePeace[x + 1][y - 1].checkpiece(team, x + 1, y - 1,
                gamePeace)) {
            return true;
        }


        return false;
    }

    public String checkpieceShort(int team, int x, int y, PolygonButton[][] gamePeace) { //used for checking victory condition
        if (team == this.team && !checkedflage) {
            checkedflage = true;
            String tempHolder=findShortestPath(team, x, y, gamePeace);
            checkedflage = false;
            if (tempHolder!=null) {
                return tempHolder;
            }
            checkedflage = false;
        }

        return null;

    }

    public static String findShortestPath(int team, int x, int y, PolygonButton[][] gamePeace) { //used for checking victory condition
        if(checkSpot(team, x, y)){return "";}
        String[] allPath=new String[6];

        if (y < gamePeace.length && x - 1 >= 0) {
            allPath[0]=gamePeace[x - 1][y].checkpieceShort(team, x - 1, y, gamePeace);
        }
        if (y < gamePeace.length && x + 1 < gamePeace.length) {
            allPath[1]=gamePeace[x + 1][y].checkpieceShort(team, x + 1, y, gamePeace);

        }
        if (x < gamePeace.length && y - 1 >= 0) {
            allPath[2]=gamePeace[x][y - 1].checkpieceShort(team, x, y - 1, gamePeace);
        }
        if (x < gamePeace.length && y + 1 < gamePeace.length) {
            allPath[3]=gamePeace[x][y + 1].checkpieceShort(team, x, y + 1, gamePeace);
        }
        if (y + 1 < gamePeace.length
                && x - 1 >= 0) {
            allPath[4]=gamePeace[x - 1][y + 1].checkpieceShort(team, x - 1, y + 1,
                    gamePeace);
        }

        if (y - 1 < gamePeace.length
                && x + 1 < gamePeace.length
                && y - 1 >= 0) {
            allPath[5]=gamePeace[x + 1][y - 1].checkpieceShort(team, x + 1, y - 1,
                    gamePeace);
        }
        int dir= findShortestString(allPath,0,5);
        if (allPath[dir]==null||allPath[dir]=="null") return null;
        switch (dir){
            //ud=y-1 & x+1  dd = y+1 & x-1  uy=y-1 dy=y+1 lx=x-1 rx=x+1
            case 0: return "lx"+allPath[0];
            case 1: return "rx"+allPath[1];
            case 2: return "uy"+allPath[2];
            case 3: return "dy"+allPath[3];
            case 4: return "dd"+allPath[4];
            case 5: return "ud"+allPath[5];
        }
        return null;
    }

    static int findShortestString(String[] paths, int lo, int hi) { //used for checking victory condition
        if ((lo==hi)){return hi;}
        int temp =findShortestString(paths,lo+1,hi);
        return  stringL(paths[lo])<stringL(paths[temp])?lo:temp;

    }

    private static int stringL(String temp){ //used for checking victory condition
        if (temp==null) return Integer.MAX_VALUE;
        else return temp.length();

    }

    private enum posDir{
        lx,rx,uy,dy,dd,ud

    }

    public static void colorPath(int x,int y, String path, GameObject game){
        while (path!=null&&path.length()!=0){
            switch (posDir.valueOf(path.substring(0,2))){
                //ud=y-1 & x+1  dd = y+1 & x-1  uy=y-1 dy=y+1 lx=x-1 rx=x+1
                case lx: x-=1; break;
                case rx: x+=1;break;
                case uy: y-=1;break;
                case dy: y+=1;break;
                case dd:  y+=1; x-=1;break;
                case ud:  y-=1; x+=1; break;
            }
            game.gamePiece[x][y].setBackground(Color.GREEN);
            path=path.substring(2,path.length());
        } System.out.println("done");
    }



    public void setTeam(int t, GameObject game) {
        this.team = t;
        if (this.team == 1)
            setBackground(game.player1.getColor());
        else if(this.team==2)
            setBackground(game.player2.getColor());
        else
            setBackground(Color.lightGray);
    }

    public int getTeam() {
        return team;
    }

}

 