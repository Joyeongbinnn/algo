package swea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 벽돌깨기 {

	static int N, W, H, ans;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			ans = Integer.MAX_VALUE;
			int[][] map = new int[H][W];

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			dfs(map, 0);
			System.out.printf("#%d %d\n", t, ans);
		}

	}

	private static void dfs(int[][] map, int depth) {
		if (depth == N) {
			int cnt = count(map);
			ans = Math.min(ans, cnt);
			return;
		}

		for (int i = 0; i < W; i++) {
			if (check(map, i))
				continue;
			int[][] copy = deepcopy(map);
			play(copy, i);
			dfs(copy, depth + 1);
		}
		dfs(map, depth + 1);
	}

	private static void play(int[][] map, int drop) {

		for (int i = 0; i < H; i++) {
			if (map[i][drop] != 0) {
				hit(map, i, drop);
				break;
			}
		}
		// todo : 정렬
		sort(map);

	}

	private static void sort(int[][] map) {
		for(int j = 0; j < W; j++) {
			List<Integer> list = new ArrayList<>();
			for(int i = H-1; i >= 0; i--) {
				if(map[i][j] != 0) {
					list.add(map[i][j]);
				}
				map[i][j] = 0;
			}
			int k = H-1;
			for(int num : list) {
				map[k--][j] = num;
			}
		}
		
	}

	private static void hit(int[][] map, int x, int y) {
		int num = map[x][y];
		map[x][y] = 0;
		if (num > 1) {
			for (int i = 1; i < num; i++) {
				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j] * i;
					int ny = y + dy[j] * i;
					if (nx < 0 || ny < 0 || nx >= H || ny >= W)
						continue;
					hit(map, nx, ny);
				}
			}
		}
	}

	private static int[][] deepcopy(int[][] map) {
		int[][] copymap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copymap[i][j] = map[i][j];
			}
		}
		return copymap;
	}

	private static boolean check(int[][] map, int j) {
		for (int i = 0; i < H; i++) {
			if (map[i][j] != 0) {
				return false;
			}
		}
		return true;
	}

	private static int count(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}
