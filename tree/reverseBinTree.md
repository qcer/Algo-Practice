### **翻转二叉树**
翻转二叉树相当于在中序遍历的意义下实现逆序效果。

**递归版本**
function reverseBinTree(rootNode){
	if (rootNode === Node.nilNode) {return;}
	[rootNode.lChild,rootNode.rChild] = [rootNode.rChild,rootNode.lChild];
	reverseBinTree(rootNode.lChild);
	reverseBinTree(rootNode.rChild);	
}


**迭代版本**
