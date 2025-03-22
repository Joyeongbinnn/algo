package boj;

import java.util.Scanner;

public class 마법사상어와파이어볼 {
	
	static int N, M, K;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][][] map, copy;
	static int[][] fireball;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		fireball = new int[M][5];  //위치 r,c 질량 m, 방향 d, 속력 s; 
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < 5; j++) {
				fireball[i][j] = sc.nextInt();
			}
		}
		
		map = new int[N + 1][N + 1][6];

		for(int k = 0; k < K; k++) {
			copy = new int[N + 1][N + 1][6];
			
			int r;
			int c;
			int m;
			int d;
			int s;

			for(int i = 0; i < M; i++) {
				r = fireball[i][0];
				c = fireball[i][1];
				m = fireball[i][2];
				d = fireball[i][3];
				s = fireball[i][4];
				
				
				
				int nr = r + (dr[d] * s); //1. 이동
				int nc = c + (dc[d] * s);
				
				copy[nr][nc][5] += 1;	//[5]에는 몇개의 파이어볼이 있는지.
				copy[nr][nc][0] = nr;
				copy[nr][nc][1] = nc;
				copy[nr][nc][2] += m;
				
				copy[nr][nc][3] = d;
				
				copy[nr][nc][4] += s;
				
				
			}
			
			
			for(int i = 1; i <=N; i++ ) {
				for(int j = 1; j <= N; j++) {
					if(copy[i][j][5] > 1) {
						
					}
				}
			}
		}
		
		
		
	}

}
