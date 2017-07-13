	static LCAofBST(rootNode,dataOne,dataTwo){
		if (dataOne > dataTwo) {
			[dataOne,dataTwo] = [dataTwo,dataOne];
		}
		var tmpNode = rootNode;
		while(true){
			if (tmpNode.data < dataOne) {
				tmpNode = tmpNode.rChild;
			}else if (tmpNode.data > dataTwo) {
				tmpNode = tmpNode.lChild;
			}else{
				break;
			}
		}
		return tmpNode;
	}

