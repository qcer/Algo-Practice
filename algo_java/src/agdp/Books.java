package agdp;
public class Books {
	public static int getMostBooks(int[] b) {//为方便计算，b数组的首项b[0]用0填充，无实际意义
		int length = b.length;
		if(length == 1){return 0;}
		int[] aux = new int[length];//辅助数组，存储子问题的解
		aux[1] = b[1];
		for (int i = 2; i < length; i++) {
			aux[i] = Math.max(aux[i-2]+b[i], aux[i-1]);//对应的在第i天借书或者不借书
		}
		return aux[length-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = {0,5,1,2,10,6,2,8};
		int result = getMostBooks(ary);
		System.out.println(result);
	}
}
