package agdp;
public class Matrix {
	public static int getMinCost(int[] p){
		int n = p.length-1;//n为矩阵链中矩阵的个数，等于p长度-1
		int[][] aux = new int[n+1][n+1];//辅助数组,为方便计算，第0行和第0列不利用
		for (int l = 2; l <= n; l++) {//矩阵链的长度，从2开始，一直到长度为n结束，满足l=j-i+1
			for (int i = 1,j; i <= n-l+1; i++) {//当j=n是，i到最大值，所以i<=n-l+1
				j = i+l-1;
				aux[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {//遍历Ai到Aj矩阵链中每一个位置,并存储最小者
					aux[i][j] = Math.min(aux[i][k]+aux[k+1][j]+p[i-1]*p[k]*p[j], aux[i][j]);
				}
			}
		}
		return aux[1][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] p = {30,35,15,5,10,20,25};
		int result = getMinCost(p);
		System.out.println(result);
	}
}

//
//for (int i = 0; i < n+1; i++) {
//	for (int k = 0; k < n+1; k++) {
//		System.out.print(aux[i][k]+"-");
//	}
//	System.out.println();
//}
