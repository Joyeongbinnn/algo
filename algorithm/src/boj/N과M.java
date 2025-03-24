package boj;

import java.util.Arrays;
import java.util.Scanner;

public class N과M {
	
	static boolean[] v;
	static int[] arr, arr1;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		
		
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		v = new boolean[N];
		arr = new int[N];
		arr1 = new int[M];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		permute(N, M, 0);
		
		System.out.println(sb);
		
	}
	
	static void permute(int N, int M, int depth) {
		if(depth == M) {
			for (int val : arr1) {
				sb.append(val + " ");
			}
			sb.append('\n');
			return;
		}
		
	
		
		for(int i = 0; i < N; i++) {
			if(!v[i]) {
				if(i > 0 && arr[i] == arr[i-1]) {//
					if(!v[i-1]) {
						continue;	
					}
					
				}
				
				v[i] = true;
				arr1[depth] = arr[i];
				permute(N, M, depth + 1);
				v[i] = false;
			}
		}
	}
}
