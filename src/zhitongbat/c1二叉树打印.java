package zhitongbat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class c1二叉树打印 {
	
	/**
	 * @Title: print
	 * @Description: 按层打印 宽度优先遍历
	 */
	public static void print(TNode t){
		// 思路：last当前行最右结点，nlast下一行最右结点
		// 使用链表，遍历链表，加入每个元素的左右孩子结点
		// 不断更新新加入的结点为nlast
		// 当当前删除结点为last，说明已经到当前层尾，更新last为nlast
		TNode last = null, nlast = null, temp = null;
		LinkedList<TNode> list = new LinkedList<TNode>();
		list.add(t);
		last= t;
		// 队列不为空，一直循环
		while(!list.isEmpty()){
			
			temp = list.getFirst();
			if(temp.left != null){
				nlast = temp.left;
				list.add(temp.left);
			}
			if(temp.right != null){
				nlast = temp.right;
				list.add(temp.right);
			}
			if(list.pop() == last){
				System.out.print(" "+temp.val+"\n");
				last = nlast;
			}else{
				System.out.print(" "+temp.val);
			}
			temp = null;
		}
	}
	/**
	 * @Title: serialize
	 * @Description: 二叉树序列化，每个结点结束用 ! 标记，null用 #! 标记
	 * @param @param t
	 * @return void
	 * @throws
	 */
	public static String serialize(TNode t){
		if(t == null){
			return "#!";
		}
		return t.val+"!" +serialize(t.left) + serialize(t.right);
	}
	
	public static TNode deserialize(String seri){
		String[] input= seri.split("!");
		Queue<String> queue = new LinkedList<String>();
		for(int i=0;i<input.length;i++){
			queue.add(input[i]);
		}
		return construct(queue);
	}
	
	public static TNode construct(Queue q){
		String val = (String) q.poll();
		if(val.equals("#")){
			return null;
		}
		TNode tNode = new TNode(Integer.parseInt(val));
		tNode.left = construct(q);
		tNode.right = construct(q);
		return tNode;
	}
	
	public static void main(String[] args) throws IOException {
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
		System.out.println(serialize(tNode1));//1!2!4!#!6!#!#!#!3!#!5!#!#!
		
		File file = new File("F:\\seri.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		bufferedOutputStream.write(serialize(tNode1).getBytes());
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		fileOutputStream.close();
		
		FileInputStream fileInputStream = new FileInputStream(file);
		int content = 0;
		StringBuilder stringBuilder = new StringBuilder();
		while ((content = fileInputStream.read()) != -1) {
			stringBuilder.append((char) content);
		}
		fileInputStream.close();

		System.out.println(serialize(deserialize(stringBuilder.toString())));
	}
}
