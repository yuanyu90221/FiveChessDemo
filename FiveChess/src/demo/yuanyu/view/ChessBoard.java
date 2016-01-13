package demo.yuanyu.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import demo.yuanyu.model.Chess;

public class ChessBoard extends JPanel implements MouseListener {
	// 邊距
	public static final int MARGIN = 30;
	// 網格間距
	public static final int GRID_SPAN = 35;
	// 棋盤行數
	public static final int ROWS = 15;
	// 棋盤列數
	public static final int COLS = 15;
	// 初始每個元素為null
	private Chess[] chessList = new Chess[(ROWS + 1)*(COLS +1)];
	// 預設黑棋先
	boolean isBlack = true;
	// 遊戲是否結束
	boolean gameOver = false;
	// 當前棋盤棋子個數
	int chessCount;
	// 目前棋子的座標
	int xIndex, yIndex;
	
	Image img;
	Color colortemp;
	
	public ChessBoard(){
		
		img = Toolkit.getDefaultToolkit().getImage("D:/board.jpg");
		
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// 轉換 x座標
				int xInit = (e.getX() - MARGIN + GRID_SPAN/2)/GRID_SPAN;
				// 轉換y座標
				int yInit = (e.getY() - MARGIN + GRID_SPAN/2)/GRID_SPAN;
				
				if(xInit < 0 || xInit > ROWS || yInit < 0 || yInit > COLS || gameOver || isPlacedChess(xInit, yInit)){
					
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				else {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 遊戲結束時，停止遊戲
		if(gameOver)
			return ;
		
		String colorName = isBlack? "黑棋" : "白棋";
		
		// 座標轉換
		xIndex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
		yIndex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
		Color color = isBlack? Color.black : Color.white;
		// 落在棋盤外不能下
		if(xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS || getChess(xIndex, yIndex ,Color.black)!=null || getChess(xIndex, yIndex ,Color.white)!=null){
			return;
		}
		
		Chess ch = new Chess(xIndex, yIndex, isBlack? Color.black: Color.white);
		chessList[chessCount++] = ch;
				
		repaint();
		
		if(isWin()){
			String msg = String.format("恭喜，%s贏了!", colorName);
			JOptionPane.showMessageDialog(this, msg);
			gameOver = true;
			restartGame();
			return;
		}
		isBlack = !isBlack;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method

	}

	// 檢查 x,y是否已經放了棋子
	private boolean isPlacedChess(int x, int y){
		for(Chess c: chessList){
			if(c!=null&& c.getX()==x && c.getY()==y){
				return true;
			}
		}
		return false;
	}
	// 繪製
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		int imgWidth = img.getWidth(this);
		int imgHeight = img.getHeight(this);// 獲得圖片的寬度與高度
		int FWidth = getWidth();
		int FHeight = getHeight();// 獲得窗口的寬度與高度
		// 填滿整個window
		g.drawImage(img, 0, 0, FWidth, FHeight ,null);
		
		// 畫橫線
		for(int i = 0 ; i <= ROWS; i++ ){
			g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);		
		}
		// 畫垂直線
		for(int i = 0; i <= COLS; i++){
			g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS *GRID_SPAN);
		}
		
		// 畫棋子
		drawChess(g);
	}
	
	private Chess getChess(int xIndex, int yIndex, Color color){
		for(Chess ch : chessList){
			if(ch != null && ch.getX() == xIndex && ch.getY() == yIndex && ch.getColor() == color){
				System.out.println( "("+ xIndex + ", " + yIndex + ") " + color.toString());
				return ch;
			}
		}
		return null;
	}
	
	private boolean isWin(){
		//  連續棋子的個數
		int continueCount = 1;
		// 橫向 往西找
		for(int x = xIndex-1; x >= 0; x--){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(x, yIndex, color)!= null){
				continueCount++;
			}
			else
				break;
		}
		// 往東找
		for(int x = xIndex+1; x <= COLS; x++){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(x, yIndex, color) != null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount >= 5){
			return true;
		}
		else
			continueCount = 1;
		// 往上下找
		for(int y = yIndex-1; y>=0; y--){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(xIndex, y, color)!=null){
				continueCount++;
			}
			else
				break;
		}
		for(int y = yIndex+1; y<= ROWS; y++){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(xIndex, y, color)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount >= 5){
			return true;
		}
		else
			continueCount = 1;
		
		// 左斜
		for(int x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(x , y, color)!=null){
				continueCount++;
			}
			else
				break;
		}
		for(int x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(x , y, color)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount >= 5){
			return true;
		}
		else
			continueCount = 1;
		
		// 右斜
		for(int x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(x , y, color)!=null){
				continueCount++;
			}
			else
				break;
		}
		for(int x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++){
			Color color = isBlack? Color.black:Color.white;
			if(getChess(x , y, color)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount >= 5){
			return true;
		}
		else
			continueCount = 1;
		return false;
	}
	
	public void restartGame(){
		// clear chess
		for(int i = 0; i < chessList.length; i++){
			chessList[i] = null;
		}
		isBlack = true;
		gameOver = false;
		chessCount = 0;
		repaint();
	}
	
	public void goback(){
		if(chessCount == 0){
			return;
		}
		chessList[chessCount-1] = null;
		chessCount--;
		if(chessCount > 0){
			xIndex = chessList[chessCount-1].getX();
			yIndex = chessList[chessCount-1].getY();
		}
	    isBlack = !isBlack;
	    repaint();
	}
	
	public Dimension getPreferedSize(){
		return new Dimension(MARGIN * 2 + GRID_SPAN * COLS, 
				             MARGIN * 2 + GRID_SPAN * ROWS);
	}

	private void drawChess(Graphics g){
		// 畫棋子
		for(int i = 0 ; i < chessCount; i++){
			// 網格交叉點x,y
			int xPos = chessList[i].getX() * GRID_SPAN + MARGIN;
			int yPos = chessList[i].getY() * GRID_SPAN + MARGIN;
			//設置顏色
			g.setColor(chessList[i].getColor());
			colortemp = chessList[i].getColor();
			RadialGradientPaint paint = null;
			if(colortemp == Color.black){
				 paint = new RadialGradientPaint(xPos - Chess.DIAMETER /2 + 25, 
												 yPos - Chess.DIAMETER /2 + 20, 20, 
												 new float[]{0f,1f},
												 new Color[]{Color.WHITE, Color.BLACK}); 			
			}
			else if(colortemp == Color.white){
				 paint = new RadialGradientPaint(xPos - Chess.DIAMETER /2 + 25, 
						 						 yPos - Chess.DIAMETER /2 + 10, 70, 
						 						 new float[]{0f,1f},
						 						 new Color[]{Color.WHITE, Color.BLACK});
			}
			if(paint != null){
				((Graphics2D) g).setPaint(paint); 
	            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
	            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
			}
			// 標記最後一顆棋子的紅色方框
			Ellipse2D e = new Ellipse2D.Float(xPos - Chess.DIAMETER / 2, yPos - Chess.DIAMETER / 2, 34, 35);			
			((Graphics2D) g).fill(e);
			if(i == chessCount - 1){
				g.setColor(Color.red);
				g.drawRect(xPos - Chess.DIAMETER / 2, yPos - Chess.DIAMETER / 2, 34, 35);
			}
		}// for end
	}
	
}
