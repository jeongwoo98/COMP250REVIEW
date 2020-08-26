package ADTTree;

public class heapNode {
    private int key;
    private heapNode leftChild,rightChild, parent;

    public heapNode(int value){
        key = value;
        leftChild = rightChild = parent = null;
    }
    public int getKey() {
        return key;
    }
    public heapNode getLeftChild() {
        return leftChild;
    }
    public heapNode getRightChild() {
        return rightChild;
    }
    public heapNode getParent() {
        return parent;
    }
    public void setLeftChild(heapNode leftChild) {
        this.leftChild = leftChild;
        leftChild.setParent(this);
    }
    public void setRightChild(heapNode rightChild) {
        this.rightChild = rightChild;
        rightChild.setParent(this);
    }
    public void setParent(heapNode parent) {
        this.parent = parent;
    }
}
