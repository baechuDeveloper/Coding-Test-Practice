package Tree;

import java.util.*;
import java.io.*;

/** ���� ������ � ��尡 ������ �� ����,�������� ���� ������ ������ �� ���ֳ� ȣȣ **/

// 2263�� - Ʈ���� ��ȸ
public class p4__Tree_Order {
    //Ŭ���� ����
    static int root;                         /*root�� �ش��ϴ� ��*/
    static int[] inOrder, postOrder;         /*��������� ���������� ������� ����ִ�.*/
    static Map<Integer, Integer> inorderMap; /* < ���������� ��, inOrder�迭���� ��ġ > */
    static boolean[] complete;               /*�ѹ� ���� �۾��� �Ǿ����� */
    static Node[] node;                      /*�� ���� �ش��ϴ� ���� ���� ���� */
    static StringBuilder answer;             /*�������꿡 �̿�� ��Ʈ������ */
    //======================================================
    //main�Լ�
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        inOrder = new int[N + 1];
        postOrder = new int[N + 1];
        inorderMap = new HashMap<>();
        complete = new boolean[N + 1];
        /*���� ����� ���� ������� �Է¹޴´�.*/
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inorderMap.put( inOrder[i], i );  // <inOrder�� �� , �� ������ġ>
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            postOrder[i] = Integer.parseInt(st.nextToken());

        /*��带 �̸� ������ش�.*/
        node = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            node[i] = new Node(i);

        /*���������� ���� ���������� �۾��� �����Ѵ�. ���� �������� ��Ʈ�̴�.*/
        root = postOrder[N];
        int whereBefore = 0;
        whereBefore = inorderMap.get(root);
        complete[whereBefore] = true;

        /*���� �����ڸ� ���鼭 �۾� ����*/
        for(int i=N-1; i>=1; i--) {
            //���� �������꿡�� ���� ��������, �� ���� �������꿡�� ��� ��ġ�ϴ��� üũ
            int now = postOrder[i];
            int whereNow = inorderMap.get(now);

            //���⼭ ���� �۾��� ���� ��ġ�� Ȯ���Ͽ� �� �����ʿ� �ִٸ� now���� ���� �����ʿ� �پ�� �Ұ��� ������ �˷��ش�.
            //���������� �����ϸ鼭 ó������ complete[k]���� true�� ���� ���� ������ �ڽ��� �Ǹ� �ȴ�.
            if(whereNow < whereBefore){
                for(int k=whereNow+1; k<=N; k++){
                    if(complete[k]==true) {
                        int parent = inOrder[k];
                        node[parent].leftChild = node[now];
                        complete[whereNow] = true;
                        break;
                    }
                }
            }
            //�� ���ʿ� �ִٸ� now���� ���� ���ʿ� �پ�� �� ���� ������ �˷��ش�.
            //�������� �����ϸ鼭 ó������ complere[k]���� true�� ���� ���� ������ �ڽ��� �Ǹ� �ȴ�.
            else {
                for(int k=whereNow-1; k>=1; k--){
                    if(complete[k]==true) {
                        int parent = inOrder[k];
                        node[parent].rightChild = node[now];
                        complete[whereNow] = true;
                        break;
                    }
                }
            }

            whereBefore = whereNow;
        }//for�� ���� - ��� Ʈ���� ����

        //�������� �۾�
        answer = new StringBuilder();
        preOrder(node[root]);
        bw.write(answer.toString());
        bw.flush();
    }//======================================================
    //��������
    static void preOrder(Node now){
        answer.append(now.val+" ");
        if(now.leftChild!=null)
            preOrder(now.leftChild);
        if(now.rightChild!=null)
            preOrder(now.rightChild);
    }//======================================================
    //��� �ڷᱸ��
    static class Node {
        int val;
        Node leftChild, rightChild;
        Node(int i){
            val = i; leftChild=null; rightChild=null;
        }
    }//======================================================
}
