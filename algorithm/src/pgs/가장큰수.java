package pgs;

import java.util.*;

class 가장큰수 {
    public String solution(int[] numbers) {
        
        String[] nums = new String[numbers.length];
        int idx = 0;
        for(int num : numbers){
            nums[idx] = String.valueOf(num);
            idx++;
        }
        //compareTo값이 양수면 변경, 음수면 그대로
        // b+a > a+b 라면 양수
        Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        
        for(String s : nums){
            sb.append(s);
        }
        if (nums[0].equals("0")) return "0";


        
        
        
        
        
        
        return sb.toString();
    }
}