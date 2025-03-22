package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소 {
		static int N, M, ans = 0;
		static int[][] map;
		static int[] dx = {-1, 0, 1, 0};
		static int[] dy = {0, 1, 0, -1};
		static int wall = 3;
		static boolean[][] v;
		static class Virus{
			int x;
			int y;
			
			public Virus(int x, int y) {
				this.x = x;
				this.y = y;
			}
		}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());	
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(ans);
	}
	private static void dfs(int dep, int start_i, int start_j) {
		if(dep == wall) {
			int[][] copy = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			v = new boolean[N][M];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(copy[i][j] == 2 && !v[i][j]) {
						bfs(i, j, copy);
					}
				}
			}
			
			int count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(copy[i][j] == 0) {
						count++;
					}
				}
			}
			
			
			
			ans = Math.max(ans, count);
			return;
		}
		
		
		
		for(int i = start_i; i < N; i++) {
			for(int j = (i == start_i ? start_j : 0); j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					dfs(dep + 1, i, j+1);
					map[i][j] = 0;
				}
			}
		}
		
	}
	private static void bfs(int x, int y, int[][] copy) { //바이러스가 퍼져나가~
		Queue<Virus> q = new LinkedList<>();
		q.add(new Virus(x, y));
		v[x][y] = true;
		
		
		while(!q.isEmpty()) {
			Virus cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny]) continue;
				if(copy[nx][ny] == 1)continue;
				copy[nx][ny] = 2;
				v[nx][ny] = true;
				q.add(new Virus(nx, ny));
			}
			
		}
		
		
		
		
		
	}
	
	
	
	
	

}
