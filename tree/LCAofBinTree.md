### **如果二叉树节点中有指向其父亲的指针。求两节点的最近公共祖先**
注：该问题规约为求两条链表的相交节点。

**JS实现**

    function LCAofBinTree(rootNode,node1,node2) {
        // body...
        var aux1 = [],
            aux2 = [],
            tmpNode = node1;
        while(tmpNode !== null){
            aux1.push(tmpNode);
            tmpNode = tmpNode.parent;
        }
        tmpNode = node2;
        while(tmpNode !== null){
            aux2.push(tmpNode);
            tmpNode = tmpNode.parent;
        }
        var cmpNode1 = null,
            cmpNode2 = null,
            anceNode = null;

        while(true){
            if (aux1.length === 0 || aux2.length === 0) {
                return anceNode;
            }
            cmpNode1 = aux1.pop();
            cmpNode2 = aux2.pop();
            if (cmpNode1 !== cmpNode2) {
                return anceNode;
            }
            anceNode = cmpNode1;
        }
    }