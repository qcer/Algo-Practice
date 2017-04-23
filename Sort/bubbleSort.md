### **冒泡排序**


### **JS实现**

	function bubbleSort(ary) {
		// body...
		var length = ary.length;
		for (let i = 0; i < length; i++) {
			for (let j = i+1; j < length; j++) {
				if (ary[i] > ary[j]) {
					[ary[i],ary[j]] = [ary[j],ary[i]];
				}
			}
		}
	}