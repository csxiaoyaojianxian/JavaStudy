package AVLTree;

/** 
 * @功能 
 * 1、AVL树的生成</br> 
 * 2、插入元素</br> 
 * 3、删除元素</br> 
 * 4、查找某一元素</br> 
 * @notice 树元素需实现Comparor接口 
 * */  
public class AVL <T extends Comparable<T>>{  
    public AVL(){  
        this(null);  
    }  
    public AVL(T t){  
        data=t;  
        parent=null;  
        leftChild=null;  
        rightChild=null;  
        height=t==null?-1:0;  
    }  
    public int getHeight(){  
        return updateHeight();  
    }  
    public AVL<T> getRoot(){  
        if(data==null)  
            return null;  
        AVL<T>root=this;  
        while(root.parent!=null)  
            root=root.parent;  
        return root;  
    }  
    public AVL<T>getLeftChild(){  
        return leftChild;  
    }  
    public AVL<T>getRightChild(){  
        return rightChild;  
    }  
    public AVL<T>getParent(){  
        return parent;  
    }  
    public boolean isEmpty(){  
        return data==null;  
    }  
    /**从当前节点所在的AVL树中插入新元素；若参数是空值则不予插入 
     * @return 新的根节点 
     * */  
    public AVL<T> insert(T t){  
        if(t==null)  
            return this;  
        //特殊情形：当前是空节点  
        if(data==null){  
            data=t;  
            updateHeight();  
            return this;  
        }  
        AVL<T>root=getRoot();//根节点  
        return root.rootInsert(t);  
    }  
    /**从当前节点所在的AVL树中删除节点 
     * @return 新的根节点 
     */  
    public AVL<T> delete(T t){  
        AVL<T>root=getRoot();  
        return root.rootDelete(t);  
    }  
    /**从当前节点所在的AVL树中查找元素所在节点*/  
    public AVL<T> find(T t){  
        AVL<T>root=getRoot();  
        return root.rootFind(t);  
    }  
    /**打印树*/  
    public void display(){  
        //空节点、叶子节点不打印  
        if(data==null||(parent!=null&&leftChild==null&&rightChild==null))  
            return;  
        System.out.println(String.format("H=%2d, %s->(%s,%s)",  
                height,  
                data.toString(),   
                leftChild==null?null:leftChild.data.toString(),  
                rightChild==null?null:rightChild.data.toString()));  
        if(leftChild!=null)  
            leftChild.display();  
        if(rightChild!=null)  
            rightChild.display();  
    }  
    /**检查是否平衡*/  
    public boolean checkBalance(){  
        int left=getLeftHeight(),  
                right=getRightHeight();  
        if(left-right>1||right-left>1)  
            return false;  
        if(leftChild!=null&&!leftChild.checkBalance())  
            return false;  
        if(rightChild!=null&&!rightChild.checkBalance())  
            return false;  
        return true;  
    }  
    protected T data;  
    protected AVL<T> parent;  
    protected AVL<T> leftChild;  
    protected AVL<T> rightChild;  
    protected int height;//该节点的高度  
    /**左子树的高度*/  
    private int getLeftHeight(){  
        return leftChild==null?-1:leftChild.height;  
    }  
    /**右子树的高度*/  
    private int getRightHeight(){  
        return rightChild==null?-1:rightChild.height;  
    }  
    /**获取树中最大的元素*/  
    private AVL<T>getMax(){  
        if(isEmpty())  
            return null;  
        if(rightChild!=null)  
            return rightChild.getMax();  
        return this;  
    }  
    /**获取树中最小的元素*/  
    private AVL<T>getMin(){  
        if(isEmpty())  
            return null;  
        if(leftChild!=null)  
            return leftChild.getMin();  
        return this;  
    }  
    /**计算当前节点到子节点node的路径长度 
     *@return node是子节点时返回路径长度，否则返回-1  
     */  
    private int depth(AVL<T>node){  
        int dep=0;  
        while(node!=null&&this!=node){  
            dep++;  
            node=node.parent;  
        }  
        return node!=null?dep:-1;  
    }  
    /**在以当前节点为根节点的树中查找元素 
     * @return 若存在则返回元素所在的节点，否则空值 
     * */  
    private AVL<T>rootFind(T t){  
        if(t!=null&&data!=null){  
            int cp=t.compareTo(data);  
            if(cp==0)  
                return this;  
            if(cp>0)  
                return rightChild==null?null:rightChild.rootFind(t);  
            return leftChild==null?null:leftChild.rootFind(t);  
        }  
        return null;  
    }  
    /**在以当前节点为根节点的树中删除元素 
     * @return 新树的根节点 
     * */  
    private AVL<T>rootDelete(T t){  
        //查找元素t所在节点  
        AVL<T>del=rootFind(t);  
        //未找到  
        if(del==null)  
            return this;  
        //删除元素值  
        del.data=null;  
        AVL<T>maxL=null,minR=null,updatePos=del;  
        int dL,dR;  
        //左子树的最大元素节点  
        maxL=del.leftChild==null?null:del.leftChild.getMax();  
        //右子树的最小元素节点  
        minR=del.rightChild==null?null:del.rightChild.getMin();  
        //del->maxL的距离，范围是-1，1，2...  
        dL=del.depth(maxL);  
        dR=del.depth(minR);  
        if(minR==null&&maxL==null){  
            updatePos=del.divFromParent();  
            del.parent=null;  
        }  
        //左子树更高，或者待删除节点到maxL节点路径更长  
        else if(dL>dR||(dL==dR&&del.getLeftHeight()>del.getRightHeight())){  
            del.data=maxL.data;  
            updatePos=maxL.divFromParent();  
            if(maxL.leftChild!=null){  
                if(dL==1)  
                    updatePos.leftChild=maxL.leftChild;  
                else  
                    updatePos.rightChild=maxL.leftChild;  
                maxL.leftChild.parent=updatePos;  
            }  
        }else{  
            del.data=minR.data;  
            updatePos=minR.divFromParent();  
            if(minR.rightChild!=null){  
                if(dR==1)  
                    updatePos.rightChild=minR.rightChild;  
                else  
                    updatePos.leftChild=minR.rightChild;  
                minR.rightChild.parent=updatePos;  
            }  
        }  
        if(updatePos!=null){  
            return updatePos.update();  
        }  
        return this;  
    }  
    /**当前节点与父节点脱离关系,仅改变父节点的高度，不对父节点做平衡调整 
     * @return 父节点 
     * */  
    private AVL<T>divFromParent(){  
        AVL<T>p=parent;  
        if(parent!=null){  
            if(parent.leftChild==this)  
                parent.leftChild=null;  
            else  
                parent.rightChild=null;  
            parent.updateHeight();  
            parent=null;  
        }  
        return p;  
    }  
    /**由当前节点开始更新，一直上溯到根节点 
     * @return 新树的根节点 
     * */  
    private AVL<T>update(){  
        if(data==null)  
            return this;  
        //当前节点更新  
        AVL<T>root=rootUpdate();  
        while(root.parent!=null){  
            //更新父节点  
            root=root.parent.rootUpdate();  
        }  
        return root;  
    }  
    /**对以当前节点为根节点的树, 
     * 检查其左右子树有过变化之后是否不平衡， 
     * 若然，则重新调整，使之平衡 
     * @return 新的根节点 
     */  
    private AVL<T>rootUpdate(){  
        //右子树的高度，若为空则为-1  
        int Rh=getRightHeight(),  
            Lh=getLeftHeight();//左子树高度  
        AVL<T>root=this;  
        //若树不平衡  
        if(Lh-Rh==2){//左子树高度更大  
            if(leftChild.getLeftHeight()>=leftChild.getRightHeight()){  
                root=rotateLeft(this);//单旋转  
            }else{  
                rotateRight(leftChild);//双旋转  
                root=rotateLeft(this);  
            }  
        }else if(Rh-Lh==2){//右子树高度更大  
            if(rightChild.getLeftHeight()<=rightChild.getRightHeight()){  
                root=rotateRight(this);  
            }else{  
                rotateLeft(rightChild);  
                root=rotateRight(this);  
            }  
        }  
        //树平衡，需更新高度  
        root.updateHeight();  
        return root;  
    }  
    /**更新当前高度*/  
    private int updateHeight(){  
        if(data==null){  
            height=-1;  
            return height;  
        }  
        int right=getRightHeight(),left=getLeftHeight();  
        height=left>right?(1+left):(1+right);  
        return height;  
    }  
    /** 
     * 以当前节点为根节点插入新元素 
     * @return 新树的根节点 
     * */  
    private AVL<T>rootInsert(T t){  
        int cp=t.compareTo(data);  
        //比较结果为0，则更新为新元素  
        if(cp==0){  
            data=t;  
            return this;  
        }  
        //小于当前节点，则插入左子树  
        if(cp<0){  
            if(leftChild==null){  
                leftChild=new AVL<T>(t);  
                leftChild.parent=this;  
            }else{  
                leftChild.rootInsert(t);  
            }  
        }else{//新节点插入到右子树  
            if(rightChild==null){  
                rightChild=new AVL<T>(t);  
                rightChild.parent=this;  
            }else{  
                rightChild.rootInsert(t);  
            }  
        }  
        //插入左右子树后更新当前节点的高度  
        return rootUpdate();  
    }  
    /**对tree和其左子树进行旋转; 
     * 设left为tree左子树; 
     * 即将tree变成left的右子树，left的右子树变成tree的新左子树 
     * @return 返回旋转后的根节点,即树原来的左子树 
     * */  
    private static <T extends Comparable<T>> AVL<T> rotateLeft(  
            AVL<T> tree) {  
        if(tree==null||tree.leftChild==null)  
            return tree;  
        AVL<T>root=tree.leftChild;  
        tree.leftChild=root.rightChild;  
        if(tree.leftChild!=null)  
            tree.leftChild.parent=tree;  
        root.rightChild=tree;  
        root.parent=tree.parent;  
        tree.parent=root;  
        if(root.parent!=null){  
            if(root.parent.leftChild==tree)  
                root.parent.leftChild=root;  
            else  
                root.parent.rightChild=root;  
        }  
        tree.updateHeight();  
        root.updateHeight();  
        return root;  
    }  
    /**对tree和其右子树进行旋转 
     * @return 返回旋转后的根节点,也即原树的右子节点 
     * */  
    private static <T extends Comparable<T>> AVL<T> rotateRight(  
            AVL<T> tree) {  
        if(tree==null||tree.rightChild==null)  
            return tree;  
        AVL<T>root=tree.rightChild;  
        tree.rightChild=root.leftChild;  
        if(tree.rightChild!=null)  
            tree.rightChild.parent=tree;  
        root.leftChild=tree;  
        root.parent=tree.parent;  
        tree.parent=root;  
        //使原先的树tree的父节点指向新的根节点,即root  
        if(root.parent!=null){  
            if(root.parent.leftChild==tree)  
                root.parent.leftChild=root;  
            else  
                root.parent.rightChild=root;  
        }  
        //更新高度  
        tree.updateHeight();  
        root.updateHeight();  
        return root;  
    }  
}  