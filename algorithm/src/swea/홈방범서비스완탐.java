package swea;

import java.util.Scanner;

public class 홈방범서비스완탐 {
	static int N, M, house, ans;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;

	static class Secu {
		int x;
		int y;
		int k;
		int prof;

		public Secu(int x, int y, int k, int prof) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.prof = prof;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			house = 0;
			ans = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					for (int k = 1; k < N + N; k++) {
						if (inhome(new Secu(i, j, k, 0)) >= 0) {
							ans = Math.max(ans, house);
						}
						house = 0;
					}
				}
			}
			System.out.printf("#%d %d\n", t, ans);

		}

	}

	static int inhome(Secu secu) {
		int x = secu.x;
		int y = secu.y;
		int k = secu.k;
		int prof = 0;
		int minus = k * k + ((k - 1) * (k - 1));

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int temp = Math.abs(i - x) + Math.abs(j - y);
				if (temp < k) {
					if (map[i][j] == 1) {
						prof += M;
						house++;
					}
				}
			}
		}
		return prof - minus;
	}

}
