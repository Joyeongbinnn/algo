package pgs;

import java.util.*;

class H_index {
    public int solution(int[] citations) {
        int answer = 0;
        int len = citations.length;
        Arrays.sort(citations);
        int idx = 0;
        for(int h : citations){
            if((len-idx) <= h){
                answer = len-idx;
                break;
            }
            idx++;
        }
        return answer;
    }
}