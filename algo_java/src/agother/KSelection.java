package agother;

public class KSelection {
	private static void swap(int[] ary,int indexA,int indexB){
		int tmp;
		tmp = ary[indexA];
		ary[indexA] = ary[indexB];
		ary[indexB] = tmp;
	}
	public static int position(int[] ary,int low,int high){
		int i = low,j = high+1;
		int pivot = ary[low];
		while(true){
			while(ary[++i] < pivot){
				if (i == high) {break;}
			}
			while(ary[--j] > pivot){
				if(j == low){break;}
			}
			if(i >= j){break;}
			swap(ary, i, j);
		}
		swap(ary, low, j);
		return j;
	}
	
	
	//此非递归的算法版本利用减治算法思想
	public static int kSelection(int[] ary,int k){
		int length = ary.length;
		if (k < 0 || k > length-1) {return -1;}//k值不在范围内
		int index,low = 0,high = length-1;
		while(true){
			index = position(ary, low, high);
			if (index == k) {break;}//命中，返回
			if (index < k) {//k必然会在index+1和high之间
				low = index+1;
			}else {//k必然会在low1和index-1之间
				high = index-1;
			}
		}
		return index;
	}
	public static boolean hasMajority(int[] ary){
		int length = ary.length;
		int index = kSelection(ary,length/2);//k为length/2
		int count = 0;
		for (int i = 0; i < length; i++) {
			if (ary[i] == ary[index]) {
				count++;
			}
		}
		return count > length/2;//如果存在主流数，ary[index]重复次数必然大于length/2
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[] ary = {3,7,3,9,3,3};
			int index = kSelection(ary,0);
			System.out.println(ary[index]);
//			System.out.println(hasMajority(ary));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
