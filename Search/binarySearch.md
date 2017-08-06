### **二分查找**
#### **JS实现**
#### **一、递归版本**
    function binarySearch(ary,low,high,target) {
        // body...
        if (low > high) {return -1}//递归终止条件
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

注：

1、递归版本为尾递归，故一定可以优化为迭代的形式。

2、当递归到2元组时，如果二元组中没有目标元素，则下一步递归会变成一个递归终止点和一个单元素。原因在于mid占用了一个比较位置。
当递归到单个元素时，如果该元素不是目标元素，则该点不是递归的终止点，单个元素的下一步会变成两个递归终止点。