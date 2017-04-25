### **快速排序**
#### **JS实现**

	function partition(ary,low,high) {
		// body...
		var pivot = ary[low];
		var i = low,
			j = high+1;
		while(true){
			while(ary[++i] < pivot){
				if (i == high) {break;}
			}
			while(ary[--j] > pivot){
				if (j == low) {break;}
			}
			if (i >= j) {break;};
			[ary[i],ary[j]] = [ary[j],ary[i]];
		}
		[ary[low],ary[j]] = [ary[j],ary[low]];
		return j;
	}

	function quickSort(ary,low,high) {
		// body...
		if (low >= high) {return}
		var index = partition(ary,low,high);
		quickSort(ary,low,index-1);
		quickSort(ary,index+1,high);
	}