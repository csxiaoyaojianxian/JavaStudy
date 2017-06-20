package com.csxiaoyao;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述
输入一个链表，从尾到头打印链表每个节点的值

问题：
1、数组赋值最后一个，length-1
2、while(ln.next != null) 则当前ln的值取不到
 */

public class nowcoder20170217从尾到头打印链表 {

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> arrayList = new ArrayList<Integer>();
    	ListNode ln = listNode;
    	if(ln == null){
    		return arrayList;
    	}
    	while(true){
    		arrayList.add(ln.val);
    		ln = ln.next;
    		if(ln == null){
    			break;
    		}
    	}
    	int length = arrayList.size();
    	int temp;
    	for(int i=0; i<length/2; i++){ 
    		temp = arrayList.get(length-1-i);
    		
    		arrayList.set(length-1-i, arrayList.get(i));
    		arrayList.set(i, temp);
    	}
    	return arrayList;
    }
	
	public static void main(String[] args) {
		ListNode ln1 = new ListNode(3);
		ListNode ln2 = new ListNode(4);
		ListNode ln3 = new ListNode(5);
		ListNode ln4 = new ListNode(6);
		ListNode ln5 = new ListNode(7);
		
		ln1.next = ln2;
		ln2.next = ln3;
		ln3.next = ln4;
		ln4.next = ln5;
		
		System.out.println(printListFromTailToHead(ln1));
	}

}
