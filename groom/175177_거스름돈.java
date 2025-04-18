import java.io.*;
class Main {
	static final int[] coins = {40, 20, 10, 5, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int coin : coins) {
			if (n==0) break;
			if (coin > n) continue;
			
			int tmp = n / coin;
			int nam = n % coin;

			// System.out.printf("%d원 %d개 남은금액 %d\n", coin, tmp, nam);
			
			cnt+=tmp;
			n = nam;
		}
		System.out.println(cnt);
		return;
	}
}
