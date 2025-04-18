import java.io.*;
import java.util.*;
class N implements Comparable<N> {
	int id;
	boolean visited = false;
	List<N> adjs = new LinkedList<>();
	public N(int id){this.id=id;}
	public void addAdj(N n) {
		int i=0;
		for (; i<adjs.size(); i++) {
			if (adjs.get(i).id > n.id) break;
		}
		adjs.add(i, n);
	}
	public void visit() {
		visited=true;
	}
	public N getNext() {
		N nextN = null;
		while (adjs.size() > 0) {
			N adj = adjs.get(0);
			
			if (adj.visited){
				adjs.remove(0);
			}
			else {
				nextN = adj;
				break;
			}
		}
		return nextN;
	}
	
	@Override
	public int compareTo(N n) {
		return Integer.compare(this.id, n.id);
	}
	@Override
	public String toString() {
		return "(id " + id + " visited " + visited + ")";
	}
}
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] split = br.readLine().split(" ");
		int n = Integer.parseInt(split[0]);//노드갯수
		int m = Integer.parseInt(split[1]);//간선갯수
		int k = Integer.parseInt(split[2]);//시작노드번호

		N[] nodes = new N[n+1];
		for (int i=1; i<=n; i++) {
			nodes[i] = new N(i);
		}
		for (int i=0; i<m; i++) {
			String[] l = br.readLine().split(" ");
			int n1 = Integer.parseInt(l[0]);
			int n2 = Integer.parseInt(l[1]);
			nodes[n1].addAdj(nodes[n2]);
			nodes[n2].addAdj(nodes[n1]);
		}
		N nextN = nodes[k];

		int cnt = 0;
		int lastNodeId = k;
		
		while (nextN != null) {
			nextN.visit();
			cnt++;
			lastNodeId = nextN.id;
			nextN = nextN.getNext();
			// System.out.println(nextN);
		}
		System.out.println(cnt + " " + lastNodeId);
	}
}
