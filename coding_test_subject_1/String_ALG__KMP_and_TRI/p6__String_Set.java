package String_ALG__KMP_and_TRI;
import java.io.*;
import java.util.*;
//Set으로하면 매우매우매우 빠르게 되지만 트라이구조로 도전을 해본다.
/**------------------------------------------------------------------------------------ 
 * 가장 중요했던건 이 경우가 조금 특수한 케이스라는 것이다. HashMap의 일반적인 빠르기의 상황보다 이 문제의 케이스 만큼은 TreeMap이 더 빨랐던 경우로서 말이다.
 * 이러한 케이스로 존재하는 경우에는 최고의 상황에서 TreeMap이 빠르게 작용할 수 도 있다는 걸 배웠다.
 * 
 * 너무나도 긴 문자열을 하나씩 하다보니 시간이 걸려서 최애애애대한 시간을 줄여볼려고 한다. 
 * main에서 생성된 객체를 통한 함수 호출이 아닌 이 클래스 안에서 호출로 진행하는 것이다.
 * 즉 Trie클래스를 이곳자체로서 적용 시키는 것이다. 이 클래스 자체가 Trie가 되는 것이다. 이렇게 해야 가장 빠른 호출이 된다. 자기 안에서 자신의 메소드를 호출하는 것이 가장 빠르다.
 * 
 * 동시에 word.charAt(i)로 하나씩 호출 하는것을 방지하고 word.toCharArray()로 만들어서 배열로 직시하게한다.
 * 
 * 마지막으롤 TreeMap()으로 자식들을 받기. 일반적으로 HashMap은 검색에 대해 O(1)로 보장해주고, TreeMap은 최악으로 O(log(n))으로 진행될 수 있어서
 * HashMap으로 작업하는게 일반적이고 더 빠른상황이 많다. 다만 해당 문제에서는 아마도 문제에서 제공되는 문자열이 그 순서대로 잘 들어옴에 따라 
 * '운이 좋게도, 혹은 우연히도, 혹은 이 문제에서 만큼은' TreeMap을 통한 입출력이 더 간결하고 빠르게 적용했다고 볼수있다.
 * 그리고 이 문제에서 만큼은 TreeMap으로 작용하는게 압도적으로 빨랐다. 
 * 
 *************************
 * 만약에만약에 Map을 사용한 문제에서 '시간초과'가 나타났다면 일반적으로 HashMap이 빠르겠지만
 * 혹시나 하면서 TreeMap으로 더 잘 적용되는 문제일지 생각해서 TreeMap을 적용한걸로 진행도 해보자.
 * 아무튼 둘 중에 무엇이 빠른지 (일반적인 생각은 맞지만)완벽한 보장은 없으니
 * 빠르기체크를 위해서 이러한 부분을 신경써보는 방법도 있다.
 --------------------------------------------------------------------------------------*/

// 14425번 - 문자열 집합
public class p6__String_Set {
	private static TrieNode rootNode = new TrieNode();
	//=========================================================
	/*트라이 노드*/
	static class TrieNode {
		private Map<Character, TrieNode> childNodes = new TreeMap<>();
		private boolean isLastChar = false;
		/*이 안의 기본적인 메소드들도 사라지게 했다.*/
	}//=========================================================
	//문자열을 트라이구조로 문자 하나씩 추가해준다. 
	static void insert(String word) {
		TrieNode nowNode = rootNode;
		char[] character = word.toCharArray();
		for(int i=0; i<word.length(); i++) {
			nowNode = nowNode.childNodes.computeIfAbsent(character[i], a->new TrieNode());
		}
		nowNode.isLastChar = true;
	}//=========================================================
	//해당 단어가 포함되어 있는지
	static boolean isContain(String word) {
		TrieNode nowNode = rootNode;
		char[] character = word.toCharArray();
		for(int i=0; i<word.length(); i++) {
			TrieNode node = nowNode.childNodes.get(character[i]);
			if(node == null) 
				return false;
			nowNode = node;
		}
		return nowNode.isLastChar;
	}//=========================================================
	/*main함수*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int answer = 0;
		
		while(N-->0) {
			insert(br.readLine());
		}
		while(M-->0) {
			if( isContain(br.readLine()) ) 
				answer++;
		}
		System.out.println(answer);
	}//=========================================================

}
