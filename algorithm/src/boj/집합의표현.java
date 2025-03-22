package boj;


import java.util.Scanner;

public class 집합의표현 {
	
	static int n, m;
	static int parent[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		parent = new int[n+1];
		
		for(int i = 0; i <= n; i++) { //자기 자신이 부모
			parent[i] = i;
		}
		
		for(int i =0; i < m; i++) {
			
			int op = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(op == 0) union(a, b);
			else if(op == 1) {
				same(a, b);
			}
			
		}
	
	}

	private static void same(int a, int b) {
		if(find(a) == find(b)) {
			System.out.println("yes");
		}
		else {
			System.out.println("NO");
		}
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	private static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if(A != B) parent[B] = A;
	}

}
