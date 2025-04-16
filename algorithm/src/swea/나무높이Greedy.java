package swea;

import java.util.Scanner;

public class 나무높이Greedy {
	static int Ans = Integer.MAX_VALUE;
	static int N;
	static int[] tree;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			Ans = Integer.MAX_VALUE;
			N = sc.nextInt();
			tree = new int[N];
			int[] diffTree = new int[N];
			int limit = 0;
			for (int i = 0; i < N; i++) {
				tree[i] = sc.nextInt();
				limit = Math.max(limit, tree[i]);
			}

			// 커야하는 나무 키의 합
			int sum = 0;
			for (int i = 0; i < N; i++) {
				diffTree[i] = limit - tree[i];
				sum += diffTree[i];
			}

			Ans = (sum / 3) * 2;
			if (sum % 3 == 1)
				Ans++;
			else if (sum % 3 == 2)
				Ans += 2;

			int evenDay = 0;
			for (int i = 0; i < N; i++) {
				evenDay += diffTree[i] / 2;
			}
			// Ans 재계산
			if (sum > evenDay * 3) {
				Ans = evenDay * 2;
			}

			int remain = sum - evenDay * 3;

			if (remain > 0) {
				Ans += remain * 2 - 1;
			}

			System.out.printf("#%d %d\n", t, Ans);

		}

	}

}
