import java.io.*;
import java.util.*;

class Node {
  int id;
  Map<Integer, Integer> adjs = new HashMap<>();

  public Node(int id) {
    this.id=id;
  }
  public void clearAdjs() {
    adjs = new HashMap<>();
  }
  public void addAdj(int nodeId, int distance) {
    adjs.put(nodeId, distance);
  }
}

public class Main {

  static Node[] nodes;
  static int[][] edges;
  static int X;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] l1 = br.readLine().split(" ");

    int n = Integer.parseInt(l1[0]);
    nodes=new Node[n];
    for (int i=0; i<n; i++) {
      nodes[i] = new Node(i);
    }
    int edgeCnt = Integer.parseInt(l1[1]);
    edges = new int[edgeCnt][3];
    X = Integer.parseInt(l1[2])-1;

    int[][] edges = new int[edgeCnt][3];
    for (int i=0; i<edgeCnt; i++) {
      String[] l = br.readLine().split(" ");
      int start = Integer.parseInt(l[0])-1;
      int end = Integer.parseInt(l[1])-1;
      int distance = Integer.parseInt(l[2]);
      edges[i][0]=start;
      edges[i][1]=end;
      edges[i][2]=distance;
    }

    // 그래프 정방향 초기화
    for (int i=0; i<edges.length; i++) {
      int start = edges[i][0];
      int end = edges[i][1];
      int distance = edges[i][2];
      nodes[start].addAdj(end, distance);
    }
    // X -> Ni 최단거리
    int[] dFromX = calc();

    // 그래프 역방향 초기화
    for (int i=0; i<n; i++) {
      nodes[i].clearAdjs();
    }
    for (int i=0; i<edges.length; i++) {
      int start = edges[i][1];
      int end = edges[i][0];
      int distance = edges[i][2];
      nodes[start].addAdj(end, distance);
    }

    // Ni -> X 최단거리
    int[] dToX = calc();

    // 왕복거리 계산
    int maxD = Integer.MIN_VALUE;
    int maxId = -1;
    for (int i=0; i<n; i++) {
      int distance = dFromX[i] + dToX[i];
      if (maxD < distance) {
        maxId=i+1;
        maxD=distance;
      }
    }
    System.out.println(maxD);
  }

  // 다익스트라
  static int[] calc() {
    int[] distances = new int[nodes.length];
    for (int i=0; i<nodes.length; i++) {
      distances[i]=Integer.MAX_VALUE;
    }
    // 초기화 : 시작점 거리 0으로 확정
    distances[X]=0;
    boolean[] finished = new boolean[nodes.length];
    finished[X]=true;
    int lastFinishedId=X;
    int finishedCnt=1;

    while (finishedCnt < nodes.length) {
      // 직전에 확정된 점을 거쳐가는 최단거리로 업데이트
      for (Map.Entry<Integer, Integer> edge : nodes[lastFinishedId].adjs.entrySet()) {
        int nextNodeId = edge.getKey();
        int distance = edge.getValue();
        distances[nextNodeId] = Math.min(distances[nextNodeId], distances[lastFinishedId]+distance);
      }
      // 다음 마을 최단거리 확정
      int minDistanceId=-1;
      int minDistance=Integer.MAX_VALUE;
      for (int i=0; i<nodes.length; i++) {
        if (!finished[i] && distances[i] <= minDistance) {
          minDistanceId=i;
          minDistance=distances[i];
        }
      }
      finished[minDistanceId]=true;
      lastFinishedId=minDistanceId;
      finishedCnt++;
    }
    return distances;
  }


}

/*
플로이드워셜로 풀면
1,000,000,000
10억회....
시간초과 남
*/
