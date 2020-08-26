package Sorting;

public class SelectionSort {
    //find index of smallest element of A[start:stop]
    //Easy to see why this is O(n)
    public static int findMin(int[] A, int start, int stop){
        int minValue = A[start];
        int minIndex = start;
        int i = start+1;

        while(i<=stop){
            if(A[i]<minValue){
                minValue = A[i];
                minIndex = i;
            }
            i++;
        }
        return minIndex;
    }

    //Selection sort: easy to see why this is O(n^2)
    public static void sort(int[] A, int n){
        for(int i =0; i<n; i++){
            int minIndex = findMin(A,i,n-1);
            int temp = A[minIndex];
            A[minIndex] = A[i];
            A[i] = temp;
        }
    }



    public static void main(String[] Args){
        int[] A = {5,2,3,6,7,3,5,1};
        sort(A,A.length);
        for(int x: A) System.out.print(x+ "   ");
    }
}
