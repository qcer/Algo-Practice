#### **希尔排序**

##### **JS实现**
	function shellSort(ary) {
		// body...
		var length = ary.length;
		var h = 1;
		while(h < parseInt(length/3)){
			h = 3*h + 1;
		}
		console.log(h);
		while(h >= 1){
			for (let i = h; i < length; i++) {
				for (let j = i; j >= h ; j-=h) {
					if (ary[j] < ary[j-h]) {
						[ary[j],ary[j-h]] = [ary[j-h],ary[j]];
					}else{
						break;
					}
				}
			}
			h = parseInt(h/3);
		}
	}