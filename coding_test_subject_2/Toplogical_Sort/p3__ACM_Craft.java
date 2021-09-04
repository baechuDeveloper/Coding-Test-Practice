package Toplogical_Sort;
import java.io.*;
import java.util.*;
/**
 * �������� ������ ������� ���𰡸� ������ �� ���̱� ���� �������
 * DP(��¼�� ���� �޸������̼�)�� �̿��ϴµ� DP�� ���� Ȯ�� ���� ���� indegree�� 0�ΰ��� ť�� ���������� ���̻� �ش� ������ ������ ��찡 �����Ƿ�
 * �� ���� dp���� ��������. 
 * �츮�� �̷��� ���� �����ϰ� ���� �ǹ��� ���� �� dp���� ���� �ǹ� ����� ���Ͽ� ���� ���� ������ �������ָ� �ȴ�.  
 * */
// 1005�� - ACM Craft
public class p3__ACM_Craft {
	
	static int N, K, W;
	static int[] devtime;
	static int[] indegree;
	static ArrayList< ArrayList<Integer> > edge;
	static Queue<Integer> q;
	static int[] dp;			//�ǹ̰� �߿��ѵ� �ǹ����� ���ÿ� ���� �� ������, ����Ǵ� �ð��� ���߾� i�ǹ��� �ϼ��ϴµ� �ɸ��� �ð��̴�. 
								//��, �ʱ���� �ڽ��� �ǹ��� �ɶ����� '����ǰ��ִ� �ð�'�� ������ �ñ�(����)�� ���Ѵ�.
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//�ǹ��� ����, �ǹ��� ��ȣ�� 1������
			K = Integer.parseInt(st.nextToken());	//�ǹ����� �Ǽ�������Ģ ����
			devtime = new int[N+1];
			
			//�ǹ� �Ǽ��� �ɸ��� �ð��� �־��ش�.
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) 
				devtime[i] = Integer.parseInt(st.nextToken());
			
			//�ǹ����� ������ �־��ش�.
			indegree = new int[N+1];
			edge = new ArrayList<>();
			for(int i=0; i<=N; i++)
				edge.add(new ArrayList<>());
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edge.get(from).add(to);
				indegree[to]++;
			}
			
			//�Ǽ��� �Ϸ��ؾ��� �ǹ� ��ȣ
			W = Integer.parseInt(br.readLine());
			dp = new int[N+1];
			
			/*�������� �˰���*/
			q = new LinkedList<>();
			for(int i=1; i<=N; i++) {
				if(indegree[i]==0) {
					dp[i] = devtime[i];	//ó���� �����°� �ڱ� �ڽŸ� ������ �ȴ�. 
					q.add(i);
				}
			}
			
			for(int cnt=1; cnt<=N; cnt++) {
				if(q.isEmpty()) 
					return;	//��� ��� �ǹ��� �Ǽ� �����ϵ��� �־����Ƿ� �� ���ǹ��� �� ���� ����.
				
				int now = q.poll();
				
				if(now == W) {	//indegree�� 0�� �� ���·� ���Դٴ� ���� ���̻� dp[W]�� ������ ��찡 ������ �˼��ִ�. 
					bw.write(dp[W]+"\n");
					break;
				}
				for(int next : edge.get(now)) {
					indegree[next]--;
					
					int temp = dp[now] + devtime[next];
					if(dp[next]<temp)
						dp[next] = temp;	//�ּ��� �ð��� ���϶�µ� �� �� ū �� �־�� �ϴ��ĸ�! �ǹ��� ���ÿ� ������ �־��. dp[next]�� �����ϴ� �͵��� ��� next�� �������ؼ� �ݵ�� ���ľ��ϴ� �ǹ����� �ȴ�. 
											//���� ��� �ǹ����� �� Ŀ���ؾ��ϴµ� �̶� ���� �����ϴ� �ð��� ���� ���ÿ� �ǹ��鵵 ������ �Ǵ� ��Ȳ�̴�.
											//�̶� ���� �ð����� dp[next]�� �����ϴ� �͵� �� ���� �ð��� ���� ���� ���ٸ� �̰ͺ��� �ð��� ���� �͵��� ��� �����ؼ� �ڱ� �ǹ����� �ϼ���Ű�� �ð������ �ϼ��ȴ�.  
											//�׷����Ͽ� �ʱ���� '����ǰ� �ִ� �ð��� ������ ����'�� �־�� �ϰ�  �׷����� �̷��� ���� ���� �ɸ��� ��츦 �Ѵٸ� �� �ǹ��� �ϼ��ϴµ� '�����ϴ� �ð��� ������ ����'�� �ȴ�.
					if(indegree[next]==0) 
						q.add(next);		//��� ��ᰡ �غ� �Ǿ��ٸ�... �� �ǹ��� ���� ���� ����� ÷���ؼ� ���� ����� �ϼ���Ų��.					
				}
				
			}/*---�������� �˰��� ����---*/
		}
		bw.flush();

	}//========================================================

}
