package algo;

/**
 *
 * https://www.acmicpc.net/problem/15685<br/>
 * ドラゴン曲線 「ドラゴンカーブ」<br/>
 *
 * @author c0040
 *
 */
class No15685 {
	public static void main(String[] $) {
		var s = new java.util.Scanner(System.in);
		int[] X = { 1, 0, -1, 0 }, Y = { 0, -1, 0, 1 };
		int[] R = new int[1024];
		boolean T[][] = new boolean[101][101];
		int j, k, m, x, y, n = s.nextInt();
		while (n-- > 0) {
			T[x = s.nextInt()][y = s.nextInt()] = T[x += X[R[0] = s.nextInt()]][y += Y[R[0]]] = true;
			k = s.nextInt();
			for (m = j = 0; ++j < 1 << k;) {
				if ((j & (j - 1)) == 0)
					m = j;

				R[j] = (R[j - m] + (j < m * 3 / 2 ? 2 : m % 2)) % 4;
				T[x += X[R[j]]][y += Y[R[j]]] = true;
			}
		}
		for (int a = 100; --a >= 0;)
			for (int b = 100; --b >= 0;)
				if (T[a][b] && T[a + 1][b] && T[a][b + 1] && T[a + 1][b + 1])
					n++;
		System.out.print(n + 1);
	}
}