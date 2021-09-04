package Lowest_Common_Ancestor;

public class About__LCA {

		/** 
		 * 아래 블로그 설명이 굉장히 잘 되어있다. 존경한다.
		 * https://m.blog.naver.com/kks227/220820773477
		 * 트리에 관한 걸 조금 더 쓰려고 합니다. 이번에 내용은 '최소 공통 조상'(Lowest Common Ancestor, LCA)입니다.
		 * LCA란, '트리'상에서 어떤 두 정점 u, v가 있을 때, u이거나 u의 조상이면서 동시에 v이거나 v의 조상인 노드들 중 가장 깊은 노드입니다.
		 * (조상은 자신의 부모의 부모의 부모의...부모로 갈수있는 계통, 즉 이런 것을 조상이라고 합니다.)
		 * 너와 나의 공통 조상중 가장 깊은(depth가 높은)것은 다시말해 너와 나에게 있어서 '너와 나'의 혈통을 나누게 만든 장본인 입니다.
		 
		 * LCA를 찾는 방법은 여러 가지가 있는데, 세그먼트 트리를 사용하는 방법도 있지만 우리의 Ries 마리오씨는 다른 방법을 선호합니다. 그래서 나도 저걸 배울겁니다.
		 * "DP를 사용하여 바텀업으로 각 정점의 정보들을 저장해 놓는 방식".  
		 * 두 방법 모두 크기 N인 트리에서 O(logN)만에 두 특정 정점의 LCA를 찾을 수 있습니다. 우리는 DP로 알아보죠.
		  
		 * LCA를 가장 단순하고 쉽게 찾는 방법은 그냥 두 정점 중 깊이가 더 깊은 정점에서 계속 부모로 이동합니다. 둘의 깊이가 같아질 때까지.
		 * 그리고 두 정점이 만날 때 까지 두 정점을 동시에 부모로 이동시키면, 두 정점이 만나는 지점이 LCA가 됩니다. 
		 * 그러나 이건 최악의 경우 O(N)입니다. 실제로는 맨날 나오지는 않아도 대회문제라면 반드시 나오겠죠!
		 * 기본적인 알고리즘은 위와 동일하지만, 부모로 이동시키는 것을 좀 더 빨리, 더 많이 건너 뛰는 것이 아이디어 입니다.
		 
		 * 희소테이블 배우고 오세요~ ㅔ
		 * 
		 * 
		 * 
		 * */
	
}
