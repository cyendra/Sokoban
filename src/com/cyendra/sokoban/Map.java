package com.cyendra.sokoban;
/**
 * ��������Ϸ�ĵ�ͼ��
 * �������е�ͼ������Ϣ
 * @author cyendra
 * @version 1.0
 * */
public class Map {

	private int manX,manY;// ��������λ�õ�����
	private byte map[][];// ��ά��ͼԪ������
	private int level;// ��ǰ��ͼ�ĵȼ�
	
	/** ��ͼԪ�غ���� */
	public final static byte WALL = 1, BOX = 2, BOX_ON_END = 3, END = 4, 
			MAN_DOWN = 5, MAN_LEFT = 6, MAN_RIGHT = 7, MAN_UP = 8, GRASS = 9, 
			MAN_DOWN_ON_END = 10, MAN_LEFT_ON_END = 11,MAN_RIGHT_ON_END = 12, MAN_UP_ON_END = 13;
	
	/** ����һ����ͼ����,���趨�ȼ� */
	public Map(byte map[][]){
		this.init(map);
	}
	
	/** ����һ����ͼ����ָ���ȼ� */
	public Map(byte map[][],int level) {
		this.init(map);
		this.level = level;
	}
	
	/** ��ʼ��һ����ͼ���� */
	public void init(byte map[][]){
		this.map = new byte[map.length][map[0].length];
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[0].length;j++){
				this.map[i][j] = map[i][j];
			}
		}
		findMan();
	}
	
	// �ж�����k�Ƿ�Ϊ����
	private boolean isMan(byte k){
		boolean res = false;
		if (k>=5&&k<=13&&k!=9) res = true;
		return res;
	}
	
	/** ���������ڵ�ͼ�е�λ�� */
	public void findMan(){
		bk:for (int i=0;i<map.length;i++){
			for (int j=0;j<map[i].length;j++){
				if (isMan(map[i][j])){
					manX = i;
					manY = j;
					break bk;
				}
			}
		}
	}
	
	/** ��ȡ��ͼ������ */
	public int getRow(){
		return map.length;
	}
	
	/** ��ȡ��ͼ������ */
	public int getColumn(){
		return map[0].length;
	}
	
	/** �������ǵ�λ�� */
	public void setMan(int x, int y){
		manX = x;
		manY = y;
	}
	
	/** ��ȡ�����ڵ�ͼ�е�X���� */
	public int getManX(){
		return manX;
	}
	
	/** ��ȡ�����ڵ�ͼ�е�y���� */
	public int getMaxY(){
		return manY;
	}
	
	/** ��ȡ(i,j)�ڵ�ͼ�е�Ԫ�� */
	public byte getMap(int i,int j){
		return map[i][j];
	}
	
	/** ����(i,j)��Ԫ������ */
	public void setMap(int i,int j,byte t){
		map[i][j]=t;
	}
	
	/** ��ȡ��ǰ�ȼ� */
	public int getLevel(){
		return level;
	}
	
	/** ����ͼ�Ϸ��� */
	public boolean isGoodMap(){
		return true;
	}
	
	/** �ж�(i,j)�Ƿ�Ϊ�յ� */
	public boolean isGrassOrEnd(int i,int j){
		if (map[i][j]==4||map[i][j]==9) return true;
		return false;
	}
	
	/** �ж�(i,j)Ϊ���� */
	public boolean isBox(int x,int y){
		if (map[x][y]==2||map[x][y]==3) return true;
		return false;
	}
	
	/** �ж�(i,j)�Ƿ��ڵ�ͼ�� */
	public boolean inMap(int x,int y){
		if (x>=0&&x<map.length&&y>=0&&y<map[x].length&&map[x][y]>0) return true;
		return false;
	}
	
}
