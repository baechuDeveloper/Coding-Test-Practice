package Tree;
import java.io.*;
import java.util.*;
/**
 * DFS ����� �̿��ؼ� �۾��� �߰�, ���� ���� ���������� �κ�Ʈ���� ��Ʈ�� �����ϸ鼭 �̳༮�� ��Ʈ�϶� ������ִ� �ִ�Ÿ��� ���ذ��鼭
 * max�� �������ش�. ���� �̰� ������ Ʈ���� ��Ʈ���� ��Ʈ�� ���� ���� ���� ���� ���� �� ���̸� �ڽ��� ȣ���� �� ��忡�� �Ѱ��ش�.
 * �׷� �� ����带 ��Ʈ�� �����ϴ� �κ�Ʈ������ �� �ڽ��� ��Ʈ�϶� ���� ���� ���� ���� ���� �� ���̸� ���ؼ� �ڽ��� ȣ���� �� ��忡�� �Ѱ��ش�.
 * �̷��� max ���Ű� ���� ��Ʈ���� ���� ���������� �Ÿ��� ���� �� ���� �����ִ°��� 1����Ʈ���� ������ �ϸ� �ȴ�.
 *
 * max�� �����ϴ� ����� ������ ����.
 * ���� ��Ʈ���� �ڽĳ�尡 1���̸� max������ �� ��忡 ���ؼ� �����ָ� �ǰ�, 2���̻� �϶� ���� �� ���� 2��'��' �����ָ�ȴ�.
 * ��Ʈ�� �ϼ��� �׸��� ���� �˰����� ������ ��Ʈ�� �߰����� �ؼ� ���� ���� ���� �ٸ� ���� ���� ��带 �̾��ִ°� ���� �� �Ÿ��� ������ִ� ����̴�.
 * �ٽ� ���ϸ� Ʈ���� ����(���� �Ʒ��� ���� �־����)���� �ٸ� ������ ���̸� ���ϴ� ���� ���� �� �Ÿ��� ���ϴ� ���̰�, ������ 1���̸� �ڱ� �ڽ��� �����̶�� �����ؼ� 1���ϋ��� �����ָ� �ȴ�.

 * �������� max�� �����ϴ� ����̰� �ڱ� �ڽ��� �θ� ��忡�� �Ѱ��� ���� �������� ���� �� �Ÿ� �ϳ��� �Ѱ��ָ� �ȴ�. Ʈ�� �׸��� ���� �˰�����
 * �ᱹ ������ �����ϴ� �����ε�, ��� �������� �ִ°� �� ������ ���� ���� �� ���� �Ѱ��ִ� ���̴�.
 * **/
// 1967�� - Ʈ���� ����
public class p2__Tree_Dist {
    static int N, max=0;
    static ArrayList< ArrayList<Node> > childList;
    //=================================================
    static class Node {
        int val, dist;
        Node(int a, int b){
            val=a;dist=b;
        }
    }//=================================================
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); //��� ����

        childList = new ArrayList<>(); //��Ʈ ��ȣ�� 1�̴�.
        for(int i=0; i<=N; i++)
            childList.add(new ArrayList<Node>() );

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            childList.get(parent).add(new Node(child, dist));
        }

        dfs(1);
        System.out.println(max);
    }//=================================================
    // �۾� ����
    static int dfs(int now) {
        ArrayList<Node> childNode = childList.get(now);
        int count = childNode.size();
        if(count==0) {
            //System.out.println(now+"���� 0�Դϴ�.");
            return 0;
        }

        int [] childDist = new int[count];
        for(int i=0; i<count; i++){
            Node next = childNode.get(i);
            childDist[i] = dfs(next.val)+next.dist;
        }

        //���� ū ���� �������ش�. ���� �� ��带 head�� �����ؼ� ������ִ� �ִ�Ÿ��� �����ش�.
        Arrays.sort(childDist);
        int ret = childDist[0];
        if(count >= 2)
            ret = childDist[count-1]+childDist[count-2];

        if(max<ret)
            max=ret;
        //System.out.print(now+"���� "+childDist[count-1]+"�Դϴ�.");
        //System.out.print(" ������ "+count+"�� �Դϴ�.");
        //System.out.println(" max�� "+max+" �Դϴ�.");
        return childDist[count-1];
    }//=================================================
}
