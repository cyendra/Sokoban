package com.cyendra.sokoban.util;

/**
 * һ�����ڼ����ָ����������
 * */
public class checkUtil {
	public static boolean isMan(byte k){
		boolean res = false;
		if (k>=5&&k<=13&&k!=9) res = true;
		return res;
	}
}
