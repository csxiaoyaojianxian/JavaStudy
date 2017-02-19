package com.csxiaoyao;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @ClassName: cs20170214
 * @Description: TODO
 * @author C逍遥剑仙-SUNSHINE
 * @date 2017年2月14日 上午10:59:23
 *
 */
public class cs20170214 {
	// 字符串第一次只出现一个的汉字
	public static String csxiaoyao_fun(String input){
		String inputArray[] = input.split("");
		LinkedList<String> result = new LinkedList<String>();
		
		 
		 
		for(int i=0;i<inputArray.length;i++){
			for (Iterator iter = result.iterator(); iter.hasNext();) {
				  String str = (String) iter.next();
				  if(inputArray[i].equals(str)){
					  iter.remove();
		            }
			}
	        result.offerLast(inputArray[i]);
		}
		return result.getFirst();
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(csxiaoyao_fun("姚德义是姚德义吗"));
	}

}
