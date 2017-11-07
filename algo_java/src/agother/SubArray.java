package agother;
import java.util.Arrays;
public class SubArray {
	private static int getMax(int ...ary){
		Arrays.sort(ary);
		return ary[ary.length-1];
	}
	/*最大元素跨越了左右两个子数组*/
	private static int getMaxSumOfCrossSubArray(int[] ary,int low,int mid,int high){
		int tmpLeftSum = 0,tmpRightSum = 0;
		int leftSum = ary[mid],rightSum = ary[mid+1];//这种情况下，最大子数组至少包含ary[mid]和ary[mid+1]两个元素，才能实现“跨越”
		for (int i = mid; i >= low; i--) {//处理子数组的左边部分
			tmpLeftSum += ary[i];
			if (leftSum < tmpLeftSum) {
				leftSum = tmpLeftSum;
			}
		}
		for (int j = mid+1; j <= high; j++) {//处理子数组的右边部分
			tmpRightSum += ary[j];
			if (rightSum < tmpRightSum) {
				rightSum = tmpRightSum;
			}
		}
		return leftSum+rightSum;
	}
	public static int getMaxSumOfSubArray(int[] ary,int low,int high){
		if (high == low) {return ary[low];}//递归基
		int mid = (high+low)/2;
		int leftMaxSum =  getMaxSumOfSubArray(ary,low,mid);//最大值数组位于左子数组中
		int crossMaxSum = getMaxSumOfCrossSubArray(ary,low,mid,high);//最大元素跨越了左右两个子数组
		int rightMaxSum = getMaxSumOfSubArray(ary,mid+1,high);//最大值数组位于左子数组中
		return getMax(leftMaxSum,crossMaxSum,rightMaxSum);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[] ary = {-3,5,-1,-2,4,-1};
			int result = getMaxSumOfSubArray(ary,0,ary.length-1);
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
