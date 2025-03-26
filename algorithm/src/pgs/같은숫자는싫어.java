package pgs;

import java.util.*;

class 같은숫자는싫어 {
    public int[] solution(int []arr) {
        int[] answer;
        
        
        Stack<Integer> st = new Stack<>();
        
        for(int num : arr){
            if(!st.isEmpty() && st.peek() == num){
                continue;
            }else{
                st.push(num);
            }
        }
        answer = new int[st.size()];
        for(int i = st.size()-1; i >= 0; i--){
            answer[i] = st.pop();
        }
        
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

        return answer;
    }
}