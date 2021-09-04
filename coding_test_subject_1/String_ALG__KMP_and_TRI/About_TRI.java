package String_ALG__KMP_and_TRI;

import java.util.HashMap;
import java.util.Map;
//https://the-dev.tistory.com/3
//�˻������ �����ִ� TRI������ �����ϰڴ�. �ؿ� ���� ��������.
public class About_TRI {
	/*Ʈ���� ���*/
	static class TrieNode{
		//�ڽ� ��� ��
		private Map<Character, TrieNode> childNodes = new HashMap<>();	//���̶��� ���̺�� �����ϸ� �Ǵ°���. �� '����'���� �������� node�� ���� ���۷��� �ּҰ��� �˷��ִ� ���̺�
		//������ �������� ����
		private boolean isLastChar;

		/** [ GETTER / SETTER �޼��� ] **/
		//�ڽ� ��� �� Getter
		Map<Character, TrieNode> getChildNodes() {
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
		void insert(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
				// word.charAt(i)�� ���ڰ� ������ ���̺� ���ٸ� �� ���ڸ� ���̺� �߰��ϰ� ���ο� ���̺��� �����, ���� ���� ���̺��� ��ȯ���ִ� �޼ҵ��̴�.
				// ���� �ִٸ� �� ��带 �Ѱ��ش�. 
				// ��ȯ�Ѱ��� thisnode�� �ٽ� �Ѱ� �����鼭 ��������� �����Ҽ��ְ� �ִ°� ��� �˷��ְ�, ������ �߰����ָ鼭 �� �����Ѵ�.
				// ����.
			}	
			thisNode.setIsLastChar(true);
		}
		//Ư�� �ܾ ����ִ��� Ȯ��
		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			for (int i = 0; i < word.length(); i++) {
				char character = word.charAt(i);
				TrieNode node = thisNode.getChildNodes().get(character);	//�ش� ����� ���̺��� �ش� ���ڸ� �����ִٸ� ���� ���ڿ� ���� ��� �ּҸ� �Ѱ��ش�. 
				if (node == null)	//������ null�� �ٰ��̰�.
					return false;
				thisNode = node;
			}
			return thisNode.isLastChar();
		}
		//Ư�� �ܾ� �����
		void delete(String word) {
			delete(this.rootNode, word, 0); // ���ʷ� delete ������ �κ�
		}
		//����
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
				delete(childNode, word, index); // �ݹ��Լ��κ�
				if(!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
					thisNode.getChildNodes().remove(character);
			}
		}

	}//==============================================================================

	/**
	 * TrieNode.java *

	 * TrieNode�� �ڽĳ��ʰ� ���� ��尡 ������ �������� ���ο� ���� ������ ������ �ֽ��ϴ�.
	 * ���⿡�� ������ ���� ���ζ� 'DEV'��� �ܾ�� 
	 * [D], [E]�� ������ ���ڰ� �ƴ����� [V]�� ������ ���ڷ�, �� �ܾ �ϼ��Ǵ� �������� �� �� �ֵ��� �ϴ� �Ҹ� ���Դϴ�.


	 * �̷��� �� ������ �Ҵ�Ǿ����� �� ������ ������ �� �ִ� Getter�� Setter�� �ʿ��մϴ�.
	 * �ڽĳ��� Trie �������� �����ؼ� ���� ���̱� ������ Getter�� ������ �ݴϴ�.
	 * ������ ���� ���δ� ���� ��� �����ϴ� �������� ������ �ʿ��ϱ� ������ Getter�� Setter�� �� �� ������ �ݴϴ�.
	 =============================================================================

	 * Trie.java *

	 * Trie�� �⺻������ �� ���ڿ��� ������ ��Ʈ��常 ������ �ֽ��ϴ�.
	 * �� �Ŀ� ���� insert �޼��带 ���� �ܾ �������ν� �׿� �°� �ڽ� ��尡 �����˴ϴ�.

	 * Ž�� ������� : �θ� ��� �� �ڽ� ���
	 * ���� ������� : �ڽ� ��� �� �θ� ���
	 */

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
