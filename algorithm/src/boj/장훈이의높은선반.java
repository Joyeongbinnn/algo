package boj;

import java.util.Scanner;

public class 장훈이의높은선반 {
	static int T, N, B, ans;
	static int[] arr;
	static boolean[] v;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			B = sc.nextInt();
			arr = new int[N];
			v = new boolean[N];

			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			ans = Integer.MAX_VALUE;
			
			perm(0, 0, 0);
			
			
			System.out.printf("#%d %d\n", t, ans - B);
		}
		

	}

	private static void perm(int depth, int sum, int start) {
		if(sum >= B) {
			
			ans = Math.min(sum, ans);
			return;
		}
		
		for(int i = start; i < N; i++) {
			if(v[i]) continue;
			v[i] = true;
			
			perm(depth + 1, sum + arr[i], i + 1);
			v[i] = false;
			
		}
		
	}

}
