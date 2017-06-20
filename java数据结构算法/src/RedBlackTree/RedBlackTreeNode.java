/**
 * @name: RedBlackTree
 * @author: 孙剑峰
 * @version: 1.0
 * @date: 2016.10.23   
 */
package RedBlackTree;

public class RedBlackTreeNode {
	private String color;
	private int key;
	private RedBlackTreeNode left;
	private RedBlackTreeNode right;
	private RedBlackTreeNode parent;
	//构造Nil结点
	public RedBlackTreeNode(){
		this.color = NodeColor.Black;
		this.key = 0;
		this.left = null;
		this.right = null;
		this.parent = null;
	}
	//构造结点
	public RedBlackTreeNode(String color, int key, RedBlackTreeNode left, RedBlackTreeNode right, RedBlackTreeNode parent){
		this.color = color;
		this.key = key;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	//获取颜色
	public String getColor() {
		return color;
	}
	//设置颜色
	public void setColor(String color) {
		this.color = color;
	}
	//获取值
	public int getKey() {
		return key;
	}
	//设置值
	public void setKey(int key) {
		this.key = key;
	}
	//获取左子树
	public RedBlackTreeNode getLeft() {
		return left;
	}
	//设置左子树
	public void setLeft(RedBlackTreeNode left) {
		this.left = left;
	}
	//获取右子树
	public RedBlackTreeNode getRight() {
		return right;
	}
	//设置右子树
	public void setRight(RedBlackTreeNode right) {
		this.right = right;
	}
	//获取父结点
	public RedBlackTreeNode getParent() {
		return parent;
	}
	//设置父结点
	public void setParent(RedBlackTreeNode parent) {
		this.parent = parent;
	}
}