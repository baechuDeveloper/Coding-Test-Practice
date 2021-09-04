package Tree;
import java.util.*;
import java.io.*;
// 11725번 - 트리의 부모 찾기
public class p1__Find_Parent {

    //=================================================================
    //트리의 노드
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
    //이진탐색'트리'
    static class BinaryTree {
        Node rootNode = null;
        //------------------------------------------------------
        //해당 값의 노드를 추가하기
        void insertNode(int element) {
            if(rootNode == null)
                rootNode = new Node(element);
            else {
                Node head = rootNode;
                Node currentNode;

                while(true) {
                    currentNode = head;

                    if(head.value > element){   //현재의 루트보다 값이 작으면 왼쪽으로 탐색한다.
                        head = head.leftChild;
                        if(head == null){
                            currentNode.leftChild = new Node(element);
                            break;
                        }
                    }
                    else {  //현재의 루트보다 값이 크면 오른쪽으로 탐색을 한다.
                        head = head.rightChild;
                        if(head == null) {
                            currentNode.rightChild = new Node(element);
                            break;
                        }
                    }
                }

            }
        }//------------------------------------------------------
        //해당 값의 노드를 지우기
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
            //지우려는 노드의 자식노드가 모두 없을 때
            if(selectNode.leftChild == null && selectNode.rightChild == null) {
                if(selectNode == rootNode)
                    rootNode = null;
                else if(selectNode == parentOfselectNode.rightChild)
                    parentOfselectNode.rightChild = null;
                else
                    parentOfselectNode.leftChild = null;
            }
            //지우려는 노드가 오른쪽 자식 노드만 있을 때
            else if (selectNode.leftChild == null) {
                if(selectNode==rootNode)
                    rootNode = selectNode.rightChild;
                else if(selectNode == parentOfselectNode.rightChild)
                    parentOfselectNode.rightChild = selectNode.rightChild;
                else
                    parentOfselectNode.leftChild = selectNode.rightChild;
            }
            //지우려는 노드가 왼쪽 자식 노드만 있을 때
            else if (selectNode.rightChild == null) {
                if(selectNode==rootNode)
                    rootNode = selectNode.leftChild;
                else if(selectNode == parentOfselectNode.rightChild)
                    parentOfselectNode.rightChild = selectNode.leftChild;
                else
                    parentOfselectNode.leftChild = selectNode.leftChild;
            }
            //지우려는 노드의 자식이 모두 존재하는 경우
            //지우려는 노드의 왼쪽 서브트리에 있는 가장 큰 값 노드를 올리거나, 오른쪽 서브트리에 있는 가장 작은 값 노드를 올리면 된다.
            //여기서는 후자의 방법을 선택한다.
            else {
                Node parentOfReplaceNode = selectNode; //삭제 대상 노드의 자식노드 중에서 대체될 노드를 찾는다.
                Node replaceNode = parentOfReplaceNode.rightChild;  //삭제 대상의 오른쪽 서브트리 탐색 지정

                while(replaceNode.leftChild != null) { //오른쪽 트리에서 가장 작은 값을 찾기 위해 왼쪽 자식 노드로 탐색한다.
                    parentOfReplaceNode = replaceNode;
                    replaceNode = replaceNode.leftChild;
                }

                if(replaceNode != selectNode.rightChild) {
                    /* 가장 작은 값을 선택하기 때문에 대체 노드의 왼쪽 자식은 빈 노드가 된다. */
                    parentOfReplaceNode.leftChild = replaceNode.rightChild;
                    /* 대체할 노드의 오른쪽 자식 노드를 삭제할 노드의 오른쪽으로 지정한다. */
                    replaceNode.rightChild = selectNode.rightChild;
                }
                /* 삭제할 노드가 루트 노드인 경우 대체할 노드로 바꾼다. */
                if(selectNode == rootNode) {
                    rootNode = replaceNode;
                }
                else if(selectNode == parentOfselectNode.rightChild){
                    parentOfselectNode.rightChild = replaceNode;
                }
                else {
                    parentOfselectNode.leftChild = replaceNode;
                }
                /* 삭제 대상 노드의 왼쪽 자식을 잇는다. */
                replaceNode.leftChild = selectNode.leftChild;
            }
            return true;
        }//------------------------------------------------------
        //중위 순회
        void inorderTree(Node root, int depth) {
            if (root != null) {
                inorderTree(root.leftChild, depth + 1);
                for (int i = 0; i < depth; i++) {
                    System.out.print("ㄴ");
                }
                System.out.println(root.value);
                inorderTree(root.rightChild, depth + 1);
            }
        }//------------------------------------------------------
         //후위 순회
        void postorderTree(Node root, int depth) {
            if (root != null) {
                postorderTree(root.leftChild, depth + 1);
                postorderTree(root.rightChild, depth + 1);
                for (int i = 0; i < depth; i++) {
                    System.out.print("ㄴ");
                }
                System.out.println(root.value);
            }
        }//------------------------------------------------------
        //전위 순회
        void preorderTree(Node root, int depth) {
            if (root != null) {
                for (int i = 0; i < depth; i++) {
                    System.out.print("ㄴ");
                }
                System.out.println(root.value);
                preorderTree(root.leftChild, depth + 1);
                preorderTree(root.rightChild, depth + 1);
            }
        }//------------------------------------------------------

    }//=================================================================

    //main함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); //노드 개수
        int[] parent = new int[N+1];    //1번노드가 root, root의 부모는 0으로 없는 것이라고 생각하자.
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
        // 루트부터 트리를 만들어간다.
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
