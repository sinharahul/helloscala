package algo.careercup;

import java.util.*;

/**
 * https://www.careercup.com/question?id=5725353829990400
 * Got this in amazon assessment interview
 * Given a 2 dimensional matrix where some of the elements are filled with 1 and rest of the elements
 * are filled. Here X means you cannot traverse to that particular points.
 * From a cell you can either traverse to left, right, up or down
 * Given two points in the matrix find the shortest path
 * between these points
 *
 * For example if the matrix is
 * 1 1 1 1 1
 * S 1 X 1 1
 * 1 1 1 1 1
 * X 1 1 E 1
 * 1 1 1 1 X
 *
 * Here S is the starting point and E is the Ending point
 */
class Node {
    String value;
    int x;
    int y;
    Node predecessor;
    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
public class ShortestPathFromSourceToDest {
    public static void main(String[] args) {
        List<List<String>> matrix=createProblemMatrix();
        ShortestPathFromSourceToDest s=new ShortestPathFromSourceToDest();
        List<Node> l=s.shortestPath(matrix,matrix.size(),matrix.get(0).size(),"S","E");
        l.forEach(s1->System.out.println(s1));
    }

    private static List<List<String>> createProblemMatrix() {
        List<List<String>> list = new ArrayList<>();
        List<String> r1= Arrays.asList("S","1","1","1","1");
        list.add(r1);
        List<String> r2= Arrays.asList("1","1","X","1","1");
        list.add(r2);
        List<String> r3= Arrays.asList("1","1","1","1","1");
        list.add(r3);
        List<String> r4= Arrays.asList("X","1","1","1","1");
        list.add(r4);
        List<String> r5= Arrays.asList("1","1","1","1","E");
        list.add(r5);
        return list;
    }

    List<Node> shortestPath(List<List<String>> matrix,int numberRows,int numberCols,String source,String dest){
        List<Node> path=new ArrayList<>();
        Node[][] arr=createMatrix(matrix,numberRows,numberCols);
        Node sourceNode=findNode(source,arr,numberRows,numberCols);
        Node destNode=findNode(dest,arr,numberRows,numberCols);
        bfs(arr,sourceNode,destNode);
        Stack<Node> stack=new Stack<>();
        while(destNode!=null){
            stack.push(destNode);
            destNode=destNode.predecessor;
        }
        while(!stack.isEmpty()){
            path.add(stack.pop());
        }
        return path;
    }

    private void bfs(Node[][] arr,Node sourceNode, Node destNode) {
        Queue<Node> queue=new LinkedList<>();
        queue.add(sourceNode);
        sourceNode.value="0";
        while(!queue.isEmpty()){
            Node c=queue.poll();
            c.value="0";
            if(c.equals(destNode)){
                return;
            }else {
                Node left=getNode(arr,c.x-1,c.y);
                markNode(queue, left,c);
                Node right=getNode(arr,c.x+1,c.y);
                markNode(queue, right,c);
                Node top=getNode(arr,c.x,c.y-1);
                markNode(queue, top,c);
                Node bottom=getNode(arr,c.x,c.y+1);
                markNode(queue, bottom,c);
            }
        }
    }

    private void markNode(Queue<Node> queue, Node left,Node pred) {
        if(left!=null){
            left.value="0";
            left.predecessor=pred;
            queue.add(left);

        }
    }

    private Node getNode(Node[][] arr, int i, int j) {
        int rows=arr.length;
        int cols=arr[0].length;
        if(i>=0 && i<rows && j>=0 && j<cols && !arr[i][j].value.equals("X" )
                && !arr[i][j].value.equals("0" )){
            return arr[i][j];
        }
        return null;
    }

    private Node findNode(String node,Node[][] arr,int numberRows,int numberCols) {
        for(int i=0;i<numberRows;i++){
            for(int j=0;j<numberCols;j++){
                if(arr[i][j].value.equals(node)){
                    return arr[i][j];
                }
            }
        }
        return null;
    }

    private Node[][] createMatrix(List<List<String>> matrix,int numberRows,int numberCols) {
        Node[][] arr =new Node[numberRows][numberCols];
        for(int i=0;i<matrix.size();i++){
            List<String> l=matrix.get(i);
            for(int j=0;j<l.size();j++){
                Node n=new Node();
                n.value=l.get(j);
                n.x=i;n.y=j;
                arr[i][j]=n;
            }
        }
        return arr;
    }
}
