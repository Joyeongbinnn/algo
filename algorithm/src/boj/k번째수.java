package boj;


import java.util.Arrays;
import java.util.Scanner;

public class k번째수 {

    
    static int N, K; 
    static int[] arr, sum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        K = sc.nextInt();

        
        
        
        System.out.println(bis());
        

    }
    private static long bis() {
        long x = N;
        long y = N;
        long hi = x*y;
        long lo = 1;
        
        while(hi > lo) {
            long mid = (lo + hi)/2;
            long count = 0;
            
            for(int i = 1; i <= N; i++) {
            	count += Math.min(mid/i, N);
            }
            
            if(K <= count) {
            	hi = mid;
            }else {
            	lo = mid + 1;
            }
                
        }
        
        
        
        return lo;
    }

}
