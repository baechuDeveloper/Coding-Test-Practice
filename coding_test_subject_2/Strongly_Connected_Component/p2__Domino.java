package Strongly_Connected_Component;
import java.util.*;
import java.io.*;

/** https://m.blog.naver.com/kks227/220802519976
 * 
 * SCC를 완성한 후 SCC하나를 하나의 정점으로 둔 그래프는 반드시 DAG(유향 비순환 그래프)입니다. 
 * 이렇게 DAG를 만들 수 있다면 위상정렬을 할 수가 있습니다. 따라서 SCC단위로 이 DAG를 이용해서 위상정렬을 할 수 있습니다.
 * 즉 SCC의 유용한 방법중 하나는 SCC단위로 압축해서 DAG로 나타내는 것입니다. 
 * 
 * 여기 문제만큼은 위상정렬로 넘어갈때 필요한 indegree의 수만 알아내도 풀 수는 있습니다.
 * */

// 4196번 - 도미노
public class p2__Domino {

	static int N, M;	//N은 도미노 개수, M은 관계의 개수
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
			else if(!finished[next])	//아직 SCC로 추출은 안되었다면 해당 뒤로 가는게 더 큰값인지 확인 
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
				
			/* SCC를 통해 만들어진 DAG를 활용해서 SCC정점의 indegree값을 구해본다. 
			 * SCCindegree를 통해 해당 SCC에 직접 들어오는 방법이 이 개수만큼 있을을 알려준다.*/
			int[] SCCindegree = new int[SN];	// SCC의 개수만큼 존재하는 정점에 대해, SCC를 한 정점으로 둔 그래프 기준으로 'SCC그 자체 정점'에게 들어가는 indegree에 대해 담아둔 값.
			int A_SCC, B_SCC; 					// A_SCC에서 B_SCC로 방향으로 가는 간선이다.
			
			for(int now=1; now<=N; now++) {	//전체 정점에서 찾아본다. 
				A_SCC = sn[now];
				for(int next : list.get(now)) {	
					B_SCC = sn[next];
					//서로 다른 정점으로 가는게 확인된다면 
					if(A_SCC != B_SCC) 
						SCCindegree[ B_SCC ]++;	//들어가는 건 SCC의 정점을 기준으로 한 형태로 들어간다.
				}
			}		
			
			//여기서 우리는 단순히 indegree가 0인 것을 찾으면 된다. 
			int result = 0;
			for(int i=0; i<SN; i++) 
				if(SCCindegree[i]==0) result++;
			
			bw.write(result+"\n");
		}
		bw.flush();
	}//=========================================

}
