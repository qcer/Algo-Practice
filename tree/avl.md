    class Node{
                constructor(data,parent=null,lChild=null,rChild=null,height=0,balFactor=0){
                    this.data = data;
                    this.parent = parent;
                    this.lChild = lChild;
                    this.rChild = rChild;
                    this.height = height;
                    this.balFactor = balFactor;
                }

                // class method
                //快捷方法
                static visitNode(node){
                    console.log(node.data,'height: '+node.height,'balFactor: '+node.balFactor);
                };
                static hasLeftChildOnly(node){
                    if(node !== null){
                        return node.lChild !== null && node.rChild === null; 
                    }
                }
                static hasRightChildOnly(node){
                    if(node !== null){
                        return node.rChild !== null && node.lChild === null; 
                    }
                }
                static hasChild(node){
                    if(node !== null){
                        return node.rChild !== null || node.lChild !== null; 
                    }
                }
                static hasBothChildren(node){
                    if(node !== null){
                        return node.rChild !== null && node.lChild !== null; 
                    }else{
                        return false;
                    }
                }
                static hasNoChild(node){
                    if (node !== null) {
                        return node.lChild === null && node.rChild === null;
                    }
                }
                static isLeftChild(node){
                    if (node.parent !== null) {
                        return node.parent.lChild === node;
                    }
                }
                static isRightChild(node){
                    if (node.parent !== null) {
                        return node.parent.rChild === node;
                    }
                }
                static isBalance(node){
                    return node.balFactor > -2 && node.balFactor < 2;
                }

                //更新方法
                static updateHeight(node){
                    var tmpNode = node;
                    while(tmpNode !== null){
                        if (this.hasBothChildren(tmpNode)) {
                            tmpNode.height = Math.max(tmpNode.rChild.height,tmpNode.lChild.height) + 1;
                        }else if(this.hasLeftChildOnly(tmpNode)){
                            tmpNode.height = tmpNode.lChild.height + 1;
                        }else if (this.hasRightChildOnly(tmpNode)) {
                            tmpNode.height = tmpNode.rChild.height + 1;
                        }else{
                            tmpNode.height = 0;
                        }
                        tmpNode = tmpNode.parent;
                    }
                };
                static updateBalFactor(node){
                    var tmpNode = node;
                    while(tmpNode !== null){
                        if (this.hasBothChildren(tmpNode)) {
                            tmpNode.balFactor = tmpNode.lChild.height - tmpNode.rChild.height;
                        }else if(this.hasLeftChildOnly(tmpNode)){
                            tmpNode.balFactor = tmpNode.lChild.height + 1;
                        }else if (this.hasRightChildOnly(tmpNode)) {
                            tmpNode.balFactor = -(tmpNode.rChild.height + 1);
                        }else{
                            tmpNode.balFactor = 0;
                        }
                        tmpNode = tmpNode.parent;
                    }
                };

                //遍历方法
                static traversalPre(node){
                    if (node == null) {return}
                    Node.visitNode(node);
                    Node.traversalPre(node.lChild);
                    Node.traversalPre(node.rChild);
                };
                static traversalMid(node){
                    if (node == null) {return}
                    Node.traversalMid(node.lChild);
                    Node.visitNode(node);
                    Node.traversalMid(node.rChild);
                };
                static traversalPost(node){
                    if (node == null) {return}
                    Node.traversalPost(node.lChild);
                    Node.traversalPost(node.rChild);
                    Node.visitNode(node);
                };

                //其他特性方法
                static getSuccNode(node){
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

                static reConstruct34(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3){
                    minNode.lChild = subtree0;
                    if (subtree0 !== null) {subtree0.parent = minNode};
                    minNode.rChild = subtree1;
                    if (subtree1 !== null) {subtree1.parent = minNode};

                    maxNode.lChild = subtree2;
                    if (subtree2 !== null) {subtree2.parent = maxNode};
                    maxNode.rChild = subtree3;
                    if (subtree3 !== null) {subtree3.parent = maxNode};

                    midNode.lChild = minNode;
                    minNode.parent = midNode;

                    midNode.rChild = maxNode;
                    maxNode.parent = midNode;

                    return midNode;
                }
                static reBalanceOfInsert(rootNode,node){
                    var tmpNode = node.parent.parent;
                    var aux = [node,node.parent];
                    while(tmpNode !== null){
                        aux.push(tmpNode);
                        if (Node.isBalance(tmpNode)) {
                            tmpNode = tmpNode.parent;
                        }else{
                            var nodeG = aux.pop();
                            var nodeP = aux.pop();
                            var nodeV = aux.pop();

                            var oldparent = nodeG.parent;
                            if (Node.isLeftChild(nodeP)) {
                                if (Node.isLeftChild(nodeV)) {
                                    var localRoot = Node.reConstruct34(nodeV,nodeP,nodeG,nodeV.lChild,nodeV.rChild,nodeP.rChild,nodeG.rChild);
                                }else{
                                    var localRoot = Node.reConstruct34(nodeP,nodeV,nodeG,nodeP.lChild,nodeV.lChild,nodeV.rChild,nodeG.rChild);
                                }

                            }else{
                                if (Node.isLeftChild(nodeV)) {
                                    var localRoot = Node.reConstruct34(nodeG,nodeV,nodeP,nodeG.lChild,nodeV.lChild,nodeV.rChild,nodeP.rChild);
                                }else{
                                    var localRoot = Node.reConstruct34(nodeG,nodeP,nodeV,nodeG.lChild,nodeP.lChild,nodeV.lChild,nodeV.rChild);
                                }
                            }
                            
                            localRoot.parent = oldparent;
                            if (oldparent === null) {
                                rootNode = localRoot;
                            }else{
                                if (oldparent.data > localRoot.data) {
                                    oldparent.lChild = localRoot;
                                }else{
                                    oldparent.rChild = localRoot;
                                }
                            }

                            Node.updateHeight(localRoot.lChild);
                            Node.updateHeight(localRoot.rChild);

                            Node.updateBalFactor(localRoot.lChild);
                            Node.updateBalFactor(localRoot.rChild);
                        }
                    }
                    return rootNode;
                }

                static reBalanceOfRemove(rootNode,node){
                    while(node !== null){
                        if (this.isBalance(node)) {
                            node = node.parent;
                        }else{
                            var nodeG = node;
                            var nodeP = null;
                            var nodeV = null;
                            var oldparent = nodeG.parent;
                            if (node.balFactor > 1) {
                                nodeP = node.lChild;
                                if (nodeP.lChild !== null) {
                                    nodeV = nodeP.lChild;
                                    var localRoot = Node.reConstruct34(nodeV,nodeP,nodeG,nodeV.lChild,nodeV.rChild,nodeP.rChild,nodeG.rChild);
                                }else{
                                    nodeV = nodeP.rChild;
                                    var localRoot = Node.reConstruct34(nodeP,nodeV,nodeG,nodeP.lChild,nodeV.lChild,nodeV.rChild,nodeG.rChild);
                                }
                            }else{
                                nodeP = node.rChild;
                                if (nodeP.lChild !== null) {
                                    nodeV = nodeP.lChild;
                                    var localRoot = Node.reConstruct34(nodeG,nodeV,nodeP,nodeG.lChild,nodeV.lChild,nodeV.rChild,nodeP.rChild);
                                }else{
                                    nodeV = nodeP.rChild;
                                    var localRoot = Node.reConstruct34(nodeG,nodeP,nodeV,nodeG.lChild,nodeP.lChild,nodeV.lChild,nodeV.rChild);
                                }
                            }
                            localRoot.parent = oldparent;
                            if (oldparent === null) {
                                rootNode = localRoot;
                            }else{
                                if (oldparent.data > localRoot.data) {
                                    oldparent.lChild = localRoot;
                                }else{
                                    oldparent.rChild = localRoot;
                                }
                            }
                            Node.updateHeight(localRoot.lChild);
                            Node.updateHeight(localRoot.rChild);

                            Node.updateBalFactor(localRoot.lChild);
                            Node.updateBalFactor(localRoot.rChild);

                            rootNode = this.reBalanceOfRemove(rootNode,localRoot.parent);
                        }
                    }
                    return rootNode;
                }
                //查找
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
                //插入
                /*
                答疑:
                插入后搜寻失衡节点集为什么应该至少从该节点的祖父节点而不是其父节点开始呢？
                因为其父节点那要么为叶节点，要么为单分支的节点
                如果其父节点为叶节点，插入后，平衡因子为+1或者-1，任然平衡。
                如果其父节点为单分支节点，插入前的平衡因子必为+1或者-1，插入后其平衡因子必为0；


                答疑：
                为什么节点插入的局部调整后局部的平衡即可保证全局的平衡呢？
                因为在插入节点之前，以G节点为根的局部子树的高度和插并后重构之后的局部子树的高度实际上是没有变的，
                正式由于调整前后局部子树的高度不变形，不会影响G的祖先节点的平衡性。

                */
                static insert(rootNode,target){
                    var tmpNode = rootNode;
                    var newNode = new Node(target);
                    while(true){
                        if (tmpNode.data === target) {
                            newNode = tmpNode;
                            break;
                        }else if (tmpNode.data < target) {
                            if (tmpNode.rChild === null) {
                                tmpNode.rChild = newNode;
                                newNode.parent = tmpNode;
                                break;
                            }else{
                                tmpNode = tmpNode.rChild;
                            }
                        }else{
                            if (tmpNode.lChild === null) {
                                tmpNode.lChild = newNode;
                                newNode.parent = tmpNode;
                                break;
                            }else{
                                tmpNode = tmpNode.lChild;
                            }
                        }
                    }
                    this.updateHeight(newNode);
                    this.updateBalFactor(newNode);
                    return newNode;
                }


                //节点的删除
                /*
                答疑：
                删除节点为什么失衡节点集中最多包含一个节点呢？
                如果X的祖先链中无节点失衡，则全局无失衡节点，全树任然保持平衡。
                如果X的祖先链中有一个节点失衡G，则该节点是唯一的失衡节点。
                因为对于具体到删除节点的策略，正真被删除的节点实际上或者是页节点，或者是但有一个孩子的单分支节点。
                G失衡具体只能是从-1-》-2失衡或者从1-》2失衡，G节点的深度 = MAX{h_left,h_right},无论哪种失衡情况，删除的节点只能位于较小高度的子树中，因此节点G暂时的树高不变，因此G的祖先节点必然是平衡的。
                但是删除节点存在失衡传播的情况。

                答疑：
                为什么删除节点会存在失衡传播的情况？
                因为局部的调整后可能会导致局部子树的高度降低，如果原先该局部子树刚好作为一棵较低深度的子树，则失衡会传递到其祖先节点。正因为删除节点存在失衡传播的现象，因此需要沿着祖先链逐层网上修复。在代码的实现上表现为递归调用重平衡函数。
                */
                static remove(rootNode,target){
                    var tmpNode = rootNode;
                    while(tmpNode !== null){
                        if (tmpNode.data === target) {
                            var tmpParent = tmpNode.parent;
                            if (tmpNode.rChild !== null && tmpNode.lChild !== null) {
                                var succNode = this.getSuccNode(tmpNode);
                                tmpNode.data = succNode.data;
                                tmpNode = tmpNode.rChild;//目标节点迭代
                                target = succNode.data;//目标数据迭代
                            }else{
                                var replaceNode = null;
                                if (tmpNode.lChild === null) {//这里包含了节点左右孩子均为null的情况
                                    replaceNode = tmpNode.rChild;
                                }else{
                                    replaceNode = tmpNode.lChild;
                                }
                                //更新其父节点的孩子指针
                                if (tmpNode.parent === null) {//如果当前节点是根节点
                                    rootNode = replaceNode;
                                }else{
                                    if (tmpNode.parent.data > target) {
                                        tmpNode.parent.lChild = replaceNode;
                                    }else{
                                        tmpNode.parent.rChild = replaceNode;
                                    }
                                }
                                //更新其后继节点的parent指针
                                if (replaceNode !== null) {
                                    replaceNode.parent = tmpNode.parent;
                                }
                                this.updateHeight(tmpParent);
                                this.updateBalFactor(tmpParent);
                                rootNode = this.reBalanceOfRemove(rootNode,tmpParent);
                                console.log(rootNode.data);
                                break;
                            }
                        }else if (tmpNode.data < target) {
                            tmpNode = tmpNode.rChild;
                        }else{
                            tmpNode = tmpNode.lChild;
                        }
                    }
                    return rootNode;//remove操作很有可能会改变树根节点的引用，由于js函数是传值调用，需要返回新的树根引用。
                }

                //创建AVL树
                static createAVLTree(ary){
                    var rootNode = new Node(ary.shift());
                    while(ary.length !== 0){
                        var newNode = Node.insert(rootNode,ary.shift());
                        rootNode = this.reBalanceOfInsert(rootNode,newNode);
                        // console.log(rootNode);
                    }
                    return rootNode;
                }   
            }

    var ary = [15,1,3,7,12,20,9,30,35,40]
    // var ary = [3,1,15,7]
    // var rootNode = BinNode.createBinSearchTree(ary);
    var rootNode = BinNode.createAVLTree(ary);
    console.log(rootNode);
    BinNode.traversalMid(rootNode);

    // var flag = BinNode.isRightChild(BinNode.search(rootNode,35));
    // BinNode.insert(rootNode,32);
    BinNode.remove(rootNode,1);
    console.log('-----');
    BinNode.traversalMid(rootNode);