package Leetcode;

public class p002_2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode root = new ListNode(0);
        ListNode pre = root;
        ListNode cur = null;
        while(true){
        	if( l1 == null && l2 == null && carry == 0){
        		break;
        	}
        	int val = (l1==null?0:l1.val) + (l2==null?0:l2.val) + carry;
        	carry = val/10;
        	cur = new ListNode(val%10);
	        pre.next = cur;
	        pre = cur;
	        
	        l1 = l1==null?l1:l1.next;
	        l2 = l2==null?l2:l2.next;
        }
        return root.next;
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
