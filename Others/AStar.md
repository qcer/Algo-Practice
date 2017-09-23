**算法原理解析**：

[http://www.cnblogs.com/qcblog/p/7507995.html](http://www.cnblogs.com/qcblog/p/7507995.html)
    
    package agstring;
    import java.util.ArrayList;
    import java.util.*;
    class Node{
        public final int x,y;
        public final double weight;
        public boolean isOpen = true;
        public Node parent;
        public int varF = 0,varG = 0,varH = 0;
        public Node(int x,int y,double weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
        public Node(int x,int y){
            this(x, y, 0.0);
        }
        public String toString(){
            return "("+"x:"+this.x+","+"y:"+this.y+","+"w:"+this.weight+")";
        }
    }
    public class AStar {
        
        public static Node[][] initData(int row,int column){
            Node[][] tableOf2D = new Node[row][column];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    tableOf2D[i][j] = new Node(i,j);
                }
            }
            return tableOf2D;
        }
        private static int getDistance(Node startNode,Node endNode){
            return Math.abs(endNode.x - startNode.x) + Math.abs(endNode.y - startNode.y);
        }
        
        private  static void updateFGH(Node node,Node startNode,Node endNode){
            node.varG = getDistance(startNode, node);
            node.varH = getDistance(node, endNode);
            node.varF = node.varG + node.varH;
        }
        
        public static Node[] AStarAlgo(Node[][] tableOf2D,Node startNode,Node endNode){
            int row = tableOf2D.length,column = tableOf2D[0].length;
            ArrayList<Node> openList = new ArrayList<Node>();
            ArrayList<Node> closeList = new ArrayList<Node>();
            Node nextNode,checkedNode = startNode;
            int minF;
            closeList.add(startNode);
            startNode.isOpen = false;
            while(!checkedNode.equals(endNode)){//!checkedNode.equals(endNode)
                if (checkedNode.y-1 >= 0 && tableOf2D[checkedNode.x][checkedNode.y-1].isOpen ) {
                    nextNode = tableOf2D[checkedNode.x][checkedNode.y-1];
                    nextNode.parent = checkedNode;
                    openList.add(nextNode);
                    updateFGH(nextNode,startNode,endNode);
                    nextNode.isOpen = false;
                }
                if (checkedNode.y+1 <= column-1 && tableOf2D[checkedNode.x][checkedNode.y+1].isOpen ) {
                    nextNode = tableOf2D[checkedNode.x][checkedNode.y+1];
                    nextNode.parent = checkedNode;
                    openList.add(nextNode);
                    updateFGH(nextNode,startNode,endNode);
                    nextNode.isOpen = false;
                }
                if (checkedNode.x-1 >= 0 && tableOf2D[checkedNode.x-1][checkedNode.y].isOpen ) {
                    nextNode = tableOf2D[checkedNode.x-1][checkedNode.y];
                    nextNode.parent = checkedNode;
                    openList.add(nextNode);
                    updateFGH(nextNode,startNode,endNode);
                    nextNode.isOpen = false;
                }
                if (checkedNode.x+1 <= row-1 && tableOf2D[checkedNode.x+1][checkedNode.y].isOpen ) {
                    nextNode = tableOf2D[checkedNode.x+1][checkedNode.y];
                    nextNode.parent = checkedNode;
                    openList.add(nextNode);
                    updateFGH(nextNode,startNode,endNode);
                    nextNode.isOpen = false;
                }
                
                minF = Integer.MAX_VALUE;
                for (int i = 0; i < openList.size(); i++) {
                    if (openList.get(i).varF < minF) {
                        checkedNode = openList.get(i);
                        minF = checkedNode.varF;
                    }
                }
                closeList.add(checkedNode);
                openList.remove(checkedNode);

            }
            return closeList.toArray(new Node[closeList.size()]);
        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            try {
                int row = 5,column = 7;
                Node[][] data = initData(row, column);
    //          for (int i = 0; i < row ; i++) {
    //              for (int j = 0; j < column; j++) {
    //                  System.out.println(data[i][j]);
    //              }
    //          }
    //          data[1][3].isOpen = data[2][3].isOpen = data[3][3].isOpen = data[4][3].isOpen = false;
    //          data[0][5].isOpen = data[1][5].isOpen = data[2][5].isOpen = data[3][5].isOpen = false;
                Node startNode = data[2][1];
                Node endNode = data[0][2];
                Node[] pathAry = AStarAlgo(data,startNode,endNode);

                Node tmpNode = endNode;
                while(!(tmpNode == null)){
                    System.out.println(tmpNode);
                    tmpNode = tmpNode.parent;
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }
