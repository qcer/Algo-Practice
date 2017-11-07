package agother;
import java.util.ArrayList;
import java.util.Stack;
public class MinStack {

	private ArrayList<Integer> ms = new ArrayList<Integer>();//栈元素的存储借助ArrayList
	private Stack<Integer> aux = new Stack<Integer>();//辅助的普通栈
	private int currMin = Integer.MAX_VALUE;//当前最小值
	private int top = -1;//栈顶指针
	public void push(int ele){
		top++;
		ms.add(ele);
		if (ele < currMin) {//如果入栈元素小于当前栈中最小与元素，更新当前最小元素，并将新的最小元素的位置（也即是top值）入辅助栈
			currMin = ele;
			aux.push(top);
		}
	}
	public Integer pop(){
		if (top<0) {return null;}
		Integer ele = ms.remove(top);
		top--;
		if (ele == currMin) {//如果出栈元素等于当前栈中最小元素，更新当前最小元素为次小元素，并弹出aux栈顶元素。
			aux.pop();
			currMin = ms.get(aux.peek());
		}
		return ele;
	}
	/*
	 * 实际上，辅助栈的栈顶元素始终记录的是ms栈中最小元素的索引值
	 * 即是是在ms压栈或者出栈的过程中，通过调整，任然保持着这一事实
	 * 因此getMin()方法总能以O(1)的时间复杂度正确找到ms栈的最小元素
	 * */
	public Integer getMin(){
		if (aux.isEmpty()) {
			return null;
		}else {
			return currMin;//或者返回ms.get(aux.peek())
		}
	}
	public int size(){
		return top+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ArrayList<Integer> test = new ArrayList<Integer>();
			test.add(0,5);
			MinStack ms = new MinStack();
			ms.push(10);
			ms.push(3);
			ms.push(5);
			ms.push(15);
			ms.push(2);
			ms.push(8);
//			System.out.println(ms.size());
//			System.out.println(ms.getMin());
			System.out.println(ms.pop());
			ms.push(1);
			System.out.println(ms.pop());
//			System.out.println(ms.pop());
//			System.out.println(ms.pop());
//			System.out.println(ms.pop());
//			System.out.println(ms.getMin());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
