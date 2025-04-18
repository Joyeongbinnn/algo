package swea;

import java.util.Scanner;

public class 나무높이Greedy {
	static int Ans = Integer.MAX_VALUE;
	static int N;
	static int[] tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			Ans = Integer.MAX_VALUE;
			N = sc.nextInt();
			tree = new int[N];
			int[] diffTree = new int[N];
			int limit = 0;
			for (int i = 0; i < N; i++) {
				tree[i] = sc.nextInt();
				limit = Math.max(limit, tree[i]);
			}

			

			System.out.printf("#%d %d\n", t, Ans);

		}

	}

}
