### **求二叉搜索树最邻近节点**

    function getNearestNode(rootNode,target){
        var tmpNode = rootNode;
        while(true){
            if (tmpNode.data === target) {
                return tmpNode;
            }else if (tmpNode.data < target) {
                if (tmpNode.rChild === null) {
                    var succNode = getSuccNode(tmpNode);
                    if (succNode !== null) {
                        return target - tmpNode.data>succNode.data - target?succNode:tmpNode;
                    }else{
                        return tmpNode;
                    }
                }
                tmpNode = tmpNode.rChild;
            }else{
                if (tmpNode.lChild === null) {
                    var predNode = getPredeNode(tmpNode);
                    if (predNode !== null) {
                        return tmpNode.data - target>target - predNode.data?predNode:tmpNode;
                    }else{
                        return tmpNode;
                    }
                }
                tmpNode = tmpNode.lChild;
            }
        }
    }