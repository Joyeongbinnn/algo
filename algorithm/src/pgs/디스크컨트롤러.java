package pgs;

import java.util.*;

class 디스크컨트롤러 {
    
    static class Job implements Comparable<Job>{
        int num;
        int arrT;
        int workT;
        
        Job(int num, int arrT, int workT){
            this.num = num;
            this.arrT = arrT;
            this.workT = workT;
        }
        
       public int compareTo(Job other) {
            if (this.workT != other.workT) {
                return this.workT - other.workT;
            } else if (this.arrT != other.arrT) {
                return this.arrT - other.arrT;
            } else {
                return this.num - other.num;
            }
        }
    }
    
    public int solution(int[][] jobs) {
        int answer = 0;
        int sum = 0;
        int len = jobs.length;
        int time = 0;
        int count = 0;
        int idx = 0;
        PriorityQueue<Job> waitq = new PriorityQueue<>();
        
        Arrays.sort(jobs, (a, b) -> (a[0] - b[0]));
       
        while(count < len){
            
            while(idx < len && jobs[idx][0] <= time){
                waitq.add(new Job(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if(!waitq.isEmpty()){
                Job cur = waitq.poll();
                count++;
                time = Math.max(time, cur.arrT);
                time += cur.workT;
                sum+=(time - cur.arrT);

            } else {
                time = jobs[idx][0];
            }
            
        }
        answer = sum/len;
        return answer;
    }
}