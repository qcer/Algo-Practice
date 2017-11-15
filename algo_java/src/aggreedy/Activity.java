package aggreedy;

import java.util.ArrayList;

public class Activity {

	/*
	 * 贪心算法实现-递归版本
	 * */
//	public static void getMaxCAS(int[] s,int[] f,int k,int n){
//		int m = k+1;
//		while(m<=n && s[m] < f[k]){
//			m++;
//		}
//		if (m<=n) {//s[m] >= f[k]
//			System.out.println(m);
//			getMaxCAS(s, f, m, n);
//		}
//	}
	/*
	 * 贪心算法迭代版本
	 * */
	public static ArrayList getMaxCAS(int[] s,int[] f){
		int m =	s.length;
		ArrayList<Integer> aux = new ArrayList<Integer>();
		int pre = 0;//记录前一次选择的活动下标
		for (int i = 1; i < m; i++) {
			if(s[i] >= f[pre]){
				pre = i;
				aux.add(i);
			}
		}
		return aux;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s = {0,1,3,0,5,3,5,6,8,8,2,8};
		int[] f = {0,4,5,6,7,9,9,10,11,12,14,16};
//		getMaxCAS(s, f, 0, s.length-1);
		ArrayList<Integer> result = getMaxCAS(s, f);
		System.out.println(result.size());
	}

}
