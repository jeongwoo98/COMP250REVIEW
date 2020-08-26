package DynamicProgramming;

import java.util.ArrayList;

public class LongestIncreasingSubsequence {

    //Input: an array A[0...n-1] of numbers
    //Output: the length of the longest increasing subsequence of A
    public static int lengthOfLIS(int[] A, int n){
        // Length of the longest increasing subsequence ending at position i and containing A[i].
        int[] LIS = new int[n+1];
        LIS[0] =1;

        // LIS[i] = 1+max{LIS[j]:j<i and A[j]<A[i]}
        for(int i=1;i<n;i++){
            LIS[i] = -1;
            for(int j=0;j<i;j++){
                if(A[j]<A[i] && LIS[j]+1>LIS[i]){
                    LIS[i] = 1 + LIS[j];
                }
            }
            if(LIS[i]==-1) LIS[i]=1;
        }

        int max =0;
        for(int num : LIS){
            if(num>max){
                max = num;
            }
        }

        return max;
    }

    public static void main(String[] args){
        int[] x = {5,1,4,2,8};
        System.out.println(lengthOfLIS(x,x.length));
    }
}
