package agother;

public class Kth {

//    public static int kSelection(int[] aryA,int[] aryB,int k) {
//        // body...
//        int n = aryA.length,m = aryB.length;
//        if (n>m) {kSelection(aryB,aryA,k);}//保证第二个数组参数为较长数组
//        int low = 0,high = m-1,mid = 0,j = 0;
//        int lowA=0,highA=n-1,lowB=0,highB=m-1;
//        int flag = 0;
//        while(flag<4){//highA+highB-lowA-lowB+2 > 4
//        	System.out.println("highB:"+highB);
//            mid = lowB + (highB-lowB)/2;
//            j = k-1-mid;
//            if (j > highA) {
//				lowB = mid;
//			}else if(j < lowA){
//				highB = mid;
//			}else{
//				if (aryA[j] > aryB[mid]) {
//					highA = j;
//					lowB = mid;
//				}else{
//					lowA = j;
//					highB = mid;
//				}
//			}
//            flag++;
//        }
//		System.out.println(k-lowA-lowB);
//        System.out.println("lowA:"+aryA[lowA]+"--highA:"+aryA[highA]+"--lowB:"+aryB[lowB]+"--highB:"+aryB[highB]);
////        int chech1 = low-1 >= 0?aryA[low-1]:Integer.MIN_VALUE;
////        int check2 = k-1-low >=0?aryB[k-1-low]:Integer.MIN_VALUE;
//        return 0;
//    }
	public static int kSelection(int[] aryA,int[] aryB,int k){
      int m = aryA.length,n = aryB.length;
      if (m>n) {kSelection(aryB,aryA,k);}//保证第二个数组参数为较长数组,保证n>m
      int low = 0,high = m-1,mid = 0,j = 0;
			while(low <= high){
				mid = low + (high-low)/2;//mid标记数组A
				j = k-1-mid;//j标记数组B
				if (j > n-1 || (j>=0 && aryB[j] > aryA[mid])) {
					low = mid + 1;
				}else{
					if(low == high){
						break;
					}else{
						high = mid;
					}
				}
			}
			int chech1 = low-1 >= 0?aryA[low-1]:Integer.MIN_VALUE;
			int check2 = k-1-low >=0?aryB[k-1-low]:Integer.MIN_VALUE;
			return Math.max(chech1,check2);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			int[] aryA = {20,30,40,50,60};
//		    int[] aryB = {1,3,5,7,80,90};
//		    int[] aryA = {1,6,7,8,12,15};
//		    int[] aryB = {3,4,9,10,16,20,25};
		    int[] aryA = {20,30};
		    int[] aryB = {1,3,5,7,40,60};
		    int result = kSelection(aryA,aryB,2);
		    System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
