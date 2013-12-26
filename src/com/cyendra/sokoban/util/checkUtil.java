package com.cyendra.sokoban.util;

/**
 * 一个用于检测各种各样杂项的类
 * */
public class checkUtil {
	public static boolean isMan(byte k){
		boolean res = false;
		if (k>=5&&k<=13&&k!=9) res = true;
		return res;
	}
}
