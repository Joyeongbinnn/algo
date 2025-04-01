package swea;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 점심식사시간 {

	static int[][] map;

	static class People implements Comparable<People> {
		int x;
		int y;
		int WS = 0;
		int work = 0;
		int intime = 0;
		int endtime = 0;

		People(int x, int y) {
			this.x = x;
			this.y = y;
		}

		People(People p) {
			this.x = p.x;
			this.y = p.y;
			this.WS = p.WS;
		}

		@Override
		public int compareTo(People o) {
			return this.work - o.work;
		}
	}

	static class Stair {
		int x;
		int y;
		int len;

		Stair(int x, int y, int len) {
			this.x = x;
			this.y = y;
			this.len = len;
		}
	}

	static Stair[] S;
	static List<People> list;
	static int num, ans;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		list = new ArrayList<>();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			map = new int[N][N];
			S = new Stair[2];
			ans = Integer.MAX_VALUE;

			list.clear();
			int idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						list.add(new People(i, j));
					} else if (map[i][j] > 1) {
						S[idx++] = new Stair(i, j, map[i][j]);
					}
				}
			}
			num = list.size();

			perm(0);
			System.out.printf("#%d %d\n", t, ans);
		}
	}

	private static void perm(int depth) {
		if (depth == num) {
			ans = Math.min(ans, move());
			return;
		}
		list.get(depth).WS = 0;
		perm(depth + 1);
		list.get(depth).WS = 1;
		perm(depth + 1);
	}

	private static int move() {
		// 리스트 복사
		List<People> copyList = new ArrayList<>();
		for (People p : list) {
			copyList.add(new People(p));
		}

		PriorityQueue<People> wq1 = new PriorityQueue<>();
		PriorityQueue<People> wq2 = new PriorityQueue<>();
		Queue<People> sq1 = new ArrayDeque<>();
		Queue<People> sq2 = new ArrayDeque<>();

		for (int i = 0; i < num; i++) {
			People person = copyList.get(i);
			int st = person.WS;
			person.work = dist(person, S[st]);
			if (st == 0) {
				wq1.add(person);
			} else {
				wq2.add(person);
			}
		}

		int time = 0;
	    while (!wq1.isEmpty() || !sq1.isEmpty() || !wq2.isEmpty() || !sq2.isEmpty()) {
	        time++;

	        // 계단에서 내려간 사람 처리
	        while (!sq1.isEmpty() && sq1.peek().intime + S[0].len <= time) {
	            People p = sq1.poll();
	            p.endtime = p.intime + S[0].len;
	        }
	        while (!sq2.isEmpty() && sq2.peek().intime + S[1].len <= time) {
	            People p = sq2.poll();
	            p.endtime = p.intime + S[1].len;
	        }

	        // 계단에 입장 가능한 사람 처리
	        while (!wq1.isEmpty() && wq1.peek().work + 1 <= time && sq1.size() < 3) {
	            People people = wq1.poll();
	            people.intime = time;
	            sq1.add(people);
	        }

	        while (!wq2.isEmpty() && wq2.peek().work + 1 <= time && sq2.size() < 3) {
	            People people = wq2.poll();
	            people.intime = time;
	            sq2.add(people);
	        }
	    }

		int max = 0;
		for (People p : copyList) {
			max = Math.max(max, p.endtime);
		}
		return max;
	}

	static int dist(People people, Stair stair) {
		return Math.abs(people.x - stair.x) + Math.abs(people.y - stair.y);
	}
}
