import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static Node[] nodes;
  static int source;
  static int target;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    nodes = new Node[Integer.parseInt(br.readLine())];
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new Node(i);
    }
    int busCnt = Integer.parseInt(br.readLine());
    for (int i = 0; i < busCnt; i++) {
      String[] split = br.readLine().split(" ");
      int n1 = Integer.parseInt(split[0]) - 1;
      int n2 = Integer.parseInt(split[1]) - 1;
      int weight = Integer.parseInt(split[2]);
      nodes[n1].addEdge(n2, weight);
    }
    String[] lastLine = br.readLine().split(" ");
    source = Integer.parseInt(lastLine[0]) - 1;
    target = Integer.parseInt(lastLine[1]) - 1;

    // 다익스트라
    int[] d = new int[nodes.length];
    for (int i = 0; i < d.length; i++) {
      d[i] = Integer.MAX_VALUE;
    }
    d[source] = 0;
    boolean[] finished = new boolean[d.length];
    finished[source] = true;
    int lastFinished = source;
    int finishedCnt = 1;

    while (finishedCnt < d.length) {
      for (Map.Entry<Integer, Integer> entry : nodes[lastFinished].edges.entrySet()) {
        int adjId = entry.getKey();
        int weight = entry.getValue();
        d[adjId] = Math.min(d[adjId], d[lastFinished] + weight);
      }
      int minD = Integer.MAX_VALUE;
      int minNode = -1;

      for (int i = 0; i < d.length; i++) {
        if (!finished[i] && d[i] < minD) {
          minNode = i;
          minD = d[i];
        }
      }
      if (minD == Integer.MAX_VALUE) {
        break;
      }
      finished[minNode] = true;
      lastFinished = minNode;
      finishedCnt++;
    }
    System.out.println(d[target]);
  }
}

class Node {
  int id;
  Map<Integer, Integer> edges;
  public Node(int id) {
    this.id=id;
    this.edges = new HashMap<>();
  }
  public void addEdge(int nodeId, int weight) {
    if (this.id==nodeId) return;
    if (edges.containsKey(nodeId)) {
      edges.put(nodeId, Math.min(edges.get(nodeId), weight));
    } else {
      edges.put(nodeId, weight);
    }
  }
}
