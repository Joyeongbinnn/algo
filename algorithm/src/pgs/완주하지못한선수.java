package pgs;

import java.util.*;

class 완주하지못한선수 {
    String answer = "";
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for(String name : completion){
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        for(String name : participant){
            if(!map.containsKey(name) || map.get(name) == 0){
                return name;
            }
            map.put(name, map.get(name)-1);
        }
        
        return answer;
    }
}