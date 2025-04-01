package swea;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 보급로 {

	static int N;
	static int[][] D;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			D = new int[N][N];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			for (int i = 0; i < N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}
			Dijk();
			System.out.printf("#%d %d\n", t, D[N - 1][N - 1]);

		}

	}

	static void Dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		D[0][0] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			int curdist = cur.dist;

			if (D[x][y] < curdist)
				continue;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				int cost = D[x][y] + map[nx][ny];
				if (cost < D[nx][ny]) {
					D[nx][ny] = cost;
					pq.offer(new Node(nx, ny, cost));
				}

			}

		}

	}

}
