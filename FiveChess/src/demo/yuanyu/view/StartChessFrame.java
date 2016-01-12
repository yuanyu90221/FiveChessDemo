package demo.yuanyu.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class StartChessFrame extends JFrame {
	private ChessBoard chessBoard;
	private JPanel toolbar;
	private JButton startButton, backButton, exitButton;
	
	private JMenuBar menuBar;
	private JMenu sysMenu;
	private JMenuItem startMenuItem, exitMenuItem, backMenuItem;
	
	private final String restart = "重新開始";
	private final String exit = "退出";
	private final String regrade = "悔棋";
	public StartChessFrame(){
		setTitle("單機五子棋");
		chessBoard = new ChessBoard();
		
		//
		Container contentPane = getContentPane();
		contentPane.add(chessBoard);
		chessBoard.setOpaque(true);
		
		//創建添加menu
		menuBar = new JMenuBar();
		sysMenu = new JMenu("系統");
		
		startMenuItem = new JMenuItem(restart);
		exitMenuItem = new JMenuItem(exit);
		backMenuItem = new JMenuItem(regrade);
		
		sysMenu.add(startMenuItem);
		sysMenu.add(exitMenuItem);
		sysMenu.add(backMenuItem);
		
		MyItemListener lis = new MyItemListener();
		
		startMenuItem.addActionListener(lis);
	    backMenuItem.addActionListener(lis);
	    exitMenuItem.addActionListener(lis);
	    
	    menuBar.add(sysMenu);
	    setJMenuBar(menuBar);
	    
	    toolbar = new JPanel();
	    
	    startButton = new JButton(restart);
	    exitButton = new JButton(exit);
	    backButton = new JButton(regrade);
	    
	    toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
	    toolbar.add(startButton);
	    toolbar.add(exitButton);
	    toolbar.add(backButton);
	    
	    startButton.addActionListener(lis);
	    exitButton.addActionListener(lis);
	    backButton.addActionListener(lis);
	    
	    add(toolbar, BorderLayout.SOUTH);
	    add(chessBoard);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	   
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    
	}
	
	private class MyItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if( obj == startMenuItem || obj == startButton){
				System.out.println("重新開始");
				chessBoard.restartGame();
			}
			else if( obj == exitMenuItem || obj == exitButton){
				System.exit(0);
			}
			else if( obj == backMenuItem || obj == backButton){
				System.out.println("悔棋");
				chessBoard.goback();
			}
		}
		
	}
}
