	class Node{
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
				
				static createBinTree(ary){
					var rootNode = new this(ary.pop());
					var tmpNode = null;
					while(ary.length !== 0){
						var newNode = new this(ary.pop());
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
						this.updateHeight(newNode);
					}
					return rootNode;
				}
			}
**递归版本**

	//前序遍历
	function traversalPre(node){
		if (node == null) {return}
		this.visitNode(node);
		this.TravelPre(node.lChild);
		this.TravelPre(node.rChild);
	};
	//中序遍历
	function traversalMid(node){
		if (node == null) {return}
		this.TravelMid(node.lChild);
		this.visitNode(node);
		this.TravelMid(node.rChild);
	};
	//后序遍历
	function traversalPost(node){
		if (node == null) {return}
		this.TravelAfter(node.lChild);
		this.TravelAfter(node.rChild);
		this.visitNode(node);
	};

**迭代版本**
	
	//前序遍历
	function traversalPre(node) {
		// body...
		var result = [];
		if (node !== null) {
			var aux = [node];
			var tmpNode = null;
			while(aux.length >0){	
				tmpNode = aux.pop();
				if (tmpNode !== null) {
					aux.push(tmpNode.rChild);
					aux.push(tmpNode.lChild);
					result.push(tmpNode.data);
				}
			}
		}
		return result;			
	}

	//中序遍历
	function reachDeepestOfLeft(node,aux) {
		// body...
		while(node !== null){
			aux.push(node);
			node = node.lChild;
		}
		return aux;
	}
	function traversalMid(node) {
		// body...
		var result = [];
		if (node !== null) {
			var aux = [];
			while(true){
				aux = reachDeepestOfLeft(node,aux);
				if (aux.length === 0) {break;}
				node = aux.pop();
				result.push(node.data);
				node = node.rChild;
			}
		}
		return result;			
	}

	//层次遍历
	function traversalLevelOrder(node){
		var aux = [node];
		var tmpNode = null;
		while(aux.length !== 0){
			tmpNode = aux.shift();
			Node.visitNode(tmpNode);
			if (tmpNode.lChild !== null) {
				aux.push(tmpNode.lChild);
			}
			if (tmpNode.rChild !== null) {
				aux.push(tmpNode.rChild);
			}
		}
	}