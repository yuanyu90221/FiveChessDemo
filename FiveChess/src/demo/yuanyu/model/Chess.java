package demo.yuanyu.model;

import java.awt.Color;

public class Chess {
	// 棋盤中的x座標
	private int x;
	// 棋盤中的y座標
	private int y;
	// 顏色
	private Color color;
	// 直徑
	public static final int DIAMETER = 30;
	
	
	public Chess(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}
	
	
}
