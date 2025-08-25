import java.io.*;

public class Main {

  static int[] parents;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] l1 = br.readLine().split(" ");
    parents =new int[Integer.parseInt(l1[0])+1];
    for (int i=0; i< parents.length; i++) {
      parents[i]=i;
    }
    for (int i=0; i<Integer.parseInt(l1[1]); i++) {
      String[] l = br.readLine().split(" ");
      if (l[0].equals("0")) {  // 합집합연산
        union(Integer.parseInt(l[1]), Integer.parseInt(l[2]));
      } else {    // 출력 연산
        int n1Root = find(Integer.parseInt(l[1]));
        int n2Root = find(Integer.parseInt(l[2]));
        System.out.println((n1Root==n2Root)?"YES":"NO");
      }
    }
  }

  // n1, n2의 루트노드끼리 연결한다. (id가 작은 쪽을 부모로)
  static void union(int n1, int n2) {
    int n1Root=find(n1);
    int n2Root=find(n2);

    int resultRoot = Math.min(n1Root, n2Root);
    parents[n1Root]=resultRoot;
    parents[n2Root]=resultRoot;
  }

  // n의 루트노드를 찾는다.
  // 찾은김에 부모노드를 루트노드로 업데이트
  static int find(int n) {
    if (parents[n]==n) return n;
    int root = find(parents[n]);
    parents[n]=root;
    return root;
  }
}
