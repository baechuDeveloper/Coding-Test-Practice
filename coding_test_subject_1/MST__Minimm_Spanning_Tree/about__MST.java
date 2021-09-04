package MST__Minimm_Spanning_Tree;

public class about__MST {
	/**=================================================================
	 * 최단경로는 드디어 아닙니다! 새로운 걸 배울 때가 되었습니다.
	 * 스패닝 트리(Spanning Tree), 우리말로는 신장 트리라고도 하는데요. 
	 * 무향 연결 그래프가 있을 때 그 그래프에서 간선을 부분적으로 뽑아 연결해서, 본래의 그래프의 모든 정점을 가지고 있는 트리입니다.
	 * '그래프에서' 모든 정점에 대한 트리를 만든다면 그것을 신장트리라고 부릅니다. 트리이기 때문에 여전히 연결 그래프이고, 트리이기 때문에 간선 개수는 V-1개로 됩니다.
	 	----------------------------------------------------------------
	 * 최소 스패닝 트리(MST, Minimum ST)는 트리의 간선마다 가중치(cost)가 있을 때, 간선의 가중치 합이 최소인 스패닝 트리입니다.
	 * 하나의 그래프에 대해서 스패닝 트리는 당연히 여러 개일 수 있으며, MST는 그보다 이하의 개수를 가지게 됩니다. 물론 MST도 단 하나가 아닐 수 있습니다. 
	 * MST를 뽑는 작업은 특정 지점에서 다른 지점으로 최단의 루트를 제공해주는 '최단경로'와는 좀 다르게, 
	 * 그냥 모든 목표물을 연결하기만 하면 되고 그 전체 연결 비용만 최소면 됩니다.
	 * 이 말로 어? 아니 그냥 최단경로인거랑 MST랑 둘다 같은 거 아니에여?! 아닙니다. 정확히 말하면 같을 수는 있어도 항상 같지 않습니다. 애초에 정의가 다릅니다.
	 * 예를 들어 최단경로는 A지점이라는 기준을 잡고 해당 기준에서 비용(혹은 거리값)를 고려해 각 정점으로 갈 수 있는 최단 경로가 무엇일지 알려줍니다. 
	 * 이 말은 A기준에서는 항상 최단의 경로만 알려주는 형태가 될뿐입니다.
	 * 반대로 MST는 어디 기준을 잡는 것이 아닌 트리 전체를 보았을때 스패닝트리를 구성하는데 있어서 전체 비용이 가장 낮은 스패닝 트리를 의미합니다. 
	 * 단순히 트리를 구성을 위해 최소 비용의 간선만 선택하는 것도 아니며 전체 비용이 작아지기만 하면 오케이가 됩니다.
	 * 이는 A라는 정점에서 다른 정점으로 갈때 최소가 된다는 보장이 전혀 안됩니다. MST는 그런 상황을 생각하고 만든 것이 아니라서 최단경로로서 보면 절대 안됩니다.
	  
	 * 아래 출처에 그림을 보면 알 수 있을텐데, A라는 기준으로 만든 최단경로와 MST는 다른 모습을 보이게 됩니다.  
	 * https://blog.naver.com/kks227/220799105543
	    ----------------------------------------------------------------
	 * 최단경로문제에 비해 매우 보기 힘든 문제 유형입니다.
	 * MST를 뽑는 알고리즘은 프림, 솔림, 크르수칼 알고리즘이 있습니다. 
	 * 우리는 가장 많이 쓰이고 빠른 편인 크루스칼 알고리즘(Kruskal's algorithm)만 써보겠습니다. 
		----------------------------------------------------------------
	 * 크루스칼 알고리즘은 가장 가중치가 작은 간선부터 훑는다는 점에서 일종의 그리디 알고리즘 측면도 있습니다. 
	 * (참고로 저렇게 욕심그득하게 접근하는 조건이면 그리디적인 측면이 있다고 보면 됩니다.) 
	 * 그리디적 생각을 갖고 진행하는구나 라고. 반드시 어. 이건 그리디 알고리즘이네? 가 아니라 조건상에 그리디적인 측면을 갖고 접근해보는구나 라고 받아드리면 된다.
	 * 이렇듯 다른 모든 알고리즘과 여러 방법들도 다 이렇게 오호 이런 개념을 갖고 여기에 붙여서 진행해보는구나 이다. 무조건 이건 DP다 그리디이다. 이렇게 고지식한게 아니다ㅎㅎ;; DP에도 조건상에 그리디가 있을수있고, 바이너리할때도 DP를 이용하기도하며 모든 상호작용이 될수있다.
	 * 그리니 크루스칼 알고리즘은 크게보면 조건상에서 그리디 알고리즘 개념을 이용해서 진행을 하는구나 알수있다. 그리디알고리즘의 분류로도 포함이 될수있구나 이다. (다른 모든 알고리즘은 여러 분류에도 포함되기도 할수있다. 물론 안그럴수있다해도 그저 말이 이렇다는거다.)
	 	----------------------------------------------------------------
	 * 크루스칼 알고리즘의 작동방식은 이렇습니다. 
	 * 1. 간선들을 가중치 순으로 오름차순 정렬하고 정점들을 각 컴포넌트로 초기화한다.
	 * 2. 간선들을 훑으면서 양쪽 정점을 포함한 컴포넌트가 연결되어 있지 않으면 간선을 뽑고 연결한다.
	 * 3. 간선 V-1개가 뽑혔을 때, 그 간선들과 정점들이 이루는 그래프가 MST이다. 
	 * 정렬을 한다고 할때 가중치 기준으로 한다는데, 어떠한 기준으로 제시된게 없습니다. 오름차순으로 해도 되고, 내림차순으로 해도 됩니다. 
	 * 덕분에 결과로 나올 수 있는 MST가 여러개 일수 있습니다. 물론 가중치의 합은 같을 것입니다.  
	 ===================================================================**/
}
