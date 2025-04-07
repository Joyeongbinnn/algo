package swea;

import java.util.*;

public class 프로세서연결하기 {
    static int N;
    static int[][] map;
    static List<Core> cores = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int maxCore = 0;     // 최대 연결 코어 수
    static int minLen = Integer.MAX_VALUE; // 최소 전선 길이

    static class Core {
        int x, y;
        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            map = new int[N][N];
            cores.clear();
            maxCore = 0;
            minLen = Integer.MAX_VALUE;

            // 입력 및 코어 리스트 작성
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue; // 가장자리면 제외
                        cores.add(new Core(i, j));
                    }
                }
            }

            dfs(0, 0, 0); // 시작: index, 연결된 코어 수, 총 전선 길이
            System.out.printf("#%d %d\n", t, minLen);
        }
    }

    static void dfs(int index, int coreCnt, int len) {
        if (index == cores.size()) {
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                minLen = len;
            } else if (coreCnt == maxCore) {
                minLen = Math.min(minLen, len);
            }
            return;
        }

        Core core = cores.get(index);

        // 4방향 시도
        for (int d = 0; d < 4; d++) {
            int cnt = canConnect(core.x, core.y, d);
            if (cnt > 0) {
                setWire(core.x, core.y, d, 2); // 전선 놓기 (2로 마킹)
                dfs(index + 1, coreCnt + 1, len + cnt);
                setWire(core.x, core.y, d, 0); // 백트래킹
            }
        }

        // 연결하지 않고 넘기기
        dfs(index + 1, coreCnt, len);
    }

    // 연결 가능한지 확인
    static int canConnect(int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        int cnt = 0;

        while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
            if (map[nx][ny] != 0) return 0;
            nx += dx[dir];
            ny += dy[dir];
            cnt++;
        }
        return cnt;
    }

    // 전선 놓기 / 지우기
    static void setWire(int x, int y, int dir, int val) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (nx >= 0 && ny >= 0 && nx < N && ny < N) {
            map[nx][ny] = val;
            nx += dx[dir];
            ny += dy[dir];
        }
    }
}
