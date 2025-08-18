class Solution {
    public int solution(int n, int start, int a, int b, int[][] fares) {
        int[][] distances = new int[n][n];
        start=start-1;
        a=a-1;
        b=b-1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                distances[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i=0; i<n; i++) {
            distances[i][i] = 0;
        }
        for (int[] fare : fares) {
            int n1=fare[0]-1;
            int n2=fare[1]-1;
            distances[n1][n2]=fare[2];
            distances[n2][n1]=fare[2];
        }
        for (int i=0; i<n; i++) {
            // s->t 갈때 i를 거쳐가는 경우 vs 안거치는 경우
            for (int s=0; s<n; s++) {
                for (int t=s+1; t<n; t++) {
                    int newDistance = add(distances[s][i], distances[i][t]);
                    distances[s][t] = Math.min(distances[s][t], newDistance);
                    distances[t][s] = Math.min(distances[s][t], newDistance);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        
        for (int i=0; i<n; i++) {
            // i까지 택시를 함께 타고감
            int newFare = add(distances[start][i], distances[i][a], distances[i][b]);
            answer = Math.min(answer, newFare);
        }
        return answer;
    }
    
    int add(int a, int b) {
        long l = (long) a + b;
        return (l>=Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)l;
    }
    
    int add(int a, int b, int c) {
        long l = (long) a + b + c;
        return (l>=Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)l;
    }
}
