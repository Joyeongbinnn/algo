package pgs;

import java.util.*;

class 의상 {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] clothe : clothes){
            String key = clothe[1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for(String key : map.keySet()){
            answer *= (map.get(key)+1);
        }
        
        
        return answer-1;
    }
}