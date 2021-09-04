package Lowest_Common_Ancestor;
import java.util.*;
import java.io.*;

//모든 도시의 쌍에는 그 도시를 연결하는 유일한 경로가 있고... 이 말은 트리구조.

// 3176번 - 도로 네트워크
public class p3__Road_Network {

	static int N;		// 도시 개수, 		 2 <= N <= 100_000	,	도시는 1번 부터 존재.
	static int K;		// 도시 쌍의 개수,	 1 <= K <= 100_000
	static ArrayList<ArrayList<Road>> roadlist;	//도시 간의 도로
	static int[][] parent;		//[지수][시작도시] = 이동된 도시
	static int[][] max, min;	//[지수][시작도시]으로 시작도시에서 이동한 도시까지 이동했을 때 가장 max와 min한 간선은 무엇이었는지 기록.
	static int[] depth;			//현재 도시의 깊이, 0은 루트
	static int MAX_DIGIT = 18;
	//=========================================================
	static class Road{
		int city, cost;
		Road(int i, int c){
			city=i; cost=c;
		}
	}//=========================================================
	static void makeTree(int now) {
		for(Road child : roadlist.get(now)) {
			int city = child.city;
			int cost = child.cost;
			if(parent[0][city]==0) {
				parent[0][city] = now;
				max[0][city] = cost;
				min[0][city] = cost;
				depth[city] = depth[now]+1;
				makeTree(city);
			}
		}
	}//=========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		roadlist = new ArrayList<>();
		for(int i=0; i<=N; i++)
			roadlist.add(new ArrayList<>());
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			roadlist.get(a).add(new Road(b, c));
			roadlist.get(b).add(new Road(a, c));
		}

		//희소배열을 위한 준비단계
		int root = 1; //원래 루트에 따라 두 개의 도시의 공통 조상은 달라진다. 다만, 우리가 구하려는게 어느 루트일때 공통조상이 아닌 공통조상은 2개의 연결지점이 되는 중간지점이다.
					  //따라서 루트를 신중히 고민하지않고 트리이니 어느 지점을 루트로 해도 되며, 공통조상은 중간지점에 역활만 해주면 된다.
		parent = new int[MAX_DIGIT][N+1];
		max	   = new int[MAX_DIGIT][N+1];
		min    = new int[MAX_DIGIT][N+1];
		for(int i=0; i<MAX_DIGIT; i++)
			Arrays.fill(min[i], 1_000_001); 	//min을 가장 높게해서 갱신이 안된건 잡힐 수 없게한다. max도 마찬가지로 가장 낮게해서 잡힐수가 없는 상태다.
		depth = new int[N+1];
		Arrays.fill(depth, -1);
		depth[1] = 0;
		parent[0][1] = -1;	//루트의 부모는 없는 것으로 하여 이렇게 두었다. 
		makeTree(root);
		
		//희소배열 완성시키기
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=N; i++) {
				int side = parent[j-1][i];
				if(side != -1) {	//매우 정확한 방식. 캬. 이미 넌 무의미한 짓을 한것이다 라고 경고해줌.
					parent[j][i] = parent[j-1][ side ];
					max[j][i] = Math.max(max[j-1][i] , max[j-1][ side ]);	//j번 이동에서 맨처음 i에서 j-1번 이동했을 때 나온 max와 j-1로 이동한 side에서 j-1번 이동한 max중 더 높은 걸 고르면 된다.
					min[j][i] = Math.min(min[j-1][i] , min[j-1][ side ]);	
				}
				else {
					parent[j][i] = parent[j-1][i];	//즉 앞으로는 0을 이어간다.
					max[j][i] = max[j-1][i];
					min[j][i] = min[j-1][i];
				}
			}
		}	
		
		//받은 질문을 해결해보자
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			//무조건 depth[D] >= depth[E]를 유지해서 진행하고자한다. 하나만 생각하는게 덜 귀찮음. 그리고 depth는 클수록 깊다.
			if(depth[D] < depth[E]) {
				int temp = D;
				D = E;
				E = temp;
			}
			int diff = depth[D] - depth[E];	//항상 depth[D] >= depth[E]를 유지
			int a = D, b = E;
			int max_A=0, max_B=0;
			int min_A=1_000_001, min_B=1_000_001;
			//System.out.println("시작"+D+" "+E);
			// 깊이차를 없애며 a를 위로 이동시켜준다. 그래서 b와 depth를 같게해준다. 
			for(int j=0; diff>0; j++) {
				if(diff%2==1) {
					max_A = Math.max(max_A, max[j][a]);
					min_A = Math.min(min_A, min[j][a]);
					a = parent[j][a];		//다 끝나고나서 다음 이동으로 옮겨준다. 만약에 이거먼저 하고 max, min하면 대참사다. 서순이다.
				}
				diff/=2;
			}
			//System.out.println("맨처음 "+max_A+" "+min_A+" "+max_B+" "+min_B);
			// a와 b가 서로 다르면 '동시에' 일정 높이 만큼 이동시킨다. 
			if(a != b) {
				for(int k=MAX_DIGIT-1; k>=0; k--) {

					if(parent[k][a]!=-1 && parent[k][a] != parent[k][b]) {
						max_A = Math.max(max_A, max[k][a]);
						min_A = Math.min(min_A, min[k][a]);
						max_B = Math.max(max_B, max[k][b]);
						min_B = Math.min(min_B, min[k][b]);
						a = parent[k][a];
						b = parent[k][b];	//다 끝나고나서 다음 이동으로 옮겨준다. 만약에 이거먼저 하고 max, min하면 대참사다. 서순이다.
						//System.out.println("도중 변경 "+max_A+" "+min_A+" "+max_B+" "+min_B);
					}
					
				}
				
				max_A = Math.max(max_A, max[0][a]);
				min_A = Math.min(min_A, min[0][a]);
				max_B = Math.max(max_B, max[0][b]);
				min_B = Math.min(min_B, min[0][b]);
				a = parent[0][a];
				//System.out.println("마지막 "+max_A+" "+min_A+" "+max_B+" "+min_B);System.out.println();
			}
			//a에는 공통조상에 정보가 담겨있다.
			int all_max = Math.max(max_A, max_B);
			int all_min = Math.min(min_A, min_B);
			sb.append(all_min+" "+all_max+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
	}//=========================================================

}
