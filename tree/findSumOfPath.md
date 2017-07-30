### **二叉树路径和**

#### **给定一颗二叉树和一个目标值,判断是否存在一条从根节点到叶节点的路径，使其路径之和等于目标值。**

	function findSumOfPath(rootNode,target) {
		// body...
		if (rootNode === null) {return false};
		if (rootNode.lChild === null && rootNode.rChild === null && rootNode.data === target) {return true}else{
			return findSumOfPath(rootNode.lChild,target - rootNode.data) || findSumOfPath(rootNode.rChild,target - rootNode.data);
		}
	}


#### **给定一颗二叉树和一个目标值,找出所有是否存在一条从任意节点出发到叶节点的路径，使其路径之和等于目标值。**