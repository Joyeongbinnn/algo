package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 녹색옷입은애가젤다지 {

	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}
	static int N;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static int[][] map;
	static List<Edge>[] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = 0;
		while(true) {
			t++;
			N = sc.nextInt();
			if(N == 0) break;
			map = new int[N][N];
			list = new ArrayList[N*N];	
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i < N*N; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < N*N; i++) {
				int x = i / N;
				int y = i % N;
				for(int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N)continue;
					list[i].add(new Edge((nx*N) + ny, map[nx][ny]));					
				}
			}
			
			int ans = dijk();
			System.out.printf("Problem %d: %d\n", t, ans);

		}
		
	}
	private static int dijk() {
		int[] dist = new int[N*N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = map[0][0];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0, dist[0]));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int now = cur.to;
			int d = cur.weight;
			
			if(d > dist[now])continue;
			for(Edge e : list[now]) {
				if(dist[e.to] > d + e.weight) {
					dist[e.to] = d + e.weight;
					pq.add(new Edge(e.to, dist[e.to]));
				}
			}
			
		}
		
		
		return dist[N*N-1];
	}

}
