package agdp;

import java.util.Arrays;

public class Cut {
	/*
	 * 递归版本实现
	 */
//	public static int getMaxReturn(int[] price,int n){
//		if (n == 0) {return 0;}
//		int profit = 0;
//		for (int i = 1; i <= n; i++) {
//			profit = Math.max(profit, price[i]+getMaxReturn(price, n-i));
//		}
//		return profit;
//	}
	
	/*
	 * 动态规划版本实现
	 * */
//	public static int getMaxReturn(int[] price,int n){
//		int[] aux = new int[n+1];//辅助数组，aux[0]不利用
//		Arrays.fill(aux, 0);
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= i; j++) {
//				aux[i] = Math.max(aux[i], aux[i-j]+price[j]);//直接利用子问题aux[i-j]的结果，避免重复求解
//			}
//		}
//		return aux[n];
//	}
	
	/*
	 * 动态规划版本实现，另一种实现方式
	 * */
	public static int getMaxReturn(int[] price,int n){
		int[] aux = new int[n+1];//辅助数组，aux[0]不利用
		Arrays.fill(aux, 0);
		aux[1] = price[1];
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i/2; j++) {//由于对称性，只需要遍历i的前半部分即可
				//取C(j)+C(i-j),price[i])两者最大者，对于且与不切两种方案的最大者
				aux[i] = Math.max(aux[i], Math.max(aux[j]+aux[i-j],price[i]));
			}
		}
		return aux[n];
	}
	
	
	
	
	/*
	 * 如果钢条必须要求切割至少依次
	 * */
//	public static int getMaxReturn(int[] price,int n){
//		int[] aux = new int[n+1];//辅助数组，aux[0]不利用
//		Arrays.fill(aux, 0);
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= i; j++) {
//				aux[i] = Math.max(aux[i], aux[i-j]+price[j]);//直接利用子问题aux[i-j]的结果，避免重复求解
//			}
//		}
//		int[] var = new int[n+1];
//		Arrays.fill(var, 0);
//		var[1] = price[1];
//		for (int i = 2; i <= n; i++) {
//			for (int j = 1; j <= i/2; j++) {
//				var[i] = Math.max(aux[j]+aux[i-j],var[i]);//直接利用子问题aux[i-j]的结果，避免重复求解
//			}
//		}
//		for(int value:var){
//			System.out.print(value+"--");
//		}
//		System.out.println();
//		return aux[n];
//	}
//	
	
	/*
	 * 如果钢条必须要求切割至少依次
	 * */
//	public static int getMaxReturn(int[] price,int n){
//		int[] aux = new int[n];//辅助数组，aux[0]不利用
//		Arrays.fill(aux, 0);
//		for (int i = 1; i <= n-1; i++) {
//			for (int j = 1; j <= i; j++) {
//				aux[i] = Math.max(aux[i], aux[i-j]+price[j]);//直接利用子问题aux[i-j]的结果，避免重复求解
//			}
//		}
//		int maxR = 0;
//		for (int i = 1; i < n; i++) {
//			maxR = Math.max(aux[i]+aux[n-i],maxR);
//		}
//		return maxR;
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = {0,1,5,8,9,10,17,17,20,24,30};
		int result = getMaxReturn(ary, 10);
		System.out.println(result);
	}

}
