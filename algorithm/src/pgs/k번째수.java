package pgs;

import java.util.*;
class k번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer;
        int len = commands.length;
        answer = new int[len];
        
        int idx = 0;
        for(int[] arr : commands){
            int i = arr[0];
            int j = arr[1];
            int k = arr[2];
            
            
            
            int[] subarr =  Arrays.copyOfRange(array, i-1, j);
            Arrays.sort(subarr);
            answer[idx] = subarr[k-1];
            idx++;
        }
        
        
        return answer;
    }
}