package zhitongbat;

import java.util.Arrays;

public class c2排序 {
	
	public static void bubble(int[] inputs){
		int temp;
		int length = inputs.length;
		for(int i=0;i<length-1;i++){
			for(int j=i+1;j<length;j++){
				if(inputs[i]>inputs[j]){
					temp = inputs[i];
					inputs[i] = inputs[j];
					inputs[j] = temp;
				}
			}
		}
	}
	public static void select(int[] inputs){
		int length = inputs.length;
		int temp;
		for(int i=0;i<length-1;i++){
			int min = i;
			for(int j=i;j<length;j++){
				if(inputs[j] < inputs[min]){
					min = j;
				}
			}
			temp = inputs[min];
			inputs[min] = inputs[i];
			inputs[i] = temp;
		}
	}
	public static void insert(int[] inputs){
		// 从前开始，不断和前面的数比较和交换
		for(int i=0;i<inputs.length;i++){
			for(int j=i-1;j>=0;j--){
				if(inputs[i]<inputs[j]){
					int temp = inputs[j];
					inputs[j] = inputs[i];
					inputs[i] = temp;
					i = j;
				}
			}
		}
	}
	public static void main(String[] args) {
		int[] inputs = {1,5,9,2,4,6};
//		bubble(inputs);
//		select(inputs);
		insert(inputs);
		System.out.println(Arrays.toString(inputs));
	}

}
