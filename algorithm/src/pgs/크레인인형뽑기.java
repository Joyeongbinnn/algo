package pgs;

import java.util.*;

class 크레인인형뽑기 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int depth = board.length;
        Stack<Integer> st = new Stack<>();
        for(int m : moves){
            for(int i = 0; i < depth; i++){
                if(board[i][m-1] != 0){
                    int now = board[i][m-1];
                    board[i][m-1] = 0;
                    if(st.isEmpty()){
                        st.push(now);
                        
                    } else if(st.peek() == now){
                        st.pop();
                        answer += 2;
                    } else {
                        st.push(now);
                    }
                    break;

                }
            }
        }
        
        return answer;
    }
}