package Toplogical_Sort;

import java.util.*;
import java.io.*;

// 1649번 - 택시
public class p5__Taxi_1649 {
	static int N, M;
	static int S, E, C;
	static int[] indegree;
	static ArrayList<ArrayList<Integer>> edge;
	static Set<Integer> set;
	//==========================================
	
	static void topLogicalSort() {
		Queue<Integer> q = new LinkedList<>();
		int[] cnt = new int[N+1];	//현재 [i]에 도달할 때까지 만들어낸 경로의 가지수
		int[] val = new int[N+1];	//현재 [i]에 도달할 때까지 만나본 C1~Ck 교차로의 최대 개수 
		
		for(int i=1; i<=N; i++) {
			if(indegree[i]==0) 
				q.offer(i);			//이게 모두 필요한 게 위상정렬로 진행하기에 '해금'이라는 과정이 필요한데 주어진 노드에서 모든 것을 지나가서 해금하는데 도움을 줘야한다. 시작점부터 시작을 안하면 cnt가 0인 base로 출발해서 경로로 인정은 안된다.  
		}	
		cnt[S] = 1;	//시작지점부터는 다른 값들에게 베이스가 되어 진행을 도와준다. 
		
		while(!q.isEmpty()) {
			int now = q.poll();
			//System.out.println("현재 "+now+" ");
			if(set.contains(now)) val[now]++;
			if(now==E) break;
			
			for(int next : edge.get(now)) {
				indegree[next]--;
				//System.out.println("	다음 "+next+" cnt "+cnt[now]+" "+cnt[next]+" val "+val[now]+" "+val[next]);
				
				//아직 방문을 하지 못한 next에게 자신에게 온 정보를 제공해준다. next는 자신의 indegree에 연결된 모든 노드에게 정보를 누적받는다. 
				//대신 정보를 받을때 아래와 같은 2가지 조건을 통해서 받는다. 여기서 나는 'next', 나에게 오는 'now'.
				//1. 나에게 오는 노드가 나보다 많은 C1~Ck를 만나고 온 녀석이라면 그녀석에게 소속되면 된다. 
				//2. 나에게 오는 노드가 나와 같은 C1~Ck 개수를 만나고 왔다면 나와 마찬가지로 정상적으로 위에서 얻어올 수 있는 C의 정보를 빠짐없이 잘 가져왔다는 뜻이 된다. 그렇기에 서로의 정보를 합쳐준다. 만약 0이라도 정상적으로 서로에게 동지이기에 서로의 정보를 합치면 된다. 
				//3. 반대로 나보다 개수가 적다면 나보다 위에서 내려오는 정보 중 그 노드로는 입수할 수 없는 C가 존재하다는 말이다. 따라서 해당 노드로 부터 오는 정보의 경로는 자신에게 올때 얻을수없는 C가 존재함을 알려주기에 정보로서 포함안하면 된다. 
				if(val[now] > val[next]) {
					val[next] = val[now];
					cnt[next] = cnt[now];
				}
				else if(val[now] == val[next]) {
					cnt[next] += cnt[now];
				}
				
				if(indegree[next]==0) {
					//System.out.println("	큐삽입");
					q.offer(next);
				}
			}
		}
		
		//마지막 E에게 정상적으로 C의 개수만큼의 정보를 담아냈다면 경로가 존재함을 알려주는 것이다. 
		if(val[E] == C)	
			System.out.println(cnt[E]);	//이때 E까지 쌓아온 경로의 개수를 넣어주면 된다.
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
