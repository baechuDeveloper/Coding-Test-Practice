package MST__Minimm_Spanning_Tree;
import java.util.*;
import java.io.*;
//문제 자체가 잘못된 문제 - 몇번 갈아탄 '개수'가 아니라 몇 '종류'의 비행기를 탔는가 물어보고 있다. 그리고 이건 연결그래프가 주어졌다. 씨발
//연결그래프라고 아시나요? 어는 정점이라도 그 정점으로 갈 길이 존재하게 되는 그래프입니다. 반대말이 비연결그래프라고 다른 정점으로 가지 못하는 경우가 생기는 그래프입니다.
//완전그래프라고 아시나요? 정말 모든 꼭지점에 대해 모든 연결이 존재합니다.
/*신장트리가 유용한 이유는 '어떠한 그래프'에서 '가장 적은 개수의 간선으로 모든 정점의 연결이 되는 트리'를 뽑아준 것이기에 
 *이를 이용해서 '왕복'에 필요한 중요한 정보도 제공해준다.*/

//9372번 - 상근이의 여행 
public class p1__Sanggen_Travel {
	static int N, M;
	static ArrayList< ArrayList<Integer> > childList;
	//=======================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			childList = new ArrayList<>();
			for(int i=0; i<=N; i++)
				childList.add(new ArrayList<>());
			
			for(int z=0; z<M; z++) {
				 st = new StringTokenizer(br.readLine());
				 int a = Integer.parseInt(st.nextToken());
				 int b = Integer.parseInt(st.nextToken());
				 childList.get(a).add(b);
				 childList.get(b).add(a);
			}
			
			System.out.println(N-1); //결국에 트리로서 모든 정점을 갈거면 트리의 간선의 '종류'만큼만 주면 된다.
			
			
			
			//사실 아래 방법은 직관적인것은 아니다. 그런데 씨발놈 문제 자체가 개 씨발놈. 낚이지말자 개색히 무식한색히
			/*boolean[] visit = new boolean[N+1];
			int result = 0;
			Queue<Integer> q = new LinkedList<>();
			q.add(1);
			visit[1] = true;
			while(!q.isEmpty()) {
				result++;
				int val = q.poll();
				for(int i:childList.get(val)) {
					if(visit[i]==false) {
						visit[i]=true;
						q.add(i);
					}
				}
			}
			
			System.out.println(result-1);
			*/
		}
	}//=======================================================

}
