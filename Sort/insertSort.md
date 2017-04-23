### **插入排序**
#### **JS实现**


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
