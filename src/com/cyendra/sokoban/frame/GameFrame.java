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
import com.cyendra.sokoban.manager.SoundManager;

public class GameFrame extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	// 管理器
	private GameManager gm;
	private DataManager dm;
	private SoundManager sm;
	
	// 双缓冲技术
	private Image iBuffer;
	private Graphics gBuffer;
	
	// 窗体信息
	private String title = "推箱子";
	private int leftX = 0, leftY = 0;
	private int width = 0, height = 0;
	private int mapRow = 0, mapColumn = 0;
	
	// 贴图数据
	private Image pic[] = null;
	
	/** 构造一个游戏窗体 */
	public GameFrame() {
		init();
	}
	
	/** 初始化窗体 */
	public void init(){
		dm = new DataManager();
		gm = new GameManager();
		sm = new SoundManager();
		
		this.setTitle(title);
		this.setSize(600,600);
		this.setLocation(300, 20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusable(true);
		
		pic = dm.getPic();
		sm.loadSound();
		
		width = this.getWidth();
		height = this.getHeight();
		
		this.addKeyListener(this);
		
		newGame(0);
	}
	
	//Container cont = this.getContentPane();
	//cont.setLayout(null);
	
	// 更新地图信息与贴图位置
	private void getMapSizeAndPosition(){
		mapRow = gm.getMap().getRow();
		mapColumn = gm.getMap().getColumn();
		leftX = (width - mapColumn * 30) / 2;
		leftY = (height - mapRow * 30) / 2;
		System.out.println("左上坐标: "+leftX+" "+leftY+" 行列数: "+mapRow+" "+mapColumn);
	}
	
	// 双缓冲技术重载paint
	public void paint(Graphics g){
		if (iBuffer == null){
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			gBuffer = iBuffer.getGraphics();
		}
		
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		
		for (int i=0;i<mapRow;i++){
			for (int j=0;j<mapColumn;j++){
				byte tp = gm.getMap().getMap(i, j);
				if (tp>0){
					gBuffer.drawImage(pic[tp], leftX+j*30, leftY+i*30, this);
				}
			}
		}
		gBuffer.setColor(Color.red);
		gBuffer.setFont(new Font("楷体_2312", Font.BOLD, 30));
		gBuffer.drawString("按R键重新开始本关", 100, 60);
		gBuffer.drawString("现在是第", 100, 100);
		gBuffer.drawString(String.valueOf(gm.getMap().getLevel()+1), 260, 100);
		gBuffer.drawString("关", 310, 100);
		if (!gm.canMove()) {
			if (dm.getMaxLevel()-1==gm.getMap().getLevel()) gBuffer.drawString("恭喜你通关了! 按回车键退出游戏!", 100, 140);
			else gBuffer.drawString("按回车键进入下一关", 100, 140);
		}
		g.drawImage(iBuffer,0,0,this);
	}
	
	// 重载update
	public void update(Graphics g){
		paint(g);
	}
	
	// 从第level关开始新游戏
	private void newGame(int level){
		gm.init(dm.createMap(level));
		gm.setGame(true);
		getMapSizeAndPosition();
		repaint();
	}
	
	//----接口--------------------------------------------------------
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_ENTER:
			if (!gm.canMove()){
				if (dm.getMaxLevel()-1==gm.getMap().getLevel()) System.exit(0);
				else newGame(gm.getMap().getLevel()+1);
			}
			break;
		case KeyEvent.VK_R:
			newGame(gm.getMap().getLevel());
			break;
		case KeyEvent.VK_UP:
			gm.manMoveTo(GameManager.UP);
			break;
		case KeyEvent.VK_DOWN:
			gm.manMoveTo(GameManager.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			gm.manMoveTo(GameManager.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			gm.manMoveTo(GameManager.RIGHT);
			break;
		}
		repaint();
		if (gm.isWin()) gm.setGame(false);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
