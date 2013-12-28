package com.cyendra.sokoban.manager;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cyendra.sokoban.Map;
import com.cyendra.sokoban.file.MapLoader;

/**
 * 读取和管理游戏地图数据的类
 * @author cyendra
 * @version 1.0
 * */
public class DataManager {
	
	private byte map[][][];// 读取出的全部地图数据
	private int maxLevel;// 地图总数即最大关卡数
	
	/** 构造一个数据管理器 */
	public DataManager() {
		loadMap();
		System.out.println("数据管理器加载完毕...");
	}
	
	/** 从文件中加载地图 */
	public void loadMap(){
		map = MapLoader.loadMap();
		maxLevel = map.length;
	}
	
	// 获取等级为level的地图的一个副本
	private byte[][] getMap(int level){
		if (level < 0) level = 0;
		if (level >= maxLevel) level = maxLevel - 1;
		byte res[][] = new byte[map[level].length][map[level][0].length];
		for (int i=0;i<res.length;i++){
			for (int j=0;j<res[i].length;j++){
				res[i][j] = map[level][i][j];
			}
		}
		return res;
	}
	
	/** 获取最大关卡数 */
	public int getMaxLevel(){
		return maxLevel;
	}
	
	/** 创造一个等级为level的地图对象 */
	public Map createMap(int level){
		if (level < 0) level = 0;
		if (level >= maxLevel) level = maxLevel - 1;
		Map mp = new Map(getMap(level),level);
		return mp;
	}
	
	/** 从文件中加载Image */
	public Image[] getPic(){
		Image pic[] = new Image[14];
		for (int i=0;i<=13;i++){
			File f = new File("images\\pic"+i+".JPG");
			try {
				pic[i] = ImageIO.read(f);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pic;
	}
	
}
