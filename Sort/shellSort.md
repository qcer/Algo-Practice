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
注：

1、内层循环的步长为好，故为j-=h。
**测试数组：**

    ary = [9,12,78,10,30,8,20];
**排序过程：**

    h=4
    [9, 8, 20, 10, 30, 12, 78]
    h=1
    [8, 9, 10, 12, 20, 30, 78]
