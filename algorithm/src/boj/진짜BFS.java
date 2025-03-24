package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 진짜BFS {
	
	static class Node{
		int p;
		int hil;
		int me;
		
		public Node(int p, int hil, int me) {
			this.p = p;
			this.hil = hil;
			this.me = me;
		}
	}

	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			//Node node
			
			st = new StringTokenizer(br.readLine());

			while(true) {
				int x = Integer.parseInt(st.nextToken());
				int index = 1;
				
				
			}
			
			
			
		}
		

	}

}
