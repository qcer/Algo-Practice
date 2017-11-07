package agdp;
public class Coins {
//	public static int changeOfLessCoins(int[] ary,int n){
//		int m = ary.length;
//		int[] aux = new int[n+1];//aux[0]充当哨兵，没事实际含义
//		int tmpLest;
//		for (int i = 1; i <= n; i++) {
//			tmpLest = Integer.MAX_VALUE;
//			for (int j = 0; j < m; j++) {
//				if (i>=ary[j]) {
//					tmpLest = Math.min(aux[i-ary[j]], tmpLest);//目的在于找出一个最小的tmpLess
//				}
//			}
//			aux[i] = tmpLest+1;
//		}
//		return aux[n];
//	}
	
	/*
	 * 贪心算法实现
	 * */
	public static int changeOfLessCoins(int[] ary,int n){
		int length = ary.length;
		int count  = 0;
		while(n>0){
			for (int i = length-1; i >= 0; i--) {//每次从最大的面值开始选择
				if (ary[i]<=n) {
					n -= ary[i];
					count++;
					break;
				}
			}
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = {1,5,10,25,50,100};
		int result = changeOfLessCoins(ary,900);
		System.out.println(result);//6
	}

}
