package Leetcode;
// 最长回文子序列
// 思路 确定回文中点，调用判断函数
public class p005 {

	public String longestPalindrome(String s) {
        int len = s.length();
        int maxLength = 0, k, temp, low=0, high=0;
        if(len<=1){
        	return s;
        }
        
		for(int i=0;i<len;i++){
			temp = getKth(s, i, i)-1;
			if((temp*2+1)>maxLength){
				maxLength = temp*2+1;
				low = i - temp;
				high  = i + temp;
			}
			if(i+1<len && s.charAt(i) == s.charAt(i+1)){
				temp = getKth(s, i, i+1)-1;
				if((temp*2+2)>maxLength){
					maxLength = temp*2+2;
					low = i - temp;
					high  = i +1 + temp;
				}
			}
		}
		return s.substring(low, high+1);
    }
	// 获取包括当前元素在内的左右拓展的长度
	public int getKth(String s, int low, int high){
		int count = 0;
		while(low>=0 && high <= s.length()-1 && s.charAt(low)==s.charAt(high)){
			low--;
			high++;
			count++;
		}
		return count;
	}
	public static void main(String[] args) {
		
		p005 p = new p005();
		System.out.println( p.longestPalindrome("ccc") );
	}
}