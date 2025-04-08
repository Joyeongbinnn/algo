package swea;

import java.util.Scanner;

public class 알고스탁DP {
	static int Ms, Ma, L, N;
	static int[][] stock;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			Ms = sc.nextInt();
			Ma = sc.nextInt();
			N = sc.nextInt();
			L = sc.nextInt();

			int prinsum = Ms + Ma * L;
			stock = new int[N + 1][L + 2];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= L; j++) {
					stock[i][j] = sc.nextInt();

				}
			}

			for (int j = 1; j <= L; j++) {
				int[] dp = new int[Ms + 1];
				for (int i = 1; i <= N; i++) {
					for (int k = stock[i][j]; k <= Ms; k++) {
						dp[k] = Math.max(dp[k - stock[i][j]] + stock[i][j + 1] - stock[i][j], dp[k]);
					}
				}
				Ms += dp[Ms];
				Ms += Ma;
			}
			System.out.println(Ms - prinsum);
		}
	}

}
