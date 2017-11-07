package agother;
/*本质上就是利用分治的思想*/
public class Ary2D {
	public static boolean find(int[][] ary,int target){
		int i = 0,j = ary[0].length-1;//从第一行的最右端开始
		while(i<ary.length && j >=0){
			if(ary[i][j] == target){//命中
				return true;
			}else if(ary[i][j] > target){//排除掉第j列
				j--;
			}else {//排除掉第i行
				i++;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[][] ary = new int[3][6];//测试数组
			ary[0] = new int[]{1,2,4,8,10,50};
			ary[1] = new int[]{5,8,10,11,15,60};
			ary[2] = new int[]{9,12,15,20,30,70};
			System.out.println(find(ary, 15));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
