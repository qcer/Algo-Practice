### **插入排序**
#### **JS实现**

版本一：
	function insertSort(ary) {
		// body...
		var length = ary.length;
		for (let i = 1; i < length; i++) {
			for (let j = i; j > 0; j--) {
				if (ary[j] < ary[j-1]) {
					[ary[j],ary[j-1]] = [ary[j-1],ary[j]];
				}
				else{
					break;
				}
			}
		}
	}


版本二：
	function insertSort(ary) {
		// body...
		var length = ary.length;
		var tmp = 0;
		var index = 0;
		for (let i = 1; i < length; i++) {
			tmp = ary[i];
			index = i;
			for (let j = i;j > 0 ; j--) {
				if (tmp < ary[j-1]) {
					ary[j] = ary[j-1];
					index = j-1;
				}else{
					break;
				}
			}
			ary[index] = tmp;
		}
	}
