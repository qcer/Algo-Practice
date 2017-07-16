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
						if (tmpNode.lChild !== null && tmpNode.rChild !== null) {
							var maxHeight = Math.max(tmpNode.rChild.height,tmpNode.lChild.height);
							if (tmpNode.height === maxHeight+1) {
								break;
							}else{
								tmpNode.height = maxHeight+1;
							}
						}else{
							tmpNode.height += 1;
						}
						tmpNode = tmpNode.parent;
					}
				};
				static traversalPre(binNode){
					if (binNode == null) {return}
					BinNode.visitNode(binNode);
					BinNode.TravelPre(binNode.lChild);
					BinNode.TravelPre(binNode.rChild);
				};
				static traversalMid(binNode){
					if (binNode == null) {return}
					BinNode.TravelMid(binNode.lChild);
					BinNode.visitNode(binNode);
					BinNode.TravelMid(binNode.rChild);
				};
				static traversalPost(binNode){
					if (binNode == null) {return}
					BinNode.TravelAfter(binNode.lChild);
					BinNode.TravelAfter(binNode.rChild);
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
						BinNode.updateHeight(newNode);
					}
					return rootNode;
				}
			}