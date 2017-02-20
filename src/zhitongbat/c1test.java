package zhitongbat;

import java.util.LinkedList;

public class c1test {
	public static void print(TNode t){
		
		LinkedList<TNode> list = new LinkedList<TNode>();
		TNode last,nlast = null;
		list.add(t);
		last = t;
		while(!list.isEmpty()){
			
			TNode tNode = list.getFirst();
			if(tNode.left!=null){
				list.add(tNode.left);
				nlast = tNode.left;
			}
			if(tNode.right != null){
				list.add(tNode.right);
				nlast = tNode.right;
			}
			
			if(list.pop() == last){
				System.out.print(" "+ tNode.val+ "\n");
				last = nlast;
			}else{
				System.out.print(" "+ tNode.val);
			}
			tNode = null;
		}
		
	}
	public static void main(String[] args) {
		TNode tNode1 = new TNode(1);
		TNode tNode2 = new TNode(2);
		TNode tNode3 = new TNode(3);
		TNode tNode4 = new TNode(4);
		TNode tNode5 = new TNode(5);
		TNode tNode6 = new TNode(6);
		tNode1.left = tNode2;
		tNode1.right = tNode3;
		tNode2.left = tNode4;
		tNode3.right = tNode5;
		tNode4.right = tNode6;
		print(tNode1);
		
		
		
		
	}
}
