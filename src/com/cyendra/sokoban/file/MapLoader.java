package com.cyendra.sokoban.file;

import java.io.File;
import java.util.Scanner;
/**
 * ��ȡ��ͼ��Դ�ļ�����
 * */
public class MapLoader {
	public static byte[][][] loadMap(){
		byte[][][] map = null;
		File file = new File("data/map.mp");
		if (file.exists()){
			try {
				Scanner scan = new Scanner(file);
				int len = scan.nextInt();
				map = new byte[len][][];
				for (int k=0;k<len;k++){
					int n = scan.nextInt();
					int m = scan.nextInt();
					map[k] = new byte[n][m];
					for (int i=0;i<n;i++){
						for (int j=0;j<m;j++){
							map[k][i][j] = scan.nextByte();
						}
					}
				}
				scan.close();
			}
			catch (Exception e){
				System.out.println("��ͼ���ݶ�ȡ��������\n"+e.toString());
			}
		}
		return map;
	}
}
