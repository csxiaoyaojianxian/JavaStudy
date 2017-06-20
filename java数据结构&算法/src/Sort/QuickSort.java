package Sort;

import java.io.PrintWriter;
import java.util.Scanner;

public class QuickSort {
	
	//输入数组长度和数据
	public int inputNum(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input the size of the array:");
		int num = Integer.parseInt(scanner.next());
		scanner.close();
		return num;
	}
	//生成随机数据,生成1~num之间的随机数
	public int[] inputData(int num, int[] array){
		for(int i=0; i<num; i++){
			array[i] = 1 + (int)(Math.random() * num);
		}
//		for(int i=0; i<num; i++){
//			System.out.println(array[i]);
//		}
		return array;
	}
	//快速排序
    public int[] quickSort(int array[], int p, int r){
    	if( p < r ){
    		int q = partition(array,p,r);
    		quickSort(array, p, q-1);
    		quickSort(array, q+1, r);
    	}
    	return array;
    }
    public int[] randomized_quickSort(int array[], int p, int r){
    	if( p < r ){
    		int q = randomized_partition(array, p, r);
    		quickSort(array, p, q-1);
    		quickSort(array, q+1, r);
    	}
    	return array;
    }
	public int partition(int[] array, int p, int r){
		int x = array[r];
		int i = p-1;
		for(int j = p; j< r; j++){
			if(array[j] <= x){
				i = i+1;
				exchange(array, i, j);
			}
		}
		exchange(array, i+1, r);
		return i+1;
	}
	public int randomized_partition(int[] array, int p,int r){
		//生成p~r的随机数，形成随机化样本
		int i = p + (int)(Math.random() * (r-p));
		exchange(array, r, i);
		return partition(array, p, r);
	}
	public int[] exchange(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}
	//打印数组
	public void Print(int num, int[] array){
		for(int i=0; i<num; i++){
			System.out.println(array[i]);
		}
	}
	public static void main(String args[]){
		int num;
		int array[];
		QuickSort quickSort = new QuickSort();
		num = quickSort.inputNum();
		
		long startMili=System.currentTimeMillis();
		array = new int[num];
		array = quickSort.inputData(num,array);
		quickSort.quickSort(array,0,num-1);
		long endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili-startMili)+"ms");
//		quickSort.Print(num, array);
		
		startMili=System.currentTimeMillis();
		array = new int[num];
		array = quickSort.inputData(num,array);
		quickSort.randomized_quickSort(array,0,num-1);
		endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili-startMili)+"ms");
//		quickSort.Print(num, array);
	}
}
