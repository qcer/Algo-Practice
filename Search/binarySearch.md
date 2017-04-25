### **二分查找**
#### **JS实现**
#### **一、递归版本**
	function binarySearch(ary,low,high,target) {
		// body...
		if (low > high) {return -1}
		var mid = low+parseInt((high-low)/2);
		if (ary[mid] > target) {return binarySearch(ary,low,mid-1,target)}
		else if(ary[mid] < target) {return binarySearch(ary,mid+1,high,target)}
		else {return mid}
	}

#### **二、迭代版本**
	function binarySearch(ary,low,high,target) {
		// body...
		var mid = 0;
		while(low <= high){
			mid = low+parseInt((high-low)/2);
			if (target < ary[mid]) {high = mid-1}
			else if(ary[mid] < target) {low = mid+1}
			else return mid;
		}
		return -1;
	}