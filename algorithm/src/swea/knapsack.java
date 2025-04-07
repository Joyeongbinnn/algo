package swea;

import java.util.Scanner;

public class knapsack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			
			
			int N = sc.nextInt();
			int K = sc.nextInt();
			int[][] map = new int[N+1][K+1];
			int[][] M = new int[N+1][2];
			
			for(int i = 1; i <= N; i++) {
				int V = sc.nextInt();
				int C = sc.nextInt();
				M[i][0] = V;
				M[i][1] = C;
				
			}
			
			for (int i = 1; i <= N; i++) {
			    for (int w = 1; w <= K; w++) {
			        if (w < M[i][0]) {
			            map[i][w] = map[i - 1][w]; // 현재 물건 넣을 수 없음
			        } else {
			            map[i][w] = Math.max(map[i - 1][w], map[i - 1][w - M[i][0]] + M[i][1]);
			        }
			    }
			}

			System.out.printf("#%d %d\n", t, map[N][K]);
			
		}
		
	}

}
