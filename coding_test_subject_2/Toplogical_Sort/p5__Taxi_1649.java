package Toplogical_Sort;

import java.util.*;
import java.io.*;

// 1649�� - �ý�
public class p5__Taxi_1649 {
	static int N, M;
	static int S, E, C;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> edge;
	static Set<Integer> set;
	//==========================================
	
	static void topLogicalSort() {
		Queue<Integer> q = new LinkedList<>();
		int[] cnt = new int[N+1];	//���� [i]�� ������ ������ ���� ����� ������
		int[] val = new int[N+1];	//���� [i]�� ������ ������ ������ C1~Ck �������� �ִ� ���� 
		
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) 
				q.offer(i);			//�̰� ��� �ʿ��� �� �������ķ� �����ϱ⿡ '�ر�'�̶�� ������ �ʿ��ѵ� �־��� ��忡�� ��� ���� �������� �ر��ϴµ� ������ ����Ѵ�. ���������� ������ ���ϸ� cnt�� 0�� base�� ����ؼ� ��η� ������ �ȵȴ�.  
		}	
		cnt[S] = 1;	//�����������ʹ� �ٸ� ���鿡�� ���̽��� �Ǿ� ������ �����ش�. 
		
		while(!q.isEmpty()) {
			int now = q.poll();
			//System.out.println("���� "+now+" ");
			if(set.contains(now)) val[now]++;
			if(now==E) break;
			
			for(int next : edge.get(now)) {
				indegree[next]--;
				//System.out.println("	���� "+next+" cnt "+cnt[now]+" "+cnt[next]+" val "+val[now]+" "+val[next]);
				
				//���� �湮�� ���� ���� next���� �ڽſ��� �� ������ �������ش�. next�� �ڽ��� indegree�� ����� ��� ��忡�� ������ �����޴´�. 
				//��� ������ ������ �Ʒ��� ���� 2���� ������ ���ؼ� �޴´�. ���⼭ ���� 'next', ������ ���� 'now'.
				//1. ������ ���� ��尡 ������ ���� C1~Ck�� ������ �� �༮�̶�� �׳༮���� �ҼӵǸ� �ȴ�. 
				//2. ������ ���� ��尡 ���� ���� C1~Ck ������ ������ �Դٸ� ���� ���������� ���������� ������ ���� �� �ִ� C�� ������ �������� �� �����Դٴ� ���� �ȴ�. �׷��⿡ ������ ������ �����ش�. ���� 0�̶� ���������� ���ο��� �����̱⿡ ������ ������ ��ġ�� �ȴ�. 
				//3. �ݴ�� ������ ������ ���ٸ� ������ ������ �������� ���� �� �� ���δ� �Լ��� �� ���� C�� �����ϴٴ� ���̴�. ���� �ش� ���� ���� ���� ������ ��δ� �ڽſ��� �ö� ���������� C�� �������� �˷��ֱ⿡ �����μ� ���Ծ��ϸ� �ȴ�. 
				if(val[now] > val[next]) {
					val[next] = val[now];
					cnt[next] = cnt[now];
				}
				else if(val[now] == val[next]) {
					cnt[next] += cnt[now];
				}
				
				if(indegree[next]==0) {
					//System.out.println("	ť����");
					q.offer(next);
				}
			}
		}
		
		//������ E���� ���������� C�� ������ŭ�� ������ ��Ƴ´ٸ� ��ΰ� �������� �˷��ִ� ���̴�. 
		if(val[E] == C)	
			System.out.println(cnt[E]);	//�̶� E���� �׾ƿ� ����� ������ �־��ָ� �ȴ�.
		else
			System.out.println(0);
	}//==========================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		indegree = new int[N+1];
		edge = new ArrayList<>();
		for(int i=0; i<=N; i++)
			edge.add(new ArrayList<>());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edge.get(start).add(end);
			indegree[end]++;
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		set = new HashSet<>();
		for(int i=0; i<C; i++) 
			set.add(Integer.parseInt(br.readLine()));
		
		topLogicalSort();
	}//==========================================

}
