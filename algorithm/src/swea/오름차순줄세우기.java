package swea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			
			Map<Integer, Integer> dp = new HashMap<>();
            int maxLen = 0;

            for (int num : arr) {
                int len = dp.getOrDefault(num - 1, 0) + 1;
                dp.put(num, len);
                maxLen = Math.max(maxLen, len);
            }
			
			int len = N - maxLen;
			System.out.printf("#%d %d\n", t, len);
			
		}
		
	}

}
