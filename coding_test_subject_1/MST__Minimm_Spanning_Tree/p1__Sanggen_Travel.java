package MST__Minimm_Spanning_Tree;
import java.util.*;
import java.io.*;
//���� ��ü�� �߸��� ���� - ��� ����ź '����'�� �ƴ϶� �� '����'�� ����⸦ ���°� ����� �ִ�. �׸��� �̰� ����׷����� �־�����. ����
//����׷������ �ƽó���? ��� �����̶� �� �������� �� ���� �����ϰ� �Ǵ� �׷����Դϴ�. �ݴ븻�� �񿬰�׷������ �ٸ� �������� ���� ���ϴ� ��찡 ����� �׷����Դϴ�.
//�����׷������ �ƽó���? ���� ��� �������� ���� ��� ������ �����մϴ�.
/*����Ʈ���� ������ ������ '��� �׷���'���� '���� ���� ������ �������� ��� ������ ������ �Ǵ� Ʈ��'�� �̾��� ���̱⿡ 
 *�̸� �̿��ؼ� '�պ�'�� �ʿ��� �߿��� ������ �������ش�.*/

//9372�� - ������� ���� 
public class p1__Sanggen_Travel {
	static int N, M;
	static ArrayList< ArrayList<Integer> > childList;
	//=======================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			childList = new ArrayList<>();
			for(int i=0; i<=N; i++)
				childList.add(new ArrayList<>());
			
			for(int z=0; z<M; z++) {
				 st = new StringTokenizer(br.readLine());
				 int a = Integer.parseInt(st.nextToken());
				 int b = Integer.parseInt(st.nextToken());
				 childList.get(a).add(b);
				 childList.get(b).add(a);
			}
			
			System.out.println(N-1); //�ᱹ�� Ʈ���μ� ��� ������ ���Ÿ� Ʈ���� ������ '����'��ŭ�� �ָ� �ȴ�.
			
			
			
			//��� �Ʒ� ����� �������ΰ��� �ƴϴ�. �׷��� ���߳� ���� ��ü�� �� ���߳�. ���������� ������ �����ѻ���
			/*boolean[] visit = new boolean[N+1];
			int result = 0;
			Queue<Integer> q = new LinkedList<>();
			q.add(1);
			visit[1] = true;
			while(!q.isEmpty()) {
				result++;
				int val = q.poll();
				for(int i:childList.get(val)) {
					if(visit[i]==false) {
						visit[i]=true;
						q.add(i);
					}
				}
			}
			
			System.out.println(result-1);
			*/
		}
	}//=======================================================

}
