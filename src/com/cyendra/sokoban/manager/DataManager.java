package com.cyendra.sokoban.manager;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cyendra.sokoban.Map;
import com.cyendra.sokoban.file.MapLoader;

/**
 * ��ȡ�͹�����Ϸ��ͼ���ݵ���
 * @author cyendra
 * @version 1.0
 * */
public class DataManager {
	
	private byte map[][][];// ��ȡ����ȫ����ͼ����
	private int maxLevel;// ��ͼ���������ؿ���
	
	/** ����һ�����ݹ����� */
	public DataManager() {
		loadMap();
		System.out.println("���ݹ������������...");
	}
	
	/** ���ļ��м��ص�ͼ */
	public void loadMap(){
		map = MapLoader.loadMap();
		maxLevel = map.length;
	}
	
	// ��ȡ�ȼ�Ϊlevel�ĵ�ͼ��һ������
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
	
	/** ��ȡ���ؿ��� */
	public int getMaxLevel(){
		return maxLevel;
	}
	
	/** ����һ���ȼ�Ϊlevel�ĵ�ͼ���� */
	public Map createMap(int level){
		if (level < 0) level = 0;
		if (level >= maxLevel) level = maxLevel - 1;
		Map mp = new Map(getMap(level),level);
		return mp;
	}
	
	/** ���ļ��м���Image */
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
