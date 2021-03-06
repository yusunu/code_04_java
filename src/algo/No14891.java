package algo;

import java.util.Scanner;

/**
*
* https://www.acmicpc.net/problem/14891<br/>
* Gear<br/>
*
* @author c0040
*
*/
public class No14891 {
	static int shift_1bit(int b, boolean rt) {
		// t:right, f:left
		return rt ? (b >> 1) | (b << 7) & 0xFF : (b >> 7) | (b << 1) & 0xFF;
	}

	static int bit6th(int b) {
		return b >> 1 & 1;
	}

	static int bit2nd(int b) {
		return b >> 5 & 1;
	}

	static boolean isEven(int n) {
		return n % 2 == 0;
	}

	public static void main(String[] x) {
		try (Scanner s = new Scanner(System.in)) {
			int tbl_len = 4, m, index0 = 0, index1 = 0, k = 0, st, tbl1[], tbl0[] = new int[tbl_len];
			for (; index0 < tbl_len; index0++)
				tbl0[index0] = s.nextInt(2);

			int rotation[][] = new int[s.nextInt()][2];
			for (; index1 < rotation.length; index1++) {
				rotation[index1][0] = s.nextInt();
				rotation[index1][1] = s.nextInt();
			}
			boolean direction, even, next;

			for (; k < rotation.length; k++) {
				st = rotation[k][0] - 1;
				direction = rotation[k][1] > 0;
				even = isEven(st);
				for (m = Math.abs(rotation[k][1]); m-- > 0;) {
					tbl1 = new int[tbl_len];
					tbl1[st] = shift_1bit(tbl0[st], direction);
					for (next = true, index0 = st; index0 > 0; index0--) {
						index1 = index0 - 1;
						if (next)
							next = (!isEven(index0) ? false : bit2nd(tbl0[index0]) != bit6th(tbl0[index1]))
									|| bit6th(tbl0[index0]) != bit2nd(tbl0[index1]);
						tbl1[index1] = next
								? shift_1bit(tbl0[index1], (isEven(index1) == even ? direction : !direction))
								: tbl0[index1];
					}
					for (next = true, index0 = st; index0 < tbl_len - 1; index0++) {
						index1 = index0 + 1;
						if (next)
							next = (isEven(index0) ? false : bit6th(tbl0[index0]) != bit2nd(tbl0[index1]))
									|| bit2nd(tbl0[index0]) != bit6th(tbl0[index1]);
						tbl1[index1] = next
								? shift_1bit(tbl0[index1], (isEven(index1) == even ? direction : !direction))
								: tbl0[index1];
					}

					tbl0 = tbl1;
				}
			}
			k = 0;
			for (index0 = tbl_len; --index0 >= 0;)
				k += (tbl0[index0] >> 7 & 1) * (1 << index0);
			System.out.println(k);
		}
	}
}