package boj;

import java.io.*;
import java.util.*;

public class 전기자동차충전소 {
	private static int N, answer;
	private static List<Point> house, pos;
	
	static class Point {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("par03\\sol\\par35\\기출\\전기자동차충전소\\전기 자동차 충전소_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			house = new ArrayList<>();
			pos = new ArrayList<>();
			
			for (int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) + 15;
				int y = Integer.parseInt(st.nextToken()) + 15;
				int d = Integer.parseInt(st.nextToken());
				house.add(new Point(x, y, d));
			}
			for (int i=1; i<=2; i++) {
				setCharger(0, i);
				if (answer != Integer.MAX_VALUE) break;
			}
			
			if (answer == Integer.MAX_VALUE) answer = -1;
			
			System.out.println("#" + tc + " " + answer);
		}
		
	}

	private static void setCharger(int cnt, int end) {
		// ��ǥ�� ���� 2���� �����Ұ� ��� ���̶� ����Ǵ��� Ȯ��
		if (cnt == end) {
			// �����Ұ� ������� ����Ǵ��� Ȯ��
			if (!check()) return;
			answer = Math.min(answer, getDistanceSum());
			return;
		}
		
		// x��ǥ, y��ǥ ����
		int startX = 0;
		int startY = 0;
		if (cnt == 1 && end == 2) {
			startX = pos.get(0).x;
			startY = pos.get(0).y;
		}
		for (int i=startX; i<=30; i++) {
			for (int j=startY; j<=30; j++) {
				Point p = new Point(i, j);
				if (pos.contains(p)) continue;
				pos.add(p);
				setCharger(cnt + 1, end);
				pos.remove(cnt);
			}
		}
	}
	
	private static boolean check() {
		boolean[] connect = new boolean[N];
		for (Point p : pos) {
			for (int i=0; i<N; i++) {
				Point h = house.get(i);
				int dist = getDistance(p.x, p.y, h.x, h.y);
				// �� ��ǥ�� �ƴϾ����
				if (p.x == h.x && p.y == h.y || dist > h.dist) continue;
				connect[i] = true;
			}
		}
		
		for (int i=0; i<N; i++) {
			if (!connect[i]) return false;
		}
		
		return true;
	}
	
	private static int getDistanceSum() {
		int sum = 0;
		int[] distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for (Point p : pos) {
			for (int i=0; i<N; i++) {
				Point h = house.get(i);
				int dist = getDistance(p.x, p.y, h.x, h.y);
				distance[i] = Math.min(dist, distance[i]);
			}
		}
		for (int i=0; i<N; i++) {
			sum += distance[i];
		}
		return sum;
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
