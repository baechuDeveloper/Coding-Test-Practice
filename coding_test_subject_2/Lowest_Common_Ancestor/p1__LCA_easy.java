package Lowest_Common_Ancestor;

import java.util.*;
import java.io.*;

// 3584번 - 가장 가까운 공통 조상
public class p1__LCA_easy {
	
	static int N;	//N개의 정점으로 이루어진 트리		2<= N <= 100_000		1번부터 시작하는 노드
	static int M;	//가장 가까운 공통 조상의 쌍의 개수	1<= M <= 100_000
	static int root;
	static Set<Integer> addset, delset;	//잠깐 루트를 찾고싶어성
	static ArrayList<ArrayList<Integer>> childlist;
	static int[][] parent;
	static int[] depth;
	static int MAX_DIGIT = 18;
	//==========================================================
	//트리구조를 만드는 메소드
	static void makeTree(int now) {
		for(int child : childlist.get(now)) {
			parent[0][child] = now;	//부모와 자식 관계를 확실히 알려줘서 바로 진행이 가능하다. 트리구조이기에 중복된 자식은 없다. 
			depth[child] = depth[now] +1;
			makeTree(child);
		}
	}//==========================================================
	//main함수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			
			N = Integer.parseInt(br.readLine());
			childlist = new ArrayList<>();
			for(int i=0; i<=N; i++)
				childlist.add(new ArrayList<>());
			addset = new HashSet<>();
			delset = new HashSet<>();
			for(int i=0; i<N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken()); addset.add(p);
				int c = Integer.parseInt(st.nextToken()); delset.add(c);
				childlist.get(p).add(c);
			}
			
			for(int i:delset) 
				addset.remove(i);
			root = (int)addset.toArray()[0];

			parent = new int[MAX_DIGIT][N+1];
			depth = new int[N+1];
			Arrays.fill(depth, -1);
			depth[root] = 0;
			
			//우선 트리구조를 형성하고 준비한다.
			makeTree(root);
			
			//희소배열을 완성한다.
			for(int j=1; j<MAX_DIGIT; j++) {
				for(int i=1; i<=N; i++) {
					if(parent[j-1][i] != -1)
						parent[j][i] = parent[j-1][ parent[j-1][i] ];
				}
			}
			//쿼리를 처리한다.
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
	
			// 깊이차를 없애며 a를 위로 이동시켜준다. 그래서 b와 depth를 같게해준다. 
			for(int j=0; diff>0; j++) {
				if(diff%2==1) a = parent[j][a];
				diff/=2;
			}

			// a와 b가 서로 다르면 '동시에' 일정 높이 만큼 이동시킨다. 
			if(a != b) {
				for(int k=MAX_DIGIT-1; k>=0; k--) {

					if(parent[k][a]!=-1 && parent[k][a] != parent[k][b]) {
						a = parent[k][a];
						b = parent[k][b];
					}

				}
				a = parent[0][a];
			}
			sb.append(a+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}//==========================================================

}
