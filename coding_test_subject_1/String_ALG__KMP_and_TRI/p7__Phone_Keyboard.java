package String_ALG__KMP_and_TRI;

import java.util.*;
import java.io.*;
/*내가 잘 풀었다. 이 문제는 중복된 단어는 절대로 안나온다는 가정하에 풀 수 있다. 문제에서도 제시해주고 있으며 만약 중복된 단어도 나온다면 계산시간이 더 늘어나는 건 확정이다. 중복임을 확인하고 돌아오는 작업도 있어서다.*/
// 5670번 - 휴대폰 자판
public class p7__Phone_Keyboard {
	static TrieNode root_node;	//루트에 대해서 이렇게 생각하면 직관적일 수 있다. 단어의 0번째 자리를 맡을 노드이며, 0번째 들어올수있는 낱말의 종류는 자신의 map에 기록되어있다. 
								//그 낱말은 골랐다면 1번째 들어올수 있는 노드를 그 낱말로 가리킨 노드로 간다. 그 노드의 map에는 1번째 낱말이 될 내용이 존재한다. 
	static String[] words;
	static int N;
	//==========================================================
	static class TrieNode {
		private Map<Character, TrieNode> childmap = new TreeMap<>();
		private int visitcount = 0;		//이 노드를 호출한 이전 노드에서부터 몇개의 단어로 파생될 수 있는지에 대한 개수이다. 이 정보로 만약 visitcount가 1로 나왔다면 이것을 포함한 다음 낱말은 모두 1로만 나올것이다. 따라서 더이상 진행안해도 자동입력 처리가 된다.
		private boolean isend = false;	//현재 노드를 호출한 이전 노드까지가 단어인지 확인해주는 곳. 이 노드를 호출한 그 이전 노드(자리)가 가지고있는 낱말까지가 하나의 단어를 완성함을 알려준다. 
	}//==========================================================
	
	static void insert(String insertword) {
		TrieNode now_node = root_node;
		int len = insertword.length();
		char[] word = insertword.toCharArray();
		
		for(int i=0; i<len; i++) {
			now_node = now_node.childmap.computeIfAbsent(word[i], c->new TrieNode());
			now_node.visitcount++;
		}
		now_node.isend = true;	
	}//==========================================================
	
	static int execute(String findword) {
		TrieNode now_node = root_node;
		int len = findword.length();
		char[] word = findword.toCharArray();
		//System.out.println("루트 "+now_node.visitcount); //루트는 
		
		//맨처음을 미리 한번 시작해준다. 처음은 무조건 클릭을 해준다는 규칙을 만들었다. 그리고 사전에 있는걸 다시 입력하는 것이니 모두 존재하는 것만 나온다.
		now_node = now_node.childmap.get(word[0]);	//첫 번째 낱말에 방문.
		int result = 1;	//처음 낱말을 포함한 시작
		
		//두 번째 입력부터 생략이 되는지 파악
		for(int i=1; i<len; i++) {
			/* 단어의 i번째 자리를 맡을 노드에 대해 살펴본다. */
			//System.out.println(word[i-1]+" "+now_node.visitcount); //현재 방문한 곳이 사전의 등록을 위해 몇번의 이용이 되었는지(몇개의 단어로 파생될 수 있는지)
			
			/*현재 노드자리에 방문할 낱말이 사전상 등록을 위해 1번만 이용이 된 경우에 이 낱말을 포함해서 그 다음 모든 트라이 구조는 '1개의 단어'를 위해 존재할 뿐이라면.*/
			if(now_node.visitcount==1) {	
				return result;	//따라서 더이상의 글자 입력 없이 자동완성을 시켜주면 된다.
			}
			/*현재노드에 방문할 낱말이 여러 단어(2개 이상)로 파생이 될수있는 경우에 본다. now_node.childmap은 그 다음 방문할 낱말의 종류가 담겨있다.*/
			else if(now_node.childmap.size()>1 || now_node.isend) { 	
				/* ( now_node.childmap.size()>1 || 
				 		(now_node.childmap.size()==1 && now_node.isend) ) 을 가리키는 내용을 코드상 줄이고 시간을 단축할수있어서 저렇게 해두었다.
				'i번째 자리을 나타낼 낱말'이 하나의 낱말만 존재하면 다음 낱말은 입력 카운트는 생략하고 그 낱말로 방문 할 수 있다. 
				그런데 이곳의 if조건에서는 '현재의 방문한 낱말'에서 적어도 '2개 이상의 단어'가 나온다는 보장을 앞의 visitcount==1 이라는 if조건을 통해 확정 지을수있다.
				
				여기서 앞으로 나올 낱말이 1개 일지라도, '현재의 노드'의 isend가 true라면 바로 이전 노드에서 선택된 낱말까지는 하나의 단어의 완성임을 알수있다. 
				그리고 현재의 노드에서 선택될 다음 낱말은 그 단어와 구분짓기위해, 정확히는 그 단어는 이제 엔터만 치면 단어가 끝나는 상황이고... 우리는 이제 우리에게 맞는 단어로 찾아 떠너야하는 상황이다. 그래서 우리가 떠나야할 낱말임을 알려주는 것이된다. 
				다시말해 이 노드에서 해당 낱말이 더 진행되기 위해서 반드시 눌러져야하는 낱말임을 알려주게된다.*/
				
				/** 이해가 가장 좋은 예시이다.
				 ab
				 abcd
				 abcdef 
				 
				 * 트라이구조 이기에  'root노드(a)' - 'b를가리킬수있는 노드(b)' - 'c를 가리킬수있는 노드(c)' - ... - 'f를 가리키는 노드(f)'로 일렬로 이어진다. 그래서 각각 now_node.childmap.size()는 1이다.
				 * 그리고 노드에서 isend가 찍혀 있는 곳은 노드 기준으로 c에 해당하는 노드, e에 해당하는 노드, 그리고 f에서 파생되어 아직 어떠한 map을 가지지 못한 마지막 만들어진 노드로 3개가 존재한다.
				 * 여기서 c에 해당하는 노드에 도달하면 이곳에 isend==true이라는 정보를 확인하는데 이는 "아 이전까지인 ab라는 단어가 존재하는 구나. 
				 * 따라서 우리는 엔터로 끝나지 않고 더 진행할려고 이곳까지 방문도 한것이니 더 진행할려면 이곳 c를 꼭 눌러야 된다. 그래야 c에 해당하는 진행으로 갈수있기떄문이다." 라고 알려주게 된다.
				 * */	
				result++; //현재에 들어갈 낱말이 입력이 되어야 한다. 혹은 계속된 생략에서 가장 먼저 생략된게 들어갔다고 체크해준다.
			}
	
			/*다음 낱말(i번째)로 방문을 한다.*/
			now_node = now_node.childmap.get(word[i]);
		}
		
		return result;
	}//==========================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while( (str=br.readLine())!=null && str.length()!=0) {
			N = Integer.parseInt(str);//사전에 속한 개수
			words = new String[N];
			root_node = new TrieNode();
			for(int i=0; i<N; i++) {
				words[i] = br.readLine();
				insert(words[i]);
			}
			
			float results = 0;
			for(int i=0; i<N; i++) 
				results += execute(words[i]);
			
			System.out.printf("%.2f\n", results/N);
		}
		
	}//==========================================================

}
