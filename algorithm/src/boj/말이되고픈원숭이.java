package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 말이되고픈원숭이 {

	static int K, W, H;
	static int[] jx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] jy = { 1, 2, 2, 1, -1, -2, -2, 1 };
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][][] v;

	static class Mok {
		int x;
		int y;
		int k;
		int move;

		public Mok(int x, int y, int k, int move) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.move = move;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		map = new int[H][W];
		v = new boolean[H][W][31];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		System.out.println(bfs());

	}

	private static int bfs() {
		Queue<Mok> q = new LinkedList<>();

		q.add(new Mok(0, 0, K, 0));
		v[0][0][K] = true;

		while (!q.isEmpty()) {

			Mok cur = q.poll();
			if (cur.x == H - 1 && cur.y == W - 1) {
				return cur.move;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int k = cur.k;

				
				if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1)
					continue;
				if (v[nx][ny][k])
					continue;

				v[nx][ny][k] = true;
				q.add(new Mok(nx, ny, k, cur.move + 1));

			}
			
			if (cur.k > 0) {
				for (int j = 0; j < 8; j++) {
					int x = cur.x + jx[j];
					int y = cur.y + jy[j];

					if (x < 0 || y < 0 || x >= H || y >= W || map[x][y] == 1)
						continue;
					if (v[x][y][cur.k - 1])
						continue;

					v[x][y][cur.k - 1] = true;
					q.add(new Mok(x, y, cur.k - 1, cur.move + 1));
				}
			}
		}
		return -1;
	}
}
