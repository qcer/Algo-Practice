package agdp;
public class SED {
	public static int getSED(String strA,String strB){
		int m = strA.length(),n = strB.length();
		int[][] aux = new int[m+1][n+1];//aux的第0行和第0列做哨兵，边界计算
		//边界值的初始化
		for (int i = 0; i < m+1; i++) {
			aux[i][0] = i;
		}
		for (int i = 0; i < n+1; i++) {
			aux[0][i] = i;
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (strA.charAt(i-1) == strB.charAt(j-1)) {
					aux[i][j] = aux[i-1][j-1];//无需编辑操作
				}else {
					//对应替换，删除、插入中的一种，需要一次编辑擦周
					aux[i][j] = Math.min(Math.min(aux[i-1][j], aux[i][j-1]), aux[i-1][j-1])+1;
				}
			}
		}
		return aux[m][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strA = "sight";
		String strB = "cite";
//		String strA = "steal";
//		String strB = "steel";
		int count = getSED(strA, strB);
		System.out.print(count);
	}
}
