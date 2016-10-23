package RedBlackTree;

public class Test {

	public static void main(String[] args) {
		RedBlackTree T = new RedBlackTree();
		RedBlackTreeNode node1 = T.RB_NODE(10);
		T.RB_INSERT(T, node1);
		RedBlackTreeNode node2 = T.RB_NODE(20);
		T.RB_INSERT(T, node2);
		RedBlackTreeNode node3 = T.RB_NODE(30);
		T.RB_INSERT(T, node3);
		T.preorder(T.getRoot());
	}

}
