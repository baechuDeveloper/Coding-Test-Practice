package Lowest_Common_Ancestor;
import java.util.*;
import java.io.*;

// 13511번 - 트리와 쿼리2
public class p4__Tree_and_Query{

	static int N;			//정점 개수 1번부터 존재.	 2 <= N <= 100_000
	static int K;			//쿼리개수				 1 <= K <= 100_000
	static int[] depth;		//트리의 깊이			 클수록 더 깊은 것 루트는 0
	static int[][] parent;	//[지수][시작점] = 이동한 점
	static long[][] pcost;
	//static long[] dcost;	//현재 점에서 루트까지 드는 비용
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
				pcost[0][child]  = (long)cost;
				//dcost[child] = dcost[now] + cost;
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
		pcost  = new long[MAX_DIGIT][N+1];
		//dcost = new long[N+1];
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
				pcost[j][i] = pcost[j-1][i] + pcost[j-1][ side ];
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
			long cost_A=0, cost_B=0;	//공통조상으로 올라가면서 발생한 비용들. 그리고 long자료형이다!
			int cnt_A=0, cnt_B=0;	//공통조상으로 올라가기 위해 각각 이동한 횟수. 십진법.

			int diff = depth[a] - depth[b];	//항상 depth[a] >= depth[b]를 유지하기에 자연수
			for(int j=0; diff>0; j++) { // 깊이차를 없애며 a를 위로 이동시켜준다. 그래서 b와 depth를 같게해준다. 
				if(diff%2==1) {	
					cost_A += pcost[j][a];	//위 아래 서순 중요하구요
					cnt_A += Math.pow(2, j);
					a = parent[j][a];
				}
				diff/=2;
			}

			if(a != b) { // a와 b가 서로 다르면 '동시에' 일정 높이 만큼 이동시킨다. 
				for(int k=MAX_DIGIT-1; k>=0; k--) {
					if(parent[k][a]!=1 && parent[k][b]!=1 && parent[k][a] != parent[k][b]) {
						cost_A += pcost[k][a];
						cost_B += pcost[k][b];	//마찬가지로 위 아래 서순 중요하구요
						cnt_A += Math.pow(2, k);
						cnt_B += Math.pow(2, k);
						a = parent[k][a];
						b = parent[k][b];
					}
				}

				cost_A += pcost[0][a];	//마지막 공통조상으로 올라가는 단 한번의 이동.
				cost_B += pcost[0][b];
				cnt_A++; cnt_B++;
				a = parent[0][a];	//공통조상 
			}
			//a에는 공통조상이 남아있다. 
			if(change==true) {
				int t1 = U;  	 U = V;  		  V = t1;
				long t2 = cost_A; cost_A = cost_B; cost_B = t2;
				int t3 = cnt_A;  cnt_A = cnt_B;   cnt_B = t3;
			}

			if(cmd==1) {
				long sum = cost_A+cost_B;
				sb.append(sum+"\n");
			}
			else {
				int num = Integer.parseInt(st.nextToken());	//U에서 시작해서 V로 향하는 경로중 num번에 해당하는 정점
				// 이 때 공통조상을 넘어가서 V쪽으로 내려가는 경우도 있으니 그걸 의식하자.
				// 여담으로 이 문제를 풀 때 아마 나중에도 유용한 방법이 되는건 트리에서 두 정점으로 가는 길에 몇개의 정점이 존재하는 지 알아보는 걸로 넘어갈수도 있겠다. 
				// 그렇게 할려면 아마 U에서 공통조상까지와 V에서 공통조상까지 몇번의 이동이 있는지 보면 될것이다.

			 	num--; //이 번호는 U를 1번으로 기준하여 작동. 그러니 num-1한 상태에서 이걸 이진수대로 몇번의 이동이 되는지 본다.

				if(cnt_A>=num) { //공통조상을 넘어서 V로 가지 않는다.
					for(int j=0; num>0; j++) {
						if(num%2==1)
							U = parent[j][U];
						num/=2;
					}
					sb.append(U+"\n"); // (num-1)번 이동하여 도착한 점을 알려준다.
				}
				else {	//공통조상을 넘어 V로 간다.
					int Vnum = cnt_B - (num - cnt_A); //V에서 올라가는 구조를 위해서, V에게 있어서는 몇번의 이동으로 해당 번째에 도달하는 횟수로 나타나는지 알려준다.
					for(int j=0; Vnum>0; j++) {
						if(Vnum%2==1)
							V = parent[j][V];
						Vnum/=2;
					}
					sb.append(V+"\n"); // Vnum번 이동하여 도착한 점을 알려준다.
				}
			}

		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================

}
