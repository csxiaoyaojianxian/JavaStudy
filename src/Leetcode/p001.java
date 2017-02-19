package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: p001
 * @Description: TODO
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月18日 下午1:08:48
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class p001 {
	// 使用map存储（键值，索引）
	public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
        	if(map.containsKey(target-nums[i])){
        		result[0] = map.get(target-nums[i]);
        		result[1] = i;
        		return result;
        	}
        	map.put(nums[i], i);
        }
		return null;
    }

	public static void main(String[] args) {
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		int[] result = new int[2];
		result = twoSum(nums, target);
		System.out.println(result[0] + " " +result[1]);
		
	}

}
