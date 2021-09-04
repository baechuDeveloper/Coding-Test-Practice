package Lowest_Common_Ancestor;
import java.util.*;
import java.io.*;

// 13511번 - 트리와 쿼리2
public class p4__Tree_and_Query_MORE {

	static int N;			//정점 개수 1번부터 존재.	 2 <= N <= 100_000
	static int K;			//쿼리개수				 1 <= K <= 100_000
	static int[] depth;		//트리의 깊이			 클수록 더 깊은 것 루트는 0
	static int[][] parent;	//[지수][시작점] = 이동한 점
	static long[] dcost;	//현재 점에서 루트까지 드는 비용
	static ArrayList<ArrayList<Node>> childlist;	//간선 정보
	static int MAX_DIGIT = 18;
	//=======================================================
	static class Node{
		int i, cost;
		Node(int i, int c){
			this.i=i; cost=c;
		}
	}//=======================================================
	static void makeTree(int now) {
		for(Node node: childlist.get(now)) {
			int child = node.i;
			int cost = node.cost;
			if(parent[0][child]==0) {
				parent[0][child] = now;
				dcost[child] = dcost[now] + cost;
				depth[child] = depth[now]+1;
				makeTree(child);
			}
		}
	}//=======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		childlist = new ArrayList<>();
		for(int i=0; i<=N; i++)
			childlist.add(new ArrayList<>());
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			childlist.get(a).add(new Node(b, c));
			childlist.get(b).add(new Node(a, c));	
		}

		//희소배열 준비
		parent = new int[MAX_DIGIT][N+1];
		dcost = new long[N+1];
		depth = new int[N+1];
		Arrays.fill(depth, -1);
		depth[1] = 0;
		parent[0][1] = 1;	//계속 자기에게 돌리는 것으로 다른 점에게도 고민했던 정보들을 줄수가있고 1번 점으로 시작하는 모든 이동들은 동일한 결과 값만 줄수있게 된다.
		//트리완성
		makeTree(1);	//루트를 1로 두었는데 이렇게 할 수 있었던 이유는 a와 b사이의 경로를 구하는데 있어서 그 중간에 루트 1이 경로에 포함되어도 a에서 루트로 b에서 루트로 하는 방법이 공통조상을 구하는 방식으로도 구할 수 있다.

		//희소배열 완성시키기.
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=N; i++) {
				int side = parent[j-1][i];
				parent[j][i] = parent[j-1][ side ];
			}
		}	
		//for(int j=0; j<MAX_DIGIT; j++) {for(int i=1; i<=N; i++) {System.out.println(i+"점 "+j+"번 이동:"+parent[j][i]+" 비용:"+pcost[j][i]);}System.out.println();}

		//쿼리내용 이행하기
		K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 1은 두 점 사이의 비용, 2는 두 점사이에 존재하는 점중 몇 번째 점을 말해달라.
			int cmd = Integer.parseInt(st.nextToken());
			//a와 b사이의 루트가 포함되어있든 없든 공통조상을 구하는 것으로 해낼 수 있다.
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			//무조건 depth[U] >= depth[V]를 유지해서 진행하고자한다. 하나만 생각하는게 덜 귀찮음. 그리고 depth는 클수록 깊다.
			boolean change = false;
			if(depth[U] < depth[V]) {
				int temp = U;
				U = V;
				V = temp;
				change = true;	//이걸 또 체크하는 건 문제상에서는 정해진 U의 값이 있는데, 내가 계산이 편리하도록 조정한 것이라 다시 값을 변경하고자 체크한다. 
			}
			int a=U, b=V;

			int diff = depth[a] - depth[b];	//항상 depth[a] >= depth[b]를 유지하기에 자연수
			for(int j=0; diff>0; j++) { // 깊이차를 없애며 a를 위로 이동시켜준다. 그래서 b와 depth를 같게해준다. 
				if(diff%2==1) {	
					a = parent[j][a];
				}
				diff/=2;
			}

			if(a != b) { // a와 b가 서로 다르면 '동시에' 일정 높이 만큼 이동시킨다. 
				for(int k=MAX_DIGIT-1; k>=0; k--) {
					if(parent[k][a]!=1 && parent[k][b]!=1 && parent[k][a] != parent[k][b]) {
						a = parent[k][a];
						b = parent[k][b];
					}
				}
				a = parent[0][a];	//공통조상 
			}
			//a에는 공통조상이 남아있다. 
			if(change==true) {
				int temp = U;  U = V;  V = temp;
			}

			if(cmd==1) {
				sb.append((dcost[U]+dcost[V]- 2*dcost[a])+"\n");
			}
			else {
				int num = Integer.parseInt(st.nextToken());	//U에서 시작해서 V로 향하는 경로중 num번에 해당하는 정점
				int cnt = 0;

				/* https://salepark.tistory.com/52
				 * 그 다음 2번 쿼리에 대해 생각해보자
				 * u와 v의 경로 K번째 노드의 번호를 출력하는 쿼리인데,
				 * K는 안전함(?)이 보장되어있으니 2가지 경우로 나눈다.
				 * K번째 노드가 최소공통조상P의 왼쪽에 있을 떄와 오른쪽에 있을 때 이다.
				 * 다른 말로해서, u - 최소공통조상 - v의 경로가 구성되어 있을 때,
				 * u-최소공통조상 사이에 있을 떄와 최소공통조상 - v사이에 있을 때 두가지로 나눌 수 있다.
				 * u - 최소공통조상 사이에 있을 땐  u 부터 K번째를 찾아가면 된다.
				 * 이때 물론 K번쨰를 찾아갈 때 LCA를 구할 때 사용했던 dp식을 이용해서 찾아가면 된다.
				 * 마찬가지로 최소공통조상 - v사이에 있을 땐, 우선 u - v거리에서 K를 빼면, v에서 최소공통조상의 거리가 된다.
				 * 이는 u - 최소공통조상의 거리를 구했던 것처럼 dp식을 이용해 찾아가면 된다.*/
				if (num > depth[U] - depth[a] + 1) {
					num -= depth[U] - depth[a] + 1;
					num = (depth[V] - depth[a])- num;
					while (num != 0) {
						if (num % 2 == 1) {
							V = parent[cnt][V];
						}
						num /= 2;
						++cnt;
					}
					sb.append(V+"\n");
				}
				else {
					--num;
					while (num != 0) {
						if (num % 2 == 1) {
							U = parent[cnt][U];
						}
						num /= 2;
						++cnt;
					}
					sb.append(U+"\n");
				}
				
			}

		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================

}
