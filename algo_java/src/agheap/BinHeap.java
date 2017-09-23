package agheap;

import java.util.ArrayList;

class Node{
	public final int value;
	Node(int value){
		this.value = value;
	}
	public String toString(){
		return "value:"+value;
	}
}
public class BinHeap {
	private static void swap(ArrayList<Node> aryList,int indexA,int indexB){
		Node tmp;
		tmp = aryList.get(indexA);
		aryList.set(indexA, aryList.get(indexB));
		aryList.set(indexB, tmp);
	}
	
	//上浮堆有序化
	private static void swim(ArrayList<Node> aryList,int index) {
		while(index > 1 && aryList.get(index).value > aryList.get(index/2).value){//当前元素非堆顶且大于其父亲，需要上浮调整
			swap(aryList, index, index/2);
			index = index/2;
		}
	}
	
	//下沉堆有序化
	private static void sink(ArrayList<Node> aryList,int index,int high){
		int indexOfMaxChild;
		while(true){
			if (2*index > high) {break;}//ary[2*index]无孩子
			if (2*index == high) {//ary[2*index]有左孩子，此时indexOfMaxChild无选择
				indexOfMaxChild = 2*index;
			}else {//ary[2*index]有左右两孩子，此时indexOfMaxChild有选择
				indexOfMaxChild = aryList.get(2*index).value > aryList.get(2*index+1).value?2*index:2*index+1;
			}
			if (aryList.get(index).value <aryList.get(indexOfMaxChild).value) {//当前ary[index]不满足局部堆有序
				swap(aryList, index, indexOfMaxChild);
			}
			index = indexOfMaxChild;//继续向下迭代
		}
	}
	
	//自动向下构造大根堆
	public static void createHeap(ArrayList<Node> aryList){
		int high = aryList.size()-1;
		for (int i = high/2; i > 0; i--) {//从非叶节点开始构造，同时sink()保持堆有序
			sink(aryList, i, high);
		}
	}
	
//	//swim()构造大根堆
//	public static void createHeap(ArrayList<Node> aryList){
//		int high = aryList.size()-1;
//		for (int i = 1; i <= high; i++) {//从索引1位置开始逐个加入，同时swim()保持堆有序
//			swim(aryList, i);
//		}
//	}
	public static void insert(ArrayList<Node> aryList,Node target){
		aryList.add(target);
		swim(aryList, aryList.size()-1);
	}
	//删除堆顶元素
	public static Node deleteMax(ArrayList<Node> aryList){
		int high = aryList.size()-1;
		Node node = null;
		if (high > 0) {
			swap(aryList, 1, high);//将末尾元素和堆顶元素交换
			node = aryList.remove(high);//删除末尾元素
			sink(aryList, 1, high-1);//对堆顶元素重新，使其重新堆有序
		}
		return node;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[] ary = {0,12,9,78,10,30,8,20,12,15};
			ArrayList<Node> aryList = new ArrayList<Node>();
			for (int i = 0; i < ary.length; i++) {
				aryList.add(new Node(ary[i]));
			}
			createHeap(aryList);
			insert(aryList, new Node(5));
//			for(Node node:aryList){
//				System.out.println(node);
//			}
			int size = aryList.size()-1;
			for (int i = 0; i < size; i++) {
				System.out.println(deleteMax(aryList));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
