package Leetcode;

public class p070 {
	static int[] array = new int[50];
	public static int climbStairs(int n) {
        if(n == 1){
        	return 1; 
        }else if(n==2){
        	return 2;
        }
		return (array[n-1]==0?array[n-1]=climbStairs(n-1):array[n-1]) + (array[n-2]==0?array[n-2]=climbStairs(n-2):array[n-2]);
    }
	
	public static void main(String[] args) {
		System.out.println(climbStairs(10));
		System.out.println(array[2]);
	}
}
