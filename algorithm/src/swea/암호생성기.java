package swea;

import java.util.LinkedList;
import java.util.Scanner;

public class 암호생성기 {

	static LinkedList<Integer> list;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		for(int t = 1; t <= 10; t++) {
			int T = sc.nextInt();
			list = new LinkedList<>();
			for(int i = 0; i < 8; i++) {
				int N = sc.nextInt();
				list.add(N);
			}
			
			int minus = 1;
			while(true) {
				if(minus == 6) minus = 1;

				int start = list.getFirst();
				list.remove(0);
				if(start - minus <= 0) {
					list.add(0);
					break;
				}
				list.add(start - minus);
				minus++;

				
			}
			
			System.out.printf("#%d ", T);
			for(int in : list) {
				System.out.printf("%d ", in);
			}
			System.out.printf("\n");
		}
		sc.close();
	}

}
