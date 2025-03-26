package pgs;

import java.util.*;

class 전력망을둘로나누기 {
	public int solution(int n, int[][] wires) {
		int answer = Integer.MAX_VALUE;

		for (int w = 0; w < wires.length; w++) {
			int[][] map = new int[n][n];
			boolean[] v = new boolean[n];
			int idx = 0;

			for (int k = 0; k < wires.length; k++) {

				if (k == w) {
					continue;
				}
				int start = wires[k][0] - 1;
				int end = wires[k][1] - 1;
				map[start][end] = 1;
				map[end][start] = 1;
			}
			int[] count = new int[2];
			int come = 0;

			for (int i = 0; i < n; i++) {
				if (!v[i]) {
					count[come++] = bfs(map, v, i);
				}
			}
			answer = Math.min(answer, Math.abs(count[0] - count[1]));
		}

		return answer;
	}

	static int bfs(int[][] map, boolean[] v, int start) {
		Queue<Integer> q = new LinkedList<>();
		int count = 1;
		v[start] = true;
		q.add(start);

		while (!q.isEmpty()) {

			int cur = q.poll();

			for (int i = 0; i < map.length; i++) {
				if (map[cur][i] == 1 && !v[i]) {
					v[i] = true;
					count++;
					q.add(i);
				}
			}

		}
		return count;

	}
}

class 전령망을둘로나누기2 {
	int N, min;
	int[][] map;
	int[] vst;

	int dfs(int n) {
		vst[n] = 1;
		int child = 1;
		for (int i = 1; i <= N; i++) {
			if (vst[i] == 0 && map[n][i] == 1) {
				child += dfs(i);
			}
		}
		min = Math.min(min, Math.abs(child - (N - child)));
		return child;
	}

	public int solution(int n, int[][] wires) {
		N = n;
		min = n;
		map = new int[n + 1][n + 1];
		vst = new int[n + 1];
		for (int[] wire : wires) {
			int a = wire[0], b = wire[1];
			map[a][b] = map[b][a] = 1;
		}
		dfs(1);
		return min;
	}
}