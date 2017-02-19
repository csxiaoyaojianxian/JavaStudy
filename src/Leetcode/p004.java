package Leetcode;
/**
 * 
 * @ClassName: p004
 * @Description: There are two sorted arrays nums1 and nums2 of size m and n respectively.Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月18日 下午9:12:27
 *
 */
public class p004 {
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if(nums1.length == 0){
			if(nums2.length %2 == 0){
				return (nums2[nums2.length/2-1] + nums2[nums2.length/2])/2.0;
			}else{
				return nums2[nums2.length/2];
			}
		}
		if(nums2.length == 0){
			if(nums1.length %2 == 0){
				return (nums1[nums1.length/2-1] + nums1[nums1.length/2])/2.0;
			}else{
				return nums1[nums1.length/2];
			}
		}
		if(nums1.length == 1 && nums2.length == 1){
			return (nums1[0]+nums2[0])/2;
		}
		int  index11=0,index12=nums1.length-1,index21=0,index22=nums2.length-1;
		int index1=0,index2=0;
		while(Math.abs(index11-index12)>1 || Math.abs(index21 - index22)>1){
			index1 = (index11 + index12)/2;
			index2 = (index21 + index22)/2;
			if(nums1[index1] < nums2[index2]){
				index11 = index1;
				index22 = index2;
			}else{
				index12 = index1;
				index21 = index2;
			}
		}
		if((nums1.length+nums2.length)%2 == 1){
			if(nums1[index1] < nums2[index2]){
				if(index1==nums1.length-1){
					return nums2[index2];
				}else{
					return Math.min(nums1[index1+1], nums2[index2]);
				}
			}else{
				if(index2==nums2.length-1){
					return nums1[index1];
				}else{
					return Math.min(nums1[index1], nums2[index2+1]);
				}
			}
		}else{
			if(nums1[index1] < nums2[index2]){
				if(index1==nums1.length-1){
					if(nums2.length>=2){
						return (nums2[index2-1] + nums2[index2])/2.0;
					}else{
						return (nums1[index1] + nums2[index2])/2.0;
					}
				}
				return (nums1[index1+1] + nums2[index2])/2.0;
			}else{
				if(index2==nums2.length-1){
					if(nums1.length>=2){
						return (nums1[index1-1] + nums1[index1])/2.0;
					}else{
						return (nums2[index2] + nums1[index1])/2.0;
					}
				}
				return (nums1[index1] + nums2[index2+1])/2.0;
			}
		}
    }
	public static void main(String[] args) {
//		int[] nums1 = {1,2,5,7,9,11};
//		int[] nums2 = {3,4,6,8,10};
//		int[] nums1 = {1,3};
//		int[] nums2 = {2,4};
		int[] nums1 = {};
		int[] nums2 = {1,2,3,4};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
}
