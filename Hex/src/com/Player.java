package com;


import java.awt.*;


public interface Player {

	//移动
	void getPlayerTurn();

	//回退
	void undoCalled();

	//设置玩家颜色
	void setColor(Color color);

	//获得玩家颜色
	Color getColor();
	
	//储存所下位置
	void setMove(Object o, Point hex);
}
