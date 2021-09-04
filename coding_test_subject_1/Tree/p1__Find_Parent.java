package Tree;
import java.util.*;
import java.io.*;
// 11725�� - Ʈ���� �θ� ã��
public class p1__Find_Parent {

    //=================================================================
    //Ʈ���� ���
    static class Node {
        int value;
        Node leftChild;
        Node rightChild;

        Node(int value){
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }//=================================================================
    //����Ž��'Ʈ��'
    static class BinaryTree {
        Node rootNode = null;
        //------------------------------------------------------
        //�ش� ���� ��带 �߰��ϱ�
        void insertNode(int element) {
            if(rootNode == null)
                rootNode = new Node(element);
            else {
                Node head = rootNode;
                Node currentNode;

                while(true) {
                    currentNode = head;

                    if(head.value > element){   //������ ��Ʈ���� ���� ������ �������� Ž���Ѵ�.
                        head = head.leftChild;
                        if(head == null){
                            currentNode.leftChild = new Node(element);
                            break;
                        }
                    }
                    else {  //������ ��Ʈ���� ���� ũ�� ���������� Ž���� �Ѵ�.
                        head = head.rightChild;
                        if(head == null) {
                            currentNode.rightChild = new Node(element);
                            break;
                        }
                    }
                }

            }
        }//------------------------------------------------------
        //�ش� ���� ��带 �����
        boolean removeNode(int element) {
            Node selectNode = rootNode;
            Node parentOfselectNode = null;

            while (selectNode.value != element){
                parentOfselectNode  = selectNode;

                if(selectNode.value > element)
                    selectNode = selectNode.leftChild;
                else
                    selectNode = selectNode.rightChild;

                if(selectNode==null)
                    return false;
            }
            //������� ����� �ڽĳ�尡 ��� ���� ��
            if(selectNode.leftChild == null && selectNode.rightChild == null) {
                if(selectNode == rootNode)
                    rootNode = null;
                else if(selectNode == parentOfselectNode.rightChild)
                    parentOfselectNode.rightChild = null;
                else
                    parentOfselectNode.leftChild = null;
            }
            //������� ��尡 ������ �ڽ� ��常 ���� ��
            else if (selectNode.leftChild == null) {
                if(selectNode==rootNode)
                    rootNode = selectNode.rightChild;
                else if(selectNode == parentOfselectNode.rightChild)
                    parentOfselectNode.rightChild = selectNode.rightChild;
                else
                    parentOfselectNode.leftChild = selectNode.rightChild;
            }
            //������� ��尡 ���� �ڽ� ��常 ���� ��
            else if (selectNode.rightChild == null) {
                if(selectNode==rootNode)
                    rootNode = selectNode.leftChild;
                else if(selectNode == parentOfselectNode.rightChild)
                    parentOfselectNode.rightChild = selectNode.leftChild;
                else
                    parentOfselectNode.leftChild = selectNode.leftChild;
            }
            //������� ����� �ڽ��� ��� �����ϴ� ���
            //������� ����� ���� ����Ʈ���� �ִ� ���� ū �� ��带 �ø��ų�, ������ ����Ʈ���� �ִ� ���� ���� �� ��带 �ø��� �ȴ�.
            //���⼭�� ������ ����� �����Ѵ�.
            else {
                Node parentOfReplaceNode = selectNode; //���� ��� ����� �ڽĳ�� �߿��� ��ü�� ��带 ã�´�.
                Node replaceNode = parentOfReplaceNode.rightChild;  //���� ����� ������ ����Ʈ�� Ž�� ����

                while(replaceNode.leftChild != null) { //������ Ʈ������ ���� ���� ���� ã�� ���� ���� �ڽ� ���� Ž���Ѵ�.
                    parentOfReplaceNode = replaceNode;
                    replaceNode = replaceNode.leftChild;
                }

                if(replaceNode != selectNode.rightChild) {
                    /* ���� ���� ���� �����ϱ� ������ ��ü ����� ���� �ڽ��� �� ��尡 �ȴ�. */
                    parentOfReplaceNode.leftChild = replaceNode.rightChild;
                    /* ��ü�� ����� ������ �ڽ� ��带 ������ ����� ���������� �����Ѵ�. */
                    replaceNode.rightChild = selectNode.rightChild;
                }
                /* ������ ��尡 ��Ʈ ����� ��� ��ü�� ���� �ٲ۴�. */
                if(selectNode == rootNode) {
                    rootNode = replaceNode;
                }
                else if(selectNode == parentOfselectNode.rightChild){
                    parentOfselectNode.rightChild = replaceNode;
                }
                else {
                    parentOfselectNode.leftChild = replaceNode;
                }
                /* ���� ��� ����� ���� �ڽ��� �մ´�. */
                replaceNode.leftChild = selectNode.leftChild;
            }
            return true;
        }//------------------------------------------------------
        //���� ��ȸ
        void inorderTree(Node root, int depth) {
            if (root != null) {
                inorderTree(root.leftChild, depth + 1);
                for (int i = 0; i < depth; i++) {
                    System.out.print("��");
                }
                System.out.println(root.value);
                inorderTree(root.rightChild, depth + 1);
            }
        }//------------------------------------------------------
         //���� ��ȸ
        void postorderTree(Node root, int depth) {
            if (root != null) {
                postorderTree(root.leftChild, depth + 1);
                postorderTree(root.rightChild, depth + 1);
                for (int i = 0; i < depth; i++) {
                    System.out.print("��");
                }
                System.out.println(root.value);
            }
        }//------------------------------------------------------
        //���� ��ȸ
        void preorderTree(Node root, int depth) {
            if (root != null) {
                for (int i = 0; i < depth; i++) {
                    System.out.print("��");
                }
                System.out.println(root.value);
                preorderTree(root.leftChild, depth + 1);
                preorderTree(root.rightChild, depth + 1);
            }
        }//------------------------------------------------------

    }//=================================================================

    //main�Լ�
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); //��� ����
        int[] parent = new int[N+1];    //1����尡 root, root�� �θ�� 0���� ���� ���̶�� ��������.
        boolean[] visit = new boolean[N+1];
        ArrayList< ArrayList<Integer> > list = new ArrayList<>();
        for(int i=0; i<=N; i++)
            list.add(new ArrayList<Integer>() );

        for(int z=0; z<N-1; z++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        // ��Ʈ���� Ʈ���� ������.
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            ArrayList<Integer> childList = list.get(now);

            for(int i : childList){
                if(visit[i] == false) {
                    parent[i] = now;
                    q.add(i);
                    visit[i] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=N; i++)
            sb.append(parent[i]+"\n");
        bw.write(sb.toString());
        bw.flush();
    }//=================================================================
}
