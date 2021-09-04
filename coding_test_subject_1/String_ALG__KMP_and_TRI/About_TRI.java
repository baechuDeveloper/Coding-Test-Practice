package String_ALG__KMP_and_TRI;

import java.util.HashMap;
import java.util.Map;
//https://the-dev.tistory.com/3
//검색기능을 보여주는 TRI구조로 설명하겠다. 밑에 설명 참고하자.
public class About_TRI {
	/*트라이 노드*/
	static class TrieNode{
		//자식 노드 맵
		private Map<Character, TrieNode> childNodes = new HashMap<>();	//맵이란건 테이블로 이해하면 되는거지. 각 '글자'마다 다음번에 node에 대한 레퍼런스 주소값을 알려주는 테이블
		//마지막 글자인지 여부
		private boolean isLastChar;

		/** [ GETTER / SETTER 메서드 ] **/
		//자식 노드 맵 Getter
		Map<Character, TrieNode> getChildNodes() {
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
		void insert(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
				// word.charAt(i)한 글자가 현재의 테이블에 없다면 그 글자를 테이블에 추가하고 새로운 테이블을 만들고, 새로 만든 테이블을 반환해주는 메소드이다.
				// 물론 있다면 그 노드를 넘겨준다. 
				// 반환한것을 thisnode에 다시 넘겨 받으면서 계속적으로 진행할수있고 있는건 계속 알려주고, 없으면 추가해주면서 더 진행한다.
				// 좋다.
			}	
			thisNode.setIsLastChar(true);
		}
		//특정 단어가 들어있는지 확인
		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				char character = word.charAt(i);
				TrieNode node = thisNode.getChildNodes().get(character);	//해당 노드의 테이블에서 해당 글자를 갖고있다면 다음 글자에 대한 노드 주소를 넘겨준다. 
				if (node == null)	//없으면 null을 줄것이고.
					return false;
				thisNode = node;
			}
			return thisNode.isLastChar();
		}
		//특정 단어 지우기
		void delete(String word) {
			delete(this.rootNode, word, 0); // 최초로 delete 던지는 부분
		}
		//원본
		private void delete(TrieNode thisNode, String word, int index) {
			char character = word.charAt(index);
			TrieNode childNode = thisNode.getChildNodes().get(character);
			index++;
			if(index == word.length()) {
				if (!childNode.getChildNodes().containsKey(character)) throw new Error("There is no [" + word + "] in this Trie.");
				if (!childNode.isLastChar()) throw new Error("There is no [" + word + "] in this Trie.");
				childNode.setIsLastChar(false);
				if (childNode.getChildNodes().isEmpty())
					thisNode.getChildNodes().remove(character);
			}else {
				delete(childNode, word, index); // 콜백함수부분
				if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
					thisNode.getChildNodes().remove(character);
			}
		}

	}//==============================================================================

	/**
	 * TrieNode.java *

	 * TrieNode는 자식노드맵과 현재 노드가 마지막 글자인지 여부에 대한 정보를 가지고 있습니다.
	 * 여기에서 마지막 글자 여부란 'DEV'라는 단어에서 
	 * [D], [E]는 마지막 글자가 아니지만 [V]는 마지막 글자로, 한 단어가 완성되는 시점임을 알 수 있도록 하는 불린 값입니다.


	 * 이렇게 두 변수가 할당되었으면 이 변수에 접근할 수 있는 Getter와 Setter가 필요합니다.
	 * 자식노드는 Trie 차원에서 생성해서 넣을 것이기 때문에 Getter만 생성해 줍니다.
	 * 마지막 글자 여부는 추후 노드 삭제하는 과정에서 변경이 필요하기 때문에 Getter와 Setter를 둘 다 생성해 줍니다.
	 =============================================================================

	 * Trie.java *

	 * Trie는 기본적으로 빈 문자열을 가지는 루트노드만 가지고 있습니다.
	 * 이 후에 나올 insert 메서드를 통해 단어를 넣음으로써 그에 맞게 자식 노드가 생성됩니다.

	 * 탐색 진행방향 : 부모 노드 → 자식 노드
	 * 삭제 진행방향 : 자식 노드 → 부모 노드
	 */

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
