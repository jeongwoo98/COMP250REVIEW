package Recursion;

public class Power {
    public static void main(String[] Args){
        System.out.println(RecursivePower(2,4));
    }

    //Return a^n
    public static int IterativePower(int a, int n){
        int product = 1;
        for(int i=0;i<n;i++){
            product *= a;
        }
        return product;
    }

    //Recursively:
    public static int RecursivePower(int a, int n){
        if(n == 0) return 1;                            //base case
        else return a * RecursivePower(a, n-1);     //recursive case
    }
}
