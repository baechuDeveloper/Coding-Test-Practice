package A_experiment;
import java.util.*;

/*
 * �̷��� �ϴ� ������ ���� �־ x�� y�� ������ ���� ���ٰ� ǥ���ϰ������ �Ϲ������� �ش� �ؽ��ڵ尪�� �ٸ��⿡ 
 * */

// ��Ŭ���� source���� generate hashcode() and equals()��� �׸��� NodeŬ�������� Ŀ���� �д��� �����ϸ� �ϼ������ִ� ��ɵ� �ִ�.
// ���� '=='�� ���۷��� Ÿ���̶�� ���۷��� �ּҰ��� �������� Ȯ���Ѵ�. new�� ���� ��������ٸ� �翬�� ������ �ּ� ���۷��� ���� �޶�پ ���� ��ü�� ���� �ƴϴ�.
// �ٸ� �� '���빰'�� ���� �͸� �˰�ʹٸ� equals()�� ���빰�� �������� ���µ� � ���۷��� Ÿ���� hashcode()�� �������� ���ϱ⵵ �Ѵ�.
// ���� ���۷��� Ÿ�Կ��� ���빰�� �������� Ȯ���ϱ����ؼ� equals()�� hashcode()�� '����' �ٲ�߸� �Ϻ��� ���빰�� ������ ǥ������ �� �ִ�. 
public class Equals_and_Hashcode {

	
	/** ���� �ڵ��׽�Ʈ�� ���� ����� �ϱ����ؼ� **/
	//����Ŭ������ ������ staticŬ���� - Equals_and_Hashcode Ŭ�����κ��� ���������� ���� ����
	static class ForTest{
		int x, y;
		ForTest(int x, int y){
			this.x=x; this.y=y;
		}
			
		/** �ٵ� ��¥ �ű��ϰԵ� static class���� IDE���� �������ִ� equals�� hashcode������ �غ��� 
		 * �ڵ����� ���� �Ʒ��� ����� �͵��� ��������.
		 * ���� �����غ��� �Ƹ� static�� ����ü�� ���������� �����ϴ� �� ����. ����Ŭ���� �ΰͿ� �������.**/
		
		/* ----------------------------------------------------
		 * IDE�� ������� x�� y�� ���� getOuterType()�� ���ְ� hashcode()�� equals()���� ���� �κ��� �ּ�üũ�ߴ�.
		 * �ڵ��׽�Ʈ���� static���� ����Ŭ������ ����� ���� ������ static�� �ܺ�Ŭ������ �ν��Ͻ��� �������ִµ� ������ �ֱ⿡ getOuterType()�� ������ �ʴ´�.
		 * �ᱹ ���� �ϳ��� Ŭ���� Equals_and_Hashcode������ ������ Ǯ������ �۾��� �ϴ� 
		 * �ܺ� �ν��Ͻ��� �׻� �����ϴٴ� �����̸鼭 �����ε� �׻� �����ϸ� equals()���� �� �۾��� ���ܵ� �ܺ� �ν��Ͻ� ���⿡ �Ѿ�� �ȴ�.
		 * ���� �ܺ� �ν��Ͻ��� �׻� ���⿡ ���빰�� ���� �ؽ��ڵ带 �����ϰ� �ִ°͵� �ش� �κ��� ��� ������ �ؽ��ڵ带 ������ �� �� �ִ�.
		 * ��� ���� Ŭ���������� �۾��� �Ѵٴ� �����Ͽ� ��� ������ �ȴ�. 
		 * ���� �ڵ��׽�Ʈ������ �̷��� equals�� hashcode�� �����ϸ� �� ���̴�. 
		 *-----------------------------------------------------*/
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			/*result = prime * result + getOuterType().hashCode();*/
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		// ���� �̰������� ����ҰŶ�� �Ű������� Node obj�� �ؼ� �����ص� �ȴ�. �翬�� �������̵��̴ϱ�.
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())	//���� Ŭ��������
				return false;
			ForTest other = (ForTest) obj;
			/*if (!getOuterType().equals(other.getOuterType()))
				return false;*/
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}//=====================================================
	
	/** �Ϲ������� ������ �Ҷ� IDE���� ������� �����̸� ����ϴ�. **/
	//����Ŭ����
	class Node {
		int x, y;
		Node(int x, int y){
			this.x=x; this.y=y;
		}
		
		// IDE�� ������� x�� y�� ���� hashcode()�� equals()
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())	//���� Ŭ��������
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		//�̰͵� IDE���� ������� ���̴�. 
		//����Ŭ������ equals�� hashcode�� ����ٸ� ��Ȯ�� �ش� ����Ŭ������ �ѷ��� �ν��Ͻ�(�ܺ�Ŭ����, �̰� �ܺ�Ŭ����.this�� �ν��Ͻ��μ� ����)�� ���������� �����Ѵ�. 
		//�� Ǯ������ ���°��̴�.
		private Equals_and_Hashcode getOuterType() {
			return Equals_and_Hashcode.this;
		}
		
	}//=====================================================
	
	
	static class A{
		int a, b;
		A(int a, int b){
			this.a=a; this.b=b;
		}
	}//=====================================================
	
	
	public static void main(String[] args) {
		System.out.println("����Ŭ���� Node");
		Equals_and_Hashcode obj = new Equals_and_Hashcode();
		Node a = obj.new Node(1, 1);
		Node b = obj.new Node(1, 1);
		//���⼭ �˾Ҵµ� ���� ��Ư������ ���� print�� a�� �ϴ°� a.toString()�ϴ°Ͱ� �����ϴ�. 
		//�� String�����δ� � Ŭ�������� �Դ��� �˷��ְ� �� �ڿ� 'hashcode'���� �˷��ٻ��̴�. 
		//���� �ű��ϰ� �츮�� ������ �Ͽ� �̹� ���빰�� ������ �־ �ű��ϰԵ� ���� �ؽ��ڵ尪�� ���� �����ִ�.
		//�� �츮���� ���۷��� �ּҰ��� �˷����� �ʾҴ�. �׷����� �翬�� a�� b�� �� ���۷��� �ּҰ��� �ٸ���. �� new������ ������.
		System.out.println("���۷��� �ּҰ��� �ƴ� �ؽ��ڵ带 �˷��ֳ�\n"+a.toString()+"\n"+b);
		//�ٸ� '=='�� ���� ���۷��������� �ּҰ����� �˾Ƴ��⿡, �翬�� ���� ���۷��� �ּҰ��� �ƴ϶� ���� �ʴ�.
		if(a==b) System.out.println("���۷��� ���� ����.");
		else System.out.println("���۷��� ���� �翬�� �ٸ���.");
		if(a.equals(b)) System.out.println("���� ���빰�̴�.");
		else System.out.println("�ٸ� ���빰�̴�.");
		//set�� �ؽ��ڵ�� ���Ͽ� �ߺ��� üũ�Ѵ�.
		Set<Node> s1 = new HashSet<>();
		s1.add(a);
		s1.add(b);
		System.out.println(s1.size()); System.out.println();
		
		System.out.println("staticŬ���� A - equals�� hashcode ���� ����");
		A aa = new A(1, 1);
		A bb = new A(1, 1);
		//���⼭�� �������� hashcode���� �˷��ִµ� ���� ���� �۾��� ���ؼ� ���� �ٸ� �ؽ��ڵ� ���� �˷��ش�.
		System.out.println("���۷��� �ּҰ��� �ƴ� �ؽ��ڵ带 �˷��ֳ�\n"+aa+"\n"+bb);
		if(aa==bb) System.out.println("���۷��� ���� ����.");
		else System.out.println("���۷��� ���� �翬�� �ٸ���.");
		if(aa.equals(bb)) System.out.println("���� ���빰�̴�.");
		else System.out.println("�ٸ� ���빰�̴�.");
		//set�� �ؽ��ڵ�� ���Ͽ� �ߺ��� üũ�Ѵ�.
		Set<A> s2 = new HashSet<>();
		s2.add(aa);
		s2.add(bb);
		System.out.println(s2.size()); System.out.println();
	
		System.out.println("staticŬ���� ForTest - equals�� hashcode ���� ��");
		ForTest ta = new ForTest(1, 1);
		ForTest tb = new ForTest(1, 1);
		//���⼭�� �������� hashcode���� �˷��ִµ� ���� ���� �۾��� ���ؼ� ���� �ٸ� �ؽ��ڵ� ���� �˷��ش�.
		System.out.println("���۷��� �ּҰ��� �ƴ� �ؽ��ڵ带 �˷��ֳ�\n"+ta+"\n"+tb);
		if(ta==tb) System.out.println("���۷��� ���� ����.");
		else System.out.println("���۷��� ���� �翬�� �ٸ���.");
		if(ta.equals(tb)) System.out.println("���� ���빰�̴�.");
		else System.out.println("�ٸ� ���빰�̴�.");
		//set�� �ؽ��ڵ�� ���Ͽ� �ߺ��� üũ�Ѵ�.
		Set<ForTest> s3 = new HashSet<>();
		s3.add(ta);
		s3.add(tb);
		System.out.println(s3.size()); System.out.println();
		
		System.out.println("map�� �����ϴ�.");
		Map<ForTest, Integer> map = new HashMap<>();
		map.put(ta, 1);
		map.put(ta, 123);
		System.out.println("���� key��� ���������.");
		System.out.println(map.size());
		System.out.println(map.get(new ForTest(1, 1)));	// �ؽ��ڵ�� ������ ǥ���ϱ⿡ �����ش�. 
	}//=====================================================

}
