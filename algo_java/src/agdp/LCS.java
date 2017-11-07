package agdp;

import java.util.Arrays;
public class LCS {
	public static int getLCS(String strA,String strB){
		int m = strA.length();
		int n = strB.length();
		int[][] aux = new int[m+1][n+1];//aux的第0行和第0列充当哨兵，无实际含义	
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (strB.charAt(j) == strA.charAt(i)) {
					aux[i+1][j+1] = aux[i][j]+1;
				}else {
					aux[i+1][j+1] = Math.max(aux[i+1][j], aux[i][j+1]);
				}
			}
		}
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				System.out.print(aux[i][j]+"-");
			}
			System.out.println();
		}
		return aux[m][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strA = "ABCBDAB";
		String strB = "BDCABA";
//		String strA = "abfgkhrc";
//		String strB = "a1a11bfgh33";
		int result = getLCS(strA, strB);
		System.out.println(result);
	}

}
