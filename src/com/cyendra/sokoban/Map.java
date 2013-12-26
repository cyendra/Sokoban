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
	 * ��ʼ��һ����ͼ����
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
	 * ���������ڵ�ͼ�е�λ��
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
	 * ��ȡ�����ڵ�ͼ�е�X����
	 * */
	public int getManX(){
		return manX;
	}
	
	/**
	 * ��ȡ�����ڵ�ͼ�е�y����
	 * */
	public int getMaxY(){
		return manY;
	}
	
	/**
	 * ��ȡ(i,j)�ڵ�ͼ�е�Ԫ��
	 * */
	public byte getMap(int i,int j){
		return map[i][j];
	}
	
	/**
	 * ��ȡ��ǰ�ȼ�
	 * */
	public int getLevel(){
		return level;
	}
	
}
