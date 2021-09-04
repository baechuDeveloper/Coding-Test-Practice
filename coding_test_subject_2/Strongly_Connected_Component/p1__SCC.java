package Strongly_Connected_Component;
import java.util.*;
import java.io.*;

// https://m.blog.naver.com/kks227/220802519976
/**
 * 타잔 알고리즘으로 SCC를 찾아보았다. 
 * 가장 중요한점은 dfsn을 활용해서 여태까지 쌓은 stack내용물을 풀어서 하나의 SCC를 만들어주는 것.
 * 
 * 낚시줄을 내려서 마지막 땅밑에 닿았을 때, 그 마지막 녀석이 위의 녀석(자신포함)을 지목한 곳까지 올라가서 그들끼리 묶음으로 SCC가 되는 형태 
 * */

// 2150번 - Strongly Connected Component 강한 연결 요소 
public class p1__SCC {
	
	//SN : SCC개수, sn[i] : i가 속한 SCC의 번호
	static int V, E;
	static ArrayList<ArrayList<Integer>> list, SCC;	//list:방향간선, SCC : 한 묶음의 SCC를 담아둠 
	static Deque<Integer> stack;
	static int cnt, SN; 
	static int[] dfsn, sn;	//dfsn은 각 정점에 방문 순서대로 번호를 매긴 것. 왼쪽먼저 진행하는 DFS에 따른 번호를 부여한다. 
	static boolean[] finished;	//SCC 분리가 끝난 정점만 true
	//===========================================
	
	//자신의 결과값을 리턴하는 DFS함수 
	static int DFS(int cur) {
		dfsn[cur] = ++cnt;	//dfsn 결정
		stack.push(cur);	//스택에 자신을 push
		
		// 자신의 dfsn, 진행하며 자식들의 결과나 dfsn 중 가장 작은 번호를 result에 저장. 자신 기준 가장 작은것이 가장 위에 있을 노드이며 자신이 돌아갈 가장 위에 것.
		int result = dfsn[cur];
		for(int next : list.get(cur)) {
			//아직 방문하지 않은 이웃
			if(dfsn[next]==0) 
				result = Math.min(result, DFS(next));
			//방문은 했으나 아직 SCC로 추출되지는 않은 이웃
			else if(!finished[next]) 
				result = Math.min(result, dfsn[next]);
		}
		
		//자신, 자신의 자손들이 도달 가능한 제일 높은 정정이 자신일 경우 SCC추출
		if(result == dfsn[cur]) {
			ArrayList<Integer> curSCC = new ArrayList<>();	//하나의 SCC
			//스택에서 자신이 나올 때까지 pop을 하여 그 내용물까지가 같은 SCC로 완성된다.
			while(true) {
				int t = stack.pop();
				curSCC.add(t);
				finished[t] = true;
				sn[t] = SN;	//같은 그룹임을 알려줌
				if(t == cur) break;		
			}
			//출력을 위해 원소 정렬
			curSCC.sort(null);
			//SCC 추출
			SCC.add(curSCC);
			SN++; //하나의 SCC는 완성 되었으니 다음 선정될 SCC의 번호가 증가한다. 
		}
		
		return result;
	}//===========================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// V개의 정점
		int E = Integer.parseInt(st.nextToken());	// E개의 간선 
		list = new ArrayList<>();
		for(int i=0; i<=V; i++)
			list.add(new ArrayList<>());

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list.get(A).add(B);
		}
		cnt = 0;
		SN = 0;
		stack = new ArrayDeque<>();
		dfsn = new int[V+1];
		sn = new int[V+1];
		finished = new boolean[V+1];
		SCC = new ArrayList<>();

		for(int i=1; i<=V; i++)
			if(dfsn[i] == 0) //이미 SCC가 완성된건 진행하지 않아도 된다.
				DFS(i);
			
		//앞자리가 먼저인 SCC부터 정렬해준다. 
		Collections.sort(SCC, new Comparator< ArrayList<Integer> >() {
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});	
		 
		bw.write(SN+"\n");	//SCC 개수
		for(ArrayList<Integer> curSCC : SCC) {
			for(int cur : curSCC)
				bw.write(cur+" ");
			bw.write(-1+"\n");
		}
		bw.flush();
	}//===========================================

}
