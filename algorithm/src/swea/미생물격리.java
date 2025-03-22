package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 미생물격리 {
	static class Mi{
		int x;
		int y;
		int num;
		int dir;
		
		
		public Mi(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			
		}
	}
	static List<Mi> list;
	static int N, M, K;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, -1, 1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		
		int T =Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list.add(new Mi(x, y, num, dir));
				
			}
			
			for(int m = 0; m < M; m++) { //m 시간동안의
				map = new int[N][N];
				int n = list.size();
				for(int i = 0; i < n; i++) { //미생물들의 이동
					Mi cur = list.get(i);
					int d = cur.dir;
					int num = cur.num;
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if(nx == 0 || nx == N - 1 || ny == 0 || ny == N - 1) { //약품처리 된곳이라면
						num = num/2;
						if(d == 1)d = 2;
						else if (d == 2)d = 1;
						else if (d == 3)d = 4;
						else if (d == 4)d = 3;
					}
					map[nx][ny]++;
					
					
					
					list.set(i, new Mi(nx, ny, num, d));
				}
				
				System.out.println();
				
				for(int i = 0; i < N; i++) {	//이동후 미생물이 겹쳤다면
					for(int j = 0; j < N; j++) {
						if(map[i][j] > 1) {
							int x = i;
							int y = j;
							int d = 0;
							int num = 0;
							int index = 0;
							int max = 0;
							int delete[] = new int[map[i][j]];
							for(int k = 0; k < list.size(); k++) {
								Mi mi = list.get(k);
								if(mi.x == i && mi.y == j) { //병합
									if(mi.num > max) {
										max = mi.num;
										d = mi.dir;
									}
									num += mi.num;
									delete[index++] = k;
								}
							}
							for(int re = map[i][j] - 1; re >= 0; re--) {
								
								list.remove(delete[re]);
							}
							list.add(new Mi(x, y, num, d));
						}
					}
				}
			}
			
			int ans = 0;
			for(int i = 0; i < list.size(); i++) {
				ans += list.get(i).num;
			}
			System.out.printf("#%d %d \n", t, ans);
		}
	}
}
