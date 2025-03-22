package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 치즈 {

	static int R, C, last;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] v;

	static class O2 {
		int x;
		int y;

		public O2(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int day = 0;
		while (check()) {

			count();
			v = new boolean[R][C];
			bfs();
			day++;
		}
		
		System.out.printf("%d\n%d", day, last);
	}

	private static void count() {
		int cheeze = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 1) {
					cheeze++;
				}
			}
		}
		last = cheeze;
	}

	private static boolean check() {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	private static void bfs() {
		Queue<O2> q = new LinkedList<>();
		q.add(new O2(0, 0));
		v[0][0] = true;
		while (!q.isEmpty()) {
			O2 cur = q.poll();
			int x = cur.x;
			int y = cur.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C || v[nx][ny])
					continue;
				v[nx][ny] = true;
				if (map[nx][ny] == 1) {
					map[nx][ny] = 0;
					continue;
				}
				q.add(new O2(nx, ny));
			}

		}

	}

}
