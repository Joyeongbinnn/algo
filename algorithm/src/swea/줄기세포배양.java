package swea;

import java.util.Scanner;

public class 줄기세포배양 {
	
	static int N, M, K;
	static int pad = 350;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	
	static class Cell{
		int X;		//생명력
		int born;	//태어난시간
		int age;	//태어난 후 지난 시간
		int state; 	// 0 : 비활성, 1 : 활성, 2 : 사멸
		int split;
		
		public Cell(int X, int born, int state, int split) {
			this.X = X;
			this.born = born;
			this.state = state;
			this.split = split;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			pad = K+2;
			Cell[][] map, copy;
			map = new Cell[pad+N][pad+M];
			copy = new Cell[pad+N][pad+M];

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					int X = sc.nextInt();
					if(X > 0) {
						map[i+pad/2][j+pad/2] = new Cell(X, 0, 0, 0);
					}
				}
			}
			
			for(int k = 0; k <= K; k++) {
				
				deepcopy(copy, map);

				
				//분열시작
				for (int i = 0; i < N + pad; i++) {
				    for (int j = 0; j < M + pad; j++) {
				        Cell cur = map[i][j];
				        if (cur == null || cur.state != 1 || cur.split == 1) continue; // 조건 미충족 시 건너뜀

				        int life = cur.X;

				        for (int d = 0; d < 4; d++) {
				            int nx = i + dx[d];
				            int ny = j + dy[d];

				            if (map[nx][ny] == null) { // 원본에 아무도 없을 때
				                if (copy[nx][ny] == null || copy[nx][ny].X < life) {
				                    copy[nx][ny] = new Cell(life, k, 0, 0);
				                }
				            }
				        }

				        cur.split = 1; // 분열 표시
				    }
				}


				aging(k, map);
				deepcopy(map, copy);
				//System.out.println(count(map));

			}
			int ans = count(map);
			System.out.printf("#%d %d\n", t, ans);
		}
	}

	private static int count(Cell[][] map) {
		int cnt = 0;
		for(int i = 0; i < N + pad; i++) {
			for(int j = 0; j < M + pad; j++) {
				if(map[i][j] != null && map[i][j].state != 2) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void deepcopy(Cell[][] map1, Cell[][] map2) {
		for(int i = 0; i < N + pad; i++) {
			for(int j = 0; j < M + pad; j++) {
				if(map2[i][j] != null) {
					map1[i][j] = map2[i][j];
				}
			}
		}
		
	}



	private static void aging(int k, Cell[][] map) {
		
		for(int i = 0; i < N + pad; i++) {
			for(int j = 0; j < M + pad; j++) {
				if(map[i][j] != null) {
					map[i][j].age = k - map[i][j].born;
					
					if(map[i][j].age >= map[i][j].X * 2) {
						map[i][j].state = 2; //사멸
					}else if (map[i][j].age >= map[i][j].X) {
						map[i][j].state = 1; //활성기
					}
				}
			}
		}		
	}

}
