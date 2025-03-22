package boj;

import java.util.*;

public class 배열돌리기4 {
    static int N, M, K;
    static int[][] original;
    static int[][] map;
    static List<Rotation> rotations = new ArrayList<>();
    static boolean[] selected;
    static int min = Integer.MAX_VALUE;

    static class Rotation {
        int r, c, s;

        Rotation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        original = new int[N + 1][M + 1];
        map = new int[N + 1][M + 1];
        selected = new boolean[K];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                original[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            int s = sc.nextInt();
            rotations.add(new Rotation(r, c, s));
        }

        permute(new ArrayList<>(), 0);

        System.out.println(min);
        sc.close();
    }

    // 순열 생성
    static void permute(List<Rotation> order, int depth) {
        if (depth == K) {
            copyOriginalToMap();
            for (Rotation rot : order) {
                turn(rot.r, rot.c, rot.s);
            }
            updateMin();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!selected[i]) {
                selected[i] = true;
                order.add(rotations.get(i));
                permute(order, depth + 1);
                order.remove(order.size() - 1);
                selected[i] = false;
            }
        }
    }

    // 원본을 map에 복사
    static void copyOriginalToMap() {
        for (int i = 1; i <= N; i++) {
            System.arraycopy(original[i], 0, map[i], 0, M + 1);
        }
    }

    // 회전 수행
    static void turn(int r, int c, int s) {
        for (int layer = 1; layer <= s; layer++) {
            List<Integer> list = new ArrayList<>();

            int top = r - layer, bottom = r + layer;
            int left = c - layer, right = c + layer;

            // 상
            for (int j = left; j < right; j++) list.add(map[top][j]);
            // 우
            for (int i = top; i < bottom; i++) list.add(map[i][right]);
            // 하
            for (int j = right; j > left; j--) list.add(map[bottom][j]);
            // 좌
            for (int i = bottom; i > top; i--) list.add(map[i][left]);

            // 회전 (맨 뒤 원소 앞으로)
            list.add(0, list.remove(list.size() - 1));

            int idx = 0;
            // 상
            for (int j = left; j < right; j++) map[top][j] = list.get(idx++);
            // 우
            for (int i = top; i < bottom; i++) map[i][right] = list.get(idx++);
            // 하
            for (int j = right; j > left; j--) map[bottom][j] = list.get(idx++);
            // 좌
            for (int i = bottom; i > top; i--) map[i][left] = list.get(idx++);
        }
    }

    // 현재 map의 행 합 최소값 갱신
    static void updateMin() {
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += map[i][j];
            }
            min = Math.min(min, sum);
        }
    }
}
