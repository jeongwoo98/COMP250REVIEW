package ADTTree;

public class Heap {
    private heapNode root;
    private heapNode lastNode;

    public Heap(heapNode pRoot){
        this.root = pRoot;
        this.lastNode = root;
    }
    //Priority Queue implementation:

    //findMin()--> O(1)
    public heapNode findMin(){
        return root;
    }

    //Insert(k,i)--> O(log n)
    public void insert(int key){
        heapNode temp = new heapNode(key);
        heapNode n;
        if(nextAvailableNode().getLeftChild()==null){
            //TODO:
        }

    }

    //nextAvailableNode: finds the location where the next node should be inserted. It runs in time O(log n)
    public heapNode nextAvailableNode(){
        heapNode n = lastNode;
        while( n.getParent()!=null && n==n.getParent().getRightChild()){
            n = n.getParent();
        }
        if(n.getParent()==null){
            while(n.getLeftChild()!=null){
                n = n.getLeftChild();
            }
        }
        else{
            n = n.getParent();
            if (n.getRightChild() != null) {
                n = n.getRightChild();
                while (n.getLeftChild() != null) {
                    n = n.getLeftChild();
                }
                n.setLeftChild(new heapNode(-1));
            }
        }
        return n;
    }
}
