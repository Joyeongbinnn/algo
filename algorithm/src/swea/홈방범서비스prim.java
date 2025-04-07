package swea;

import java.util.*;

public class 홈방범서비스prim {
	static int N, M, maxHome;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N][N];
			maxHome = 0;

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					map[i][j] = sc.nextInt();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j); // 각 위치에서 Prim처럼 확장
				}
			}

			System.out.printf("#%d %d\n", t, maxHome);
		}
	}

	static void bfs(int sx, int sy) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{sx, sy});
		visited[sx][sy] = true;

		int houseCnt = map[sx][sy];
		int k = 1;

		while (k <= N + 1) {
			int cost = k * k + (k - 1) * (k - 1);
			int profit = houseCnt * M;

			if (profit >= cost) {
				maxHome = Math.max(maxHome, houseCnt);
			}

			int size = q.size();
			for (int s = 0; s < size; s++) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];

				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

					if (manhattan(sx, sy, nx, ny) < k) {
						q.offer(new int[]{nx, ny});
						visited[nx][ny] = true;
						if (map[nx][ny] == 1) houseCnt++;
					}
				}
			}
			k++;
		}
	}

	static int manhattan(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
