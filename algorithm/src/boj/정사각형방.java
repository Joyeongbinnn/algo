package boj;

import java.util.Scanner;

public class 정사각형방 {
	
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans, ans1, tmp;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			
			ans = 0;
			ans1 = Integer.MAX_VALUE;
			tmp = 0;
			N = sc.nextInt();
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dfs(i, j, 0, map[i][j]);
					
				}
			}
			
			System.out.printf("#%d %d %d\n", t,  ans1, ans+1);
			
		
			
			
		}
		

	}
	private static void dfs(int x, int y, int dep, int start) {
		if(dep >= ans) {
			if(dep == ans) {
				ans1 = Math.min(ans1, start);
			}else {
				ans1 = start;	
			}
			ans = dep;		//계산순서 조심하자

			
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N)continue;
			if(map[x][y] + 1  == map[nx][ny]) {
				dfs(nx, ny, dep + 1, start);

			}
			
		}
		
	}

}
