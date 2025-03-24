package boj;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 알고스탁 {
	static int Ans = Integer.MAX_VALUE;
	static int N;
	static int Ms, Ma, L, maxBenefit;
	static int[][] data;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/알고스탁.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			Ans = Integer.MAX_VALUE;
			Ms = sc.nextInt();
			Ma = sc.nextInt();
			N = sc.nextInt();
			L = sc.nextInt();
			data = new int[N][L+1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < L+1; j++) {
					data[i][j] = sc.nextInt();
				}
			}
			//print(data);
			// 현재금액
			int money = Ms;
			for (int month = 0; month < L; month++) {
				// 구매리스트(이익나는 종목리스트)
				ArrayList<Stock> stockList = new ArrayList(); 
				for (int i = 0; i < N; i++) {
					// 현재달보다 다음달에 주식이 오르면 리스트에 저장
					if(data[i][month]<data[i][month+1]) {
						stockList.add(new Stock(data[i][month],data[i][month+1]
								,data[i][month+1]-data[i][month]));
					}
				}
				// 이번달에 얻을수 있는 최대이익
				maxBenefit = 0;
				// 만약 구매할 종목이 하나라면 최대구매를 한다(몰빵)

				// 만약 구매할 종목이 여러개라면 최대이익 찾기를 한다(조합)
				dfs(0,money,0,stockList);

				// 이번달 이익금 계산
				money += maxBenefit;
				// 월별 추가금 계산
				money += Ma;
				// 계산 끝났으면 다음달로 넘어갑시다
				
			} // end of for(month)
			
			// 모든달의 최대이익금액을 구했다면 
			// 최대이익금액 - 초기금액 - 월별불입금 을 계산한다
			//System.out.println(money);
			Ans = money - Ms - (Ma*L);
			System.out.printf("#%d %d\n",tc,Ans);
		}
	}
					// 구매할종목번호,현재가지고있는금액,이익금,구매종목리스트
	private static void dfs(int idx, int money, int benefit, ArrayList<Stock> stockList) {
		// base part
		// 구매하려는 종목을 모두 살펴봤으면 계산하고 탈출
		if(idx==stockList.size()) {
			// 현재재귀에서 얻이 이익이 최대치인지 비교하여 저장한다
			if(maxBenefit<benefit) {
				maxBenefit = benefit;
			}
			return;
		}
		
		// inductive part
		Stock stock = stockList.get(idx);
		// 1. 종목을 구매한다면
		
		// 한주를 사고 다음 재귀로 넘긴다
		if(money >= stock.curPrice) {
			// 구매할수 있을때 까지 구매한다
			dfs(idx,money-stock.curPrice,benefit+stock.diff,stockList);
		}
		// 2. 종목을 구매하지 않는다면 다음종목으로 넘긴다
		dfs(idx+1,money,benefit,stockList);
	}

	static class Stock{
		// 이번달금액, 다음달금액, 차이(이익)
		int curPrice,diff;

		public Stock(int curPrice, int nextPrice, int diff) {
			this.curPrice = curPrice;
			this.diff = diff;
		}

		@Override
		public String toString() {
			return "Stock [curPrice=" + curPrice + ", diff=" + diff + "]";
		}
		
	}
	
	private static void print(int[][] adj) {
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[i].length; j++) {
				System.out.print(adj[i][j]+" ");
			}
			System.out.println();
		}
	}

}
