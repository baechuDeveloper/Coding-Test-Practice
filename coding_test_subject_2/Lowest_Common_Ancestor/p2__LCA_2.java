package Lowest_Common_Ancestor;

import java.util.*;
import java.io.*;
// https://m.blog.naver.com/kks227/220820773477
// 11438번 - LCA 2
public class p2__LCA_2 {
	//트리의 루트는 1번노드
	static int N;	//N개의 정점으로 이루어진 트리		2<= N <= 100_000		1번부터 시작하는 노드
	static int M;	//가장 가까운 공통 조상의 쌍의 개수	1<= M <= 100_000
	static ArrayList<ArrayList<Integer>> childlist;
	static int[][] parent;
	static int[] depth;
	static int MAX_DIGIT = 18;
	//==========================================================
	//트리구조를 만드는 메소드
	static void makeTree(int root) {
		for(int child : childlist.get(root)) {
			if(depth[child]==-1) {
				parent[0][child] = root;
				depth[child] = depth[root]+1;
				makeTree(child);
			}
		}
	}//==========================================================
	//main함수
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
			childlist.get(a).add(b);
			childlist.get(b).add(a);
		}
		parent = new int[MAX_DIGIT][N+1];
		depth = new int[N+1];
		for(int i=0; i<MAX_DIGIT; i++)
			Arrays.fill(parent[i], -1);
		Arrays.fill(depth, -1);
		depth[1] = 0;
		//우선 트리구조를 형성하고 준비한다.
		makeTree(1);
		//희소배열을 완성한다.
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=N; i++) {
				if(parent[j-1][i] != -1)
					parent[j][i] = parent[j-1][ parent[j-1][i] ];
			}
		}
		//쿼리를 처리한다.
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt(st.nextToken());
			 int b = Integer.parseInt(st.nextToken());

			 //무조건 depth[a] >= depth[b]를 유지해서 진행하고자한다. 하나만 생각하는게 덜 귀찮음. 그리고 depth는 클수록 깊다.
			 if(depth[a] < depth[b]) {
				 int temp = a;
				 a = b;
				 b = temp;
			 }
			 int diff = depth[a] - depth[b];	//항상 depth[a] >= depth[b]를 유지
			 // System.out.println("시작 : "+a+" "+b);
			 
			 // 깊이차를 없애며 a를 위로 이동시켜준다. 그래서 b와 depth를 같게해준다. 
			 for(int j=0; diff>0; j++) {
				 if(diff%2==1) a = parent[j][a];
				 diff/=2;
			 }
			 //System.out.println(depth[a]+" "+depth[b]);
			 
			 // a와 b가 서로 다르면 '동시에' 일정 높이 만큼 이동시킨다. 
			 if(a != b) {
				 for(int k=MAX_DIGIT-1; k>=0; k--) {
					 //'루트보다 위'에 도달하는 것만 제외했으며 같은 depth에서 같은 이동횟수로 올라갔으니 항상 depth는 서로 같다.
					 //서로 각각 이동한 곳의 장소가 아직 다르다 하면... 항상 우리의 공통조상은 더 위에 있고 '공통조상에 바로 아래 칸'을 향하여 가능한 계속 움직이게된다.
					 //parent[k][a] != parent[k][b]라는 조건 덕분에 서로 이동한 값이 같지는 못하지만 k를 지속적으로 진행하면서 결국에 딱 바로 그 밑에 단계까지 이동이 되는 희소배열 움직이는 방법이 된다. 
					 //그렇기에 일단 가장 높은 곳부터 비교를 해서 진행한다. 만약 해당 지수로 옮겼다면 바로 그 아래 지수부터 다시 시작하여 더 진행할 수 있는곳까지 진행을 할 수 있게 된다.
					 //이 구조는 희소배열에 높이 올라가는 방법이라고 적어둔 그 방법이랑 같다. 
					 //거기에 구조상 단 한번이라도 같은 값이 나오면 계속 같은 같이 나오니 '같지 않는다는 조건'이 될때 까지 지 단계별로 올라가며 그 단계안에서 또 올라갈 수 있게되는 구조다.
					 //System.out.println(k+" a: "+a+" b: "+b);
					 if(parent[k][a]!=-1 && parent[k][a] != parent[k][b]) {
						 a = parent[k][a];
						 b = parent[k][b];
					 }
					 //이렇게 해서 최악으로 계속 올라갈 수 있는 곳은 '루트'의 위치 1번이다.	'-1'은 루트의 부모로 없는 곳이다.
				 }
				 //딱 바로 같아지기 전 아래까지 도달했으니 바로 윗단계가 서로 공통된 조상값이 된다.
				 a = parent[0][a];
			 }
			 sb.append(a+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}//==========================================================

}
