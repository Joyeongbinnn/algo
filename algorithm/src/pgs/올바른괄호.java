package pgs;

import java.util.*;

class 올바른괄호 {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        
        for(char c : s.toCharArray()){
            if(c == '('){
                st.push(c);
            } else {
                if(!st.isEmpty() && st.peek() == '('){
                    st.pop();
                }
                else st.push(c);
            }
            
        }
        
        
        return st.isEmpty();
    }
}