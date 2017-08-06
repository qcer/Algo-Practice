### **随机构造二叉树**
**给定艺数组，随机构造二叉树**

    function createBinTreeOfRandom(ary){
        var rootNode = new Node(ary.pop());
        var tmpNode = null;
        while(ary.length > 0){
            var newNode = new this(ary.pop());
            tmpNode = rootNode;
            while(true) {
                if(Math.random() > 0.5){
                    if (tmpNode.lChild === null) {
                        tmpNode.lChild = newNode;
                        break;
                    }else{
                        tmpNode = tmpNode.lChild;
                    }
                }else{
                    if (tmpNode.rChild === null) {
                        tmpNode.rChild = newNode;
                        break;
                    }else{
                        tmpNode = tmpNode.rChild;
                    }
                }
            }
        }
        return rootNode;
    }