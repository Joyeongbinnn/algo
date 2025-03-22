package swea;

import java.util.Scanner;

public class 달팽이 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		Scanner sc = new Scanner(System.in);
		int[][] map;
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			
			int N = sc.nextInt();
			map = new int[N][N];
			
			int num = 1;
			int x = 0;
			int y = 0;
			int i = 0;
			map[x][y] = num++;
			while(true) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] != 0) {
					i++;
					if(num > N*N)break;
				}
				if(i == 4)i = 0;
				x += dx[i];
				y += dy[i];
				map[x][y] = num++;
				
				
				
				
			}
			
			
			
			
			
			
			System.out.printf("#%d\n", t);
			for(int o = 0; o < N; o++) {
				for(int p = 0; p < N; p++) {
					System.out.printf("%d ", map[o][p]);
				}
				System.out.println();
			}
		}

	}

}
