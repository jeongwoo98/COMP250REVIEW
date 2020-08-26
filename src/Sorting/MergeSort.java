package Sorting;
/*  Merge Sort is a Divide and Conquer algorithm. It divides input
    array in two halves, calls itself for the two halves and then merges
    the two sorted halves. Runtime: T(n) = 2T(n/2) + O(n)
    Worst-case running time : O(n*log n)
    */

import java.util.Collections;

public class MergeSort {
    public static void sort(int[] A, int l, int r){
        if(l<r) {
            int m = (l + r) / 2;
            sort(A, l, m);
            sort(A, m + 1, r);
            merge(A,l,m,r);
        }
    }

    // Merges two SORTED sub-arrays of A[]
    // First subarray is A[l..m]
    // Second subarray is A[m+1..r]
    public static void merge(int[] A, int l, int m, int r){
        //Size of two sub-arrays
        int n1 = m-l+1;
        int n2 = r-m;

        //Create two sub-arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        //Copy data to temp arrays
        for(int i=0;i<n1; i++) L[i] = A[l+i];
        for(int j=0;j<n2; j++) R[j] = A[m+1+j];

        //Merge Process:
        int i =0, j =0;     //indices of sub-arrays, L and R respectively
        int k = l;          //index of merged array.

        while(i<n1 && j<n2){
            if(L[i] <= R[j]){
                A[k] = L[i];
                i++;
            }else{
                A[k] = R[j];
                j++;
            }
            k++;
        }

        //Copy remaining elements of L[] or R[] into merged array if there are any
        while(i<n1){
            A[k] = L[i];
            i++;
            k++;
        }
        while(j<n2){
            A[k] = R[j];
            j++;
            k++;
        }
    }
    public static void main(String args[])
    {
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        sort(arr,0,arr.length-1);
        for(int i=0; i<arr.length; i++) System.out.print(arr[i]+"   ");
    }
}
