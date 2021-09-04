package MST__Minimm_Spanning_Tree;

import java.io.*;
import java.util.*;

/**=================================================================
 * 크루스칼 알고리즘의 작동방식은 이렇습니다. 
 * 1. 간선들을 가중치 순으로 오름차순 정렬하고 정점들을 각 컴포넌트(그룹)로 초기화한다.
 * 2. 간선들을 훑으면서 양쪽 정점을 포함한 컴포넌트가 연결되어 있지 않으면 간선을 뽑고 연결한다.
 * 3. 간선 V-1개가 뽑혔을 때, 그 간선들과 정점들이 이루는 그래프가 MST이다. 
 * 정렬을 한다고 할때 가중치 기준으로 한다는데, 어떠한 기준으로 제시된게 없습니다. 오름차순으로 해도 되고, 내림차순으로 해도 됩니다. 
 * 덕분에 결과로 나올 수 있는 MST가 여러개 일수 있습니다. 물론 가중치의 합은 같을 것입니다.  
  
 * 그러니 크루스칼의 기본은 '간선'을 중심으로 생각하는 것이다. 
 * 무향이기에 2개의 정점에 대한 2개의 방향으로 간선을 만들었는데, 크루스칼에서는 2개의 정점만 포함한 하나의 간선만 제시해주면 됩니다. 
 * 초기에는 각 간선은 자신만의 그룹으로 독립(혹은 어떤 그룹에도 속하지 않았다 해서 0혹은-1에 속하고 시작)되지만, 이후 과정을 거쳐가며 간선들이 다른 그룹의 포함이 되어 결국 하나의 그룹이 될때까지 진행하면 됩니다.
 * 간선의 가중치가 가장 작은 것을 고른 후에 싸이클이 생기지 않도록 모든 노드를 방문할 수 있도록 고르면 된다.
 ===================================================================**/
// 1197번 - 최소 스패닝 트리 
public class p2__MST {
	/*클래스 변수*/
	static PriorityQueue<Edge> pq;	// 가중치가 작은 간선을 순서대로 가져옴
	static int[] group;				// 각 정점에 대해서 독립된 그룹이 시작함. 유니온 파인드 배열로 둠.
	//=======================================================
	/*간선 자료구조*/  		//MST가 아닌 최단경로에서는 다익스트라로 쓸때 'Node'로 정점에 대해서 자료구조를 만들고 2개의 방향으로 간선을 진행했다.
	static class Edge implements Comparable<Edge>{
		int idx1, idx2, weight;
		Edge(int a, int b, int w){
			idx1=a; idx2=b; weight=w;
		}
		public int compareTo(Edge o) {	//둘의 가중치가 같을 때 처리하는 방법은 기준이 없다. 그래서 마음대로 -1이든 1로 줘도된다.
			if(weight<o.weight) return -1;
			else return 1;
		}
	}//=======================================================
	/*유니온 파인드의 find연산*/		
	static int groupFind(int a) {	
		if(group[a]==-1) return a;	//아직 어떤 그룹에도 속하지 않았으니 자기 자신을 리턴해준다. 그럼 자기 자신으로 그룹이 지정된다. 또한 마지막에 도달한게 이녀석이라는 것이라서 시작이 어떤 값이든 이녀석이 우리의 보스임을 알려주는 셈이다.
		return group[a] = groupFind(group[a]);	//어떤그룹이라도 속했다면(자기자신도 가능) 현재 그 그룹의 가장 높은 수장을 찾도록 재귀한다.
		//(각 그룹은 하나의 그룹의 값만 갖고있고 갖고있다면 더이상 바뀌지 않아도 된다고 생각했었다 왜냐하면 이렇게 자신이 속한 그룹에 가면, 그 그룹이 속한 또 다른 그룹이 있어서 이렇게 쭉 상하관계가 형성된다.
		//모든 MST를 마치면 그룹의 상하관계가 일직선으로 나타나게 된다.
		//그리고 가장 위에 있는 그룹이 최고의 수장이 되며, 각각의 그룹의 바로 한단계 위는 다른 그룹일지라도.. 그 맨위는 해당 최고의 수장님이 되어서 모두가 그 그룹수장에게 포함되었다고 구성된다.)
	
		//더욱이 여기서는 최근에 구한대로 정보를 갱신해주어서 혹여나 반복되는 연산과정을 조금이나마 줄이도록 해주었다. 그래서 일직선은 맞아도 모든 그룹을 다 포함하지 않고 갱신을 통해 줄여주었다.
		//모든 MST를 끝냈지만 수장그룹으로 갱신이 안된 정점도 다시 find를 하면 가장 높은 수장에게 가니 문제없다. 여전히 일직선구조이기 때문이다.
	}//=======================================================
	/*유니온 파인드의 merge연산*/
	static boolean groupMerge(int a, int b) {
		a = groupFind(a);
		b = groupFind(b);
		if(a==b) return false;	//서로의 그룹이 같다면 합체하지 않아도 된다고 알려준다.
		group[b] = a;			//이곳에 왔다면 둘중에 한명을 더 높은 그룹이라고 보면 된다. 그렇게 하면 하나의 포함된다.
		return true;			//서로 합쳐졌음을 알린다.
	}//=======================================================
	/*main함수*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Edge(a, b, c));
		}

		/* 크루스칼 알고리즘 */
		group = new int[V+1];
		Arrays.fill(group, -1);
		int result=0, count=0;	//result : 가중치합, count:뽑은 간선 수 
		
		while(true) {
			Edge edge = pq.poll(); 
			if( groupMerge(edge.idx1, edge.idx2) ) {
				result += edge.weight; 
				if(++count == V-1) //트리의 구성요소만큼 간선을 확보했다면 종료.
					break;
			}
		}/*==크루스칼 알고리즘 종료==*/
		
		for(int i=1; i<=V; i++)
			System.out.print(group[i]+" ");
		
		System.out.println(result);	
	}//=======================================================

}
