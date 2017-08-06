### **如果二叉树中节点没有指向其父亲的指针*，求两节点的最近公共祖先*

    function LCAofGZ(rootNode,node1,node2){
        console.log(rootNode);
        if (rootNode === null || node1 === null || node2 === null) {
            return null;
        }
        if (rootNode === node1 || rootNode == node2) {
            return rootNode;
        }
        var left = LCAofGZ(rootNode.lChild,node1,node2);
        var right = LCAofGZ(rootNode.rChild,node1,node2);
        if (left !== null && right !== null) {
            return rootNode;//最近公共祖先
        }
        return left === null?right:left;
    }

注：
递归的过程就是逐层向上传递的过程。