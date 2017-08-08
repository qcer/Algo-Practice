	class Node{
				constructor(data,parent=null,lChild=null,rChild=null,height=0){
					this.data = data;
					this.parent = parent;
					this.lChild = lChild;
					this.rChild = rChild;
					this.height = height;
				}

				// class method
				static visitNode(node){
					console.log(node.data);
				};
				static updateHeight(node){
					var tmpNode = node.parent;
					while(tmpNode !== null){
						if (tmpNode.lChild !== null && tmpNode.rChild !== null) {//如果父节点左右子树
							var maxHeight = Math.max(tmpNode.rChild.height,tmpNode.lChild.height);
							if (tmpNode.height === maxHeight+1) {//但是左右子树的高度变化对父节点的高度没有影响
								break;//直接退出循环
							}else{
								tmpNode.height = maxHeight+1;//左右子树的高度变化对父节点的高度有影响,参与下轮循环
							}
						}else{//父节点只有左子树或者右子树,高度+1，参与下轮循环。
							tmpNode.height += 1;
						}
						tmpNode = tmpNode.parent;//迭代循环
					}
				};
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

				//传入节点node和目标值，在以传入节点node为根的子树中搜索目标节点。
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


                //插入节点操作
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
							}
							tmpNode = tmpNode.rChild;
						}else{
							if (tmpNode.lChild === null) {
								tmpNode.lChild = newNode;
								newNode.parent = tmpNode;
								break;
							}
							tmpNode = tmpNode.lChild;
						}
					}
					return newNode;
				}

                /*
                注意：插入新节点需要更新节点的parent指针和其父节点的孩子（lChild或者rChild指针）。
                */


                //删除节点
				static remove(rootNode,target){
                    var tmpNode = rootNode;
                    while(tmpNode !== null){
                        if (tmpNode.data === target) {
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
                                if (replaceNode !== null) {replaceNode.parent = tmpNode.parent}
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
                /*
                删除节点分为3类情况：
                A：删除叶节点，直接用null节点替换即可待删除节点
                B：删除单分支节点，用其左孩子或者右孩子替换待删除节点
                C：删除双分支节点，用其后继节点替换其待删除接待，迭代删除其后继节点。

                其中A和B可以合并为一种情况处理。

                注意点：
                1、删除操作在某些特殊的情况会改变根节点的引用。
                具体的讲，当BST退化为一棵左斜树或者右斜树（当然也包括单节点的树）的情况，如果待删除节点刚好是根节点，此时就会改根节点的引用。

                由于删除操作需要更新待删除节点的父节点的孩子指针，因此在程序处理的时候需要特殊考虑这种情况。
                */


                static createBinSearchTree(ary){
                    var rootNode = new Node(ary.shift());
                    var tmpNode = null;
                    while(ary.length !== 0 ){
                        var newNode = new Node(ary.shift());
                        tmpNode = rootNode;
                        while(true){
                            if (newNode.data > tmpNode.data) {
                                if (tmpNode.rChild === null) {
                                    tmpNode.rChild = newNode;
                                    newNode.parent = tmpNode;
                                    break;
                                }
                                tmpNode = tmpNode.rChild;
                            }else{
                                if (tmpNode.lChild === null) {
                                    tmpNode.lChild = newNode;
                                    newNode.parent = tmpNode;
                                    break;
                                }
                                tmpNode = tmpNode.lChild;
                            }
                        }
                        Node.updateHeight(newNode);
                    }
                    return rootNode;
                };                    
			}


	**测试用例**
    1、左斜树删除根节点
	var ary = [30,20,10];
	var rootNode = Node.createBinSearchTree(ary);
	rootNode = Node.remove(rootNode,30);

    2、删除单节点树
    var ary = [30];
    var rootNode = Node.createBinSearchTree(ary);
    rootNode = Node.remove(rootNode,30);

    3、删除双分支节点
    var ary = [30,20,40];
    var rootNode = Node.createBinSearchTree(ary);
    rootNode = Node.remove(rootNode,30);