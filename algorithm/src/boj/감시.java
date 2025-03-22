package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 감시 {

    static class Cctv {
        int x, y, type;
        public Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    static int[][] map;
    static int N, M, cctvN, ans = Integer.MAX_VALUE;
    // 상, 우, 하, 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Cctv[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 최대 8대의 CCTV를 담는 배열
        arr = new Cctv[8];
        cctvN = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());	
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) {
                    arr[cctvN++] = new Cctv(i, j, map[i][j]);
                }
            }
        }
        
        boolean[][] visited = new boolean[N][M];
        dfs(0, visited);
        System.out.println(ans);
    }
    
    // dep : 현재 처리중인 CCTV 인덱스, v : 현재까지 감시된 영역
    private static void dfs(int dep, boolean[][] v) {
        if (dep == cctvN) {
            int blind = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!v[i][j] && map[i][j] == 0) {
                        blind++;
                    }
                }
            }
            ans = Math.min(ans, blind);
            return;
        }
        
        int type = arr[dep].type;
        int x = arr[dep].x;
        int y = arr[dep].y;
        // CCTV 위치
        v[x][y] = true;
        
        if (type == 1) {
            for (int i = 0; i < 4; i++) {
                boolean[][] copy = deepCopy(v);
                extend(copy, x, y, i);
                dfs(dep + 1, copy);
            }
        } else if (type == 2) {
            // 2가지 경우
            for (int i = 0; i < 2; i++) {
                boolean[][] copy = deepCopy(v);
                extend(copy, x, y, i);
                extend(copy, x, y, i + 2);
                dfs(dep + 1, copy);
            }
        } else if (type == 3) {
            // 4가지 경우
            for (int i = 0; i < 4; i++) {
                boolean[][] copy = deepCopy(v);
                extend(copy, x, y, i);
                extend(copy, x, y, (i + 1) % 4);
                dfs(dep + 1, copy);
            }
        } else if (type == 4) {
            // 4가지 경우
            for (int i = 0; i < 4; i++) {
                boolean[][] copy = deepCopy(v);
                for (int j = 0; j < 4; j++) {
                    if (j == i) continue;  // i 방향만 제외
                    extend(copy, x, y, j);
                }
                dfs(dep + 1, copy);
            }
        } else if (type == 5) {
            // 전방향
            boolean[][] copy = deepCopy(v);
            for (int i = 0; i < 4; i++) {
                extend(copy, x, y, i);
            }
            dfs(dep + 1, copy);
        }
    }
    
    // 주어진 방향(dir)으로 벽(6)이 나오거나 범위를 벗어날 때까지 감시 영역을 확장
    private static void extend(boolean[][] v, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 6) {
            // CCTV나 빈 공간(0) 모두 감시 가능하므로 true 처리
            v[nx][ny] = true;
            nx += dx[dir];
            ny += dy[dir];
        }
    }
    
    // 2차원 boolean 배열을 깊은 복사하는 메서드
    private static boolean[][] deepCopy(boolean[][] original) {
        boolean[][] copy = new boolean[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}