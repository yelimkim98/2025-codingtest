import java.io.*;
import java.util.*;

class Main {

	static Node[] nodes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] l1 = br.readLine().split(" ");
		int n = Integer.parseInt(l1[0]); // 도시의 수
		int m = Integer.parseInt(l1[1]); // 도로의 수
		int s = Integer.parseInt(l1[2]); // 출발도시
		int e = Integer.parseInt(l1[3]); // 도착도시

		nodes = new Node[n+1];

		for (int i=1; i<=n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i=0; i<m; i++) {
			String[] l = br.readLine().split(" ");
			int n1 = Integer.parseInt(l[0]);
			int n2 = Integer.parseInt(l[1]);

			nodes[n1].addAdj(nodes[n2]);
			nodes[n2].addAdj(nodes[n1]);
		}
		for (int i=1; i<=n; i++) {
			int res = f(s, e, i);
			System.out.println(res);
		}
		
		return;
	}
	
	// g : 공사중인 도시 번호
	static int f(int start, int end, int g) {
		Queue<Node> q = new ArrayDeque<>();
		if (start == g) return -1;
		q.offer(nodes[start]);
		nodes[start].order = 1;
		boolean[] visited = new boolean[nodes.length+1];
		visited[start] = true;
		visited[g] = true;

		while (!q.isEmpty()) {
			Node current = q.poll();
			int nextOrder = current.order+1;

			for (Node adj : current.adjs) {
				if (!visited[adj.id]) {
					if (adj.id == end) {
						return nextOrder;
					}
					adj.order = nextOrder;
					q.offer(adj);
					visited[adj.id] = true;
				}
			}
		}
		return -1;
	}
}
class Node {
	int id;
	int order = 0;
	Set<Node> adjs = new HashSet<>();
	
	public Node(int id) {
		this.id = id;
	}
	public void addAdj(Node n) {
		this.adjs.add(n);
	}
}
