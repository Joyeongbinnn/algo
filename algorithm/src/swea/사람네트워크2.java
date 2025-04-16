package swea;

import java.util.Scanner;

public class 사람네트워크2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int[][] dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int in = sc.nextInt();
					if (i != j && in == 0)
						dist[i][j] = Integer.MAX_VALUE;
					else
						dist[i][j] = in;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
							if (dist[i][k] + dist[k][j] < dist[i][j]) {
								dist[i][j] = dist[i][k] + dist[k][j];
							}
						}
					}
				}
			}
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (i != j && dist[i][j] != Integer.MAX_VALUE) {
						sum += dist[i][j];
					}
				}
				min = Math.min(sum, min);
			}

			System.out.printf("#%d %d\n", t, min);

		}

	}

}
