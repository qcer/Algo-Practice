package aglist;

class Node{
	public final int value;
	public Node next = null;
	Node(int value){
		this.value = value;
	}
	public static void addTail(Node head,Node node){
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = node;
	}
	public static Node getNode(Node head,int index){
		int i = 0;
		Node temp = head;
		while(i < index && temp.next != null){
			temp = temp.next;
			i++;
		}
		return temp;
	}
	public static Node getTail(Node head){
		Node temp = head;
		while(temp.next != null){
			temp = temp.next;
		}
		return temp;
	}
}
public class Circle {
	public static boolean hasCircle(Node head){
		Node fast = head,slow = head;//两指针，一个步长为1，一个步长为2
		while(fast.next != null && fast.next.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow){return true;}//两指针相遇，必有环，且相遇点在环内
		}
		return false;
	}
	private static Node getMeetNode(Node head){
		Node fast = head,slow = head;
		while(fast.next != null && fast.next.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow){break;}
		}
		return fast;
	}
	private static int getLengthOfCircle(Node meetNode){
		int length = 1;
		Node temp = meetNode.next;
		while(temp != meetNode){//由于相遇点必在环内，只要顺着环走一圈，即可获得环的长度
			length++;
			temp = temp.next;
		}
		return length;
	}
	/*
	 * 在相遇点将环分开，视为形成两个单链表
	 * 一个单链表以head为头
	 * 另一个单链表以meetNode为头
	 * 该问题进而规约为求两个单链表的交点，交点即为环的起点
	 * 并且其交点到两链表头等长
	 * */
	private static Node getStartOfCircle(Node head,Node meetNode){
		while(head != meetNode){
			head = head.next;
			meetNode = meetNode.next;
		}
		return meetNode;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[] ary = {3,4,5,7,1,40,10,50};
			Node head = new Node(30);
			for (int i = 0; i < ary.length; i++) {
				Node.addTail(head, new Node(ary[i]));
			}
			Node.getTail(head).next = Node.getNode(head, 0);
			if (hasCircle(head)) {
				Node meetNode = getMeetNode(head);
				int lenght = getLengthOfCircle(meetNode);
				Node start = getStartOfCircle(head, meetNode);
				System.out.println("length:"+lenght);
				System.out.println("StartNode:"+start.value);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
