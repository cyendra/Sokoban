package com.cyendra.sokoban.manager;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		System.out.println("数据管理器加载完毕");
	}
	public void LoadMap(){
		map = MapLoader.loadMap();
		/*
		System.out.println(map.length);
		System.out.println(map[0].length);
		System.out.println(map[0][0].length);
		System.out.println(map[0].length+"  "+map[0][0].length);
		for (int i=0;i<map[0].length;i++){
			for (int j=0;j<map[0][i].length;j++){
				System.out.print(map[0][i][j]);
			}
			System.out.println();
		}
		*/
		maxLevel = map.length;
	}
	public byte[][] getMap(int level){
		if (level < 0) level = 0;
		if (level >= maxLevel) level = maxLevel - 1;
		byte res[][] = new byte[map[level].length][map[level][0].length];
		
		//System.out.println(res.length+"  "+res[0].length);
		
		for (int i=0;i<res.length;i++){
			for (int j=0;j<res[i].length;j++){
				res[i][j] = map[level][i][j];
			}
		}
		return res;
	}
	public Image[] getPic(){
		Image pic[] = new Image[14];
		for (int i=0;i<=13;i++){
			File f = new File("images\\pic"+i+".JPG");
			try {
				pic[i] = ImageIO.read(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pic;
	}
	
	
	public int getMaxLevel(){
		return maxLevel;
	}
	public Map createMap(int level){
		if (level < 0) level = 0;
		if (level >= maxLevel) level = maxLevel - 1;
		Map mp = new Map(getMap(level),level);
		return mp;
	}
}
