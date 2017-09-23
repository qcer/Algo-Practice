package agother;

import java.util.Arrays;

public class Pointer {

	public static boolean search(int[] ary,int low,int high,int sum){
		int i = low,j = high;//一前一后两指针
		while(i<j){//两指针i,j不能碰头，这样能够避免sum=2*ary[i]=2*ary[j]的情况
			if (ary[i]+ary[j] < sum) {
				i++;
			}else if(ary[i]+ary[j] > sum){
				j--;
			}else {
				return true;
			}
		}
		return false;
	}
	/*
	 *实现原地数组去重，可改变原数组，返回唯一元素的个数。
	 * */
	public static int removeDuplicates(int[] ary,int low,int high){
		int i = low,j = i+1;//两指针，i从low开始，j从i+1开始
		for (; j <= high; j++) {
			if (ary[i] != ary[j]) {
				ary[++i] = ary[j];//i始终记录唯一序列的末尾位置
			}
		}
		return i-low+1;
	}

	/*
	 * 如果要求重复元素的个数最多可为n(n>=1)
	 * */
	public static int removeDuplicates(int[] ary,int low,int high,int n){
		int i = low+n-1,j = i+1;//i直接从第n个元素开始，j从i+1开始
		for (; j <= high; j++) {
			if (ary[i-n+1] != ary[j]) {//在ary[i]前面且距离为n的元素为ary[i-n+1]
				ary[++i] = ary[j];
			}
		}
		return Math.min(i-low+1, ary.length);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			int[] ary = {1,8,15};
//			System.out.println(search(ary, 0, ary.length-1, 15));
			
			int[] ary = {1,3,3,3,5,6,8,8,9,10};
			int length = removeDuplicates(ary, 0, ary.length-1,40);
			System.out.println(length);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
