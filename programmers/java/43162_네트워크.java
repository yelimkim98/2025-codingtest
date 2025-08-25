import java.util.*;

class Solution {
    int[] arr;
    public int solution(int n, int[][] computers) {
        arr=new int[n];
        for (int i=0; i<n; i++) {
            arr[i]=i;
        }
        
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> roots = new HashSet<>();
        for (int i=0; i<n; i++) {
            roots.add(find(arr[i]));
        }
        return roots.size();
    }
    // union : 두 집합의 루트 노드를 연결 (숫자 큰쪽 -> 작은쪽 방향으로)
    void union(int n1, int n2) {
        int n1Root = find(n1);
        int n2Root = find(n2);
        if (n1Root != n2Root) {
            int root = Math.min(n1Root, n2Root);
            arr[n1Root]=root;
            arr[n2Root]=root;
        }
    }
    
    // find : 어떤 점의 루트 노드를 발견 (만약 최종 루트로 되어있지 않으면 최종 루트로 업데이트까지)
    int find(int n) {
        if (arr[n] == n) return n;
        int rootNode = find(arr[n]);
        arr[n] = rootNode;
        return rootNode;
    }
}
