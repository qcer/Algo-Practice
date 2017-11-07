package agother;

import com.sun.org.apache.regexp.internal.recompile;

public class Bit {

	//	public static int find(int[] ary,int N){
	//	int length = ary.length;
	//	int sum = 0;
	//	for (int i = 0; i < length; i++) {
	//		sum += ary[i];
	//	}
	//	return N*(N+1)/2-sum;
	//}
//	public static int find(int[] ary){
//		int tmp = 0;
//		for (int i = 0; i < ary.length; i++) {
//			tmp = tmp^ary[i];//依次进行异或运算
//		}
//		return tmp;
//	}
	public static int find(int[] ary){
		int tmp = 0,mask;
		int targetOne = 0,targetTwo = 0;
		for(int ele:ary){
			tmp = tmp^ele;
		}
		int pos = getFirstBit1(tmp);//以tmp的最右边变的1作为分组条件
		mask = 1<<(pos-1);//这里的mask实际上就是一个比特掩码，只有pos位为1 ，其余位均为0
		for (int i = 0; i < ary.length; i++) {
			if ((ary[i]&mask) ==  mask) {//说明ary[i]的pos位为1
				targetOne = targetOne^ary[i];
			}else {//说明ary[i]的pos位为0
				targetTwo = targetTwo^ary[i];
			}
		}
		return targetOne+targetTwo;
	}
//	public static int getFirstBit1(int N){
//		if (N == 0) {return -1;}
//		int mask = 1;
//		int pos = 1;
//		while(mask <= N){
//			if((N&mask) == mask){
//				break;
//			}else {
//				mask <<= 1;
//				pos++;
//			}
//		}
//		return pos;
//	}
	public static int getFirstBit1(int N){
		int tmp = (N^(N-1))+1;
		int pos = 0;
		while(tmp > 1){
			tmp >>= 1;
			pos++;
		}
		return pos;
	}
//	/*常规的算法*/
//	public static int getNumberOfBit1(int N){
//		int count = 0;
//		int mask = 1;
//		while(mask <= N){
//			System.out.println("ok");
//			if((N&mask) == mask){
//				count++;
//			}
//			mask <<= 1;
//		}
//		return count;
//	}
	/*较为高效的算法*/
	public static int getNumberOfBit1(int N){
		int count = 0;
		while(N != 0){
			count++;
			N = (N-1)&N;
			System.out.println("ok");
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[] ary = {2,3,6,3,4,9,4,2,1,6};
//			int result = find(ary);
//			System.out.println(result);
//			System.out.println(9^1);
//			System.out.println(1<<2);
//			System.out.println(getNumberOfBit1(1000000));
			System.out.println(getFirstBit1(12));
//			System.out.println(2^2^3^3);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
