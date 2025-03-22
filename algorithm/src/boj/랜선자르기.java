package boj;

import java.util.Scanner;

public class 랜선자르기 {
    
    static int N, K;
    static long[] arr;  // ⚠ long 타입 사용 (오버플로우 방지)
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();
        N = sc.nextInt();
        arr = new long[K];
        
        long hi = 0;
        for (int i = 0; i < K; i++) {
            arr[i] = sc.nextLong();  // ⚠ 입력값이 2^31-1까지 가능하므로 long 사용
            hi = Math.max(hi, arr[i]);  // 가장 긴 랜선의 길이를 upper bound로 설정
        }
        
        long lo = 1;
        System.out.println(binarySearch(lo, hi));
    }

    private static long binarySearch(long lo, long hi) {
        long ans = 0;
        
        while (lo <= hi) {  // 이진 탐색 수행
            long mid = (lo + hi) / 2;
            
            long lan = 0;  // ⚠ long 타입 사용 (int 오버플로우 방지)
            for (int i = 0; i < K; i++) {
                lan += arr[i] / mid;
            }
            
            if (lan >= N) {  // mid 길이로 충분한 랜선을 만들 수 있다면
                ans = mid;  // 정답 후보 저장
                lo = mid + 1;  // 더 긴 길이를 시도
            } else {
                hi = mid - 1;  // 더 짧은 길이를 시도
            }
        }
        
        return ans;
    }
}
