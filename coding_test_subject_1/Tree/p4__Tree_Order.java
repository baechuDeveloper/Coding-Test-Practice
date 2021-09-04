package Tree;

import java.util.*;
import java.io.*;

/** 중위 오더는 어떤 헤드가 있으면 그 왼쪽,오른쪽은 서브 하위를 구분해 갈 수있네 호호 **/

// 2263번 - 트리의 순회
public class p4__Tree_Order {
    //클래스 변수
    static int root;                         /*root에 해당하는 값*/
    static int[] inOrder, postOrder;         /*중위연산과 후위연산의 결과값이 들어있다.*/
    static Map<Integer, Integer> inorderMap; /* < 중위연산의 값, inOrder배열에서 위치 > */
    static boolean[] complete;               /*한번 노드로 작업이 되었는지 */
    static Node[] node;                      /*각 값에 해당하는 값을 갖는 노드들 */
    static StringBuilder answer;             /*전위연산에 이용될 스트링버퍼 */
    //======================================================
    //main함수
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        inOrder = new int[N + 1];
        postOrder = new int[N + 1];
        inorderMap = new HashMap<>();
        complete = new boolean[N + 1];
        /*중위 연산과 후위 연산들을 입력받는다.*/
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inorderMap.put( inOrder[i], i );  // <inOrder의 값 , 그 순서위치>
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            postOrder[i] = Integer.parseInt(st.nextToken());

        /*노드를 미리 만들어준다.*/
        node = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            node[i] = new Node(i);

        /*후위연산의 가장 마지막부터 작업을 시작한다. 가장 마지막이 루트이다.*/
        root = postOrder[N];
        int whereBefore = 0;
        whereBefore = inorderMap.get(root);
        complete[whereBefore] = true;

        /*후위 연산자를 돌면서 작업 시작*/
        for(int i=N-1; i>=1; i--) {
            //현재 후위연산에서 값이 무엇인지, 그 값이 중위연산에서 어디에 위치하는지 체크
            int now = postOrder[i];
            int whereNow = inorderMap.get(now);

            //여기서 이전 작업한 것의 위치를 확인하여 더 오른쪽에 있다면 now에서 현재 오른쪽에 붙어야 할곳이 있음을 알려준다.
            //오른쪽으로 진행하면서 처음으로 complete[k]값이 true가 나온 곳에 새로히 자식이 되면 된다.
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
            //더 왼쪽에 있다면 now에서 현재 왼쪽에 붙어야 할 곳이 있음을 알려준다.
            //왼쪽으로 진행하면서 처음으로 complere[k]값이 true가 나온 곳에 새로히 자식이 되면 된다.
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
        }//for문 종료 - 모든 트리를 구성

        //전위연산 작업
        answer = new StringBuilder();
        preOrder(node[root]);
        bw.write(answer.toString());
        bw.flush();
    }//======================================================
    //전위연산
    static void preOrder(Node now){
        answer.append(now.val+" ");
        if(now.leftChild!=null)
            preOrder(now.leftChild);
        if(now.rightChild!=null)
            preOrder(now.rightChild);
    }//======================================================
    //노드 자료구조
    static class Node {
        int val;
        Node leftChild, rightChild;
        Node(int i){
            val = i; leftChild=null; rightChild=null;
        }
    }//======================================================
}
