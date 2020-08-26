package DynamicProgramming;

public class makeChange {
    private int[] C;

    public makeChange(int ... Coins){
        C = new int[Coins.length];
        for(int i =0; i<C.length;i++) C[i] = Coins[i];
    }

    public static void main(String[] Args){
        makeChange x = new makeChange(2,3);
        System.out.println(x.opt(5));
    }

    /*
    Input: an array C containing the values of the coins
            an integer n
    Output: The minimal number of coins needed to make a total of n
    Formula:
    Opt(n) = 1 + min{ Opt( n – C1 ), Opt( n – C2 ) , ... Opt( n – Ck ) }
     */
    public int opt(int n){
        //X is the array that stores Opt(0...n)
        int[] X  = new int[n+1];
        X[0] = 0;

        for(int i=1;i<n+1;i++) {
            int smallest = Integer.MAX_VALUE;
            for (int j = 0; j < C.length; j++) {
                if (C[j] <= i) {
                    smallest = Math.min(smallest, X[i - C[j]]);
                }
            }

            X[i] = smallest + 1;
        }
        return X[n];
    }
}
