package com.cyendra.sokoban.manager;

import com.cyendra.sokoban.Map;

/**
 * ������Ϸ�������߼�����
 * @author cyendra
 * @version 1.0
 * */
public class GameManager {

	public final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;// ����ӳ��

	private final int direct[][] = { {-1,0}, {0,1}, {1,0}, {0,-1} };// ������	
	private Map map;// ��ͼ��
	private boolean gameOn = true;// ��Ϸ�Ƿ�ɲ���

	/** ���캯�� */
	public GameManager(){}

	/** ��ʼ����ϷΪ��ͼmap */
	public void init(Map map){
		this.map = map;
	}
	
	/** ��dir�����ƶ����� */
	public boolean manMoveTo(int dir){
		if (!canMove()) return false;
		int dx = map.getManX()+direct[dir][0];
		int dy = map.getMaxY()+direct[dir][1];
		if (!map.inMap(dx, dy)) return false;
		if (map.isGrassOrEnd(dx, dy)){
			manOut(map.getManX(),map.getMaxY());
			manIn(dx,dy,dir);
		}
		else if (map.isBox(dx, dy)){
			int ddx = dx + direct[dir][0];
			int ddy = dy + direct[dir][1];
			if (!map.inMap(ddx, ddy)) return false;
			if (map.isGrassOrEnd(ddx, ddy)){
				BoxOut(dx,dy);
				BoxIn(ddx,ddy);
				manOut(map.getManX(),map.getMaxY());
				manIn(dx,dy,dir);
			}
		}
		return true;
	}
	
	// �����뿪(x,y)
	private void BoxOut(int x, int y) {
		byte tp = map.getMap(x,y);
		if (tp == Map.BOX) map.setMap(x, y, Map.GRASS);
		if (tp == Map.BOX_ON_END) map.setMap(x, y, Map.END);
	}
	
	// ���ӽ���(x,y)
	private void BoxIn(int x, int y) {
		byte tp = map.getMap(x,y);
		if (tp == Map.GRASS) map.setMap(x, y, Map.BOX);
		if (tp == Map.END) map.setMap(x, y, Map.BOX_ON_END);
	}
	
	//��ɫ�뿪�˵�(x,y)
	private void manOut(int x,int y){
		byte tp = map.getMap(x, y);
		if (tp>=5 && tp<=8) map.setMap(x, y, Map.GRASS);
		if (tp>=10 && tp<=13) map.setMap(x, y, Map.END);
	}
	
	//��ɫ��dir�������˵�(x,y)
	private void manIn(int x,int y,int dir){
		byte tp = map.getMap(x, y);
		if (tp == Map.END) {
			switch(dir){
			case UP:
				map.setMap(x, y, Map.MAN_UP_ON_END);
				break;
			case RIGHT:
				map.setMap(x, y, Map.MAN_RIGHT_ON_END);
				break;
			case DOWN:
				map.setMap(x, y, Map.MAN_DOWN_ON_END);
				break;
			case LEFT:
				map.setMap(x, y, Map.MAN_LEFT_ON_END);
				break;
			}
		}
		if (tp == Map.GRASS){
			switch(dir){
			case UP:
				map.setMap(x, y, Map.MAN_UP);
				break;
			case RIGHT:
				map.setMap(x, y, Map.MAN_RIGHT);
				break;
			case DOWN:
				map.setMap(x, y, Map.MAN_DOWN);
				break;
			case LEFT:
				map.setMap(x, y, Map.MAN_LEFT);
				break;
			}
		}
		map.setMan(x, y);
	}
	
	/** �ж��Ƿ�ʤ�� */
	public boolean isWin(){
		for (int i=0;i<map.getRow();i++){
			for (int j=0;j<map.getColumn();j++){
				if (map.getMap(i, j)==Map.END||map.getMap(i, j)>=10&&map.getMap(i, j)<=13) return false;
			}
		}
		return true;
	}
	
	/** ��ȡ��Ϸ�Ƿ�ɲ��� */
	public boolean canMove(){
		return gameOn;
	}
	
	/** ������Ϸ�Ƿ�ɲ��� */
	public void setGame(boolean ok){
		gameOn = ok;
	}
	
	/** ��ȡ��ͼ�� */
	public Map getMap(){
		return map;
	}
	
}
