package ADTTree;

public class maxHeap {
    private int[] Heap;
    private int size;
    private int maxSize;

    public maxHeap(int pMaxSize){
        maxSize = pMaxSize;
        size = 0;
        Heap = new int[pMaxSize+1]; //array of size n+1
        Heap[0] = Integer.MAX_VALUE;
        for(int i=1;i<=pMaxSize;i++) Heap[i] =Integer.MIN_VALUE;
    }
    //Function to return the position of the parent for the node currently at pos
    public int parent(int pos) {
        return pos / 2;
    }

    //Function to return the position of the left child for the node currently at pos
    public int leftChild(int pos) {
        return (2 * pos);
    }

    //Function to return the position of the right child for the node currently at pos
    public int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    //Function to swap two nodes of the heap
    public void swap(int pos1, int pos2){
        int temp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = temp;
    }

    public int findMax(){
        return Heap[1];
    }
    public int[] getHeap(){
        return Heap;
    }

    //element = key
    public void insert(int element){
        if(size>=maxSize){
            System.out.println("Heap is full!");
        }else{
            Heap[++size] = element;     //note: size = lastNode
            int n = size;
            //Bubbling up
            while(parent(n)!=0 && Heap[parent(n)]<Heap[n]) {
                swap(parent(n), n);
                n = parent(n);
            }
        }
    }

    private boolean isLeaf(int pos)
    {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    //Bubbling down to heap-ify:
    public void removeMax() {
        swap(size,1);
        Heap[size] =Integer.MIN_VALUE;
        size--;
        int n = 1;
        while(!isLeaf(n) && Heap[n]<Math.max(Heap[leftChild(n)],Heap[rightChild(n)])){
            if(Heap[leftChild(n)]>Heap[rightChild(n)]){
                swap(n,leftChild(n));
                n = leftChild(n);
            }else{
                swap(n,rightChild(n));
                n = rightChild(n);
            }
        }
    }

    public static void main(String[] args){
        maxHeap h = new maxHeap(10);
        h.insert(2);
        h.insert(5);
        h.insert(6);
        h.insert(7);
        h.insert(10);
        h.insert(8);
        h.insert(9);
        h.insert(9);
        h.insert(12);
        h.insert(8);

        h.removeMax();
        h.removeMax();

    }
}
