package com.cyendra.sokoban.file;

import java.io.File;
import java.util.Scanner;

/**
 * 读取地图资源文件的类
 * @author cyendra
 * @version 1.0
 * */
public class MapLoader {
	
	/** 读取文件中的地图数据 */
	public static byte[][][] loadMap(){
		byte[][][] map = null;
		File file = new File("data/map.mp");
		if (file.exists()){
			try {
				Scanner scan = new Scanner(file);
				int len = scan.nextInt();
				System.out.println(len);
				map = new byte[len][][];
				for (int k=0;k<len;k++){
					int n = scan.nextInt();
					int m = scan.nextInt();
					System.out.println(n+" "+m);
					map[k] = new byte[n][m];
					for (int i=0;i<n;i++){
						for (int j=0;j<m;j++){
							map[k][i][j] = scan.nextByte();
							System.out.print(map[k][i][j]);
						}
						System.out.println();
					}
					System.out.println();
				}
				scan.close();
			}
			catch (Exception e){
				System.out.println("地图数据读取出错！！！\n"+e.toString());
			}
		}
		return map;
	}
	
}
