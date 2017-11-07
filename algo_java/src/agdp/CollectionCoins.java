package agdp;
public class CollectionCoins {
	private static int[][] initData(int m,int n){
		int[][] ary = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				ary[i][j] = (int)(Math.random()*10);//随机生成[0,10]的整数
			}
		}
		return ary;
	} 
	public static int getMostCoins(int[][] ary){
		int m = ary.length,n = ary[0].length;
		int[][] aux = new int[m+1][n+1];//aux第0行和第0列均置0，充当哨兵作用
		for (int i = 1; i < m+1; i++) {
			for (int j = 1; j < n+1; j++) {
				//递推式
				aux[i][j] = Math.max(aux[i-1][j], aux[i][j-1])+ary[i-1][j-1];
			}
		}
		return aux[m][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 2,n = 3;
		int[][] ary = initData(m,n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(ary[i][j]+"-");
			}
			System.out.println();
		}
		int result = getMostCoins(ary);
		System.out.print(result);
	}
}
