package Strongly_Connected_Component;
import java.util.*;
import java.io.*;

/** https://m.blog.naver.com/kks227/220802519976
 * 
 * SCC�� �ϼ��� �� SCC�ϳ��� �ϳ��� �������� �� �׷����� �ݵ�� DAG(���� ���ȯ �׷���)�Դϴ�. 
 * �̷��� DAG�� ���� �� �ִٸ� ���������� �� ���� �ֽ��ϴ�. ���� SCC������ �� DAG�� �̿��ؼ� ���������� �� �� �ֽ��ϴ�.
 * �� SCC�� ������ ����� �ϳ��� SCC������ �����ؼ� DAG�� ��Ÿ���� ���Դϴ�. 
 * 
 * ���� ������ŭ�� �������ķ� �Ѿ�� �ʿ��� indegree�� ���� �˾Ƴ��� Ǯ ���� �ֽ��ϴ�.
 * */

// 4196�� - ���̳�
public class p2__Domino {

	static int N, M;	//N�� ���̳� ����, M�� ������ ����
	static int cnt, SN;
	static int[] dfsn, sn;
	static boolean[] finished;
	static ArrayList<ArrayList<Integer>> list;
	static Deque<Integer> stack;
	//=========================================
	
	static int DFS(int now) {
		dfsn[now] = ++cnt;
		stack.push(now);
		int result = dfsn[now];
		for(int next : list.get(now)) {
			if(dfsn[next]==0)
				result = Math.min(result, DFS(next));
			else if(!finished[next])	//���� SCC�� ������ �ȵǾ��ٸ� �ش� �ڷ� ���°� �� ū������ Ȯ�� 
				result = Math.min(result, dfsn[next]);
		}
		
		if(result == dfsn[now]) {
			while(true) {
				int t = stack.pop();
				finished[t] = true;
				sn[t] = SN;
				if(t == now) break;
			}
			SN++;
		}
		
		return result;
	}//=========================================
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));		
		int testcase = Integer.parseInt(br.readLine());
		
		while(testcase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt=0; SN=0;
			dfsn = new int[N+1];
			sn = new int[N+1];
			finished = new boolean[N+1];
			list = new ArrayList<>();
			for(int i=0; i<=N; i++) 
				list.add(new ArrayList<>());
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.get(x).add(y);
			}
			stack = new ArrayDeque<>();
			
			for(int i=1; i<=N; i++) 
				if(dfsn[i]==0) 
					DFS(i);
				
			/* SCC�� ���� ������� DAG�� Ȱ���ؼ� SCC������ indegree���� ���غ���. 
			 * SCCindegree�� ���� �ش� SCC�� ���� ������ ����� �� ������ŭ ������ �˷��ش�.*/
			int[] SCCindegree = new int[SN];	// SCC�� ������ŭ �����ϴ� ������ ����, SCC�� �� �������� �� �׷��� �������� 'SCC�� ��ü ����'���� ���� indegree�� ���� ��Ƶ� ��.
			int A_SCC, B_SCC; 					// A_SCC���� B_SCC�� �������� ���� �����̴�.
			
			for(int now=1; now<=N; now++) {	//��ü �������� ã�ƺ���. 
				A_SCC = sn[now];
				for(int next : list.get(now)) {	
					B_SCC = sn[next];
					//���� �ٸ� �������� ���°� Ȯ�εȴٸ� 
					if(A_SCC != B_SCC) 
						SCCindegree[ B_SCC ]++;	//���� �� SCC�� ������ �������� �� ���·� ����.
				}
			}		
			
			//���⼭ �츮�� �ܼ��� indegree�� 0�� ���� ã���� �ȴ�. 
			int result = 0;
			for(int i=0; i<SN; i++) 
				if(SCCindegree[i]==0) result++;
			
			bw.write(result+"\n");
		}
		bw.flush();
	}//=========================================

}
