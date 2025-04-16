package boj;

import java.util.Scanner;

public class 키순서 {
	static int N, M, P1, P2;
	static int[][] adj;
	static boolean[] v;


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		int ans = 0;
		
		adj = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			adj[start][end] = 1;
		}

		for (int i = 1; i < N+1; i++) {
			P1 = 0;	//초기화 for문안에서 안하고 뭐하냐
			P2 = 0;

			v = new boolean[N + 1];
			v[i] = true;
			dfs1(i);
			v = new boolean[N + 1];
			v[i] = true;
			dfs2(i);
			int sum = P1 + P2;
			if (sum == N - 1) {
				ans++;
			}

		}
		System.out.println(ans);

	}

	private static void dfs2(int x) {
		for (int i = 1; i < N + 1; i++) {
			if (adj[i][x] == 0 || v[i])
				continue;
			v[i] = true;
			P2++;
			dfs2(i);
		}

	}

	private static void dfs1(int x) {
		for (int i = 1; i < N + 1; i++) {
			if (adj[x][i] == 0 || v[i])
				continue;
			v[i] = true;
			P1++;
			dfs1(i);
		}

	}

}
