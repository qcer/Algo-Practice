package agsort;

import java.util.*;
import java.util.HashSet;

import org.omg.CORBA.PRIVATE_MEMBER;


class Node{
	public int value;
	public int index;
	public Node(int value,int index){
		this.value = value;
		this.index = index;
	}
	public String toString(){
		return "value:"+this.value+"--"+"index:"+this.index;
	}
}

public class Sort {
	
	private static void swap(int[] ary,int indexA,int indexB){
		int tmp;
		tmp = ary[indexA];
		ary[indexA] = ary[indexB];
		ary[indexB] = tmp;
	}
	public static void bubbleSort(int[] ary){
		int length = ary.length;  
		//每完成一轮外层循环，ary[i]的次序在全局确定
		for (int i = 0; i < length; i++) {
			//内层循环向后扫描，且立即交换。
			for (int j = i+1; j < length; j++) {
				if (ary[i] > ary[j]) {
					swap(ary, i, j);
				}
			}
		}
	}
	public static void selectionSort(int[] ary){
		int length = ary.length;
		int minIndex;
		//每完成一轮外层循环，ary[i]的次序在全局确定
		for (int i = 0; i < length; i++) {
			minIndex = i;
			for (int j = i+1; j < length; j++) {
				if (ary[minIndex] > ary[j]) {//非立即交换，只更小元素的记录位置
					minIndex = j;
				}
			}
			//内层循环全部借宿再交换
			swap(ary, i, minIndex);
		}
	}
//	public static void insertSort(int[] ary){
//		int length = ary.length;
//		for (int i = 0; i < length; i++) {
//			for (int j = i; j > 0; j--) {
//				//正确确定元素ary[j-1]的位置
//				//如果ary[j-1]严格大于ary[j],就将ary[j-1]向前挪动
//				if (ary[j] < ary[j-1]) {
//					swap(ary, j, j-1);
//				}else{
//					break;
//				}
//			}
//		}
//	}
	public static void insertSort(int[] ary){
		int length = ary.length;
		int tmp,position;
		for (int i = 0; i < length; i++) {
			position = i;
			tmp = ary[i];
			for (int j = i; j > 0; j--) {
				if (tmp < ary[j-1]) {
					//挪动比tmp更大的元素，为tmp元素腾出位置。此时ary[j]和ary[j-1]相同
					ary[j] = ary[j-1];
					position = j-1;//更新其准确位置
				}else{
					break;
				}
				ary[position] = tmp;//正确放置ary[position]
			}
		}
	}
	public static void shellSort(int[] ary){
		int length = ary.length;
		int h = 1;
		while(h < length/3){
			//h的取值遵循等差数列：1,4,13,40,121,364...
			//并且h最终定能取1
			h = 3*h + 1;
		}
		while(h >= 1){
			for (int i = h; i < length; i++) {
				for (int j = i; j >= h; j -= h) {//h有序子序列
					if (ary[j] < ary[j-h]) {
						swap(ary, j, j-h);
					}else{
						break;
					}
				}
			}
			h = h/3;
		}
	}
	private static void merge(int[] ary,int low,int mid,int high){
//		System.out.println("low:"+low+"--"+"mid:"+mid+"--"+"high:"+high);
		int[] aux = ary.clone();//辅助数组
		int i = low,j = mid+1;//i为前一个分数组头部开始，j从后一个分数组头部开始
		for (int k = low; k <= high; k++) {
			if (i > mid) {ary[k] = aux[j++];}//前一个分数组已全部合并，只需拷贝后一个分数组
			else if(j > high){ary[k] = aux[i++];}//后一个分数组已全部合并
			else if(aux[i] <= aux[j]){ary[k] = aux[i++];}//<=的条件保证合并算法的稳定性
			else {ary[k] = aux[j++];}
		}
	}
	public static void mergeSort(int[] ary,int low,int high){
		if (low >= high) {return;}//递归基
		int mid = low + (high-low)/2;
		mergeSort(ary, low, mid);//mid位置的元素归属前一个分数组，切位前一个分数组的最后一个位置
		mergeSort(ary, mid+1, high);//后一个分数组从mid+1开始
		merge(ary, low, mid, high);//递归从两个单元素数组开始，如merge(ary,0,0,1)
	}
	
	
//	public static void mergeSort(int[] ary,int low,int high){
//		int mid;
//		int lenght = high-low+1;//待排序列的长度
//		//size为待合并的分数组的长度
//		//size呈现等比数列增长：1,2,4,8,16.。。
//		for (int size = 1; size < lenght; size=2*size) {
//			System.out.println(size);
//			//start为合并的两数组的前一个数组的起始位置
//			//start+size为待合并的两数组的第二个分数组的起始位置
//			for (int start = low; start+size <= high; start+=2*size) {
//				mid = start+size-1;
//				//start+2*size-1为待合并的两数组的第二个分数组的结束位置
//				//Math.min(start+2*size-1,high)主要是考虑第二个合并的分数组长度可能不足size，原待排序列长度为奇数
//				merge(ary, start, mid,Math.min(start+2*size-1,high));
//			}
//		}
//	}
	private static int position(int[] ary,int low,int high){
		int i = low,j = high+1;
		int pivot = ary[low];//选定中轴元素
		while(true){
			while(ary[++i] < pivot) {//遇到大于等于pivot时停下
				if (i == high) {break;}//此处有必要检查,当pivot刚好为当前序列的最大值时，从此处break出来。
			}
			while(ary[--j] > pivot){//遇到小于等于pivot时停下
				if(j == low){break;}//此处可无需检查，因为j==low时，ary[j] == ary[low]，必会在while检查时停下。
			}
//			System.out.println("i:"+i+"---"+"j:"+j);
			if (i >= j) {break;}//退出主循环，注意，i有可能大于j
			swap(ary, i, j);
		}
		swap(ary, low, j);//因为j停下的位置时是小于等于pivot，因此应当让pivot和ary[j]交换
		return j;//返回pivot在全局下的次序位置
	}
	public static void quickSort(int[] ary,int low,int high){
		if(low >= high){return;}//递归基，单个元素自然有序
		int position = position(ary, low, high);
		quickSort(ary, low, position-1);//low到position-1中的所有元素均<=ary[position]
		quickSort(ary, position+1, high);//position+1到high中的所有元素均>=ary[position]
	}
	private static void createHeap(int[] ary,int index,int high){
		int indexOfMaxChild;
		while(true){
			if (2*index > high) {break;}//ary[2*index]无孩子
			if (2*index == high) {//ary[2*index]有左孩子，此时indexOfMaxChild无选择
				indexOfMaxChild = 2*index;
			}else {//ary[2*index]有左右两孩子，此时indexOfMaxChild有选择
				indexOfMaxChild = ary[2*index] > ary[2*index+1]?2*index:2*index+1;
			}
			if (ary[index] < ary[indexOfMaxChild]) {//当前ary[index]不满足局部堆有序
				swap(ary, index, indexOfMaxChild);
			}
			index = indexOfMaxChild;//继续向下迭代
		}
	}
	public static void heapSort(int[] ary){
		int high = ary.length-1;
		for (int i = high/2; i > 0; i--) {//从最大的非叶节点开始构造堆，堆顶位置为1，舍弃数组的0位
			createHeap(ary, i, high);
		}
		while(high > 0){
			swap(ary, 1, high);//将大根堆的堆顶元素(最大值)与数组尾部值交换。
			high--;//大根堆的范围缩小一个单位。
			createHeap(ary, 1, high);//重构大根堆。
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			int[] ary = {4,1,3,7,5,2};
//			int[] ary = {3,2,2,0};
			int[] ary = {0,12,9,78,10,30,8,20,12,15};
//			int[] ary = {9,12,8,10,7};
//			bubbleSort(ary);
//			heapSort(ary);
			for (int i = (ary.length-1)/2; i > 0; i--) {//从最大的非叶节点开始构造堆，堆顶位置为1，舍弃数组的0位
				createHeap(ary, i, ary.length-1);
			}
//			System.out.println("position:"+position(ary, 0, ary.length-1));
			for(int value:ary){
				System.out.print(value+",");
			}
			
			
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
