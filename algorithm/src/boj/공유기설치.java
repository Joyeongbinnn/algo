package boj;

import java.util.Arrays;
import java.util.Scanner;

public class 공유기설치 {

	
	static int N, C; 
	static long[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		C = sc.nextInt();
		arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextLong();
		}
		
		Arrays.sort(arr);
		
		System.out.println(bis()-1);

	}
	private static long bis() {
		
		long hi = arr[N-1] - arr[0] + 1;
		long lo = 1;
		
		while(hi > lo) {
			long mid = (hi + lo)/2;
			
			long wifi = 1;
			long now = arr[0];
			for(int i = 1; i < N; i++) {
				long temp = arr[i] - now;
				if(temp >= mid) {
					now = arr[i];
					wifi++;
				}
			}
			
			if(wifi < C) {
				hi = mid;
				
			}else {
				lo = mid + 1;
			}
		}
		
		return (lo);
		
	}

}
