package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: p003
 * @Description: Given a string, find the length of the longest substring without repeating characters.
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月18日 下午8:08:05
 *
 */
public class p003 {
	public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int maxLength = 0;
        for( int i=0, j=0;i<s.length();i++ ){
        	if(map.containsKey(s.charAt(i))){
        		// 相同元素的下一个，当前的相同元素不计
//        		j = map.get(s.charAt(i))+1;
        		j = Math.max(j,map.get(s.charAt(i))+1);
        	}
        	map.put(s.charAt(i), i);
        	maxLength = Math.max(maxLength, i-j+1);
        }
		return maxLength;
    }
	
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("abba"));
	}
}
