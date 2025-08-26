import java.io.*;
import java.util.*;

public class Main {

  static int[][] allCityMap;
  static int[] parents;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int allCityCnt = Integer.parseInt(br.readLine());
    int travelCityCnt = Integer.parseInt(br.readLine());
    allCityMap = new int[allCityCnt][allCityCnt];
    for (int i=0; i<allCityCnt; i++) {
      String[] l = br.readLine().split(" ");
      for (int j=0; j<l.length; j++) {
        if (l[j].equals("1")) allCityMap[i][j]=1;
      }
    }

    String[] lastL = br.readLine().split(" ");
    Set<Integer> travelCities = new HashSet<>();
    for (int i=0; i<lastL.length; i++) {
      travelCities.add(Integer.parseInt(lastL[i])-1);
    }
    // union find
    parents=new int[allCityCnt];
    for (int i=0; i<allCityCnt; i++){
      parents[i]=i;
    }

    for (int i=0; i<allCityCnt; i++) {
      for (int j=i+1; j<allCityCnt; j++) {
        if (allCityMap[i][j]==1) {
          union(i, j);
        }
      }
    }
    Set<Integer> set = new HashSet<>();
    for (int city : travelCities) {
      set.add(find(city));
    }
    System.out.println((set.size() == 1) ? "YES" : "NO");
  }

  static void union(int n1, int n2) {
    // n1의 root node와 n2의 root node를 연결한다.
    int n1Root = find(n1);
    int n2Root = find(n2);
    int minRoot = Math.min(n1Root, n2Root);
    parents[n1Root] = minRoot;
    parents[n2Root] = minRoot;
  }
  static int find(int n) {
    if (parents[n]==n) return n;
    int rootNode = find(parents[n]);
    parents[n]=rootNode;
    return rootNode;
  }
}
