package swea;

import java.util.Scanner;

public class 키순서 {
	static int[][] adj;
	static int N, res, M;
	static int people = 0;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			adj = new int[N + 1][N + 1];
			res = 0;

			for (int i = 0; i < M; i++) {

				int x = sc.nextInt();
				int y = sc.nextInt();

				adj[x][y] = 1;

			}

			for (int i = 1; i <= N; i++) {

				people = 0;
				dfs1(i, new boolean[N + 1]);
				dfs2(i, new boolean[N + 1]);

				if (people == N - 1) {
					res++;
				}
			}

			System.out.printf("#%d %d\n", t, res);
		}

	}

	static void dfs1(int student, boolean[] v1) {
		v1[student] = true;

		for (int i = 1; i <= N; i++) {// 역방향
			if (adj[i][student] == 1 && !v1[i]) {
				people++;
				dfs1(i, v1);
			}
		}

	}

	static void dfs2(int student, boolean[] v2) {
		v2[student] = true;

		for (int i = 1; i <= N; i++) {// 정방향
			if (adj[student][i] == 1 && !v2[i]) {
				people++;
				dfs2(i, v2);
			}
		}

	}

}