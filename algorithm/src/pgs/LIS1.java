package pgs;

import java.util.Scanner;

public class LIS1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] LIS = new int[N];
		
		for(int i = 0; i < N; i++) { //입력 받으면서 할 수 있음
			arr[i] = sc.nextInt();
		}
		int max = 0;
		for(int i = 0; i < N; i++) {
			LIS[i] = 1; 
			for(int j = 0; j < i; j++) { //i보다 앞에있는 보든 대상에 대해 탐색
				if(arr[j] < arr[i] && LIS[i] < LIS[j]+1) { //나보다 앞에 있는 j의 값이 나보다 작고
					LIS[i] = LIS[j]+1;						//뒤에 i를 세우는것이 더 최장을 만족한다면
				}
			}
			max = Math.max(max, LIS[i]);
		}
		System.out.println(max);
	}
	
}
