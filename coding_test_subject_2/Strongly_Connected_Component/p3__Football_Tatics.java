package Strongly_Connected_Component;

import java.io.*;
import java.util.*;

// SCC�� ���� ���� ADG�� �� �ϳ��� indegree�� 0�� SCC�� �ִ� ���� �����ָ� �ȴ�. 

// 3977�� - �౸ ����
public class p3__Football_Tatics {
	static int N, M;
	static int cnt, SN;
	static int[] dfsn, sn;
	static boolean[] finished;
	static ArrayList<ArrayList<Integer>> list, SCC;
	static Deque<Integer> stack;
	//=========================================================
	
	static int DFS(int now) {
		dfsn[now] = ++cnt;
		stack.push(now);
		
		int result = dfsn[now];
		for(int next : list.get(now)) {
			if(dfsn[next]==0)
				result = Math.min(result, DFS(next));
			else if(!finished[next])
				result = Math.min(result, dfsn[next]);
		}
		
		if(result == dfsn[now]) {
			SCC.add(new ArrayList<>());
			while(true) {
				int t = stack.pop();
				sn[t] = SN;
				finished[t] = true;
				SCC.get(SN).add(t);
				if(t==now) break;
			}
			SN++;
		}
		
		return result;
	}//=========================================================
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt = 0; SN = 0;
			dfsn = new int[N];
			sn = new int[N];
			finished = new boolean[N];

			list = new ArrayList<>();
			for(int i=0; i<N; i++) 
				list.add(new ArrayList<>());
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.get(a).add(b);
			}
			
			SCC = new ArrayList<>();
		
			stack = new ArrayDeque<>();
			
			for(int i=0; i<N; i++)
				if(dfsn[i]==0)
					DFS(i);
			
			int[] SCCindegree = new int[SN];
			int A_SCC, B_SCC;
			
			for(int now=0; now<N; now++) {
				A_SCC = sn[now];
				for(int next : list.get(now)) {
					B_SCC = sn[next];
					if(A_SCC != B_SCC) SCCindegree[B_SCC]++;
				}
			}
			
			
			boolean only_one = false;
			int choose_one = -1;
			for(int i=0; i<SN; i++) {
				if(SCCindegree[i]==0) {
					if(only_one) {	//only_one�� true�� �ٲ���¿��� �ٽ� �ѹ� 0�� indegree�� �ִٸ� �ٷ� Ż���ϵ��� �Ѵ�.
						only_one = false;
						break;	
					}
					only_one = true;
					choose_one = i;
				}
			}
			
			if(only_one) {
				ArrayList<Integer> choose = SCC.get(choose_one);
				choose.sort(null);
				for(int i : choose) 
					bw.write(i+"\n");
			}
			else {
				bw.write("Confused\n");
			}
			
			bw.write("\n");
			if(testcase>0) br.readLine();
		}
		bw.flush();
	}	//=========================================================

}
