package boj;

import java.util.Scanner;

public class 로봇청소기 {

	static int N, M, ans;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;

	static class Robot {
		int x;
		int y;
		int d;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		ans = 0;
		dfs(r, c, d);
		System.out.println(ans);
		
	}

	private static void dfs(int x, int y, int d) {
		
		if(map[x][y] == 0) { //청소
			map[x][y] = 2;
			ans++;
		}
		
		int back = (d + 2) % 4;
		int turn = (d + 3) % 4;
		int nx = x + dx[back];
		int ny = y + dy[back];
		
		if (!search(x, y)) {
			if (map[nx][ny] == 1) {
				return;
			} else {
				dfs(nx, ny, d);
			}
		} else {
			nx = x + dx[turn];
			ny = y + dy[turn];
			int nd = (d + 3) % 4;
			if(map[nx][ny] == 0) {
				dfs(nx, ny, nd);
			}else {
				dfs(x, y, nd);
			}
		}

	}

	private static boolean search(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (map[nx][ny] == 0) {
				return true;
			}

		}
		return false;
	}

}
