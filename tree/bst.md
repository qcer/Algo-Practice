	class BinNode{
				constructor(data,parent=null,lChild=null,rChild=null,height=0){
					this.data = data;
					this.parent = parent;
					this.lChild = lChild;
					this.rChild = rChild;
					this.height = height;
				}

				// class method
				static visitNode(binNode){
					console.log(binNode.data);
				};
				static updateHeight(binNode){
					var tmpNode = binNode.parent;
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

				static createBinTree(ary){
					var rootNode = new BinNode(ary.pop());
					var tmpNode = null;

					while(ary.length !== 0){
						var newNode = new BinNode(ary.pop());
						tmpNode = rootNode;
						while(true) {
							if(Math.random() > 0.5){
								if (tmpNode.lChild === null) {
									tmpNode.lChild = newNode;
									console.log(tmpNode.data,newNode.data,'left');
									break;
								}else{
									tmpNode = tmpNode.lChild;
								}
							}else{
								if (tmpNode.rChild === null) {
									tmpNode.rChild = newNode;
									console.log(tmpNode.data,newNode.data,'right');
									break;
								}else{
									tmpNode = tmpNode.rChild;
								}
							}
						}
					}
					return rootNode;
				}
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

				static succ(rootNode,targetNode){
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
				static remove(rootNode,target){
					var targetNode = BinNode.search(rootNode,target);
					if (targetNode !== null) {
						if (targetNode.rChild === null && targetNode.lChild === null) {
							if (targetNode.parent.data > target) {
								targetNode.parent.lChild = null;
							}else{
								targetNode.parent.rChild = null;
							}
						}else if (targetNode.rChild !== null && targetNode.lChild === null) {
							if (targetNode.parent.data > target) {
								targetNode.rChild.parent = targetNode.parent;
								targetNode.parent.lChild = targetNode.rChild;
							}else{
								targetNode.rChild.parent = targetNode.parent;
								targetNode.parent.rChild = targetNode.rChild;
							}
						}else if (targetNode.rChild === null && targetNode.lChild !== null) {
							if (targetNode.parent.data > target) {
								targetNode.lChild.parent = targetNode.parent;
								targetNode.parent.lChild = targetNode.lChild;
							}else{
								targetNode.lChild.parent = targetNode.parent;
								targetNode.parent.rChild = targetNode.lChild;
							}
						}else{
							var succNode = BinNode.succ(rootNode,targetNode);
							targetNode.data = succNode.data;
							BinNode.remove(targetNode.rChild,succNode.data);
						}
					}
				}						
			}

			
	var ary = [15,1,7,3,20,30,40,35,12,9];
	var rootNode = BinNode.createBinSearchTree(ary);
	BinNode.traversalMid(rootNode);

	BinNode.remove(rootNode,7);