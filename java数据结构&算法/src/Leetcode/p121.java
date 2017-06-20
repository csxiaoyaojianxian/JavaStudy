package Leetcode;

public class p121 {
	public static int maxProfit(int[] prices) {
        int e = 0;
        int low = 0;
        for(int i=1;i<prices.length;i++){
        	int temp = prices[i] - prices[low];
        	if(temp > e){
        		e = temp;
        	}
        	if(prices[i] < prices[low]){
        		low = i;
        	}
        }
		return e;
    }
	
	public static void main(String[] args) {
		int[] prices = {7, 6,4,3,1};
		System.out.println(maxProfit(prices));
	}
}
