package boj;

import java.util.Arrays;
import java.util.Scanner;

public class 나무자르기 {

    
    static int N, M; 
    static long[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new long[N];
        
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextLong();
        }
        
        Arrays.sort(arr);
        
        System.out.println(bis()-1);

    }
    private static long bis() {
        
        long hi = arr[N-1];
        long lo = 0;
        
        while(hi > lo) {
            long mid = (hi + lo)/2;
            
            long sum = 0;
            for(int i = 0; i < N; i++) {
            	
            	if(arr[i]-mid > 0) {
                	sum += arr[i]-mid;
            	}
            	
            }
            
            if(sum >= M) {
            	lo = mid + 1;
            }else {
            	hi = mid;
            }
        }
        return lo;   
    }

}
