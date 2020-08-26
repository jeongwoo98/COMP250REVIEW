package DynamicProgramming;

public class Fibonacci {
    public static void main(String[]args){
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
    }
    public static int fib(int n){
        int[] F = new int[n+1];
        F[0] = 0;
        F[1] = 1;
        for(int i=2; i<n+1;i++){
            F[i] = F[i-2] + F[i-1];
        }
        return F[n];
    }
}

/*
    Solve each small problem once, saving their solution
    Use the solutions of the smaller problems to obtain solutions to
    larger problems.

    Bottom-up Approach.
 */