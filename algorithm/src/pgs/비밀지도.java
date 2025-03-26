package pgs;

class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {
            int raw1 = arr1[i];
            int raw2 = arr2[i];
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                if (((raw1 & (1 << j)) != 0) || ((raw2 & (1 << j)) != 0)) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        return answer;
    }
}
