package agstring;
import java.util.*;


public class RPN {
	public static String[] preDeal(String infix){
		infix = infix.trim();
		int length = infix.length();
		ArrayList<String> infixOfArrayList = new ArrayList<String>();
		char currChar;
		int index = 0;
		final String regex = "\\+|-|\\*|\\/|\\(|\\)";
		for (int i = 0; i < length; i++) {
			currChar = infix.charAt(i);
			if(String.valueOf(currChar).matches(regex)){//运算符
				if (index < i) {
					infixOfArrayList.add(infix.substring(index,i));//add数字
				}
				infixOfArrayList.add(String.valueOf(currChar));
				index = i+1;
			}
		}
		infixOfArrayList.add(infix.substring(index,length));
		return infixOfArrayList.toArray(new String[infixOfArrayList.size()]);
	}
	public static String[]  getPostfix(String infix) {
		String[] infixOfAry = preDeal(infix);
		int length = infixOfAry.length;
		ArrayDeque<String> stack = new ArrayDeque<String>();
		String currString = "";
		final String regex = "\\+|-|\\*|\\/|\\(|\\)";
		ArrayList<String> postfixOfArrayList = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			currString = infixOfAry[i];
			if (currString.matches(regex)) {//symbol
				if (currString.matches("\\*|\\/|\\(")) {
					stack.offerFirst(currString);
				}else {//),+,-
					String top = "";
					if(currString.equals(")")){
						while(!stack.isEmpty()){
							top = stack.removeFirst();
							if (top.equals("(")) {
								break;
							}
							postfixOfArrayList.add(top);
						}
					}else {//+ ,-
						if (!stack.isEmpty()) {
							top = stack.peekFirst();
							if (top.equals("*") || top.equals("/")) {
								while(!stack.isEmpty()){
									postfixOfArrayList.add(stack.removeFirst());
								}
							}
						}
						stack.offerFirst(currString);
					}
				}
			}else {//number
				postfixOfArrayList.add(currString);
			}
		}
		while(!stack.isEmpty()){
			postfixOfArrayList.add(stack.removeFirst());
		}
		return postfixOfArrayList.toArray(new String[postfixOfArrayList.size()]);
	}
	public static double computePostfix(String infix){
		String[] postfixAry = getPostfix(infix);
		ArrayDeque<Double> stack = new ArrayDeque<Double>();
		int length = postfixAry.length;
		String currString = "";
		final String regex = "\\+|-|\\*|\\/|\\(|\\)";
		double operandOne,operandTwo;
		for (int i = 0; i < length; i++) {
			currString = postfixAry[i];
			if (currString.matches(regex)) {
				operandOne = stack.removeFirst();
				operandTwo = stack.removeFirst();
				switch (currString.charAt(0)) {
				case '+':
					stack.addFirst(operandTwo + operandOne);
					break;
				case '-':
					stack.addFirst(operandTwo - operandOne);
					break;
				case '*':
					stack.addFirst(operandTwo * operandOne);
					break;
				case '/':
					stack.addFirst(operandTwo / operandOne);
					break;
				}
				
			}else {
				stack.addFirst(Double.parseDouble(currString));
			}
		}
		return stack.removeFirst();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String infix = "100+8*5-(10/2)*5+10.5";
//			String infix = "10*20/5-10";
			double result = computePostfix(infix);
			System.out.println(result);	
//			for(String str:getPostfix(infix)){
//				System.out.println(str);
//			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

