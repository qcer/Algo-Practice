### **二叉树路径和**

#### **给定一颗二叉树和一个目标值,判断是否存在一条从根节点到叶节点的路径，使其路径之和等于目标值。**


**迭代法实现**

    function findSumOfPath(rootNode,target) {
        // body...
        if (rootNode === null) {return false};
        if (rootNode.lChild === null && rootNode.rChild === null && rootNode.data === target) {return true}else{
            return findSumOfPath(rootNode.lChild,target - rootNode.data) || findSumOfPath(rootNode.rChild,target - rootNode.data);
        }
    }


**回溯法实现**

    function getSumOfPath(rootNode,target) {
        // body...
        if (rootNode === null) {return false};
        var tmpNode = rootNode,
            deep = 1,
            boolen = [],
            sum = 0,
            aux = [];
        boolen[1] = 0;
        var ary = [0,'lChild','rChild','parent']; 
        while(deep >0){
            if (tmpNode.lChild === null && tmpNode.rChild === null) {
                if (sum === target - tmpNode.data) {
                    return true;
                }
                deep--;
                tmpNode = tmpNode.parent;
            }
            else{
                boolen[deep] += 1;
                if (boolen[deep]  < 2) {
                    sum += tmpNode.data;
                }
                aux[deep] = tmpNode;
                //从根节点到叶节点
                while(boolen[deep] < 3){
                    if(tmpNode[ary[boolen[deep]]] === null){
                        boolen[deep]+=1;
                    }else{
                        break;
                    }
                }
                if (boolen[deep] < 3) {
                    tmpNode = tmpNode[ary[boolen[deep]]];
                    deep++;
                    boolen[deep] = 0;
                }else{
                    sum -= aux[deep].data;
                    deep--;
                    tmpNode = aux[deep];
                }
            }
        }
    }

#### **问题升级**
#### **给定一颗二叉树和一个目标值,找出所有是否存在一条从任意节点出发到叶节点的路径，使其路径之和等于目标值。**