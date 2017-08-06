### **翻转二叉树**
翻转二叉树相当于在中序遍历的意义下实现逆序效果。

**递归版本**

    function reverseBinTree(rootNode){
        if (rootNode === Node.nilNode) {return;}
        [rootNode.lChild,rootNode.rChild] = [rootNode.rChild,rootNode.lChild];
        reverseBinTree(rootNode.lChild);
        reverseBinTree(rootNode.rChild);    
    }


**迭代版本**

    function reverseBinTree(rootNode){
        var aux = [rootNode];
        var tmpNode = null; 
        while(aux.length !== 0){
            tmpNode = aux.shift();
            if (tmpNode !== null) {
                if (tmpNode.lChild !== null) {
                    aux.push(tmpNode.lChild);
                }
                if (tmpNode.rChild !== null) {
                    aux.push(tmpNode.rChild);
                }
                [tmpNode.lChild,tmpNode.rChild] = [tmpNode.rChild,tmpNode.lChild];
            }
        }
    }


注：迭代版本实际上是借助中序遍历的算法实现的。
