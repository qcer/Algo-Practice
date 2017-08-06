    class Node{
        constructor(data,color=true,parent=null,lChild=null,rChild=null){
            this.data = data;
            this.color = color;//true == red,false == black
            this.parent = parent;
            this.lChild = lChild;
            this.rChild = rChild;
        }
        static createNode(data,color=true){
            if (this.nilNode === undefined) {
                this.nilNode = new Node(-1,false);
            }
            return (new Node(data,color,this.nilNode,this.nilNode,this.nilNode));
        }
        static isRoot(node){
            if (node.parent === this.nilNode) {
                return true;
            }else{
                return false;
            }
        }
        static isRed(node){
            return node.color;
        }
        static hasNoChild(node){
            if (node !== this.nilNode) {
                return node.lChild === this.nilNode && node.rChild === this.nilNode;
            }
        }   
        static hasOneChild(node){
            if (node !== this.nilNode) {
                var falg = node.lChild === this.nilNode ^ node.rChild === this.nilNode;
                if (falg === 1) {
                    return true;
                }else{
                    return false;
                }
            }
        }
        static hasLeftChildOnly(node){
            if (node !== this.nilNode) {
                return node.lChild !== this.nilNode && node.rChild === this.nilNode;
            }
        }
        static hasRightChildOnly(node){
            if (node !== this.nilNode) {
                return node.lChild === this.nilNode && node.rChild !== this.nilNode;
            }
        }
        static hasBothChildren(node){
            if (node !== this.nilNode) {
                return node.lChild !== this.nilNode && node.rChild !== this.nilNode;
            }
        }
        static isLeftChild(node){
            return node.parent.data > node.data;
        }
        static isRightChild(node){
            return node.parent.data < node.data;
        }
        static getSiblingNode(node){
            if (node.parent !== this.nilNode) {
                if (this.isLeftChild(node)) {
                    return node.parent.rChild;
                }else{
                    return node.parent.lChild;
                }
            }
        }
        static GetSuccNode(node){//中序遍历下,目标节点的直接后继只能是在父节点或者右子树的最左边节点。
            var tmpNode = this.nilNode;
            if (node.rChild !== this.nilNode) {
                tmpNode = node.rChild;
                while(tmpNode.lChild !== this.nilNode){
                    tmpNode = tmpNode.lChild;
                }
            }else{
                tmpNode = node.parent;
            }
            return tmpNode;
        }
        static visitNode(node){
            console.log(node);
        };

        static traversalPre(rootNode){
            if (rootNode == this.nilNode) {return}
            this.visitNode(rootNode);
            this.traversalPre(rootNode.lChild);
            this.traversalPre(rootNode.rChild);
        };
        static traversalMid(rootNode){
            if (rootNode == this.nilNode) {return}
            this.traversalMid(rootNode.lChild);
            this.visitNode(rootNode);
            this.traversalMid(rootNode.rChild);
        };
        static traversalPost(rootNode){
            if (rootNode == this.nilNode) {return}
            this.traversalPost(rootNode.lChild);
            this.traversalPost(rootNode.rChild);
            this.visitNode(rootNode);
        };

        static search(rootNode,target){
            var tmpNode = rootNode;
            while(tmpNode !== this.nilNode){
                if (tmpNode.data === target) {
                    break;
                }else if (tmpNode.data < target) {
                    tmpNode = tmpNode.rChild;
                }else{
                    tmpNode = tmpNode.lChild;
                }
            }
            return tmpNode;
        };
        static reConstruct23(minNode,maxNode,subtree0,subtree1,subtree2){
            minNode.lChild = subtree0;
            if (subtree0 !== this.nilNode) {subtree0.parent = minNode};
            minNode.rChild = subtree1;
            if (subtree1 !== this.nilNode) {subtree1.parent = minNode};
            maxNode.rChild = subtree2;
            if (subtree2 !== this.nilNode) {subtree2.parent = maxNode};

            maxNode.lChild = minNode;
            minNode.parent = maxNode;

            minNode.color = true;
            maxNode.color = false;
            return maxNode;
        }

        // static reConstruct34(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3){
        //  minNode.lChild = subtree0;
        //  if (subtree0 !== this.nilNode) {subtree0.parent = minNode};
        //  minNode.rChild = subtree1;
        //  if (subtree1 !== this.nilNode) {subtree1.parent = minNode};
        //  maxNode.lChild = subtree2;
        //  if (subtree2 !== this.nilNode) {subtree2.parent = maxNode};
        //  maxNode.rChild = subtree3;
        //  if (subtree3 !== this.nilNode) {subtree3.parent = maxNode};

        //  midNode.lChild = minNode;
        //  minNode.parent = midNode;
        //  midNode.rChild = maxNode;
        //  maxNode.parent = midNode;

        //  midNode.color = true;//其实这个肯定是red
        //  minNode.color = false;
        //  maxNode.color = false;

        //  return midNode;
        // }

        

        static reConstruct34(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3){
            //子树重构
            minNode.lChild = subtree0;
            if (subtree0 !== this.nilNode) {subtree0.parent = minNode};
            minNode.rChild = subtree1;
            if (subtree1 !== this.nilNode) {subtree1.parent = minNode};
            maxNode.lChild = subtree2;
            if (subtree2 !== this.nilNode) {subtree2.parent = maxNode};
            maxNode.rChild = subtree3;
            if (subtree3 !== this.nilNode) {subtree3.parent = maxNode};

            ////节点重构
            midNode.lChild = minNode;
            minNode.parent = midNode;
            midNode.rChild = maxNode;
            maxNode.parent = midNode;

            //颜色重构
            midNode.color = false;
            minNode.color = true;
            maxNode.color = true;
            // midNode.color = true;
            // minNode.color = false;
            // maxNode.color = false;
            return midNode;
        }

        static fixUpOfInsert(rootNode,node){//注意fix的过程中有可能会变根
            if (this.isRoot(node)) {
                if (this.isRed(node)) {
                    node.color = false;
                }
            }else{
                var tmpParent = node.parent;
                if (this.isRed(tmpParent)) {
                    var siblingNode = this.getSiblingNode(tmpParent);
                    if (this.isRed(siblingNode)) {
                        tmpParent.color = false;
                        siblingNode.color = false;
                        tmpParent.parent.color = true;
                        rootNode = this.fixUpOfInsert(rootNode,tmpParent.parent);
                    }else{
                        var oldParent = tmpParent.parent.parent;
                        var localRoot = this.nilNode;
                        var minNode = null;
                        var midNode = null;
                        var maxNode = null;
                        var subtree0 = null;
                        var subtree1 = null;
                        var subtree2 = null;
                        var subtree3 = null;
                        if (this.isLeftChild(tmpParent)) {
                            if (this.isLeftChild(node)) {
                                minNode = node;
                                midNode = tmpParent;
                                maxNode = tmpParent.parent;
                                subtree0 = minNode.lChild;
                                subtree1 = minNode.rChild;
                                subtree2 = midNode.rChild;
                                subtree3 = maxNode.rChild;
                            }else{
                                minNode = tmpParent;
                                midNode = node;
                                maxNode = tmpParent.parent;
                                subtree0 = minNode.lChild;
                                subtree1 = midNode.lChild;
                                subtree2 = midNode.rChild;
                                subtree3 = maxNode.rChild;
                            }
                        }else{
                            if (this.isLeftChild(node)) {
                                minNode = tmpParent.parent;
                                midNode = node;
                                maxNode = tmpParent;
                                subtree0 = minNode.lChild;
                                subtree1 = midNode.lChild;
                                subtree2 = midNode.rChild;
                                subtree3 = maxNode.rChild;
                            }else{
                                minNode = tmpParent.parent;
                                midNode = tmpParent;
                                maxNode = node;
                                subtree0 = minNode.lChild;
                                subtree1 = midNode.lChild;
                                subtree2 = maxNode.lChild;
                                subtree3 = maxNode.rChild;
                            }
                        }
                        localRoot = this.reConstruct34(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3);
                        localRoot.parent = oldParent;
                        if (this.isRoot(localRoot)) {
                            rootNode = localRoot;
                        }else{
                            if (this.isLeftChild(localRoot)) {
                                localRoot.parent.lChild = localRoot;
                            }else{
                                localRoot.parent.rChild = localRoot;
                            }
                        }
                    }
                }
            }
            return rootNode;
        }
        static insert(rootNode,target){
            var newNode = this.createNode(target);
            var tmpNode = rootNode;
            while(true){
                if (tmpNode.data === target) {
                    return true;
                }else if (tmpNode.data < target) {
                    if (tmpNode.rChild === this.nilNode) {
                        tmpNode.rChild = newNode;
                        newNode.parent = tmpNode;
                        break;
                    }else{
                        tmpNode = tmpNode.rChild;
                    }
                }else{
                    if (tmpNode.lChild === this.nilNode) {
                        tmpNode.lChild = newNode;
                        newNode.parent = tmpNode;
                        break;
                    }else{
                        tmpNode = tmpNode.lChild;
                    }
                }
            }
            if (this.isRed(newNode)) {
                rootNode = this.fixUpOfInsert(rootNode,newNode);
            }
            return rootNode;
        }
        static fixUpBB_1(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3){
            minNode.lChild = subtree0;
            if (subtree0 !== this.nilNode) {subtree0.parent = minNode};
            minNode.rChild = subtree1;
            if (subtree1 !== this.nilNode) {subtree1.parent = minNode};
            maxNode.lChild = subtree2;
            if (subtree2 !== this.nilNode) {subtree2.parent = maxNode};
            maxNode.rChild = subtree3;
            if (subtree3 !== this.nilNode) {subtree3.parent = maxNode};

            midNode.lChild = minNode;
            minNode.parent = midNode;
            midNode.rChild = maxNode;
            maxNode.parent = midNode;

            return midNode;
        }
        static fixUpBB_3(minNode,maxNode,subtree0,subtree1,subtree2){
            minNode.lChild = subtree0;
            if (subtree0 !== this.nilNode) {subtree0.parent = minNode};
            minNode.rChild = subtree1;
            if (subtree1 !== this.nilNode) {subtree1.parent = minNode};
            maxNode.rChild = subtree2;
            if (subtree2 !== this.nilNode) {subtree2.parent = maxNode};

            maxNode.lChild = minNode;
            minNode.parent = maxNode;

            [minNode.color,maxNode.color] = [maxNode.color,minNode.color];
            return maxNode;

        }
        static reBalanceOfRemove(rootNode,node){
            var siblingNode = this.getSiblingNode(node);
            var oldParent = siblingNode.parent.parent;
            var localRoot = this.nilNode;
            var minNode = null;
            var midNode = null;
            var maxNode = null;
            var subtree0 = null;
            var subtree1 = null;
            var subtree2 = null;
            var subtree3 = null;
            if (!this.isRed(siblingNode)) {//s节点为黑色
                if (this.isRed(siblingNode.lChild) || this.isRed(siblingNode.rChild)) {//s节点的两孩纸至少有一个为红
                    if (this.isRightChild(node)) {//node是右孩子
                        if (this.isRed(siblingNode.lChild)) {//s的左孩子是红色，包含双红色孩子
                            minNode = siblingNode.lChild;
                            midNode = siblingNode;
                            maxNode = siblingNode.parent;
                            subtree0 = minNode.lChild;
                            subtree1 = minNode.rChild;
                            subtree2 = midNode.rChild;
                            subtree3 = maxNode.rChild;
                        }else{//s的右孩子是红色
                            minNode = siblingNode;
                            midNode = siblingNode.rChild;
                            maxNode = siblingNode.parent;
                            subtree0 = minNode.lChild;
                            subtree1 = midNode.lChild;
                            subtree2 = midNode.rChild;
                            subtree3 = maxNode.rChild;
                        }
                        localRoot = this.fixUpBB_1(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3);
                        localRoot.color = localRoot.rChild.color;
                    }else{//node是左孩子
                        if (this.isRed(siblingNode.lChild)) {
                            minNode = siblingNode.parent;
                            midNode = siblingNode.lChild;
                            maxNode = siblingNode;
                            subtree0 = minNode.lChild;
                            subtree1 = midNode.lChild;
                            subtree2 = midNode.rChild;
                            subtree3 = maxNode.rChild;
                        }else{
                            minNode = siblingNode.parent;
                            midNode = siblingNode;
                            maxNode = siblingNode.rChild;
                            subtree0 = minNode.lChild;
                            subtree1 = midNode.lChild;
                            subtree2 = maxNode.lChild;
                            subtree3 = maxNode.rChild;
                        }
                        localRoot = this.fixUpBB_1(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3);
                        localRoot.color = localRoot.lChild.color;
                    }
                    localRoot.rChild.color = false;
                    localRoot.lChild.color = false;
                    localRoot.parent = oldParent;
                    if (this.isRoot(localRoot)) {
                        rootNode = localRoot;
                    }else{
                        if (this.isLeftChild(localRoot)) {
                            localRoot.parent.lChild = localRoot;
                        }else{
                            localRoot.parent.rChild = localRoot;
                        }
                    }
                }else{//s节点的左右孩子为全黑
                    if (this.isRed(node.parent)) {//node的父亲是红色
                        siblingNode.parent.color = false;
                        siblingNode.color = true;
                    }else{//node的父亲是黑色
                        if (this.isLeftChild(node)) {
                            siblingNode.color = true;
                            rootNode = this.reBalanceOfRemove(rootNode,siblingNode.parent);
                        }
                    }
                }
            }else{//s节点是红色,s的父亲必为黑
                if (this.isLeftChild(node)) {
                    minNode = siblingNode.parent;
                    maxNode = siblingNode;
                    subtree0 = minNode.lChild;
                    subtree1 = maxNode.lChild;
                    subtree2 = maxNode.rChild;
                }else{
                    minNode = siblingNode;
                    maxNode = siblingNode.parent;
                    subtree0 = minNode.lChild;
                    subtree1 = minNode.rChild;
                    subtree2 = maxNode.rChild;
                }
                localRoot = this.fixUpBB_3(minNode,maxNode,subtree0,subtree1,subtree2);
                localRoot.parent = oldParent;
                if (this.isRoot(localRoot)) {
                    rootNode = localRoot;
                }else{
                    if (this.isLeftChild(localRoot)) {
                        localRoot.parent.lChild = localRoot;
                    }else{
                        localRoot.parent.rChild = localRoot;
                    }
                }
                rootNode = this.reBalanceOfRemove(rootNode,node);
            }

            return rootNode;
        }
        static remove(rootNode,target){
            var targetNode = this.search(rootNode,target);
            if (targetNode === this.nilNode) {return false;}
            if (this.hasNoChild(targetNode)) {
                if (this.isRed(targetNode)) {
                    if (this.isLeftChild(targetNode)) {//红色叶节点
                        targetNode.parent.lChild = this.nilNode;
                    }else{
                        targetNode.parent.rChild = this.nilNode;
                    }
                }else{//黑色叶节点，情况很复杂
                    this.nilNode.parent = targetNode.parent;
                    this.nilNode.data = targetNode.data;
                    if (this.isLeftChild(targetNode)) {
                        targetNode.parent.lChild = this.nilNode;
                    }else{
                        targetNode.parent.rChild = this.nilNode;
                    }
                    rootNode = this.reBalanceOfRemove(rootNode,this.nilNode);
                }
            }else if (this.hasLeftChildOnly(targetNode)) {
                targetNode.data = targetNode.lChild.data;
                targetNode.lChild = this.nilNode;
            }else if (this.hasRightChildOnly(targetNode)) {
                targetNode.data = targetNode.rChild.data;
                targetNode.rChild = this.nilNode;
            }else{//因为targetNode有右孩子，其后继必不是其父亲。
                var succNode = this.GetSuccNode(targetNode);
                targetNode.data = succNode.data;
                this.remove(targetNode.rChild,succNode.data);
            }
            return rootNode;
        }

        static createRedBlackTree(ary){
            var rootNode = this.createNode(ary.shift());
            rootNode.color = false;
            while(ary.length !== 0){
                rootNode = this.insert(rootNode,ary.shift());
            }
            return rootNode;
        }
    }