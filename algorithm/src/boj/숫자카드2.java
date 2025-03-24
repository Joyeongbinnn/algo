package boj;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class 숫자카드2 {
	
	static int N, C;

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		StringBuilder sb = new StringBuilder();
		
		Arrays.sort(arr);
		
		int M = sc.nextInt();
		
		for(int i = 0; i < M; i++) {  //이분탐색
			int key = sc.nextInt();
			
			sb.append(upp(arr, key) - low(arr, key)).append(' ');
			
		}
		System.out.println(sb);
		

	}

	private static int low(int[] arr, int key) {
		int lo = 0;
		int hi = arr.length;
		
		while(lo < hi) {
			int mid = (lo + hi) /2;
			
			if(key <= arr[mid]) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		return lo;
	}

	private static int upp(int[] arr, int key) {
		int lo = 0;
		int hi = arr.length;
		
		while(lo < hi) {
			int mid = (lo + hi) /2;
			
			if(key < arr[mid]) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		return lo;
	}

}
