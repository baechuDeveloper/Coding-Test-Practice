package String_ALG__KMP_and_TRI;
import java.io.*;
import java.util.*;
//Set�����ϸ� �ſ�ſ�ſ� ������ ������ Ʈ���̱����� ������ �غ���.
/**------------------------------------------------------------------------------------ 
 * ���� �߿��ߴ��� �� ��찡 ���� Ư���� ���̽���� ���̴�. HashMap�� �Ϲ����� �������� ��Ȳ���� �� ������ ���̽� ��ŭ�� TreeMap�� �� ������ ���μ� ���̴�.
 * �̷��� ���̽��� �����ϴ� ��쿡�� �ְ��� ��Ȳ���� TreeMap�� ������ �ۿ��� �� �� �ִٴ� �� �����.
 * 
 * �ʹ����� �� ���ڿ��� �ϳ��� �ϴٺ��� �ð��� �ɷ��� �־־־ִ��� �ð��� �ٿ������� �Ѵ�. 
 * main���� ������ ��ü�� ���� �Լ� ȣ���� �ƴ� �� Ŭ���� �ȿ��� ȣ��� �����ϴ� ���̴�.
 * �� TrieŬ������ �̰���ü�μ� ���� ��Ű�� ���̴�. �� Ŭ���� ��ü�� Trie�� �Ǵ� ���̴�. �̷��� �ؾ� ���� ���� ȣ���� �ȴ�. �ڱ� �ȿ��� �ڽ��� �޼ҵ带 ȣ���ϴ� ���� ���� ������.
 * 
 * ���ÿ� word.charAt(i)�� �ϳ��� ȣ�� �ϴ°��� �����ϰ� word.toCharArray()�� ���� �迭�� �����ϰ��Ѵ�.
 * 
 * ���������� TreeMap()���� �ڽĵ��� �ޱ�. �Ϲ������� HashMap�� �˻��� ���� O(1)�� �������ְ�, TreeMap�� �־����� O(log(n))���� ����� �� �־
 * HashMap���� �۾��ϴ°� �Ϲ����̰� �� ������Ȳ�� ����. �ٸ� �ش� ���������� �Ƹ��� �������� �����Ǵ� ���ڿ��� �� ������� �� ���ȿ� ���� 
 * '���� ���Ե�, Ȥ�� �쿬����, Ȥ�� �� �������� ��ŭ��' TreeMap�� ���� ������� �� �����ϰ� ������ �����ߴٰ� �����ִ�.
 * �׸��� �� �������� ��ŭ�� TreeMap���� �ۿ��ϴ°� �е������� ������. 
 * 
 *************************
 * ���࿡���࿡ Map�� ����� �������� '�ð��ʰ�'�� ��Ÿ���ٸ� �Ϲ������� HashMap�� ����������
 * Ȥ�ó� �ϸ鼭 TreeMap���� �� �� ����Ǵ� �������� �����ؼ� TreeMap�� �����Ѱɷ� ���൵ �غ���.
 * �ƹ�ư �� �߿� ������ ������ (�Ϲ����� ������ ������)�Ϻ��� ������ ������
 * ������üũ�� ���ؼ� �̷��� �κ��� �Ű�Ẹ�� ����� �ִ�.
 --------------------------------------------------------------------------------------*/

// 14425�� - ���ڿ� ����
public class p6__String_Set {
	private static TrieNode rootNode = new TrieNode();
	//=========================================================
	/*Ʈ���� ���*/
	static class TrieNode {
		private Map<Character, TrieNode> childNodes = new TreeMap<>();
		private boolean isLastChar = false;
		/*�� ���� �⺻���� �޼ҵ�鵵 ������� �ߴ�.*/
	}//=========================================================
	//���ڿ��� Ʈ���̱����� ���� �ϳ��� �߰����ش�. 
	static void insert(String word) {
		TrieNode nowNode = rootNode;
		char[] character = word.toCharArray();
		for(int i=0; i<word.length(); i++) {
			nowNode = nowNode.childNodes.computeIfAbsent(character[i], a->new TrieNode());
		}
		nowNode.isLastChar = true;
	}//=========================================================
	//�ش� �ܾ ���ԵǾ� �ִ���
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
	/*main�Լ�*/
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
