package ADTTree;

public class treeNode {
    private int key;
    private treeNode parent, leftChild, rightChild;

    //Constructor
    public treeNode(int pValue){
        key = pValue;
        parent = leftChild = rightChild = null;
    }

    //Get methods
    public int getKey(){
        return key;
    }
    public treeNode getParent(){
        return parent;
    }
    public treeNode getLeftChild(){
        return leftChild;
    }
    public treeNode getRightChild(){
        return rightChild;
    }
    public treeNode getSibling() throws Exception{
        if(parent==null){
            throw new Exception("The node has no parent!");
        }else if(parent.getLeftChild()==this){
            return rightChild;
        }else return leftChild;
    }

    //Set methods
    public void setParent(treeNode pParent) {
        parent = pParent;
    }
    public void setLeftChild(treeNode pLeftChild){
        leftChild = pLeftChild;
    }
    public void setRightChild(treeNode pRightChild){
        rightChild = pRightChild;
    }

    //Return depth of the current node
    public int depth(){
        if(parent==null){       //ie. this is the root node
            return 0;
        }else{
            return 1 + parent.depth();
        }
    }

    //Return height of the current node
    public int height(){
        if(leftChild==null){    //ie. this is a leaf node.
            return 0;
        }else{
            return 1 + Math.max(leftChild.height(), rightChild.height());
        }
    }

    //Binary search tree methods:
    public treeNode find(treeNode n, int key){
        if(n==null) return null;
        else{
            if(n.key>key) return find(n.leftChild,key);
            if(n.key<key) return find(n.rightChild,key);
            if(n.key == key) return n;
        }
        return null;
    }

   public void insert(treeNode n, int key){
        if(n.key>=key){
            if(n.leftChild!=null){
                insert(n.leftChild,key);
            }else{
                n.setLeftChild(new treeNode(key));
            }
        }else{
            if(n.rightChild!=null){
                insert(n.rightChild,key);
            }else{
                n.setRightChild(new treeNode(key));
            }
        }
   }

   public treeNode remove(treeNode root, int key){
        treeNode x = find(root,key);
        if(x==null) return null;
        //Leaf case:
        if(x.leftChild==null && x.rightChild==null){
            replace(x,null);
            return x;
        }
        //One child case:
        else if(x.leftChild==null || x.rightChild==null){
            if(x.leftChild==null){
                replace(x,x.rightChild);
            }else if(x.rightChild == null){
                replace(x,x.leftChild);
            }
            return x;
        }
        //Two children case:
        else{
            treeNode xPrime = x.rightChild;
            while(xPrime.leftChild!=null){
                xPrime = xPrime.leftChild;
                x.key = xPrime.key;
            }
            replace(xPrime,xPrime.rightChild);
            return x;
        }

   }


    //A utility function that copies node y onto node x, overwriting x.
    public void replace(treeNode x, treeNode y){
        if(x.parent!=null){
            if(x.parent.leftChild==x){
                x.parent.setLeftChild(y);
            }else{
                x.parent.setRightChild(y);
            }
        }
        if(y!=null) {
            y.parent = x.parent;
        }else{
            x = null;
        }
    }

}
