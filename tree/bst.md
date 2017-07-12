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
					
				}					
			}

			
	var ary = [15,3,7,1,20,30,40,12,9];
	var rootNode = BinNode.createBinSearchTree(ary);
	BinNode.traversalMid(rootNode);

	BinNode.insert(rootNode,35);
	BinNode.remove(rootNode,7);