import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcaseCnt = Integer.parseInt(br.readLine());

		for (;testcaseCnt>0;testcaseCnt--) {
			String[] l2 = br.readLine().split(" ");
			int n = Integer.parseInt(l2[0]);
			int k = Integer.parseInt(l2[1]);
	
			int[][] arr = new int[n][n];
	
			for (int i=0; i<n; i++) {
				String[] l = br.readLine().split(" ");
				for (int j=0; j<l.length; j++) {
					arr[i][j] = Integer.parseInt(l[j]);
					// System.out.print(arr[i][j] + " ");
				}
				// System.out.println();
			}
			int lastIdx = n-k;
			int minCnt = Integer.MAX_VALUE;
			for (int i=0; i<=lastIdx; i++) {
				for (int j=0; j<=lastIdx; j++) {
					// arr[i][j] ~ arr[i+lastIdx][j+lastIdx]
					int cnt = 0;
					// System.out.printf("arr[%d][%d]에서 시작\n", i, j);
					for (int row=i; row<i+k; row++) {
						for (int col=j; col<j+k; col++) {
					    // System.out.print(arr[row][col] + " ");
							if (arr[row][col] == 1) cnt++;
						}
						// System.out.println();
					}
					minCnt = Math.min(minCnt, cnt);
				}
			}
			System.out.println(minCnt);
		}
		return;
	}
}
