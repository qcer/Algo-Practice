package agother;

public class Compare {

	public static int getSumOfMaxAndMin(int[] ary){
		int lenght = ary.length;
		int max,min,i;
		if (lenght%2 == 0) {
			max = ary[0];
			min = ary[1];
			i = 2;
		}else {
			max = min = ary[0];
			i = 1;
		}
		for (; i < lenght; i+=2) {
			if (ary[i]<ary[i+1]) {
				if (ary[i]<min) {min = ary[i];}
				if (ary[i+1]>max) {max = ary[i+1];}
			}else {
				if (ary[i+1]<min) {min = ary[i+1];}
				if (ary[i]>max) {max = ary[i];}
			}
		}
		return max+min;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ary = {8,3,1,5,6,10,30,9};
		int result = getSumOfMaxAndMin(ary);
		System.out.println(result);
	}

}
