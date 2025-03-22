package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 무선충전 {
    
    static class BC{
        int x;
        int y;
        int C;
        int P;
        public BC(int x, int y, int C, int P) {
            this.x = x;
            this.y = y;
            this.C = C;
            this.P = P;
        }
    }
    static class User{
        int x;
        int y;
        
        public User(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static int M, A;
    static int[][] marr;
    static int[][][] map;
    static boolean[] v;
    static BC[] BCarr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            marr = new int[2][M];
            BCarr = new BC[A]; 
            int N = 10;
            map = new int[N+1][N+1][A];
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                marr[0][i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                marr[1][i] = Integer.parseInt(st.nextToken());
            }
            
            for(int i = 0; i < A; i++) {//충전소 설치
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                
                BCarr[i] = new BC(x, y, C, P);
                
                map[y][x][i] = P;
                    
                for(int o = 1; o <= N; o++) {
                    for(int p = 1; p <= N; p++) {
                        if(Math.abs(o - y) + Math.abs(p - x) <= C) {
                            map[o][p][i] = P;

                        }
                    }
                }
            }
            User user1 = new User(1, 1);
            User user2 = new User(10, 10);
            int x1 = user1.x;
            int y1 = user1.y;
            int x2 = user2.x;
            int y2 = user2.y;
            int test = 0;
            
            for(int i = 0; i <= M; i++) { //사용자 이동시작
                v = new boolean[A];
                int max = 0;
                max = perm(map[y1][x1], map[y2][x2]);
                if(i!=M) {
                    x1 += dx[marr[0][i]];    //이동
                    y1 += dy[marr[0][i]];
                    x2 += dx[marr[1][i]];
                    y2 += dy[marr[1][i]];
                }
                test += max;
            }
            System.out.printf("#%d %d\n", t, test);
        }
    }
    
	private static int perm(int[] arr1, int[] arr2) {
		int max = 0;
		for(int i = 0; i < A; i++) {
			for(int j = 0; j < A; j++) {
				int temp = 0;
				if(i == j) {
				    if(arr1[i] > 0 && arr2[i] > 0)
				        temp = arr1[i] / 2 + arr2[i] / 2; // 두 사용자 모두 연결된 경우
				    else
				        temp = arr1[i] + arr2[i];         // 한쪽만 연결된 경우
				} else {
				    temp = arr1[i] + arr2[j];
				}

				max = Math.max(max, temp);
			}
		}
		return max;
	}
}
