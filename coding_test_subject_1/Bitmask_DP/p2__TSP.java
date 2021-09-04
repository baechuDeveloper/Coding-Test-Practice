package Bitmask_DP;

import java.util.*;
import java.io.*;

// 2098번 외판원 순회 - Traveling Salesman Problem(TSP)
// 모두 거쳐야하는 상황
// https://dragon-h.tistory.com/29
public class p2__TSP {

	static int N; 		//도시 개수
	static int visit=0; //최대 16개의 도시가 존재. 비트마스크로 방문 표시. 방문증
	static int[][] cost;//i에서 j로 가는 비용. [i][j]
	static int[][] dp;	//dp[node][visit]는 현재 node번호에 있고 visit를 방문하고 왔을 때, 0번 노드로 가는 최소의 거리 
	static int INF = 16 * 1_000_000 + 1;	//dp가 가질수있는 최대 크기 
	static int start_city, start_visit;
	//-------------------------------------------------------------------
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		dp = new int[N][ (1<<N)-1 ];	//[도시 개수][ visit값 ] 여기서 [visit]의 전체 배열을 전부 사용하는 것은 아니며 현재 visit방문증을 지니고 있음을 보여주는 형식이다. 이를 비트씩 표현하므로 이정도의 값이 필요하다.  
		for(int i=0; i<N; i++)
			Arrays.fill(dp[i], INF);
		
		for(int z=0; z<N; z++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) 
				cost[z][x] = Integer.parseInt(st.nextToken());
		}
		start_city = 0;	//이곳을 N-1번호까지 어떤 숫자로 바꾸든 결과는 같을 것이다 하하. N=4이면 0,1,2,3다 쌉가능하다는 말이다. 대신 어떤 N의 값이든 0은 항상 존재하므로 문제로 제출하기 위해선 0으로 붙여서 시작하면 된다. 아니면 N-1로 시작해도 된다.
		start_visit = (1<<start_city);
		int result = tsp(start_city, start_visit);	//모두 거쳐야 한다. 자기 자신만 먼저 방문했다는 표시로 현재 출발하는 도시에 해당하는 visit로 시작한다. 0번노드부터 시작했다면 2^0인 1인 visit로 시작하면 된다.
								//더 나아가 만약에 1번노드부터 시작했다면 2^1에 해당하는 비트 값 2로서 (1, 2)로 시작하면 되고.
								//2번 노드부터 시작했다면 2^2에 해당하는 비트 값 4로서 (2, 4)로 시작하면 된다. 
								//그런데 최소에 해당하는 경로는 모든 점을 경유하는 형태로 하나가 무조건 존재하기 때문에. 결국 어디서 시작을 하든 말이다. 같을 것이다.
								//이 상황은 모든 점을 한번은 경유를 하는 것인데, 결국 어느 점에서 시작하든 그 점을 반드시 지날 것이기에 결과는 같아야 한다. 
								//예시로 두면 만약 1->2->3->4->1로 가는 구조라면 3에서 시작해도 3->4->1->2->3으로 똑같은 결과로 나올 수 있다. 
								// 3->4 , 4->1 , 1->2 , 2->3 이렇게 4가지 형태로 쪼개지는 값으로 조합만 달라질 뿐 쪼개지는게 같기에 결과 값도 같아야한다.
		//다 뿌리고 끝에 도달한것을 다 거두어가서 다시 호출 한곳에 최종 결과가 찍히는 피라미드 구조
		bw.write(result+"");
		bw.flush();
	}//-------------------------------------------------------------------
	
	static int tsp(int node, int visit) {	//node는 현재 방문한 도시 , visit는 현재의 방문증 tsp를 재귀로 호출 할수록 방문한 도시가 많아질것이다. 
		//만약 모든 지점을 방문 한 경우
		if(visit == (1<<N)-1) {	//방문증에 모든 도시가 적혀있음 1111 1111 1111 1111 이렇게 
			if(cost[node][start_city] == 0) return INF;	//그런데 맨처음 도시 0번으로 가는 도로가 없다.
			else return cost[node][start_city];			//다행히 도시 0번으로 가는 도로가 있다.
		}
		
		//지금 방문한 곳이 이미 방문해서 계산이 끝난 경우
		if(dp[node][visit] != INF) 
			return dp[node][visit];	//이미 방문해서 계산이 끝난 결과를 내보내준다.
		
		//정상적으로 방문이 되었다면 다음 방문지를 골라본다. 
		for(int i=0; i<N; i++) {
			if(cost[node][i] == 0 || (visit & (1<<i)) != 0) // 방문할 도로가 없거나, 이미 방문을 한 곳 
				continue; //넘어간다.
			
			int next = visit | (1<<i);	//해당 번호의 도시를 방문 했다고 비트마스크에 추가해준다. 
			
			dp[node][visit] = Math.min(dp[node][visit], cost[node][i] + tsp(i, next));	//해당 node에서 N번의 도시로 가는 경우중 가장 작은 값을 가지는 걸 가져온다. 
		} //맨 처음에 호출한 (0,1)은 결국 모든 return을 거치고 온 모든 경우의 값들 중 가장 최소를 가져가게 될 것이다.
		
		//최소의 결과를 가진 자신의 방문 결과를 호출해준곳에 보내준다.
		return dp[node][visit];
	}//-------------------------------------------------------------------

}
