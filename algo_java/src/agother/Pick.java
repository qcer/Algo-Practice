package agother;

public class Pick {

//	public static int find(int[] ary,int N){
//		int length = ary.length;
//		int sum = 0;
//		for (int i = 0; i < length; i++) {
//			sum += ary[i];
//		}
//		return N*(N+1)/2-sum;
//	}
//	public static int find(int[] ary){
//		int tmp = 0;
//		for (int i = 0; i < ary.length; i++) {
//			tmp = tmp^ary[i];
//		}
//		return tmp;
//	}
	public static int find(int[] ary){
		int tmp = 0;
		for (int i = 0; i < ary.length; i++) {
//			if (ary[i]^0 = 1) {
//				
//			}
		}
		return 0;
	}
	public static int getFirstBit1(int target){
		if (target == 0) {return -1;}
		int mask = 1;
		int index = 1;
		while(mask <= target){
			if((target&mask) == mask){
				break;
			}else {
				mask = mask*2;
				index++;
			}
		}
		return index;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(getFirstBit1(0));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
