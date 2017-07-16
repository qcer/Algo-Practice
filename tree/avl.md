	class BinNode{
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
					static visitNode(binNode){
						console.log(binNode.data,'height: '+binNode.height,'balFactor: '+binNode.balFactor);
					};
					static hasLeftChildOnly(binNode){
						if(binNode !== null){
							return binNode.lChild !== null && binNode.rChild === null; 
						}
					}
					static hasRightChildOnly(binNode){
						if(binNode !== null){
							return binNode.rChild !== null && binNode.lChild === null; 
						}
					}
					static hasChild(binNode){
						if(binNode !== null){
							return binNode.rChild !== null || binNode.lChild !== null; 
						}
					}
					static hasBothChildren(binNode){
						if(binNode !== null){
							return binNode.rChild !== null && binNode.lChild !== null; 
						}else{
							return false;
						}
					}
					static hasNoChild(binNode){
						if (binNode !== null) {
							return binNode.lChild === null && binNode.rChild === null;
						}
					}
					static isLeftChild(binNode){
						if (binNode.parent !== null) {
							return binNode.parent.lChild === binNode;
						}
					}
					static isRightChild(binNode){
						if (binNode.parent !== null) {
							return binNode.parent.rChild === binNode;
						}
					}
					static isBalance(binNode){
						return binNode.balFactor > -2 && binNode.balFactor < 2;
					}

					//更新方法
					static updateHeight(binNode){
						var tmpNode = binNode;
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
					static updateBalFactor(binNode){
						var tmpNode = binNode;
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
					static traversalPre(binNode){
						if (binNode == null) {return}
						BinNode.visitNode(binNode);
						BinNode.traversalPre(binNode.lChild);
						BinNode.traversalPre(binNode.rChild);
					};
					static traversalMid(binNode){
						if (binNode == null) {return}
						BinNode.traversalMid(binNode.lChild);
						BinNode.visitNode(binNode);
						BinNode.traversalMid(binNode.rChild);
					};
					static traversalPost(binNode){
						if (binNode == null) {return}
						BinNode.traversalPost(binNode.lChild);
						BinNode.traversalPost(binNode.rChild);
						BinNode.visitNode(binNode);
					};

					//其他特性方法
					static succ(rootNode,targetNode){//中序遍历下,目标节点的直接后继只能是在父节点或者右子树的最左边节点。
						var tmpNode = null;
						if (targetNode.rChild !== null) {
							tmpNode = targetNode.rChild;
							while(tmpNode.lChild !== null){
								tmpNode = tmpNode.lChild;
							}
						}else{
							tmpNode = targetNode.parent;
						}
						return tmpNode;
					}
					static rebulid34(minNode,midNode,maxNode,subtree0,subtree1,subtree2,subtree3){
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
					static reBalance(rootNode,binNode){
						var tmpNode = binNode.parent.parent;
						if (tmpNode === null) {return rootNode;}
						var aux = [binNode,binNode.parent];
						while(true){
							aux.push(tmpNode);
							if (BinNode.isBalance(tmpNode)) {
								tmpNode = tmpNode.parent;
								if (tmpNode === null) {return rootNode;}
							}else{
								break;
							}
						}
						// console.log(tmpNode);
						var nodeG = aux.pop();
						var nodeP = aux.pop();
						var nodeV = aux.pop();
						// console.log(nodeG);
						// console.log(nodeP);
						// console.log(nodeV);
						console.log('has g p v');

						var oldparent = nodeG.parent;
						if (BinNode.isLeftChild(nodeP)) {
							if (BinNode.isLeftChild(nodeV)) {
								var localRoot = BinNode.rebulid34(nodeV,nodeP,nodeG,nodeV.lChild,nodeV.rChild,nodeP.rChild,nodeG.rChild);
							}else{
								var localRoot = BinNode.rebulid34(nodeP,nodeV,nodeG,nodeP.lChild,nodeV.lChild,nodeV.rChild,nodeG.rChild);
							}

						}else{
							if (BinNode.isLeftChild(nodeV)) {
								var localRoot = BinNode.rebulid34(nodeG,nodeV,nodeP,nodeG.lChild,nodeV.lChild,nodeV.rChild,nodeP.rChild);
							}else{
								var localRoot = BinNode.rebulid34(nodeG,nodeP,nodeV,nodeG.lChild,nodeP.lChild,nodeV.lChild,nodeV.rChild);
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

						BinNode.updateHeight(localRoot.lChild);
						BinNode.updateHeight(localRoot.rChild);

						BinNode.updateBalFactor(localRoot.lChild);
						BinNode.updateBalFactor(localRoot.rChild);

						return rootNode;
					}


					//创建二叉搜搜树
					static createBinSearchTree(ary){
						var rootNode = new BinNode(ary.shift());
						var tmpNode = null;
						while(ary.length !== 0 ){
							var newNode = new BinNode(ary.pop());
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
							BinNode.updateHeight(newNode);
						}
						return rootNode;
					};

					//查找
					static search(rootNode,target){
						var tmpNode = rootNode;
						while(tmpNode !== null){
							if (tmpNode.data === target) {
								break;
							}else if (tmpNode.data < target) {
								tmpNode = tmpNode.rChild;
							}else{
								tmpNode = tmpNode.lChild;
							}
						}
						return tmpNode;
					}
					//插入
					static insert(rootNode,target){
						var tmpNode = rootNode;
						var newNode = new BinNode(target);
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
					static remove(rootNode,target){
						var targetNode = this.search(rootNode,target);
						if (targetNode === null) {return false;}
						var parent = targetNode.parent;
						if (this.hasNoChild(targetNode)) {
							if (this.isLeftChild(targetNode)) {
								targetNode.parent.lChild = null;
							}else{
								targetNode.parent.rChild = null;
							}
							this.reBalance(rootNode,targetNode.parent);
						}else if (this.hasLeftChildOnly(targetNode)) {
							if (this.isLeftChild(targetNode)) {
								targetNode.lChild.parent = targetNode.parent;
								targetNode.parent.lChild = targetNode.lChild;
							}else{
								targetNode.lChild.parent = targetNode.parent;
								targetNode.parent.rChild = targetNode.lChild;
							}
						}else if (this.hasRightChildOnly(targetNode)) {
							if (this.isLeftChild(targetNode)) {
								targetNode.rChild.parent = targetNode.parent;
								targetNode.parent.lChild = targetNode.rChild;
							}else{
								targetNode.rChild.parent = targetNode.parent;
								targetNode.parent.rChild = targetNode.rChild;
							}
						}else{
							var succNode = this.succ(rootNode,targetNode);
							targetNode.data = succNode.data;
							targetNode = succNode;
							parent = targetNode.parent
							if (this.hasNoChild(targetNode)) {//叶节点
								if (this.isLeftChild(targetNode)) {
									targetNode.parent.lChild = null;
								}else{
									targetNode.parent.rChild = null;
								}
							}else{//有右子树
								if (this.isLeftChild(targetNode)) {
									targetNode.rChild.parent = targetNode.parent;
									targetNode.parent.lChild = targetNode.rChild;
								}else{
									targetNode.rChild.parent = targetNode.parent;
									targetNode.parent.rChild = targetNode.rChild;
								}
							}
						}

						this.updateHeight(parent);
						this.updateBalFactor(parent);

						return true;

					}
					//创建AVL树
					static createAVLTree(ary){
						var rootNode = new BinNode(ary.shift());
						while(ary.length !== 0){
							var newNode = BinNode.insert(rootNode,ary.shift());
							rootNode = this.reBalance(rootNode,newNode);
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