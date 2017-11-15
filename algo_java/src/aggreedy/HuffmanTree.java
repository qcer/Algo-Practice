package aggreedy;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class HuffmanTree {
	//树节点的数据结构
	static class Node {
		private int weight = 0;
		private Node left = null;
		private Node right = null;
		Node(int w){
			this.weight = w; 
		}
	}
	
	//构造赫夫曼树
	public static Node createHuffmanTree(double[] w){
		
		 Comparator<Node> cmp = new Comparator<Node>() {//实例化比较器
	            @Override
	            public int compare(Node node1, Node node2) {
	            	if (node2.weight > node1.weight) {
						return -1;
					}else if(node2.weight ==  node1.weight){
						return 0;
					}else {
						return 1;
					}
	            }
	      };
		PriorityQueue<Node> queen = new PriorityQueue<Node>(10,cmp);
		for (int i = 0; i < w.length; i++) {//初始状态为单节点子树
			queen.add(new Node((int)(w[i]*100)));
		}
		Node tmpNode1,tmpNode2,tmpMerge;
		while(queen.size()>1){
			//取出优先队列中最小和次小的元素，合并成新的子树，并加入队列
			tmpNode1 = queen.poll();
			tmpNode2 = queen.poll();
			tmpMerge = new Node(tmpNode1.weight+tmpNode2.weight);
			tmpMerge.left = tmpNode1;
			tmpMerge.right = tmpNode2;
			queen.add(tmpMerge);
		}
		return queen.poll();
	}
	//先序遍历赫夫曼树
	public static void preTravel(Node node){
		if (node != null) {
			System.out.println(node.weight);
			preTravel(node.left);
			preTravel(node.right);
		}
	}
	//工具方法，判定是否为叶节点
	private static boolean isLeaf(Node node){
		return (node.left == null) && (node.right == null);
	}
	
	//递归实现编码：0代表转向左子树，1代表转向左子树
	private static void innerCode(Node node,String code){
		if (!isLeaf(node)) {
			StringBuilder tmpCode1 = new StringBuilder(code);
			if (node.left != null) {
				tmpCode1.append("0");
			}
			innerCode(node.left,tmpCode1.toString());
			StringBuilder tmpCode2 = new StringBuilder(code);
			if (node.right != null) {
				tmpCode2.append("1");
			}
			innerCode(node.right,tmpCode2.toString());
		}else {
			System.out.println(code);
		}
	}
	public static void code(Node node){//根据赫夫曼树进行编码
		innerCode(node, "");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] ary = {0.15,0.10,0.30,0.20,0.20,0.05};
//		double[] ary = {0.10,0.15,0.20,0.20,0.35};
		Node root = createHuffmanTree(ary);
//		preTravel(root);
		code(root);
	}
}
