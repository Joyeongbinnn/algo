package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class 달이차오른다가자 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N, M;
	static char[][] map;
	static Minsic minsic;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N][M];
		String str = "";
		for(int i = 0; i < N; i++) {
			str = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					minsic = new Minsic(i, j, 0, 0);
				}
			}
		}
		bfs(minsic);
		if(ans == 0)ans = -1;
		
		System.out.println(ans);
	}
	
	
	static int ans = 0;
	private static void bfs(Minsic minsic) {
		Queue<Minsic> q = new ArrayDeque<>();
		q.offer(minsic);
		boolean[][][] v = new boolean[N][M][1<<6];
		
		v[minsic.r][minsic.c][minsic.key] = true;
		
		while(!q.isEmpty()){
			Minsic p = q.poll();
			
			if(map[p.r][p.c] == '1') {//도착이라면
				ans = p.cnt;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				int nk = p.key;
				//지도 영역체크
				if(check(nr, nc) && !v[nr][nc][nk] && map[nr][nc] != '#') {
					// 열쇠를 만났을 경우
					if(map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
						nk = p.key | (1<<(map[nr][nc]-'a'));		//비트마스크
					}
					// 문을 만났을 경우
					if(map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
						if((p.key & (1<<(map[nr][nc]-'A')))==0) {
							//열쇠가 없다면 이동못하징
							continue;
						}
					}
					//이동
					v[nr][nc][nk] = true;
					q.add(new Minsic(nr, nc, nk, p.cnt+1));
				}
			}
		}
		
		
	}

	private static boolean check(int nr, int nc) {
		if(nr>=0&&nr<N&&nc>=0&&nc<M)return true;
		return false;
	}

	static class Minsic{
		int r, c, cnt;
		int key;
		Minsic(int r, int c, int key, int cnt){
			this.r = r;
			this.c = c;
			this.key = key;
			this.cnt = cnt;
		}
	}

}
