package com.cyendra.sokoban.frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.cyendra.sokoban.manager.DataManager;
import com.cyendra.sokoban.manager.GameManager;

public class GameFrame extends JFrame implements ActionListener, MouseListener, KeyListener {
	
	GameManager gm;
	DataManager dm;
	
	private String title = "推箱子";
	private int leftX = 0, leftY = 0;
	private int width = 0, height = 0;
	private int mapRow = 0, mapColumn = 0;
	private Image pic[] = null;
	
	public GameFrame() {
		init();
	}
	public void init(){
		dm = new DataManager();
		gm = new GameManager();
		gm.init(dm.createMap(0));
		this.setTitle(title);
		this.setSize(600,600);
		this.setLocation(300, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container cont = this.getContentPane();
		cont.setLayout(null);
		cont.setBackground(Color.black);
		pic = dm.getPic();
		width = this.getWidth();
		height = this.getHeight();
		this.setFocusable(true);
		//initMap();
		getMapSizeAndPosition();
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	private void getMapSizeAndPosition(){
		mapRow = gm.getMap().getRow();
		mapColumn = gm.getMap().getColumn();
		leftX = (width - mapColumn * 30) / 2;
		leftY = (width - mapRow * 30) / 2;
		System.out.println(leftX+" "+leftY+" "+mapRow+" "+mapColumn);
	}
	
	public void paint(Graphics g){
		for (int i=0;i<mapRow;i++){
			for (int j=0;j<mapColumn;j++){
				byte tp = gm.getMap().getMap(i, j);
				if (tp>0){
					g.drawImage(pic[tp], leftX+j*30, leftY+i*30, this);
				}
			}
		}
		g.setColor(Color.red);
		g.setFont(new Font("楷体_2312", Font.BOLD, 30));
		g.drawString("现在是第", 150, 140);
		g.drawString(String.valueOf(gm.getMap().getLevel()+1), 310, 140);
		g.drawString("关", 360, 140);
	}
	
	//----接口--------------------------------------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP:
			gm.manMoveTo(gm.UP);
			break;
		case KeyEvent.VK_DOWN:
			gm.manMoveTo(gm.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			gm.manMoveTo(gm.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			gm.manMoveTo(gm.RIGHT);
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
