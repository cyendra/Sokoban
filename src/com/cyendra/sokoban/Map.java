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
	/** 初始化一个地图对象 */
	public void init(byte map[][]){
		this.map = new byte[map.length][map[0].length];
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[0].length;j++){
				this.map[i][j] = map[i][j];
			}
		}
		findMan();
	}
	
	/** 计算主角在地图中的位置 */
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
	
	//是不是人啊
	private boolean isMan(byte k){
		boolean res = false;
		if (k>=5&&k<=13&&k!=9) res = true;
		return res;
	}
	/** 设置主角的位置 */
	public void setMan(int x, int y){
		manX = x;
		manY = y;
	}
	
	/** 获取主角在地图中的X坐标 */
	public int getManX(){
		return manX;
	}
	
	/** 获取主角在地图中的y坐标 */
	public int getMaxY(){
		return manY;
	}
	
	/** 获取(i,j)在地图中的元素 */
	public byte getMap(int i,int j){
		return map[i][j];
	}
	
	/** 设置(i,j)的元素类型 */
	public void setMap(int i,int j,byte t){
		map[i][j]=t;
	}
	/** 获取当前等级 */
	public int getLevel(){
		return level;
	}
	
	/** 检测地图合法性 */
	public boolean isGoodMap(){
		return true;
	}
	
	/** 判断(i,j)是否为空 */
	public boolean isGrassOrEnd(int i,int j){
		if (map[i][j]==4||map[i][j]==9) return true;
		return false;
	}
	
	/**(i,j)为箱子*/
	public boolean isBox(int x,int y){
		if (map[x][y]==2||map[x][y]==3) return true;
		return false;
	}
	
	/** 判断(i,j)是否在地图上 */
	public boolean inMap(int x,int y){
		if (x>=0&&x<map.length&&y>=0&&y<map[x].length&&map[x][y]>0) return true;
		return false;
	}
	
}
