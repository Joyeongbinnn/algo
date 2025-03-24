package boj;

import java.util.Scanner;
import java.util.Stack;

public class 괄호짝짓기 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = 10;
		for(int t = 1; t <= T; t++) {
			Stack<Character> st = new Stack<>();
			
			int N = sc.nextInt();
			int ans = 0;
			String str = sc.next();
			for(int i = 0; i < N; i++) {
				char now = str.charAt(i);
				
				if(now == '}') {
					if(!st.isEmpty() && st.peek() == '{') {
						st.pop();
					}else st.push(now);
					
				} else if(now == ']') {
					if(!st.isEmpty() && st.peek() == '[') {
						st.pop();
					}else st.push(now);
				} else if(now == '>') {
					if(!st.isEmpty() && st.peek() == '<') {
						st.pop();
					}else st.push(now);
				} else if(now == ')') {
					if(!st.isEmpty() && st.peek() == '(') {
						st.pop();
					}else st.push(now);
				} else {
					st.push(now);
				}
				
			}
			if(st.isEmpty()) ans = 1;
			System.out.printf("#%d %d\n", t, ans);
		}
	}
}
