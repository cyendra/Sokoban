package com.cyendra.sokoban.file;

import java.io.File;
import java.util.Scanner;

/**
 * ��ȡ��ͼ��Դ�ļ�����
 * @author cyendra
 * @version 1.0
 * */
public class MapLoader {
	
	/** ��ȡ�ļ��еĵ�ͼ���� */
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
				System.out.println("��ͼ���ݶ�ȡ��������\n"+e.toString());
			}
		}
		return map;
	}
	
}
