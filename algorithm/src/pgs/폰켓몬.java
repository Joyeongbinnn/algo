package pgs;

import java.util.*;


class 폰켓몬 {
    public int solution(int[] nums) {
        int answer = 0;
        
        Set<Integer> set = new HashSet<>();
        
        
        int len = nums.length/2;
        
        for(int i : nums){
            set.add(i);
        }
        if(set.size() > len){
            return len;
        }else{
            return set.size();
        }

    }
}