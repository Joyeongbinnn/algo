package swea;

import java.util.Scanner;

public class 사랑의카운슬러 {

	static int N;
	static int[][] worm;
	static boolean[] selected;
	static long ans;
	static long totalX, totalY;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			worm = new int[N][2];
			selected = new boolean[N];
			totalX = 0;
			totalY = 0;
			ans = Long.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				worm[i][0] = sc.nextInt();
				worm[i][1] = sc.nextInt();

			}

			comb(0, 0); // idx, cnt, xSum, ySum
			System.out.printf("#%d %d\n", t, ans);
		}

		sc.close();
	}

	static void comb(int idx, int count) {
		if (count == N / 2) {
			int x1 = 0, y1 = 0; // 선택된 그룹 벡터합
			int x2 = 0, y2 = 0; // 선택 안된 그룹 벡터합

			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					x1 += worm[i][0];
					y1 += worm[i][1];
				} else {
					x2 += worm[i][0];
					y2 += worm[i][1];
				}
			}

			long dx = x1 - x2;
			long dy = y1 - y2;
			long dist = dx * dx + dy * dy;
			ans = Math.min(ans, dist);
			return;
		}

		if (idx == N)
			return;

		selected[idx] = true;
		comb(idx + 1, count + 1);
		selected[idx] = false;
		comb(idx + 1, count);
	}
}
