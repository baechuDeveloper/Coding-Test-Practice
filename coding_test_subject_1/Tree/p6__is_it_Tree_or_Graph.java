package Tree;
import java.util.*;
import java.io.*;

/**-----------------------------------------------------------------------------
 * �׷����� ������ �������� �մ� ������� �̷���� �����̴�.
 * ���� ���� ���� �ִ� ������ �ִٸ� �ش� ������ �ٸ� � �������� '�ϳ��� �׷���'�� �ƴϴ�.
 * �� �ش� ������ �ϳ��� �׷����� �ȴ�.
 * ���� �׷����� �ڽŰ� ����Ǿ��ִ� ������ �����̶�� ���� �ȴ�. �׷��� ���� ������ �������� ���� �׷����� �ƴϴ�.
 * ���� ���⼱ '�׷������� ����Ŭ�� ���� ���'�� 'Ʈ��'��� ������ش�. 
 -----------------------------------------------------------------------------**/
/**----------------------------------
 * Ʈ����� ��� ��带 ���� �������� �ξ Ʈ���� �ȴ�.
 *
 * https://www.crocus.co.kr/630
 ------------------------------------**/

// 4803�� - Ʈ��
public class p6__is_it_Tree_or_Graph{
    static int n, m;
    static int tcase=0;
    //main�Լ�
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n==0 && m==0) break;
            ArrayList< ArrayList<Integer> > childList = new ArrayList<>();
            for(int i=0; i<=n; i++)
                childList.add(new ArrayList<>());

            //���� �Է�
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                childList.get(a).add(b);
                childList.get(b).add(a);
            }

            Queue<Integer> q = new LinkedList<>();
            boolean[] visit = new boolean[n+1];
            int cnt = 0;
            //�ϳ��� ��� Ȥ�� ����Ŭ�� ���� V, E�� �� �� �ְ� �ȴ�.
            /*��带 �ϳ��� �������鼭 ���� ������ ������ ���غ��� �����̴�.*/
            for(int i=1; i<=n; i++) {
                //�湮�� �� ���� ���� �湮
                int edge = 0;
                int vertex = 0;
                if(visit[i] == false){
                    cnt++;  //�켱 Ʈ���� �����Ѵٰ� �����ϰ� ����
                    q.add(i);

                    /*���⼭ �ش� ���� ����� ��� ��带 ã�ƺ��� �װ��� �ϳ��� Ʈ���� �����ֵ��� �̹� �湮�� ������ �˷��ش�.*/
                    while(!q.isEmpty()) {
                        int here = q.poll();

                       if(visit[here])
                            continue;
                        vertex++;   //�湮�� ������ ������ �÷��ش�.
                        visit[here] = true;

                        //����� �׷����� ������ �߰��ȴ�.
                        int size = childList.get(here).size();
                        for(int j=0; j<size; j++){
                            edge++; //�����ϴ� ������ ������ŭ �÷��ش�.
                            q.add(childList.get(here).get(j));  //������ �� ������ �˷��ش�.
                        }
                    }

                    //Ʈ���̱� ���ؼ��� '����/2 == ����-1' �̾�� �Ѵ�.
                    if(edge/2 != vertex-1)
                        cnt--;  //�츮�� Ʈ����� �����Ѱ��� ��� Ʈ���� �ƴϾ���!
                }
            }

            if(cnt>=2)
                bw.write("Case "+(++tcase)+": A forest of "+cnt+" trees.\n");
            else if(cnt==1)
                bw.write("Case "+(++tcase)+": There is one tree.\n");
            else
                bw.write("Case "+(++tcase)+": No trees.\n");
        }
        bw.flush();
    }//==================================================
}
