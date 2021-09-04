package Strongly_Connected_Component;

import java.util.*;
import java.io.*;

/* 재밌는 점은 SCC에 포함된 레스토랑은 SCC에 모든 ATM을 가져올 수 있다. 최소 거리일 필요는 없기에 최대한 많은 돈은 떙껴오는게 좋은거다. 물론 한번 땡기면 다시 충전되는 건 아니다.*/
// 4013번 - ATM
public class p4__ATM {

	static int N, M;
	static int cnt, SN, S, P;	//S는 출발장소, P는 레스토랑 개수 
	static int[] dfsn, sn, money;
	static boolean[] finished;
	static Set<Integer> setRest;		//레스토랑에 대한 정보
	static ArrayList<ArrayList<Integer>> list;
	static ArrayList<Integer> SCCmoney;			//각 SCC번호에 맞는 총 가격이 들어있다.
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
		/*SCC 세팅 준비*/
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
		SCCmoney = new ArrayList<>();		//SCC가 갖는 현금 액수 총합
			
		/*SCC를 구해둔다.*/
		for(int i=1; i<=N; i++) 
			if(dfsn[i]==0) DFS_SCC(i);
		
		ArrayList<ArrayList<Integer>> SCCadj = new ArrayList<>();	//SCC와 인접한 다른 SCC를 담고있는 리스트
		for(int i=0; i<SN; i++)
			SCCadj.add(new ArrayList<>());
		int SCCstart = -1; 					// 시작점을 포함하는 SCC의 번호
		int[] SCCindegree = new int[SN];	// 이 SCC의 indegree
		boolean[] hasRest = new boolean[SN];
		int A_SCC, B_SCC;
		
		for(int now=1; now<=N; now++) {
			A_SCC = sn[now];
			if(now == S) SCCstart = A_SCC;
			if(setRest.contains(now)) hasRest[A_SCC] = true;
			
			for(int next:list.get(now)) {
				B_SCC = sn[next];
				if(A_SCC != B_SCC) {
					if(!SCCadj.get(A_SCC).contains(B_SCC)) { //사실 이조건이 없어도 된다. 여러번 중복이 되었다 할때 중복된 만큼 indegree가 늘어났지만 그만큼 방문해서 지워지고, 여러번 같은 방문이라도 sMax는 맨 처음을 제외하곤 같은 값을 MAX로 비교해서 현상유지를 계속 한다. 
						SCCadj.get(A_SCC).add(B_SCC);
						SCCindegree[ B_SCC ]++;
					}
				}
			}
		}
		
		/*위상정렬을 준비한다.*/
		Queue<Integer> q = new LinkedList<>();
		int[] sMax = new int[SN];	//여기에 해당 SCC번호에 들어올 가장 큰 값을 전해준다. 
		boolean[] try_pos = new boolean[SN];	//시작점에서 이 SCC에 도달할수 있는가
		
		for(int i=0; i<SN; i++) {
			sMax[i] = SCCmoney.get(i);
			if(i == SCCstart) //만약 시작위치라면
				try_pos[i] = true;
			if(SCCindegree[i]==0) 
				q.add(i);
		}
		
		/* 말할게 있다면 왜 위상정렬을 굳이 사용하는가? 라고 볼수있다. 스킬트리처럼 반드시 indegree가 0이 되어서 진행해야 하는게 아닌데 말이다.
		 * 맞다. 네 말도 가능하다. 다만 여기서는 위상정렬의 방식으로 진행했지만 불필요한 SCC로부터 다가오는 indegree는 결국 try_pos라는 당신이 시작점에서 올수있는 건지 판단해서
		 * sMax에 내용물을 변화하지 않게 했다. 즉, 방문은 할수있다. 다만 방문은 했지만 유의미한 변화를 주지않고 indegree값만 줄여주는 일만 해주면 될 뿐이다.
		 * 굳이 위상정렬을 쓴 이유는 모든 정점에 대해 빠르게 진행이 가능하다. 이렇게 불필요한 건 변화를 주지 않고 indegree만 줄여주면 되고, 
		 * 구조상 반드시 연결된 모든 정점들은 결국 indegree가 0이 되어 연결된 모든 정점을 반드시 진행할 수 있다. 
		 * 또한, 실제로 시작점에서 도달 가능한 곳에서 indegree가 하나씩 지워 주는걸로 더이상 자기에게 올게 없음을 알리고 최대 상태에서 자신을 시작할 수 있다.
		 * 그래서 위상정렬로 모든 기반을 최대로 진행 할 수 있고, 불필요한 방문일지라도 indegree만 줄여주게 하면 되고 이렇게 indegree를 지워주는 구조는 반드시 모든 연결된 정점을 지나오게 할 수 있다.
		 * 
		 * 위상정렬을 통해 sMax에 들어오는 값들을 indegree 0인 것부터 시작해서 점점 쌓여가지만, 시작점에서 관련 없는 내용물은 sMax의 내용을 늘려주지 못하기 때문에 시작점에서 올 수 있는 SCC만 sMax의 내용물을 늘릴수있다.
		 * 또, 이렇게 모든 sMax에 대해서도 시작점에서 시작한 SCC에 대해서만 유의미한 정보로 쓰고 동시에 레스토랑도 포함한 SCC가 우리가 필요한 정보가 된다.
		 * */
		
		/*위상정렬 시작*/
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int next : SCCadj.get(now)) {
				//자신이 시작점에서 도달이 가능하다면 next도 진행이 시작점에서도 가능함을 알리고 sMax에 대한 변화를 줄 수 있다.
				if(try_pos[now]) {
					sMax[next] = Math.max(sMax[next], sMax[now] + SCCmoney.get(next));	//우리가 알아본 기존에 next있던 값과 현재까지 구해온 now의 돈에서 next에 대한 SCC 종합가격을 더한걸 비교한다. 
					try_pos[next] = true;	//시작점에서 
				}
				if(--SCCindegree[next] == 0) 
					q.add(next);
			}
		}
		
		int result = 0;
		for(int i=0; i<SN; i++) {
			if(hasRest[i] && try_pos[i])	//시작점에서 올수있는 SCC이고, 해당 SCC가 레스토랑을 포함한다면...
				result = Math.max(result, sMax[i]);	//해당 sMax값은 시작점에서 
		}
			
		System.out.println(result);	
	}//==============================================

}
