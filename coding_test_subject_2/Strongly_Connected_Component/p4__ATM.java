package Strongly_Connected_Component;

import java.util.*;
import java.io.*;

/* ��մ� ���� SCC�� ���Ե� ��������� SCC�� ��� ATM�� ������ �� �ִ�. �ּ� �Ÿ��� �ʿ�� ���⿡ �ִ��� ���� ���� �������°� �����Ŵ�. ���� �ѹ� ����� �ٽ� �����Ǵ� �� �ƴϴ�.*/
// 4013�� - ATM
public class p4__ATM {

	static int N, M;
	static int cnt, SN, S, P;	//S�� ������, P�� ������� ���� 
	static int[] dfsn, sn, money;
	static boolean[] finished;
	static Set<Integer> setRest;		//��������� ���� ����
	static ArrayList<ArrayList<Integer>> list;
	static ArrayList<Integer> SCCmoney;			//�� SCC��ȣ�� �´� �� ������ ����ִ�.
	static Deque<Integer> stack;
	//==============================================
	
	static int DFS_SCC(int now) {
		dfsn[now] = ++cnt;
		stack.push(now);
		int result = dfsn[now];
		for(int next:list.get(now)) {
			if(dfsn[next]==0)
				result = Math.min(result, DFS_SCC(next));
			else if(!finished[next])
				result = Math.min(result, dfsn[next]);
		}
		
		if(result == dfsn[now]) {
			int sum = 0;
			while(true) {
				int t = stack.pop();
				finished[t] = true;
				sn[t] = SN;
				sum += money[t];
				if(t == now) break;
			}
			SCCmoney.add(sum);
			SN++;
		}
		
		return result;
	}//==============================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		/*SCC ���� �غ�*/
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = 0; SN = 0;
		dfsn = new int[N+1];
		sn = new int[N+1];
		finished = new boolean[N+1];
		money = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++)
			list.add(new ArrayList<>());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.get(x).add(y);
		}
		
		for(int i=1; i<=N; i++) 
			money[i] = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		setRest = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<P; i++)
			setRest.add(Integer.parseInt(st.nextToken()));
		
		stack = new ArrayDeque<>();
		SCCmoney = new ArrayList<>();		//SCC�� ���� ���� �׼� ����
			
		/*SCC�� ���صд�.*/
		for(int i=1; i<=N; i++) 
			if(dfsn[i]==0) DFS_SCC(i);
		
		ArrayList<ArrayList<Integer>> SCCadj = new ArrayList<>();	//SCC�� ������ �ٸ� SCC�� ����ִ� ����Ʈ
		for(int i=0; i<SN; i++)
			SCCadj.add(new ArrayList<>());
		int SCCstart = -1; 					// �������� �����ϴ� SCC�� ��ȣ
		int[] SCCindegree = new int[SN];	// �� SCC�� indegree
		boolean[] hasRest = new boolean[SN];
		int A_SCC, B_SCC;
		
		for(int now=1; now<=N; now++) {
			A_SCC = sn[now];
			if(now == S) SCCstart = A_SCC;
			if(setRest.contains(now)) hasRest[A_SCC] = true;
			
			for(int next:list.get(now)) {
				B_SCC = sn[next];
				if(A_SCC != B_SCC) {
					if(!SCCadj.get(A_SCC).contains(B_SCC)) { //��� �������� ��� �ȴ�. ������ �ߺ��� �Ǿ��� �Ҷ� �ߺ��� ��ŭ indegree�� �þ���� �׸�ŭ �湮�ؼ� ��������, ������ ���� �湮�̶� sMax�� �� ó���� �����ϰ� ���� ���� MAX�� ���ؼ� ���������� ��� �Ѵ�. 
						SCCadj.get(A_SCC).add(B_SCC);
						SCCindegree[ B_SCC ]++;
					}
				}
			}
		}
		
		/*���������� �غ��Ѵ�.*/
		Queue<Integer> q = new LinkedList<>();
		int[] sMax = new int[SN];	//���⿡ �ش� SCC��ȣ�� ���� ���� ū ���� �����ش�. 
		boolean[] try_pos = new boolean[SN];	//���������� �� SCC�� �����Ҽ� �ִ°�
		
		for(int i=0; i<SN; i++) {
			sMax[i] = SCCmoney.get(i);
			if(i == SCCstart) //���� ������ġ���
				try_pos[i] = true;
			if(SCCindegree[i]==0) 
				q.add(i);
		}
		
		/* ���Ұ� �ִٸ� �� ���������� ���� ����ϴ°�? ��� �����ִ�. ��ųƮ��ó�� �ݵ�� indegree�� 0�� �Ǿ �����ؾ� �ϴ°� �ƴѵ� ���̴�.
		 * �´�. �� ���� �����ϴ�. �ٸ� ���⼭�� ���������� ������� ���������� ���ʿ��� SCC�κ��� �ٰ����� indegree�� �ᱹ try_pos��� ����� ���������� �ü��ִ� ���� �Ǵ��ؼ�
		 * sMax�� ���빰�� ��ȭ���� �ʰ� �ߴ�. ��, �湮�� �Ҽ��ִ�. �ٸ� �湮�� ������ ���ǹ��� ��ȭ�� �����ʰ� indegree���� �ٿ��ִ� �ϸ� ���ָ� �� ���̴�.
		 * ���� ���������� �� ������ ��� ������ ���� ������ ������ �����ϴ�. �̷��� ���ʿ��� �� ��ȭ�� ���� �ʰ� indegree�� �ٿ��ָ� �ǰ�, 
		 * ������ �ݵ�� ����� ��� �������� �ᱹ indegree�� 0�� �Ǿ� ����� ��� ������ �ݵ�� ������ �� �ִ�. 
		 * ����, ������ ���������� ���� ������ ������ indegree�� �ϳ��� ���� �ִ°ɷ� ���̻� �ڱ⿡�� �ð� ������ �˸��� �ִ� ���¿��� �ڽ��� ������ �� �ִ�.
		 * �׷��� �������ķ� ��� ����� �ִ�� ���� �� �� �ְ�, ���ʿ��� �湮������ indegree�� �ٿ��ְ� �ϸ� �ǰ� �̷��� indegree�� �����ִ� ������ �ݵ�� ��� ����� ������ �������� �� �� �ִ�.
		 * 
		 * ���������� ���� sMax�� ������ ������ indegree 0�� �ͺ��� �����ؼ� ���� �׿�������, ���������� ���� ���� ���빰�� sMax�� ������ �÷����� ���ϱ� ������ ���������� �� �� �ִ� SCC�� sMax�� ���빰�� �ø����ִ�.
		 * ��, �̷��� ��� sMax�� ���ؼ��� ���������� ������ SCC�� ���ؼ��� ���ǹ��� ������ ���� ���ÿ� ��������� ������ SCC�� �츮�� �ʿ��� ������ �ȴ�.
		 * */
		
		/*�������� ����*/
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int next : SCCadj.get(now)) {
				//�ڽ��� ���������� ������ �����ϴٸ� next�� ������ ������������ �������� �˸��� sMax�� ���� ��ȭ�� �� �� �ִ�.
				if(try_pos[now]) {
					sMax[next] = Math.max(sMax[next], sMax[now] + SCCmoney.get(next));	//�츮�� �˾ƺ� ������ next�ִ� ���� ������� ���ؿ� now�� ������ next�� ���� SCC ���հ����� ���Ѱ� ���Ѵ�. 
					try_pos[next] = true;	//���������� 
				}
				if(--SCCindegree[next] == 0) 
					q.add(next);
			}
		}
		
		int result = 0;
		for(int i=0; i<SN; i++) {
			if(hasRest[i] && try_pos[i])	//���������� �ü��ִ� SCC�̰�, �ش� SCC�� ��������� �����Ѵٸ�...
				result = Math.max(result, sMax[i]);	//�ش� sMax���� ���������� 
		}
			
		System.out.println(result);	
	}//==============================================

}
