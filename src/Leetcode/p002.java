package Leetcode;

public class p002 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int t = 0;
        ListNode root = null,pre = null;
        ListNode listNode = null;
        while(true){
        	if(l1 == null && l2 == null){
        		if( t== 0){
        			break;
	        	}else{
	        		//考虑5+5需要进位的情况
		        	listNode = new ListNode(1);
		        	pre.next = listNode;
		        	break;
	        	}
	        }else if(l1 == null){
        		l1 = new ListNode(0);
        	}else if(l2 == null){
        		l2 = new ListNode(0);
        	}

	        if(pre == null){
	        	int val = l1.val + l2.val;
		        if(val<10){
		        	pre = new ListNode(val);
			        t = 0;
		        }else{
		        	pre = new ListNode(val -10);
		        	t=1;
		        }
		        root = pre;
	        }else{
		        int val = l1.val + l2.val + t;
		        if(val<10){
		        	listNode = new ListNode(val);
			        t = 0;
		        }else{
		        	listNode = new ListNode(val -10);
		        	t=1;
		        }
		        pre.next = listNode;
		        pre = listNode;
	        }
	        
	        
	        l1 = l1.next;
	        l2 = l2.next;
        }
        return root;
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
//		ListNode l2 = new ListNode(4);
//		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(9);
		ListNode l5 = new ListNode(9);
//		ListNode l6 = new ListNode(4);
//		l1.next = l2;
//		l2.next = l3;
		l4.next = l5;
//		l5.next = l6;
		System.out.println(addTwoNumbers(l1, l4).next.next.val);
	}

}
