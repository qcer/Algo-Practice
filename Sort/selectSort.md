### **选择排序**
#### **一、算法思想**
给定数组为ary，
遍历给定数组，找出最小元素，将其与首元素交换，至此，完成一轮循环；
对剩余元素，重复上述步骤，直到最后一个元素。

两层循环，内循环负责找出最小元素的下标，外循环负责将内循环找出的最小元素放到有序的位置上。

	function selectSort(ary) {
		// body...
		var length = ary.length;
		for (let i = 0; i < length; i++) {
			var min = i;
			for (let j = i+1; j < length; j++) {
				if (ary[min] > ary[j]) {
					min = j;
				}
			}
			[ary[i],ary[min]] = [ary[min],ary[i]];
		}
	}
