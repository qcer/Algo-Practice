### **快速排序**
#### **JS实现**

    function partition(ary,low,high) {
        // body...
        var pivot = ary[low];
        var i = low,
            j = high+1;
        while(true){
            while(ary[++i] < pivot){
                if (i == high) {break;}//此处的检查必要，当pivot为最大时，从这里break；
            }
            while(ary[--j] > pivot){
                if (j == low) {break;}//此处不是必须的，循环也不可能从此处退出
            }
            if (i >= j) {break;};//此处必须，且相当重要
            [ary[i],ary[j]] = [ary[j],ary[i]];//两指针未相遇
        }
        [ary[low],ary[j]] = [ary[j],ary[low]];
        return j;
    }

    function quickSort(ary,low,high) {
        // body...
        if (low >= high) {return} //递归终止条件
        var index = partition(ary,low,high);
        quickSort(ary,low,index-1);
        quickSort(ary,index+1,high);
    }
注：

1、分割函数中，左右扫描停下来的位置建议为分别为：大于等于基准元素和小于等于基准元素。这样能避免某些恶劣的情况。

2、快排是不稳定的排序，同时是偏好随机的排序算法。

3、分析partition()函数的退出情况（前提是假定数组中没有重复元素）：
1）如果pivot元素刚好是序列最大的元素，循环从   

         if (i == high) {break;}

这里退出。

2）如果pivot元素为当前序列最小元素，循环从

         if (i >= j) {break;};
这里退出。此时，i=j。

3）如果pivot不是最大也不是最小，循环也从

         if (i >= j) {break;};
这里退出。此时i>j;

4）但实际上条件
     if (j == low) {break;}
均不是必须的，可去掉。

**测试数组：**

    ary = [9,12,78,10,30,8,20];
**排序过程：**

    [8, 9, 78, 10, 30, 12, 20]
    [8, 9, 20, 10, 30, 12, 78]
    [8, 9, 12, 10, 20, 30, 78]
    [8, 9, 10, 12, 20, 30, 78]