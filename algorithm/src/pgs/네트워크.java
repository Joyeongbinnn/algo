package pgs;


class 네트워크 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[][] v = new boolean[n][n];
         for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!v[i][j] && computers[i][j] == 1){
                    v[i][j] = true;
                    dfs(computers, v, i, j);
                    answer++;
                }
            }
        }
        return answer;
    }
    static void dfs(int[][] map, boolean[][] v, int x, int y){
        for(int i = 0; i < map.length; i++){
            if(v[y][i])continue;
            if(map[y][i] == 0)continue;
            v[y][i] = true;
            dfs(map, v, y, i);
        }
    }
}