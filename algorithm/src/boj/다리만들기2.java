package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 다리만들기2 {
	static class Island{
		int x;
		int y;
		int number;
		
		Island(int x, int y, int number){
			this.x = x;
			this.y = y;
			this.number = number;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] v;
	static int N, M;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		v = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int k = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!v[i][j] && map[i][j] == 1) {
					bfs(i, j, k++);
				}
				
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.printf("%d ", map[i][j]);
				
			}
			System.out.println();
		}
		
		
		
		
	}
	
	static void bfs(int x, int y, int num) {
		Queue<Island> q = new LinkedList<>();
		Island is = new Island(x, y, num);
		
		q.offer(is);
		while(!q.isEmpty()) {
			Island now = q.poll();
			v[now.x][now.y] = true;
			map[now.x][now.y] = now.number; 
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || v[nx][ny] || map[nx][ny] == 0) continue;
				q.add(new Island(nx, ny, num));
			}
			
			
		}
		
	}

}
