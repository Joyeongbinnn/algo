package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 해킹 {
		
	static int n, d, c, cnt;
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
	static List<Edge>[] adj;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			n = sc.nextInt();
			d = sc.nextInt();
			c = sc.nextInt();
			adj = new ArrayList[n+1];
			cnt = 0;
			
			for(int i = 1; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < d; i++) {
				int end = sc.nextInt();
				int start = sc.nextInt();
				int time = sc.nextInt();
				
				adj[start].add(new Edge(end, time));
			}
			
			int ans = dijk();
			System.out.printf("%d %d\n",cnt, ans);
			
		}

		
	}


	private static int dijk() {
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		int sum = 0;
		
		dist[c] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(c, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int now = cur.to;
			int d = cur.weight;
			
			if(d > dist[now])continue;
			for(Edge e : adj[now]) {
				if(dist[e.to] > e.weight + d) {
					dist[e.to] = d + e.weight;
					pq.add(new Edge(e.to, dist[e.to]));
				}
			}
		}
		for(int i = 1; i <= n; i++) {
			if(dist[i] != Integer.MAX_VALUE) {
				sum = Math.max(sum, dist[i]);
				cnt++;
			}
		}
		
		return sum;
	}

}
