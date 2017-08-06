### **堆排序**
#### **JS实现**
    function sink(ary,k,end) {
        // body...
        var j = 0;
        while(true){
            j = 2*k;
            if (j > end) {break;}
            if (j < end && ary[j] < ary[j+1]) {j++;}//注意考虑单个子节点的情况,j为连个子节点中较大元素的下标
            if (ary[j] > ary[k]) {
                [ary[j],ary[k]] = [ary[k],ary[j]];
            }else{
                break;
            }
            k = j;
        }
    }
    function heapSort(ary) {
        // body...
        var end = ary.length-1;
        for(let k = parseInt(end/2);k>0;k--){//创建大根堆，a[0]不参与建堆
            sink(ary,k,end);
        }
        console.log(ary);
        while(end>0){//a[0]不参与排序
            [ary[1],ary[end]] = [ary[end],ary[1]];
            sink(ary,1,end-1);
            end--;
        }
    }
注：

1、调整sink()函数部分代码实现为

    if (j < end && ary[j] > ary[j+1]) {j++;}//j为子节点中较小元素的下标
    if (ary[j] < ary[k]) {
        [ary[j],ary[k]] = [ary[k],ary[j]];
    }else{
        break;
    }.
即可构造小根堆，同样可以方便实现递减排序。