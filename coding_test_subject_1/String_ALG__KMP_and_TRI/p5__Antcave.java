package String_ALG__KMP_and_TRI;
import java.util.*;
import java.io.*;

//문제 구성은 다 맞았는데 틀렸었다. 그 이유는 어떨때는 정렬된 상태로 값이 잘나왔는데, 어떨때는 뒤죽박죽인 상태로 문자열로 정렬이 안되게 나왔다.
//문자열도 기본적으로 사전순으로 정렬이 가능하다. 그런데 안된것은 HashMap에 대해 내가 오해를 하고있었다. 마치 이안에 들어가면 정렬도 되는 줄 알았다.
//하지만 절대로 아니다.
//HashMap은 내부 Hash값에 따라 Key순서가 정해져 특정한 규칙 없이 출력이 됩니다.
//따라서 TreeMap을 이용해 KeySet()으로 가져온다면 Key순서대로 정렬된 상태로 가져옵니다.
//Map에서 Key에 의한 정렬을 위한다면 HashMap을 TreeMap을 사용해서 Key값을 기준으로 정렬을 해줄 수 있다.

/*트라이구조를 활용해서 문제를 풀어보았다.*/

//14725번 - 개미굴 
public class p5__Antcave {
	/*클래스 변수*/
	static StringBuilder sb = new StringBuilder();
	//==============================================================================
	/*트라이 노드*/
	static class TrieNode{
		//자식 노드 맵
		private Map<String, TrieNode> childNodes = new HashMap<>();	//맵이란건 테이블로 이해하면 되는거지. 각 '글자'마다 다음번에 node에 대한 레퍼런스 주소값을 알려주는 테이블
		//마지막 글자인지 여부
		private boolean isLastChar;

		/** [ GETTER / SETTER 메서드 ] **/
		//자식 노드 맵 Getter
		Map<String, TrieNode> getChildNodes() {
			return this.childNodes;
		}
		//마지막 글자인지 여부 Getter
		boolean isLastChar() {
			return this.isLastChar;
		}
		//마지막 글자인지 여부 Setter
		void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}//==============================================================================
	/*트라이 구조*/
	static class Trie{
		//루트 노드
		private TrieNode rootNode;
		//생성자
		Trie() {
			rootNode = new TrieNode();
		}

		/** 메소드 **/
		//자식 노드 추가
		public void insert(String[] words) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i<words.length; i++) {
				thisNode = thisNode.getChildNodes().computeIfAbsent(words[i], c -> new TrieNode());
				// words[i]가 현재의 테이블에 없다면 그 글자를 테이블에 추가하고 새로운 테이블을 만들고, 새로 만든 테이블을 반환해주는 메소드이다.
				// 물론 있다면 그 노드를 넘겨준다. 
				// 반환한것을 thisnode에 다시 넘겨 받으면서 계속적으로 진행할수있고 있는건 계속 알려주고, 없으면 추가해주면서 더 진행한다.
				// 좋다.
			}	
			thisNode.setIsLastChar(true);
		}
		//DFS적으로 출력을 하는 방법
		private void printNode(int depth, TrieNode nowNode) {
			StringBuilder level = new StringBuilder();
			for(int i=0; i<depth; i++) level.append("--");	
			
			Map<String, TrieNode> nowChild = nowNode.getChildNodes();
			TreeMap<String, TrieNode> sortChild = new TreeMap<>(nowChild);
			Set< Map.Entry<String, TrieNode> > entries = sortChild.entrySet();
			
			for(Map.Entry<String, TrieNode> i : entries) {
				sb.append(level.toString() + i.getKey()+"\n");
				printNode(depth+1, i.getValue());
			}
		}
		//외부에서 호출문 
		public void print() {
			printNode(0, this.rootNode);
		}

	}//==============================================================================
	/*main함수*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
		int N = Integer.parseInt(br.readLine());
		Trie root = new Trie();
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String[] words = new String[n];
			for(int i=0; i<n; i++) 
				words[i] = st.nextToken();
			root.insert(words);
		}
		root.print();
		bw.write(sb.toString());
		bw.flush();
	}//==============================================================================

}
