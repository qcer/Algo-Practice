### **概述**

    红黑树的5点性质：
    1. 每个节点非红即黑
    2. 根节点为黑色
    3. 外部节点为黑色（null节点判定为黑色节点）
    4. 如果一个节点为红色，其子节点为黑色（红色节点为叶节点时也成立，叶节点的子节点为null，null被判定为黑色。）
    5. 从任一外部节点到根节点的沿途，黑节点的数目相等--黑色完美平衡。（这一性质在局部性满足，进而在全局性得到满足）

    红黑树是完美的黑色平衡。（任何叶节点到根节点路径上的黑链接数量是相等的。）


    红黑树和2-3-4树树意义对应的关系。

    红黑树综合了二叉查找树的高效查找和2-3-4树的高效平衡操作，同时易于实现。


    将链接颜色------》节点颜色：
    因为每个节点都只有一条指向自己的链接，可以将指向自己的链接的颜色定义为该节点的颜色（注：根节点为黑色节点。）


### **实现**
    
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
            if (node.parent.lChild === node) {
                return true;
            }else{
                return false;
            }
        }
        static isRightChild(node){
            if (node.parent.rChild === node) {
                return true;
            }else{
                return false;
            }
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
        static getSuccNode(node){
            if (node === this.nilNode) {return this.nilNode};
            if (node.rChild !== this.nilNode) {
                node = node.rChild;
                while(node.lChild !== this.nilNode){
                    node = node.lChild;
                }
            }else{
                while(node.parent.rChild === node){
                    node = node.parent;
                    if (node.parent === this.nilNode) {break}
                }
                node = node.parent;
            }
            return node;
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

        static search(node,target){
            while(node !== null){
                if (node.data === target) {
                    break;
                }else if (node.data < target) {
                    node = node.rChild;
                }else{
                    node = node.lChild;
                }
            }
            return node;
        }
    

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

            //重染色,染色策略为根黑两子红
            midNode.color = false;
            minNode.color = true;
            maxNode.color = true;
            return midNode;
        }

        static fixUpOfInsert(rootNode,node){//能够进入此函数的前提是node必为红色。注意fix的过程中有可能会改变树根的引用
            if (this.isRoot(node)) {
                if (this.isRed(node)) {
                    node.color = false;
                }
            }else{
                var tmpParent = node.parent;
                if (this.isRed(tmpParent)) {//父亲节点为红色
                    var siblingNode = this.getSiblingNode(tmpParent);//父亲节点的兄弟节点，即叔父节点
                    if (this.isRed(siblingNode)) {//叔父节点为红色，对应情况B-1
                        tmpParent.color = false;
                        siblingNode.color = false;
                        tmpParent.parent.color = true;
                        rootNode = this.fixUpOfInsert(rootNode,tmpParent.parent);
                    }else{//叔父节点为红色，对应情况B-2
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
                            if (localRoot.data < oldParent.data) {
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
        /*
        几个要点：
        1、插入新节点的始终为叶节点
        2、局部性的黑高和平衡性得以保持不变，且局部性子树不会影响其他的局部性平衡，则该局部性修复是无后患的。
        3、插入节点有可能会改变根节点的指向。（注：js语言是传值调用的特性使然）
        4、统一定义一个nilNode节点，它也是Node类型的实例，但是其颜色只能是hi黑色，同时作为外部节点（也即作为叶节点的孩子或者根节点的父亲），这样可以方便的实现。
        5、nilNode在删除节点的时候会充当哨兵的作用。
        流程的描述为：
        A、如果插入节点后，其父节点为黑色，则不会破坏全树的平衡性，不必修复。
        B、如果插入的节点后，其父节点为红色，必破坏局部的平衡性，需修复。修复的情况有分两种：
        B-1、节点的叔父为红色，重新颜色，，使其叔父和父亲变变为黑色，祖父的颜色变为红色。局部子树的黑高和平衡性得以满足，但是局部子树的局部根节点为红色了，有可能在更上层的地方影响全局的平衡性。以当前节点的祖父为新的迭代节点，循环递归。
        这种情况只会重染色，但是不会调整局部子树。

        B-2、节点的叔父为黑色，进行3+4重构，重染色，重染色的策略为根黑两子红。
        答疑：
        在这种情况局部调整后的颜色重染策略为什么是PP黑，P红和X红。
        因为这样保证了局部子树的黑色平衡，并且由于保证了新的局部子树不会影响到其他地方放的平衡性，从而达到全局的平衡性。避免递归修复操作。

        */
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
            if (this.isRed(tmpNode)) {//父节点为黑色节点，无需修复
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
                        if (localRoot.data < oldParent.data) {
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
                    if (localRoot.data < oldParent.data) {
                        localRoot.parent.lChild = localRoot;
                    }else{
                        localRoot.parent.rChild = localRoot;
                    }
                }
                rootNode = this.reBalanceOfRemove(rootNode,node);
            }
            return rootNode;
        }


        /*
        由于删除操作实际上的删除落脚点只能是归结为两种情况：删除叶节点、删除单分支节点。
        A：对于删除单分支节点。
        由任意节点的局部黑色平衡的性质可以推断，待删除节点X必为黑色节点，且其孩子分支节点必为红色节点。
        答疑：
        如果待删接待为单分支分支节点，为什么它一定是黑色节点，其孩子为何必为红节点？


        B：删除叶节点
        B-1：待删叶节点为红色节点，直接删。
        B-2：待删叶节点为黑色叶节点，情况复杂，需要平衡的修复。
        具体有四种修复情况：

        B-2-1：待删节点X的X兄弟节点S为黑，且S至少有一个红孩子。
        B-2-2：待删节点X的父节点为红，兄弟节点为黑。
        B-2-4：待删节点X的父节点为黑，兄弟节点S为红色。经过一次调整后，会转移待情况B-2-1或者B-2-3.
        根据节点P的局部平衡性可得知，S节点至少有一个黑色孩子。变幻之后局部子树的黑色平衡仍然保持。
        B-2-4：待删节点X的父节点为黑，兄弟节点S为红色。经过一次调整后，会转移待情况B-2-1或者B-2-3.
        根据节点P的局部平衡性可得知，S节点至少有一个黑色孩子。变幻之后局部子树的黑色平衡仍然保持。

        总结：
        四种情况中，B-2-1、B-2-2都是单次修复局部重平衡并且能保证全局的平衡。
        即使是B-2-4的情况，在两次修复后，一样满足局部重平衡并且能保证全局的平衡。
        只有情况B-2-3的情况失衡会向上传播，此时需要递归的调用修复函数逐层修复。

        四种情况的变幻看起来很复杂，其目除了保证修复后局部平衡，还要尽可能的保证修复前后局部字子树的黑高不变化，因为如果修复前后局部子树的黑高不发生变化，那么该修复动作就不会影响到祖先链的平衡性，从而保证全局的平衡性，达到单次修复即可结束的效果。
        情况B-2-3之所以失衡会向上传播，其根本在于，修复后局部子树的黑高降低了，这样势必会影响其祖先链的平衡向，所以需要递归的调用修复。

        */
        static remove(rootNode,target){
            var tmpNode = rootNode;
            while(tmpNode !== this.nilNode){
                if (tmpNode.data === target) {
                    if (tmpNode.rChild !== this.nilNode && tmpNode.lChild !== this.nilNode) {
                        var succNode = this.getSuccNode(tmpNode);
                        tmpNode.data = succNode.data;
                        tmpNode = tmpNode.rChild;//目标节点迭代
                        target = succNode.data;//目标数据迭代
                    }else{
                        var replaceNode = this.nilNode;
                        if (tmpNode.lChild === this.nilNode) {//这里包含了节点左右孩子均为null的情况
                            replaceNode = tmpNode.rChild;
                        }else{
                            replaceNode = tmpNode.lChild;
                        }
                        //更新其父节点的孩子指针
                        if (tmpNode.parent === this.nilNode) {//如果当前节点是根节点
                            rootNode = replaceNode;
                        }else{
                            if (tmpNode.parent.data > target) {
                                tmpNode.parent.lChild = replaceNode;
                            }else{
                                tmpNode.parent.rChild = replaceNode;
                            }
                        }
                        //更新其后继节点的parent指针
                        replaceNode.parent = tmpNode.parent;
                        // if (replaceNode !== this.nilNode) {replaceNode.parent = tmpNode.parent}
                        break;
                    }
                }else if (tmpNode.data < target) {
                    tmpNode = tmpNode.rChild;
                }else{
                    tmpNode = tmpNode.lChild;
                }
            }
            if (replaceNode === this.nilNode && !this.isRed(tmpNode)) {//删除黑色叶节点
                rootNode = this.reBalanceOfRemove(rootNode,replaceNode);
            }else{
                replaceNode.color = false;
            }
            return rootNode;//remove操作很有可能会改变树根节点的引用，由于js函数是传值调用，需要返回新的树根引用。
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

### **测试用例**
**测试插入节点**

    //测试情况B-2
    var ary = [8,15];
    var rootNode = Node.createRedBlackTree(ary);
    Node.insert(rootNode,30);

    //测试情况B-1
    var ary = [8,15,30];
    var rootNode = Node.createRedBlackTree(ary);
    Node.insert(rootNode,20);

    //测试情况B-2
    var ary = [8,15,30,20,50,18];
    var rootNode = Node.createRedBlackTree(ary);
    Node.insert(rootNode,17);

**测试删除节点**

    //测试情况A
    var ary = [8,15,30,20,50,18];
    var rootNode = Node.createRedBlackTree(ary);
    Node.remove(rootNode,20);

    //测试情况B-1
    var ary = [8,15,30,20,50,18];
    var rootNode = Node.createRedBlackTree(ary);
    Node.remove(rootNode,18);

    //测试情况B-1
    var ary = [8,15,30,20,50,18];
    var rootNode = Node.createRedBlackTree(ary);
    Node.remove(rootNode,18);