package Tree;
import java.io.*;

//BinaryTree_and_Amazing_BufferedReader

// 5639�� - ���� �˻� Ʈ��
public class p5__Binary_Search_Tree{
    /*����Ʈ�� ��ü*/
    static BinaryTree tree;
    static StringBuilder sb;
    //==========================================================
    /*��� �ڷᱸ��*/
    static class Node {
        int val;    //����� ��
        Node leftChild, rightChild; //���� ������ �ڽ�
        Node(int i){
            val=i; leftChild=null; rightChild=null;
        }
    }//==========================================================
    /*����Ʈ�� Ŭ����*/
    static class BinaryTree {
        /*��Ʈ�� �Ǵ� ���*/
        Node root=null;
        //-------------------------------
        /*��带 �����ϴ� �޼ҵ�*/
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
        /*���� ��ȸ�� ȣ���ϴ� �޼ҵ�*/
        public void printPostOrder() {
            postOrder(root);
        }//-------------------------------
        /*���� ��ȸ �۾�*/
        void postOrder(Node now) {
            if(now.leftChild!=null)
                postOrder(now.leftChild);
            if(now.rightChild!=null)
                postOrder(now.rightChild);
           sb.append(now.val+"\n");
        }//-------------------------------
    }//==========================================================
    /*main �Լ�*/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        tree = new BinaryTree();
        String str = "";
        //���� ��ȸ�� �� ����� ���´�.
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
