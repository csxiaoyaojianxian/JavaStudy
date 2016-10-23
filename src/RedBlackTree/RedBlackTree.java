package RedBlackTree;

public class RedBlackTree {
	private static RedBlackTreeNode nil = new RedBlackTreeNode();
	private RedBlackTreeNode root = new RedBlackTreeNode();
	//构造空红黑树
	public RedBlackTree(){
		root = nil;
	}
	//创建结点
	public RedBlackTreeNode RB_NODE(int key){
		RedBlackTreeNode node = new RedBlackTreeNode(NodeColor.Red, key, nil, nil, nil);
		return node;
	}
	//判断是否为Nil结点
	public boolean IsNil(RedBlackTreeNode node){
		if( node == nil){
			return true;
		}else{
			return false;
		}		
	}
	//获取根结点
	public RedBlackTreeNode getRoot() {
		return root;
	}
	//设置根结点
	public void setRoot(RedBlackTreeNode root) {
		this.root = root;
	}
	//插入结点
	public void RB_INSERT(RedBlackTree T, RedBlackTreeNode z){
		//临时变量结点y，存储临时结点，默认为Nil
		RedBlackTreeNode y = T.nil;
		//x初试为根结点
		RedBlackTreeNode x = T.getRoot();
		//循环二查找合适的插入点
		while( IsNil(x) == false ){
			//保存当前结点，作为结果的根结点
			y = x;
			if( z.getKey() < x.getKey() ){
				//查找左子树
				x = x.getLeft();
			}else{
				//查找右子树
				x = x.getRight();
			}
		}
		//临时结点y设置为插入点的父结点
		z.setParent(y);
		
		if(  IsNil(y) == true ){
			//空树时设置z为根结点		
			T.setRoot(z);
		}else if( z.getKey() < y.getKey() ){
			//插入为左子树
			y.setLeft(z);
		}else{
			//插入为右子树
			y.setRight(z);
		}
		//将插入结点的左右子树设为Nil，颜色为红色，已经在构造时设置过，可以省略
		z.setLeft(T.nil);
		z.setRight(T.nil);
		z.setColor(NodeColor.Red);
		//插入调整
		RB_INSERT_FIXUP(T, z);
	}
	//插入调整
	public void RB_INSERT_FIXUP(RedBlackTree T, RedBlackTreeNode z){
		//当z的父结点为红色时，插入结点和父结点同为红色，需要调整
		while( z.getParent().getColor() == NodeColor.Red ){
			//插入结点的父结点是祖父节点的左子树
			if( z.getParent() == z.getParent().getParent().getLeft() ){
				//y定义为叔叔结点
				RedBlackTreeNode y = z.getParent().getParent().getRight();
				//case1：如果叔叔结点为红色，上层父结点和叔叔结点设为黑色，祖父节点设为红色
				if( y.getColor() == NodeColor.Red ){
					z.getParent().setColor(NodeColor.Black);
					y.setColor(NodeColor.Black);
					z.getParent().getParent().setColor(NodeColor.Red);
					//祖父结点设为z
					z = z.getParent().getParent();
				}
				//case2：插入结点为父结点的右子树，（叔叔结点一定为黑色）,父结点设为z，对z左旋
				else if( z == z.getParent().getRight() ){
					//对父结点左旋
					z = z.getParent();
					LEFT_ROTATE(T, z);
				}
				//case3：插入结点为父结点的左子树，（叔叔结点一定为黑色），父结点设为黑色，祖父结点设为红色，对祖父结点右旋
				z.getParent().setColor(NodeColor.Black);
				z.getParent().getParent().setColor(NodeColor.Red);
				RIGHT_ROTATE(T, z.getParent().getParent());
			}
			//插入结点的父结点是祖父节点的右子树，同理，方向交换
			else{
				RedBlackTreeNode y = z.getParent().getParent().getLeft();
				if( y.getColor() == NodeColor.Red ){
					z.getParent().setColor(NodeColor.Black);
					y.setColor(NodeColor.Black);
					z.getParent().getParent().setColor(NodeColor.Red);
					z = z.getParent().getParent();
				}
				else if( z == z.getParent().getLeft() ){
					z = z.getParent();
					RIGHT_ROTATE(T, z);
				}
				z.getParent().setColor(NodeColor.Black);
				z.getParent().getParent().setColor(NodeColor.Red);
				LEFT_ROTATE(T, z.getParent().getParent());
			}
		}
		T.getRoot().setColor(NodeColor.Black);
	}
	//删除结点子函数，把根结点为u的子树替换为根结点为v的子树
	public void RB_TRANSPLANT( RedBlackTree T, RedBlackTreeNode u, RedBlackTreeNode v){
		//u为根结点
		if( IsNil(u.getParent()) ){
			T.root = v;
		}
		//u为左子树
		else if( u == u.getParent().getLeft() ){
			u.getParent().setLeft(v);
		}
		//u为右子树
		else{
			u.getParent().setRight(v);
		}
		//父结点交换
		v.setParent(u.getParent());
	}
	//查找后继
	public RedBlackTreeNode TREE_MINIMUM(RedBlackTreeNode x){
		//不断查找左子树
		while( IsNil(x.getLeft()) == false ){
			x = x.getLeft();
		}
		return x;
	}
	//删除结点
	public void RB_DELETE(RedBlackTree T, RedBlackTreeNode z){
		//临时结点y保存即将删除的结点z信息
		RedBlackTreeNode y = z;
		//在结点删除或者移动前必须保存结点的颜色
		String yOriginColor = y.getColor();
		//临时结点x，用于记录y的位置
		RedBlackTreeNode x = null;
 		//case1：z无左子树，直接将右子树置于z位置
		if( IsNil(z.getLeft()) == true ){
			x = z.getRight();
			RB_TRANSPLANT(T, z, z.getRight());
		}
		//case2：z无右子树，直接将左子树置于z位置
		else if( IsNil(z.getRight()) == true ){
			x = z.getLeft();
			RB_TRANSPLANT(T, z, z.getLeft());
		}
		//case3：z有左右子树
		else{
			//找到右子树中最小的结点，即z的后继
			y = TREE_MINIMUM(z.getRight());
			//删除的实际是y的位置的结点，要记录y的颜色
			yOriginColor = y.getColor();
			//y可能有右孩子，一定无左孩子，保存右孩子
			x = y.getRight();
			//若y为z的右孩子，直接相连
			if( y.getParent() == z ){
				x.setParent(y);
			}
			//若不相连
			else{
				RB_TRANSPLANT(T, y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			RB_TRANSPLANT(T, z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
		}
		//删除结点为黑色时需要调整
		if( yOriginColor == NodeColor.Black ){
			RB_DELETE_FIXUP(T, x);
		}
	}
	//删除调整
	public void RB_DELETE_FIXUP(RedBlackTree T, RedBlackTreeNode x){
		//临时结点
		RedBlackTreeNode w = null;
		//非根结点且为黑色
		while( x != T.getRoot() && x.getColor() == NodeColor.Black ){
			//x为父结点左孩子
			if( x == x.getParent().getLeft() ){
				//w为兄弟结点
				w = x.getParent().getRight();
				//case1：w兄弟结点为红色
				if( w.getColor() == NodeColor.Red ){
					//w设为黑色
					w.setColor(  NodeColor.Black );
					//被删结点的父结点设为黑色
					x.getParent().setColor( NodeColor.Red );
					//对x的父结点左旋
					LEFT_ROTATE(T, x.getParent());
					//更新x的兄弟结点
					w = x.getParent().getRight();
				}
				//case2：w兄弟结点和两个孩子结点都为黑
				if( w.getLeft().getColor() == NodeColor.Black && w.getRight().getColor() == NodeColor.Black ){
					//w设为黑色
					w.setColor(NodeColor.Red);
					//重设x为x的父结点
					x = x.getParent();
				}
				//case3：w兄弟结点为黑，w的左孩子为红，右孩子为黑
				else if( w.getRight().getColor() == NodeColor.Black ){
					//w的左孩子设为黑
					w.getLeft().setColor(NodeColor.Black);
					//w设为红
					w.setColor(NodeColor.Red);
					//右旋
					RIGHT_ROTATE(T, w);
					//更新w
					w = x.getParent().getRight();
				}
				//case4：w兄弟结点为黑，w的右孩子为红
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(NodeColor.Black);
				w.getRight().setColor(NodeColor.Black);
				LEFT_ROTATE(T, x.getParent());
				x = T.getRoot();
			}
			//x为父结点右孩子
			else{
				w = x.getParent().getLeft();
				if( w.getColor() == NodeColor.Red ){
					w.setColor(  NodeColor.Black );
					x.getParent().setColor( NodeColor.Red );
					RIGHT_ROTATE(T, x.getParent());
					w = x.getParent().getLeft();
				}
				if( w.getRight().getColor() == NodeColor.Black && w.getLeft().getColor() == NodeColor.Black ){
					w.setColor(NodeColor.Red);
					x = x.getParent();
				}
				else if( w.getLeft().getColor() == NodeColor.Black ){
					w.getRight().setColor(NodeColor.Black);
					w.setColor(NodeColor.Red);
					LEFT_ROTATE(T, w);
					w = x.getParent().getLeft();
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(NodeColor.Black);
				w.getLeft().setColor(NodeColor.Black);
				RIGHT_ROTATE(T, x.getParent());
				x = T.getRoot();
			}
		}
		x.setColor(NodeColor.Black);
	}
	//左旋
	public void LEFT_ROTATE(RedBlackTree T, RedBlackTreeNode x){
		//左旋结点右子树不能为空
		if ( IsNil(x.getRight()) == true )
			return;
		//定义y结点
		RedBlackTreeNode y = x.getRight();
		//y左子树 -> x右子树
		x.setRight(y.getLeft());
		//x -> y左子树父结点
		y.getLeft().setParent(x);
		//x父结点 -> y父结点
		y.setParent(x.getParent());
		//y -> x父结点左/右子树或根结点
		if( IsNil(x.getParent()) == true){
			//x为根结点，y设为根结点
			T.setRoot(y);
		}else if( x.getParent().getLeft() == x){
			//x为左子树，y设为左子树
			x.getParent().setLeft(y);
		}else{
			//x为右子树，y设为右子树
			x.getParent().setRight(y);
		}
		//x -> y左子树
		y.setLeft(x);
		//y -> x父结点
		x.setParent(y);
	}
	//右旋
	public void RIGHT_ROTATE(RedBlackTree T, RedBlackTreeNode x){
		//右旋结点父结点不能为空
		if ( IsNil(x.getParent()) == true )
			return;
		//定义y结点
		RedBlackTreeNode y = x.getParent();
		//x右子树 -> y左子树
		y.setLeft(x.getRight());
		//y -> x右子树父结点
		x.getRight().setParent(y);
		//y父结点 -> x父结点
		x.setParent(y.getParent());
		//x -> y父结点左/右子树或根结点
		if( IsNil(y.getParent()) == true){
			//y为根结点，x设为根结点
			T.setRoot(x);
		}else if( y.getParent().getLeft() == y){
			//y为左子树，x设为左子树
			y.getParent().setLeft(x);
		}else{
			//y为右子树，x设为右子树
			y.getParent().setRight(x);
		}
		//y -> x右子树
		x.setRight(y);
		//x -> y父结点
		y.setParent(x);
	}
	//前序遍历
	public void preorder(RedBlackTreeNode t){
	    if(IsNil(t) == false){
	    	System.out.println(t.getKey());
	        preorder(t.getLeft());  
	        preorder(t.getRight());  
	    }  
	} 
	//中序遍历
	public void midorder(RedBlackTreeNode t){
	    if(IsNil(t) == false){
	    	midorder(t.getLeft()); 
	    	System.out.println(t.getKey());
	    	midorder(t.getRight());  
	    }  
	} 
	//后序遍历
	public void postorder(RedBlackTreeNode t){
	    if(IsNil(t) == false){
	    	postorder(t.getLeft());  
	    	postorder(t.getRight());  
	    	System.out.println(t.getKey());
	    }  
	} 
}