package Tree;
import java.io.*;

//BinaryTree_and_Amazing_BufferedReader

// 5639번 - 이진 검색 트리
public class p5__Binary_Search_Tree{
    /*이진트리 객체*/
    static BinaryTree tree;
    static StringBuilder sb;
    //==========================================================
    /*노드 자료구조*/
    static class Node {
        int val;    //노드의 값
        Node leftChild, rightChild; //왼쪽 오른쪽 자식
        Node(int i){
            val=i; leftChild=null; rightChild=null;
        }
    }//==========================================================
    /*이진트리 클래스*/
    static class BinaryTree {
        /*루트가 되는 노드*/
        Node root=null;
        //-------------------------------
        /*노드를 삽입하는 메소드*/
        public void insertNode(int val){
            Node head = root;
            if(root==null){
                root = new Node(val);
            }
            else{
                while(true) {
                    if (head.val < val) {
                        if (head.rightChild == null) {
                            head.rightChild = new Node(val);
                            break;
                        }
                        else {
                            head = head.rightChild;
                        }
                    }
                    else {
                        if (head.leftChild == null) {
                            head.leftChild = new Node(val);
                            break;
                        }
                        else {
                            head = head.leftChild;
                        }
                    }
                }
            }
        }//-------------------------------
        /*전위 순회을 호출하는 메소드*/
        public void printPostOrder() {
            postOrder(root);
        }//-------------------------------
        /*전위 순회 작업*/
        void postOrder(Node now) {
            if(now.leftChild!=null)
                postOrder(now.leftChild);
            if(now.rightChild!=null)
                postOrder(now.rightChild);
           sb.append(now.val+"\n");
        }//-------------------------------
    }//==========================================================
    /*main 함수*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        tree = new BinaryTree();
        String str = "";
        //전위 순회를 한 결과가 들어온다.
        while( (str=br.readLine()) != null && str.length()!=0 ) {
            int val = Integer.parseInt(str);
            tree.insertNode(val);
        }

        sb = new StringBuilder();
        tree.printPostOrder();
        bw.write(sb.toString());
        bw.flush();
    }//==========================================================
}
