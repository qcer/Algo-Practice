## **求中序遍历下的后继节点**

**前驱节点**

    function getPredeNode(node) {
        // body...
        if (node === null) {return null};
        if (node.lChild !== null) {
            node = node.lChild;
            while(node.rChild !== null){
                node = node.rChild;
            }
        }else{
            while(node.parent.lChild === node){
                node = node.parent;
                if (node.parent === null) {break}
            }
            node = node.parent;
        }
        return node;
    }


**后继节点**
    function getSuccNode(node){
        if (node === null) {return null};
        if (node.rChild !== null) {
            node = node.rChild;
            while(node.lChild !== null){
                node = node.lChild;
            }
        }else{
            while(node.parent.rChild === node){
                node = node.parent;
                if (node.parent === null) {break}
            }
            node = node.parent;
        }
        return node;
    }
