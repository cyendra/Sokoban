package com.cyendra.sokoban.manager;

import com.cyendra.sokoban.Map;
import com.cyendra.sokoban.file.MapLoader;

/**
 * 读取和管理游戏地图数据的类
 * */
public class DataManager {
	private byte map[][][];
	private int maxLevel;
	public DataManager() {
		LoadMap();
	}
	public void LoadMap(){
		map = MapLoader.loadMap();
		maxLevel = map.length;
	}
	public byte[][] getMap(int level){
		if (level < 0) level = 0;
		if (level >= maxLevel) level = maxLevel - 1;
		byte res[][] = new byte[map[level].length][map[level][0].length];
		for (int i=0;i<res.length;i++){
			for (int j=0;j<res[i].length;i++){
				res[i][j] = map[level][i][j];
			}
		}
		return res;
	}
	public int getMaxLevel(){
		return maxLevel;
	}
	public Map createMap(int level){
		Map mp = new Map(getMap(level),level);
		return mp;
	}
}
