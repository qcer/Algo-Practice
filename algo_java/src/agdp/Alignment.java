package agdp;
public class Alignment {
	//根据对齐骨规则生成相应的对齐后的字符串
	private static String[] generate(String base,String ...origin){
		int num = origin.length;
		String[] align = new String[num];
		for (int i = 0; i < num; i++) {
			if (origin[i].length() == base.length()) {
				align[i] = origin[i];
			}else {//base.length()只能是等于或者大于两个原字符串的长度
				String tmp = "";
				for (int j = 0,k = 0; j < base.length(); j++) {
					if (base.charAt(j) == '+') {
						if (i == 0) {tmp = tmp+"*";}
						else{tmp = tmp+origin[i].charAt(k++);}
					}else if(base.charAt(j) == '-'){
						if (i == 0) {tmp = tmp+origin[i].charAt(k++);}
						else{tmp = tmp+"*";}
					}
					else {
						tmp = tmp+origin[i].charAt(k++);
					}
				}
				align[i] = tmp;
			}
		}
		return align;
	}
	public static String align(String strA,String strB){
		int m = strA.length(),n = strB.length(),tmp;
		//aux数组记录子问题的最有对齐方案的分数，也即子问题的最高分数。
		int[][] aux = new int[m+1][n+1];
		//rule数组记录对齐方案，分别用"+"、"-"、"="和"~"记录四种情况。
		String[][] rule = new String[m+1][n+1];
		//rule初始化
		rule [0][0] = "";
		for (int i = 1; i < m+1; i++) {
			rule[i][0] = rule[i-1][0]+"-";
		}
		for (int i = 1; i < n+1; i++) {
			rule[0][i] = rule[0][i-1]+"+";
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (strA.charAt(i-1) == strB.charAt(j-1)) {
					aux[i][j] = aux[i-1][j-1]+3;
					rule[i][j] = rule[i-1][j-1] + "=";//A[i]==B[j]：->"="
				}else {
					tmp = Math.max(Math.max(aux[i-1][j], aux[i][j-1]), aux[i-1][j-1]+1);
					aux[i][j] = tmp;
					if (tmp == aux[i-1][j-1]-1) {//A[i]!=B[j]且A[i]和	B[j]不为空字符：->"~"
						rule[i][j] = rule[i-1][j-1]+"~";
					}else if(tmp == aux[i-1][j]-2){//B[i]为空字符:->"-"
						rule[i][j] = rule[i-1][j]+"-";
					}else{
						rule[i][j] = rule[i][j-1]+"+";//A[i]为空字符:->"+"
					}
				}
			}
		}
		//格式化输出aux数组
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				System.out.format("%3d",aux[i][j]);
			}
			System.out.println();
		}
		//格式化输出rule数组
		for (int i = 0; i < m+1; i++) {
			for (int j = 0; j < n+1; j++) {
				System.out.format("%-15s",rule[i][j]);
			}
			System.out.println();
		}
		//返回最优的对齐方法对应的规则
		return rule[m][n];
	}
	//根据规则字符串计算分数
	public static int getScore(String ruleStr){
		int score = 0;
		for (int i = 0; i < ruleStr.length(); i++) {
			if (ruleStr.charAt(i) == '=') {
				score += 3;
			}else if (ruleStr.charAt(i) == '~') {
				score += 1;
			}
		}
		return score;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] scoreAry = {3,1,0,0};
//		String strA = "GATCGGCAT";
//		String strB = "CAATGTGAATC";
		String strA = "GATC";
		String strB = "ATCG";
//		String strA = "GAC";
//		String strB = "ATCG";
		String ruleStr = align(strA, strB);
		System.out.println(ruleStr);
		int score = getScore(ruleStr);
		System.out.println(score);
		String[] alignStr = generate(ruleStr, strA,strB); 
		for(String str:alignStr){
			System.out.println(str);
		}
	}
}
