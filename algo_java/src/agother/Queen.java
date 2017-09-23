package agother;

import java.util.Arrays;

public class Queen {
	/*
	 * 为了方便，二维矩阵的0行0列设为哨兵，不会正真使用
	 * aux为辅助数组，数组的头和尾均设为哨兵，因此数组长度为n+2
	 * deep为试探层
	 * aux[deep]为deep层的皇后在deep层的位置
	 * */
	public static int queenOfN(int n){
		int count = 0;
		int[][] ary = new int[n+1][n+1];
		int[] aux = new int[n+2];
		int deep = 1;//从第一层开始
		while(deep>0){
			if (deep > n) {//有解
				deep--;
				count++;
			}else {//能够到deep层，有可能是从deep-1层试探而来，也有可能是从deep+1回溯而来
				aux[deep] += 1;//因为有哨兵的作用，总是可以从aux[deep]的下一个位置开始
				while(aux[deep] <= n){//在deep层进行枝剪
					if (check(aux, deep)) {//deep层的aux[deep]列符合条件
						deep++;//试探到下一层
						aux[deep] = 0;//重置下一层的初始位置
						break;
					}else {//deep层的aux[deep]列不符合条件
						aux[deep] += 1;
					}
				}
				if (aux[deep] >= n) {deep--;}//回溯到上一层
			}
		}
		return count;
	}
	/*
	 * 因为试探的过程是逐层往下的，因此行冲突不会发生
	 * aux[i] == aux[k]会引起类冲突
	 * Math.abs(i-k) == Math.abs(aux[i]-aux[k]会引起正反对角线冲突
	 * */
	private static boolean check(int[] aux,int k){
		for (int i = 1; i < k; i++) {
			if (Math.abs(i-k) == Math.abs(aux[i]-aux[k]) || aux[i] == aux[k]){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int count = queenOfN(8);
			System.out.println(count);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
