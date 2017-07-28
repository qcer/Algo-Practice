## **求中序遍历下的后继节点**

**前驱节点**

	function getPredeNode(node) {
		// body...
		var tmpNode = null;
		if (node === null) {return null};
		if (node.lChild !== null) {
			tmpNode = node.lChild;
			while(tmpNode.rChild !== null){
				tmpNode = tmpNode.rChild;
			}
			return tmpNode;
		}else{
			if (node.parent.rChild === node) {
				return node.parent;
			}else{
				tmpNode = node.parent
				while(true){
					if (tmpNode.parent === null) {return null;}
					if (tmpNode.parent.rChild === tmpNode) {break;}
					tmpNode = tmpNode.parent;
				}
				return tmpNode.parent;
			}
		}
	}


**后继节点**
	function getSuccNode(node){
		var tmpNode = null;
		if (node === null) {return null};
		if (node.rChild !== null) {
			tmpNode = node.rChild;
			while(tmpNode.lChild !== null){
				tmpNode = tmpNode.lChild;
			}
			return tmpNode;
		}else{
			if (node.parent.lChild === node) {
				return node.parent;
			}else{
				tmpNode = node.parent
				while(true){
					if (tmpNode.parent === null) {return null;}
					if (tmpNode.parent.lChild === tmpNode) {break;}
					tmpNode = tmpNode.parent;
				}
				return tmpNode.parent;
			}
		}
	}
