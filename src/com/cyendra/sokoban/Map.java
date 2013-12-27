package com.cyendra.sokoban;

public class Map {

	private int manX,manY;
	private byte map[][];
	private int level;
	public byte[][] getMap(){
		return map;
	}
	public Map(byte map[][]){
		this.init(map);
	}
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
	
	public int getRow(){
		return map.length;
	}
	public int getColumn(){
		return map[0].length;
	}
	
	//�ǲ����˰�
	private boolean isMan(byte k){
		boolean res = false;
		if (k>=5&&k<=13&&k!=9) res = true;
		return res;
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
	
	/** �ж�(i,j)�Ƿ�Ϊ�� */
	public boolean isGrassOrEnd(int i,int j){
		if (map[i][j]==4||map[i][j]==9) return true;
		return false;
	}
	
	/**(i,j)Ϊ����*/
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
