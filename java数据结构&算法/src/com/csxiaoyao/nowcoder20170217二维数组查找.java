package com.csxiaoyao;
/**
题目描述
在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 
问题：
1、二分法low = middle+1，high = middle-1
2、测试用例:16,[[]]，java.lang.ArrayIndexOutOfBoundsException: 0
 */
public class nowcoder20170217二维数组查找 {
	
	public static boolean Find(int target, int [][] array) {
			int low, high;
			
			for(int i = 0; i < array.length; i++){
				// 若target < 某行第一个数，则后面的数都不可能存在
				// 需要考虑第二维为空的情况
				if( array[i].length==0 || target < array[i][0] ){
					return false;
				}
				// 二分查找
				low = 0;
				high = array[i].length-1;
				while(low <= high){
					int middle = (low+high)/2;
					if( target == array[i][middle]  ){
						return true;
					}
					if( target < array[i][middle] ){
						high = middle-1;
					}else{
						low = middle+1;
					}
				}
			}
			return false;
    }
	
	public static void main(String[] args) {
//		int[][] array=new int[3][5];
		int[][] array = {};
		
		System.out.println(Find(16,array));

	}

}
