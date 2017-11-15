package agdp;
public class Package {
	public static int getMaxValue(int[] w,int[] v,int p){
		int n = v.length-1;
		int[][] aux = new int[n+1][p+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= p ; j++) {
				if (j<w[i]) {
					aux[i][j] = aux[i-1][j];
				}else {
					aux[i][j] = Math.max(aux[i-1][j], aux[i-1][j-w[i]]+v[i]);
				}
			}
		}
		return aux[n][p];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] w = {0,2,1,3,2};
		int[] v = {0,12,10,20,15};
		int result = getMaxValue(w, v, 5);
		System.out.println(result);
	}
}
//for (int i = 0; i < n+1; i++) {
//	for (int j = 0; j < p+1; j++) {
//		System.out.print(aux[i][j]+"--");
//	}	
//	System.out.println();
//}
