package Sorting;

public class QuickSort {
    //Output: Index left and rearranges elements of A such that
    //∀i < left, A[i] ≤ A[left] and ∀k > left, A[k] ≥ A[left]. Takes O(n) time.
    public static int partition(int[] A, int p, int q){
        int pivotValue = A[q];  //pivot is the last element.
        int left = p;
        int right = q-1;

        while(left<=right){
            while(left<=right && A[left]< pivotValue){
                left++;
            }
            while(left<=right && A[right] >= pivotValue){
                right--;
            }
            //swap if left is strictly less than right
            if(left<right){
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }
        A[q] = A[left];
        A[left] = pivotValue;
        return left;
    }

    //TODO: debug later... Not working correctly
    public static int partition2(int[] A, int p, int q){
        int pivotValue = A[q];
        int left = p;
        for(int right = p+1; right < q; right++){
            if(A[right]<=pivotValue){
                left = left +1;
                int temp = A[left-1];
                A[left-1] = A[right];
                A[right] = temp;
            }
        }
        A[q] = A[left];
        A[left] = pivotValue;
        return left;
    }

    //This is another Divide & Conquer algorithm.
    //worst case: sorted or inversely sorted arrays ->O(n^2)
    //average case -> O(n *log n)
    //worst case: decrease recursive calls by 1 only. Best case: pivot divides array in two each time.
    public static void sort(int[] A, int p, int q){
        if(p<=q){
            int x = partition(A, p, q);
            sort(A,p,x-1);
            sort(A,x+1,q);
        }
    }

    //NOTE: Quicksort, InsertionSort and SelectionSort are all in-place algorithms.
    public static void main(String[] Args){
        int[] A = {2,3,7,4,6,8,7,5};
        sort(A,0,7);
        for(int number: A) System.out.print(number+ "   ");
    }
}
