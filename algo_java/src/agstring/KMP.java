package agstring;

public class KMP {
	public static int[] getNextAry(String sub){
		int subLenght = sub.length();
		int[] next = new int[subLenght];
		int t = next[0] = -1,j = 0;
		while(j < subLenght-1){
				if(t < 0 || sub.charAt(t) == sub.charAt(j)){
					t++;
					j++;
					next[j] = t;//可优化
				}else {
					t = next[t];
				}
		}
		return next;
	}
	//改进的next数组的算法
	public static int[] getNextAryExt(String sub){
		int subLenght = sub.length();
		int[] next = new int[subLenght];
		int t = next[0] = -1,j = 0;
		while(j < subLenght-1){
				if(t < 0 || sub.charAt(t) == sub.charAt(j)){
					t++;
					j++;
					next[j] = sub.charAt(t) != sub.charAt(j)?t:next[t];
				}else {
					t = next[t];
				}
		}
		return next;
	}

	/*KMP主算法
	 *i为主串位置指针，j为sub串位置指针
	 *j<0的情况为sub串的位置指针为0,且sub[0] != base[i]
	 *匹配能够成功的情况必为j==subLength
	 * */
	public static int  matchOfKMP(String base,String sub){
		int baseLength = base.length();
		int subLength = sub.length();
		int i = 0,j = 0;
		int[] next = getNextAryExt(sub);
		while(i < baseLength && j < subLength){
			if(j < 0 || base.charAt(i) == sub.charAt(j)){
				i++;
				j++;
			}else {
				j = next[j];
			}
		}
		int result = j == subLength?i-j:-1;
		return result;
	}
	
	public static void main(String[] args) {
		try {
			String base = "ababghababa";
			String sub = "ababap";//chinchilla,ababaaaba，
			int result = matchOfKMP(base, sub);
			System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
