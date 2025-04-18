import java.io.*;
class Point {
	int r=0;  // 가로줄 갯수
	int c=0;  // 세로줄 갯수
	@Override
	public String toString() {
		return String.format("(r=%d, c=%d)", r, c);
	}
}
class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] l1 = br.readLine().split(" ");
		int n = Integer.parseInt(l1[0]);//정사각형 가로/세로 크기
		int m = Integer.parseInt(l1[1]);// 반직선 갯수
		Point[][] points = new Point[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				points[i][j] = new Point();
			}
		}
		
		for (int i=0; i<m; i++) {
			String[] l = br.readLine().split(" ");
			int r = Integer.parseInt(l[0])-1;
			int c = Integer.parseInt(l[1])-1;
			char cc = l[2].charAt(0);
			
			// d[0] = dRow, d[1]=dCol
			int[] d = getD(cc);

			// System.out.printf("(r=%d, c=%d) %c\n", r, c, cc);
			while (0<=r && r<n && 0<=c && c<n) {
				if (cc == 'U' || cc == 'D') {
					points[r][c].c++;
				} else {
					points[r][c].r++;
				}
				r += d[0];
				c += d[1];
			}
		}
		long res = 0;
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				res += (long)points[i][j].r * points[i][j].c;
				// System.out.print(points[i][j] + " ");
			}
			// System.out.println();
		}
		System.out.println(res);
	}

	//return d[0] = dRow, d[1]=dCol
	static int[] getD(char c) {
				int[] r = new int[2];

				if ('U' == c) {
					r[0] = -1;
					r[1] = 0;
				}
				else if ('D' == c) {
					r[0] = 1;
					r[1] = 0;
				}
				else if ('L' == c) {
					r[0] = 0;
					r[1] = -1;
				}
				else if ('R'==c) {
					r[0] = 0;
					r[1] = 1;
				}
				return r;
	}
}
