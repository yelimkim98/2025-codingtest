import java.io.*;
import java.util.*;

class N implements Comparable<N>{
	int n10;
	int cnt;
	public N(int n, int c) {
		n10=n; cnt=c;
	}
	@Override
	public int compareTo(N n) {
		int compare1 = Integer.compare(n.cnt, this.cnt);
		if (compare1!=0) return compare1;
		return Integer.compare(n.n10, this.n10);
	}
}
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] l1 = br.readLine().split(" ");
		int n = Integer.parseInt(l1[0]);
		int k = Integer.parseInt(l1[1]);
		// System.out.println(f(7));
		List<N> l = new ArrayList<>();
		String[] l2 = br.readLine().split(" ");
		for (int i=0; i<n; i++) {
			int n10 = Integer.parseInt(l2[i]);
			int cnt = f(n10);
			l.add(new N(n10, cnt));
		}
		Collections.sort(l);
		// idx k-1
		System.out.println(l.get(k-1).n10);
	}
	// n10을 2진수로 바꿨을 때 1의 갯수
	static int f(int n10){
		int cnt=0;
		while (n10 >0) {
			int tmp = n10/2;
			int n = n10%2;
			n10 = tmp;
			if (n==1) cnt++;
		}
		return cnt;
	}
}
