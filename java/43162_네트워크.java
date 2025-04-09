import java.util.*;
class Solution {
    
    public int solution(int n, int[][] computers) {
        int[] unionFind = new int[n];
        for (int i=0; i<n; i++) {
            unionFind[i] = i;
        }
        
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (computers[i][j] == 1) {
                    int minRoot;
                    int maxRoot;
                    
                    if (unionFind[i] < unionFind[j]) {
                        minRoot = unionFind[i];
                        maxRoot = unionFind[j];
                    } else {
                        minRoot = unionFind[j];
                        maxRoot = unionFind[i];
                    }
                    unionFind[i] = minRoot;
                    unionFind[j] = minRoot;
                    for (int k=0; k<n; k++) {
                        if (unionFind[k] == maxRoot) {
                            unionFind[k] = minRoot;
                        }
                    }
                }
            }
        }
        
        Set<Integer> before = new HashSet<>();
        for (int i=0; i<n; i++) {
            System.out.print(unionFind[i] + " ");
            before.add(unionFind[i]);
        }
        return before.size();
    }
}
/*
    0 1 2
0 [ 1 1 0 ]
1 [ 1 1 1 ]
2 [ 0 1 1 ]

[1,0,0,1,0,0]
[0,1,1,0,0,0]
[0,1,1,0,1,0]
[1,0,0,1,0,1]
[0,0,1,0,1,1]
[0,0,0,1,1,1]

*/
