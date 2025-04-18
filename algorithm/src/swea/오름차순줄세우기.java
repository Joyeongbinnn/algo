package swea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 오름차순줄세우기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			
			int[] arr = new int[N];
			
			List<Integer> lis = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			for(int num : arr) {
				int idx = Collections.binarySearch(lis, num);
				if(idx < 0) idx = -(idx + 1);
				if(idx == lis.size()) lis.add(num);
				else lis.set(idx, num);
			}
			
			int len = N - lis.size();
			System.out.printf("#%d %d\n", t, len);
			
		}
		
	}

}
