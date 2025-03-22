package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 통나무옮기기 {
    static int[] dx = { -1, 0, 1, 0, -1, 1, 1, -1 };
    static int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };
    static int N;
    static int[][] map;
    static Queue<Tong> q = new LinkedList<>();
    static boolean[][][] v;

    static int endX, endY, endForm; // 도착지 중심 좌표와 방향

    static class Tong {
        int x;
        int y;
        int form;
        int move;

        public Tong(int x, int y, int form, int move) {
            this.x = x;
            this.y = y;
            this.form = form;
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        v = new boolean[N][N][2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0'; // 'B' == 66 - 48 = 18, 'E' == 69 - 48 = 21
            }
        }

        findtong(); // 시작 위치 찾기
        findend();  // 도착 위치 찾기

        System.out.println(bfs());
    }

    private static int bfs() {
        while (!q.isEmpty()) {
            Tong cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int form = cur.form;
            int move = cur.move;

            if (isfit(x, y, form)) {
                return move;
            }

            // 회전
            if (isturn(x, y, form)) {
                int newForm = form ^ 1;
                if (!v[x][y][newForm]) {
                    v[x][y][newForm] = true;
                    q.add(new Tong(x, y, newForm, move + 1));
                }
            }

            // 상하좌우
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isgo(nx, ny, form) || v[nx][ny][form]) continue;

                v[nx][ny][form] = true;
                q.add(new Tong(nx, ny, form, move + 1));
            }
        }
        return 0;
    }

    private static boolean isfit(int x, int y, int form) {
        return x == endX && y == endY && form == endForm;
    }

    private static boolean isturn(int x, int y, int form) {
        if (x - 1 < 0 || y - 1 < 0 || x + 1 >= N || y + 1 >= N) return false;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (map[nx][ny] == 1) return false;
        }
        return true;
    }

    private static boolean isgo(int nx, int ny, int form) {
        if (form == 0) { // 가로
            if (nx < 0 || nx >= N || ny - 1 < 0 || ny + 1 >= N) return false;
            for (int i = -1; i <= 1; i++) {
                if (map[nx][ny + i] == 1) return false;
            }
        } else { // 세로
            if (nx - 1 < 0 || nx + 1 >= N || ny < 0 || ny >= N) return false;
            for (int i = -1; i <= 1; i++) {
                if (map[nx + i][ny] == 1) return false;
            }
        }
        return true;
    }

    private static void findtong() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 18) { // 'B'
                    if (i - 1 >= 0 && i + 1 < N && map[i - 1][j] == 18 && map[i + 1][j] == 18) {
                        q.add(new Tong(i, j, 1, 0)); // 세로
                        v[i][j][1] = true;
                        return;
                    }
                    if (j - 1 >= 0 && j + 1 < N && map[i][j - 1] == 18 && map[i][j + 1] == 18) {
                        q.add(new Tong(i, j, 0, 0)); // 가로
                        v[i][j][0] = true;
                        return;
                    }
                }
            }
        }
    }

    private static void findend() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 21) { // 'E'
                    if (i - 1 >= 0 && i + 1 < N && map[i - 1][j] == 21 && map[i + 1][j] == 21) {
                        endX = i;
                        endY = j;
                        endForm = 1; // 세로
                        return;
                    }
                    if (j - 1 >= 0 && j + 1 < N && map[i][j - 1] == 21 && map[i][j + 1] == 21) {
                        endX = i;
                        endY = j;
                        endForm = 0; // 가로
                        return;
                    }
                }
            }
        }
    }
}
