package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class 소문난7공주 {

	static char[][] map = new char[5][5];
	static int ans = 0;
	static int[] v = new int[7];
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}

		combine(0, 0);

		System.out.println(ans);
	}

	private static void combine(int idx, int cnt) {
		if (cnt == 7) {
			List<int[]> coords = new ArrayList<>();
			int sCount = 0;

			for (int i = 0; i < 7; i++) {
				int num = v[i];
				int x = num / 5;
				int y = num % 5;
				coords.add(new int[] { x, y });
				if (map[x][y] == 'S')
					sCount++;
			}

			if (sCount >= 4 && isConnected(coords)) {
				ans++;
			}
			return;
		}

		for (int i = idx; i < 25; i++) {
			v[cnt] = i;
			combine(i + 1, cnt + 1);
		}
	}

	private static boolean isConnected(List<int[]> coords) {
		int[][] map = new int[5][5];
		int cnt = 0;
		boolean[][] vi = new boolean[5][5];
		for (int[] coor : coords) {
			int x = coor[0];
			int y = coor[1];
			map[x][y] = 1;
		}
		int[] coor = coords.get(0);
		vi[coor[0]][coor[1]] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(coor);
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			cnt ++;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || vi[nx][ny] || map[nx][ny] == 0)
					continue;
				vi[nx][ny] = true;
				q.add(new int[] { nx, ny });

			}

		}
		if(cnt != 7) {
			return false; 
		}
		return true;
	}
}
