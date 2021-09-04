package String_ALG__KMP_and_TRI;
import java.util.*;
import java.io.*;

//���� ������ �� �¾Ҵµ� Ʋ�Ⱦ���. �� ������ ����� ���ĵ� ���·� ���� �߳��Դµ�, ����� ���׹����� ���·� ���ڿ��� ������ �ȵǰ� ���Դ�.
//���ڿ��� �⺻������ ���������� ������ �����ϴ�. �׷��� �ȵȰ��� HashMap�� ���� ���� ���ظ� �ϰ��־���. ��ġ �̾ȿ� ���� ���ĵ� �Ǵ� �� �˾Ҵ�.
//������ ����� �ƴϴ�.
//HashMap�� ���� Hash���� ���� Key������ ������ Ư���� ��Ģ ���� ����� �˴ϴ�.
//���� TreeMap�� �̿��� KeySet()���� �����´ٸ� Key������� ���ĵ� ���·� �����ɴϴ�.
//Map���� Key�� ���� ������ ���Ѵٸ� HashMap�� TreeMap�� ����ؼ� Key���� �������� ������ ���� �� �ִ�.

/*Ʈ���̱����� Ȱ���ؼ� ������ Ǯ��Ҵ�.*/

//14725�� - ���̱� 
public class p5__Antcave {
	/*Ŭ���� ����*/
	static StringBuilder sb = new StringBuilder();
	//==============================================================================
	/*Ʈ���� ���*/
	static class TrieNode{
		//�ڽ� ��� ��
		private Map<String, TrieNode> childNodes = new HashMap<>();	//���̶��� ���̺�� �����ϸ� �Ǵ°���. �� '����'���� �������� node�� ���� ���۷��� �ּҰ��� �˷��ִ� ���̺�
		//������ �������� ����
		private boolean isLastChar;

		/** [ GETTER / SETTER �޼��� ] **/
		//�ڽ� ��� �� Getter
		Map<String, TrieNode> getChildNodes() {
			return this.childNodes;
		}
		//������ �������� ���� Getter
		boolean isLastChar() {
			return this.isLastChar;
		}
		//������ �������� ���� Setter
		void setIsLastChar(boolean isLastChar) {
			this.isLastChar = isLastChar;
		}
	}//==============================================================================
	/*Ʈ���� ����*/
	static class Trie{
		//��Ʈ ���
		private TrieNode rootNode;
		//������
		Trie() {
			rootNode = new TrieNode();
		}

		/** �޼ҵ� **/
		//�ڽ� ��� �߰�
		public void insert(String[] words) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i<words.length; i++) {
				thisNode = thisNode.getChildNodes().computeIfAbsent(words[i], c -> new TrieNode());
				// words[i]�� ������ ���̺� ���ٸ� �� ���ڸ� ���̺� �߰��ϰ� ���ο� ���̺��� �����, ���� ���� ���̺��� ��ȯ���ִ� �޼ҵ��̴�.
				// ���� �ִٸ� �� ��带 �Ѱ��ش�. 
				// ��ȯ�Ѱ��� thisnode�� �ٽ� �Ѱ� �����鼭 ��������� �����Ҽ��ְ� �ִ°� ��� �˷��ְ�, ������ �߰����ָ鼭 �� �����Ѵ�.
				// ����.
			}	
			thisNode.setIsLastChar(true);
		}
		//DFS������ ����� �ϴ� ���
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
		//�ܺο��� ȣ�⹮ 
		public void print() {
			printNode(0, this.rootNode);
		}

	}//==============================================================================
	/*main�Լ�*/
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
