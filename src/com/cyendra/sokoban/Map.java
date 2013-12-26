package com.cyendra.sokoban;

import com.cyendra.sokoban.util.checkUtil;

public class Map {

	private int manX,manY;
	private byte map[][];
	private int level;
	
	public Map(byte map[][]){
		this.init(map);
	}
	public Map(byte map[][],int level) {
		this.init(map);
		this.level = level;
	}
	/**
	 * 初始化一个地图对象
	 * */
	public void init(byte map[][]){
		this.map = new byte[map.length][map[0].length];
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[0].length;j++){
				this.map[i][j] = map[i][j];
			}
		}
		findMan();
	}
	
	/**
	 * 计算主角在地图中的位置
	 * */
	public void findMan(){
		bk:for (int i=0;i<map.length;i++){
			for (int j=0;j<map[i].length;j++){
				if (checkUtil.isMan(map[i][j])){
					manX = i;
					manY = j;
					break bk;
				}
			}
		}
	}
	
	/**
	 * 获取主角在地图中的X坐标
	 * */
	public int getManX(){
		return manX;
	}
	
	/**
	 * 获取主角在地图中的y坐标
	 * */
	public int getMaxY(){
		return manY;
	}
	
	/**
	 * 获取(i,j)在地图中的元素
	 * */
	public byte getMap(int i,int j){
		return map[i][j];
	}
	
	/**
	 * 获取当前等级
	 * */
	public int getLevel(){
		return level;
	}
	
}
