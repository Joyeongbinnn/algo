package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;



public class 작업순서 {
	
	
	static int V, E;
	static List<ArrayList<Integer>> list;
	static List<Integer> tO;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			for(int i = 0; i <= V; i++) {
				list.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				list.get(start).add(end);
			}
			
			bfs();
			System.out.printf("#%d", t);
			for(int n : tO) {
				System.out.printf(" %d", n);
			}
			System.out.println();
		}
	}
	
	private static void bfs() {
		int[] inDegree = new int[V+1];
		for(int i = 1; i <= V; i++) {
			for(int v : list.get(i)) {
				inDegree[v]++;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= V; i++) {
			if(inDegree[i] == 0){
				q.add(i);
			}
		}
		
		tO = new ArrayList<>();
		while (!q.isEmpty()) {
			int cur = q.poll(); 
			tO.add(cur);
			
			for(int next : list.get(cur)) {
				inDegree[next]--;
				if(inDegree[next] == 0) {
					q.add(next);
				}
			}
		}
	}

}
