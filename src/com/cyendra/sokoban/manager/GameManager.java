package com.cyendra.sokoban.manager;

import com.cyendra.sokoban.Map;

public class GameManager {
	public final byte WALL = 1, BOX = 2, BOX_ON_END = 3, END = 4, 
			MAN_DOWN = 5, MAN_LEFT = 6, MAN_RIGHT = 7, MAN_UP = 8, GRASS = 9, 
			MAN_DOWN_ON_END = 10, MAN_LEFT_ON_END = 11,MAN_RIGHT_ON_END = 12, MAN_UP_ON_END = 13;
	private final int direct[][] = { {-1,0},{0,1},{1,0},{0,-1} };
	public final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	private Map map;
	public Map getMap(){
		return map;
	}
	public GameManager(){}
	public GameManager(Map map) {
		init(map);
	}
	public void init(Map map){
		this.map = map;
	}
	public boolean manMoveTo(int dir){
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
	
	/** 判断是否胜利 */
	public boolean isWin(){
		for (int i=0;i<map.getMap().length;i++){
			for (int j=0;j<map.getMap()[0].length;j++){
				if (map.getMap(i, j)==END) return false;
			}
		}
		return true;
	}
	
	private void BoxIn(int x, int y) {
		byte tp = map.getMap(x,y);
		if (tp == GRASS) map.setMap(x, y, BOX);
		if (tp == END) map.setMap(x, y, BOX_ON_END);
	}
	private void BoxOut(int x, int y) {
		byte tp = map.getMap(x,y);
		if (tp == BOX) map.setMap(x, y, GRASS);
		if (tp == BOX_ON_END) map.setMap(x, y, END);
	}
	//角色离开此地
	private void manOut(int x,int y){
		byte tp = map.getMap(x, y);
		if (tp>=5 && tp<=8) map.setMap(x, y, GRASS);
		if (tp>=10 && tp<=13) map.setMap(x, y, END);
	}
	//角色以dir方向进入此地
	private void manIn(int x,int y,int dir){
		byte tp = map.getMap(x, y);
		if (tp == END) {
			switch(dir){
			case UP:
				map.setMap(x, y, MAN_UP_ON_END);
				break;
			case RIGHT:
				map.setMap(x, y, MAN_RIGHT_ON_END);
				break;
			case DOWN:
				map.setMap(x, y, MAN_DOWN_ON_END);
				break;
			case LEFT:
				map.setMap(x, y, MAN_LEFT_ON_END);
				break;
			}
		}
		if (tp == GRASS){
			switch(dir){
			case UP:
				map.setMap(x, y, MAN_UP);
				break;
			case RIGHT:
				map.setMap(x, y, MAN_RIGHT);
				break;
			case DOWN:
				map.setMap(x, y, MAN_DOWN);
				break;
			case LEFT:
				map.setMap(x, y, MAN_LEFT);
				break;
			}
		}
		map.setMan(x, y);
	}
}
