package ADTTree;

import java.util.PriorityQueue;

//Running time: O(n * log n),
public class heapSort {
    public static void minSort(int[] A){
        minHeap h = new minHeap(A.length);
        for(int i=0;i<A.length;i++){
            h.insert(A[i]);
        }
        for(int i=0;i<A.length;i++){
            A[i] = h.findMin();
            h.removeMin();
        }
    }

    public static void main(String[] args){
        int[] A = {20,24,26,54,7,2,7,2,9,87,100,11,6};
        minSort(A);
        for(int x: A) System.out.print(x+"  ");
    }
}
