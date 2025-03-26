package pgs;

import java.util.*;

class 게임맵최단거리 {
    static class Man{
        int x;
        int y;
        int len;
        Man(int x, int y, int len){
            this.x = x;
            this.y = y;
            this.len = len;
        }
        
    }
    public int solution(int[][] maps) {
        int ans = -1;
        int N = maps.length;
        int M = maps[0].length;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] v = new boolean[N][M];
        
        Queue<Man> q = new LinkedList<>();
        q.add(new Man(0, 0, 1));
        v[0][0] = true;
        while(!q.isEmpty()){
            Man cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int len = cur.len;
            if(x == N-1 && y == M-1){
                ans = len;
            }
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || v[nx][ny])continue;
                if(maps[nx][ny] == 0)continue;
                v[nx][ny] = true;
                q.add(new Man(nx, ny, len+1));
            }
            
        }
 
        return ans;
    }
}