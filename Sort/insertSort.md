### **插入排序**
#### **JS实现**

**版本一：平方复杂度交换**

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


**版本二：线性复杂度交换**

    function insertSort(ary) {
        // body...
        var length = ary.length;
        var tmp = 0,
            index = 0;
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


注：

1、外层循环起始位置为1，内层循环的起始位置为i(因为内层循环是要确定i的位置)，并且内存循环向左遍历，即是j--。

2、内层循环的判断条件

    if (ary[j] < ary[j-1]) {
        [ary[j],ary[j-1]] = [ary[j-1],ary[j]];
    }
    else{
        break;
    }
决定了插入排序是稳定的排序。

**测试数组：**

    ary = [9,2,1,10,30,8,20];
**排序过程：**

    [2, 9, 1, 10, 30, 8, 20]
    [1, 2, 9, 10, 30, 8, 20]
    [1, 2, 9, 10, 30, 8, 20]
    [1, 2, 9, 10, 30, 8, 20]
    [1, 2, 8, 9, 10, 30, 20]
    [1, 2, 8, 9, 10, 20, 30]
    [1, 2, 8, 9, 10, 20, 30]