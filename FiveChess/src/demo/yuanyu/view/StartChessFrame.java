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

import demo.yuanyu.model.SelfResources;



public class StartChessFrame extends JFrame {
	private ChessBoard chessBoard;
	private JPanel toolbar;
	private JButton startButton, backButton, exitButton;
	
	private JMenuBar menuBar;
	private JMenu sysMenu;
	private JMenuItem startMenuItem, exitMenuItem, backMenuItem;
	
	public StartChessFrame(){
		setTitle(SelfResources.GAME_NAME);
		chessBoard = new ChessBoard();
		
		//
		Container contentPane = getContentPane();
		contentPane.add(chessBoard);
		chessBoard.setOpaque(true);
		
		//創建添加menu
		menuBar = new JMenuBar();
		sysMenu = new JMenu(SelfResources.SYSTEM);
		
		startMenuItem = new JMenuItem(SelfResources.RESTART);
		exitMenuItem = new JMenuItem(SelfResources.EXIT);
		backMenuItem = new JMenuItem(SelfResources.REGRADE);
		
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
	    
	    startButton = new JButton(SelfResources.RESTART);
	    exitButton = new JButton(SelfResources.EXIT);
	    backButton = new JButton(SelfResources.REGRADE);
	    
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
	    setSize(610, 700);
	    setResizable(false);
	    
	}
	
	private class MyItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			
			if( obj == startMenuItem || obj == startButton){
				System.out.println(SelfResources.RESTART);
				chessBoard.restartGame();
			}
			else if( obj == exitMenuItem || obj == exitButton){
				System.out.println(SelfResources.EXIT);
				System.exit(0);
			}
			else if( obj == backMenuItem || obj == backButton){
				System.out.println(SelfResources.REGRADE);
				chessBoard.goback();
			}
		}
		
	}
}
