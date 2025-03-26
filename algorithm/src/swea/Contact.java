package swea;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Contact {
	
	static class Mem{
		int num;
		int dep;
		
		Mem(int num, int dep){
			this.num = num;
			this.dep = dep;
		}
	}
	static boolean[][] map;
	static boolean[] v;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {
			map = new boolean[100 + 1][100 + 1];
			v = new boolean[100 + 1];
			int len = sc.nextInt();
			int start = sc.nextInt();

			for (int i = 0; i < len / 2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				map[from][to] = true;
			}
			System.out.printf("#%d %d\n", t, bfs(start)); 

		}

	}

	private static int bfs(int start) {
		Queue<Mem> q = new LinkedList<>();
		v[start] = true;
		Mem mem = new Mem(start, 1);
		q.add(mem);
		int max = 0;
		
		int call = 0;

		while (!q.isEmpty()) {
			Mem cur = q.poll();
			int num = cur.num;
			int dep = cur.dep;
			
			if(dep > call) {
				call = dep;
				max = 0;
			}
			if(num > max) {
				max = num;
			}

			

			for (int i = 1; i <= 100; i++) {
				if (map[num][i] && !v[i]) {
					q.add(new Mem(i, dep+1));
					v[i] = true;
				}
			}

		}
		return max;

	}

}
