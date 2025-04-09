package pgs;

import java.util.Arrays;
import java.util.Scanner;

public class LIS2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		int[] C = new int[N];	//각 길이를 LIS로 만족하는 가장 끝에 오는 최소값
		
		for(int i = 0; i < N; i++) { //입력 받으면서 할 수 있음
			arr[i] = sc.nextInt();
		}
		
		int size = 0;
		for(int i = 0; i < N; i++) {
			//i원소가 최소값으로 끝에 설 수 있는 위치 찾기
			int pos = Arrays.binarySearch(C, 0, size, arr[i]);	//못찾으면 -insertion point -1
			if(pos >= 0)return; //찾는 값이 있다는 얘기는 값의 중복이 있다는 의미 : 문제에 따라 다르게 처리
			
			int temp = Math.abs(pos)-1;
			C[temp] = arr[i];
			if(temp == size) ++size;
		}
		
		
		System.out.println(size);
	}
	
}
