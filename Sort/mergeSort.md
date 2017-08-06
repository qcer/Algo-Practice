### **归并排序**
#### **JS实现**
#### ** 一、自顶向下版本**

    function merge(ary,low,mid,high) {
        // body...
        var aux = [],
            i = low,
            j = mid+1;
        for (let k = low; k <= high; k++) {
            aux[k] = ary[k];
        }
        for (let m = low; m <= high; m++) {
            if (i > mid) {ary[m] = aux[j++]}
            else if (j > high) {ary[m] = aux[i++]}
            else if (aux[i] <= aux[j]) {ary[m] = aux[i++]}
            else  {ary[m] = aux[j++]}
        }
    }
    function mergeSort(ary,low,high) {
        // body...
        if (low >= high) {return}//递归终止条件
        var mid = low+parseInt((high-low)/2);
        mergeSort(ary,low,mid);
        mergeSort(ary,mid+1,high);
        merge(ary,low,mid,high);
    }

注：

1、递归版本的递归终止条件不能遗漏。

2、算法流程为首先将大数组分割，一直切分为n个单元素的数组，单素的数组递归的调用mergeSort()会直接从递归终止条件退出。然后开始调用merge()开始两两合并。这种实现又称为自顶向下的归并算法。

3、merge()中的mid更准确的含义为**左子数组尾元素的下标**，故也可表示mid = start+left_length-1。其中left_length为左子数组的长度。且左子数组的长度始终为1、2、4、8
。。。

3、实际上merge()首先将两个长度为1的子数组合并，再将两个长度为2的子数组合并。也即长度为1的子数组不会进入merge()函数，而是直接由递归终止条件返回。递归的思想，可证明能够正确的将数组排序。

4、在merge()函数中

    else if (aux[i] <= aux[j]) {ary[m] = aux[i++]}
代码中，小于等于条件保证了归并排序是稳定的。

#### **二、自底向上版本（非递归版）**

    function merge(ary,low,mid,high) {
        // body...
        var aux = [],
            i = low,
            j = mid+1;
        for (let k = low; k <= high; k++) {
            aux[k] = ary[k];
        }
        for (let m = low; m <= high; m++) {
            if (i > mid) {ary[m] = aux[j++]}
            else if (j > high) {ary[m] = aux[i++]}
            else if (aux[i] <= aux[j]) {ary[m] = aux[i++]}
            else  {ary[m] = aux[j++]}
        }
    }
    function mergeSort(ary) {
        // body...
        var length = ary.length;
        var mid = 0;
        for (let size = 1; size < length; size+=size) {
            for (let start = 0; start < length-size ; start+=2*size) {
                mid = start+size-1;
                merge(ary,start,mid,Math.min(start+2*size-1,length-1));
            }
        }
    }

**总结：**

递归版本实现好理解，同时代码更容易编写，不容易出错。迭代版本不太好理解，也容易将边界条件写错。

**测试数组：**

    ary = [9,12,78,10,30,8,20];
**排序过程：**

    [9, 12, 78, 10, 30, 8, 20]
    [9, 12, 10, 78, 30, 8, 20]
    [9, 10, 12, 78, 30, 8, 20]
    [9, 10, 12, 78, 8, 30, 20]
    [9, 10, 12, 78, 8, 20, 30]
    [8, 9, 10, 12, 20, 30, 78]
