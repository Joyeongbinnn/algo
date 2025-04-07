package swea;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 알고스탁 {
	
	static int Ms, Ma, L, N, depo;
	static int[] haveStock;
	static int[][] chart;
	static class Stock implements Comparable<Stock>{
		double ratio;
		int num;
		public Stock(double ratio, int num) {
			this.ratio = ratio;
			this.num = num;
		}
		@Override
		public int compareTo(Stock o) {
			// TODO Auto-generated method stub
			return Double.compare(o.ratio, this.ratio);
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			Ms = sc.nextInt();
			Ma = sc.nextInt();
			N = sc.nextInt();
			L = sc.nextInt();
			depo = Ms - Ma;
			haveStock = new int[N];
			chart = new int[N][L+1];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j <= L; j++) {
					chart[i][j] = sc.nextInt();
					
				}
			}
			
			for(int m = 0; m <= L; m++) {
				depo += Ma;
				sell(m);
				if(m < L) {
					buy(m);	
				}
			}
			int profit = depo - (Ms + Ma * L);
			System.out.printf("#%d %d\n", t, profit);
			
			
		}
		
		
	}

	private static void sell(int m) {
		for(int i = 0; i < N; i++) {
			depo += haveStock[i] * chart[i][m];
		}
		haveStock = new int[N];
		
	}

	private static void buy(int m) {
		PriorityQueue<Stock> pq = new PriorityQueue<>(); 
		
		for(int i = 0; i < N; i++) {
			if(chart[i][m+1] - chart[i][m] <= 0) continue;
			double rate = (chart[i][m + 1] - chart[i][m]) / (double) chart[i][m];
			pq.offer(new Stock(rate, i));
			
		}
		
		while(!pq.isEmpty()) {
			Stock st = pq.poll();
			int num = st.num;
			
			if(depo < chart[num][m])continue;
			int cnt = depo/chart[num][m];
			depo = depo - cnt*chart[num][m];
			haveStock[num] = cnt;
			
		}

	}

}
