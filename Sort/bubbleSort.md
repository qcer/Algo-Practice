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

注：
1、内层循环过程中，当其序列的**首位置始终保持为最小元素**，外层循环每完成一次，当前序列的最小的元素就会冒到当前序列的首位置。所以叫做冒泡排序。

2、内层循环判断交换的条件

    if (ary[i] > ary[j]) {
                [ary[i],ary[j]] = [ary[j],ary[i]];
            }
决定了冒泡排序是非稳定的排序。例如最开始时元素值大于重复元素，将会有重复元素被暂时性的换到前面去。
