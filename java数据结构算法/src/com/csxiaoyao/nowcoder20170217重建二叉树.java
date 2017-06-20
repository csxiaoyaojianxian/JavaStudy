package com.csxiaoyao;

import java.util.Arrays;

/**
题目描述
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

问题：
important
 */
public class nowcoder20170217重建二叉树 {
		public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
			if(pre.length == 0 || in.length == 0){
				return null;
			}
			TreeNode treeNode = new TreeNode(pre[0]);
			for(int i=0; i<in.length; i++){
				if(pre[0] == in[i]){
					treeNode.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in,0, i));
					treeNode.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
					break;
				}
			}			
	        return treeNode;
	    }
		
		public static void main(String[] args) {
			int[] pre = {1,2,4,7,3,5,6,8};
			int[] in = {4,7,2,1,5,3,8,6};
			reConstructBinaryTree(pre,in);
			
		}
}
