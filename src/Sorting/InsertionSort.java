package Sorting;

//Iterative method to sort objects. Relatively slow, O(n^2). Can do better with recursion
public class InsertionSort {
    public static void main(String[] Args){
        int[] A = {4,3,2,1};
        sort(A);
        for(int i =0; i<A.length;i++) System.out.println(A[i]);
    }

    public static void sort(int[] A){
        for(int i = 1; i<A.length;i++){
            int j = i;
            while(j>0 && A[j]<A[j-1]){
                int temp= A[j];
                A[j] = A[j-1];
                A[j-1] = temp;
                j--;
            }
        }
    }

}
