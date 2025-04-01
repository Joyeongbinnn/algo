package swea;

import java.util.*;
import java.io.*;

class 프로세서연결하기 {
	static class Core {
		int x, y;

		public Core(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N, cell[][], minLength, maxCore;
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };
	static List<Core> corel;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			cell = new int[N][N];
			corel = new ArrayList<>();

			// 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int in = Integer.parseInt(st.nextToken());
					if (in == 1) {
						cell[i][j] = in;

						if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
							continue;
						corel.add(new Core(i, j));
					}

				}
			}

			minLength = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;

			startConnect(0, 0, 0);

			sb.append("#" + t + " " + minLength + "\n");
		}

		System.out.println(sb.toString());
	}

	public static void startConnect(int idx, int ccount, int wcount) {
		if (idx == corel.size()) {
			if (maxCore < ccount) { //
				maxCore = ccount;
				minLength = wcount;
			} else if (maxCore == ccount) {
				minLength = Math.min(wcount, minLength);
			}
			return;
		}

		int x = corel.get(idx).x;
		int y = corel.get(idx).y;

		for (int dir = 0; dir < 4; dir++) {
			int count = 0, nx = x, ny = y;

			while (true) {
				nx += dx[dir];
				ny += dy[dir];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
					break;
				}

				if (cell[ny][nx] == 1) {
					count = 0;
					break;
				}

				count++;
			}

			int fill_x = x;
			int fill_y = y;

			for (int i = 0; i < count; i++) {
				fill_x += dx[dir];
				fill_y += dy[dir];
				cell[fill_y][fill_x] = 1;
			}

			if (count == 0)
				startConnect(idx + 1, ccount, wcount);
			else {
				startConnect(idx + 1, ccount + 1, wcount + count);

				fill_x = x;
				fill_y = y;

				for (int i = 0; i < count; i++) {
					fill_x += dx[dir];
					fill_y += dy[dir];
					cell[fill_y][fill_x] = 0;
				}
			}
		}
	}
}