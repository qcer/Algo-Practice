package agdp;
public class LPS {
	public static int getLPS(String str){
		int n = str.length();
		int[][] aux = new int[n][n];
		for (int i = 0; i < n; i++) {
			aux[i][i] = 1;//单子字符，最长回文子序列长度为1
		}
		for (int l = 2; l <= n; l++) {//子序列长度，从2到n
			for (int i = 0,j; i <= n-l; i++) {
				j = l+i-1;
				if (str.charAt(i) == str.charAt(j)) {
					aux[i][j] = aux[i+1][j-1]+2;//首尾元素相等，必是回文子序列中的元素
				}else {
					//首尾元素不等，取(i+1)->j序列和i->(j-1)系列中较长的回文子序列
					aux[i][j] = Math.max(aux[i+1][j], aux[i][j-1]);
				}
			}
		}
		return aux[0][n-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "character";;
		String str = "javaej";
		int result  = getLPS(str);
		System.out.println(result);
	}
}
//
//for (int i = 0; i < aux.length; i++) {
//	for (int j = 0; j < aux.length; j++) {
//		System.out.print(aux[i][j]+"--");
//	}
//	System.out.println();
//}
