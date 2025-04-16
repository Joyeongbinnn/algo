package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 파티 {

	static class Edge implements Comparable<Edge> {
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

	static int N, M, X;
	static List<Edge>[] adj, Radj;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		adj = new ArrayList[N+1];
		Radj = new ArrayList[N+1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
			Radj[i] = new ArrayList<>();

		}
		
		for(int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int time = sc.nextInt();
			adj[start].add(new Edge(end, time));
			Radj[end].add(new Edge(start, time));
		}
		
		int[] toX = dijk(Radj, X);
		int[] fromX = dijk(adj, X);
		
		int ans = 0;
		for(int i = 1; i <=N; i++) {
			ans = Math.max(ans, toX[i] + fromX[i]);
		}
		System.out.println(ans);

	}

	private static int[] dijk(List<Edge>[] g, int start) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		dist[start] = 0;
		pq.add(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int now = cur.to;
			int d = cur.weight;
			
			if(d > dist[now]) continue;
			
			for(Edge e : g[now]) {
				if(dist[e.to] > d + e.weight) {
					dist[e.to] = d + e.weight;
					pq.offer(new Edge(e.to, dist[e.to]));
				}
			}
			
		}
		
		return dist;
	}
	
	

	

}
