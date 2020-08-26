package Recursion;

public class BinarySearch {
    public static void main(String[] Args){
        int[] A = {1,2,4,6,8,9,14,36,86};
        System.out.println(binarySearch(A,0,7,69));
    }

    //output index at which key is found, or -1 if not found. A is sorted. O(log n) running time
    public static int binarySearch(int[] A, int i, int j, int x){
        if(i<=j){
            int e = (i+j)/2;
            if(A[e]>x) return binarySearch(A,i,e-1,x);      //Search left half
            else if(A[e]<x) return binarySearch(A,e+1,j,x); //Search right half
            else return e;
        }else{
            return -1;
        }
    }
}
