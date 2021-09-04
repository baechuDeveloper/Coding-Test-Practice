package String_ALG__KMP_and_TRI;

import java.util.*;

public class Lambda_for_HashMap {

	// Map �̶�� ���̺�
	// �� ���̺��� �̷�� �� Entry��
	// Entry�� Key�� Value�� �� �������� ǥ���Ǹ�, �ϳ��� Entry�� Map�� �� �����̴�. 
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 12 );
		map.put("b", 123);
		map.put("c", 1234);

		/**===================== �������ΰ� lambda =====================**/

		/*===================================================
		 * ��� Key�� print �ϴ� ���			
		=====================================================*/
		Set<String> keys = map.keySet();

		for(String i : keys) {
			System.out.println(i);
		}
		// or

		keys.forEach( i -> System.out.println(i) );		
		//�̰��� ���� ǥ����, �ѹ� �������� ���������. 
		//'i'�� ���� �����ϴ°� �ƴ� ���� ���ص� ������ ����. �� ������ �� key�� ������ ���Եȴ�. 
		keys.forEach(( k -> System.out.println( map.get(k) ))); //value�� �̾ƺ���

		/*====================================================
		 * ��� Value�� print �ϴ� ���
		=====================================================*/
		Collection<Integer> values = map.values();
		values.forEach( i -> System.out.print(i+" ") );
		System.out.println();


		/*====================================================
		 * Map�� �ٺ��� Entry Set�� �̿��ϴ� �� 			Entry�� key�� value�� ������ ����
		=====================================================*/

		Set< Map.Entry<String, Integer> > entries = map.entrySet();
		//Ŭ���� ���°� Map�� ��Ʈ������ �˸����ֵ��� �� Ŭ���� ���·� ���ִ� " Map.Entry<String, Integer> "��� Ŭ������ ���� �ȴ�. �� �޼ҵ尡 Ŭ������ �������ش�.
		for(Map.Entry<String, Integer> i : entries) {
			System.out.print("key: "+ i.getKey());
			System.out.println(", val: "+ i.getValue());
		}

		/*====================================================
		 * Map��ü�� �̿��ؼ� print �ϴ� ���
		=====================================================*/

		map.forEach( (k,v) -> System.out.println("Ű�� ���� :"+k+" "+v) );

		map.forEach( (k,v) -> {
			System.out.print("key: "+k);
			System.out.println(", value: "+v);
			} 
		);
		// ���ٽ����� ���� �ڵ����� �ۼ��ϰ� ������ �߰�ȣ�� ��� ����ϸ� �ȴ�.
		//====================================================

		System.out.println("computeIfAbsent");

		//computeIfAbsent() �޼ҵ�
		/**============================================================
		 * Key�� �����ϴ��� �˰� ���� ���
		 * ����, key�� �����ϴ��� �ƴ��� Ȯ���ϰ� ���� ��찡 �����. 
		 * �� ������ �Ĵ����� �б⸦ ������ �Ѵ�. 
		 * ���� ��� �������α׷����� �޸������̼�(Memoization, ��Ÿ�ƴ�) 
		 * �� ���� ���� ���Ǵ� ��� �߿� �ϳ��̴�. 
		 * 
		 * containsKey() ��ſ� 
		 * computeIfAbsent() �� ����Ѵٸ� �츮�� �̰��� �� �� ª�� �����ϰ� ���� �� �ִ�.
		 * 
		 * �� computeIfAbsent() �޼ҵ�� �ΰ��� �Է°��� �޴´�. 
		 * ù��°�� key�̰� �ι�°�� key�� ����Ͽ� ���ʵ��� value�� ��ȯ�ϴ� �Լ��� �������̽��̴�. 
		 * map�� key�� ������ ���� ��ȯ�Ѵٴ� ���̵���. 
		 * �׷��� �ʴٸ�, value�� ����ϰ� map�� �߰��� ���Ŀ� value�� �����ٰ��̴�. 
		 * �̷��� �ϸ� �ڵ尡 ���� ������ ���� ª������.
		 ==============================================================*/

		Map<String, Integer> map_new = new HashMap<>();
		map_new.put("��", 100);
		map_new.put("����", 80);
		map_new.put("�ⵢ", 75);

		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );

		System.out.println(map_new.computeIfAbsent("����", i-> 10 ) );	//return 10
		//"����"�̶�� ���ڿ��� map_new ���̺��� Key�� �ƴϴ�.
		// �ش� Ű�� ���� ���� �ʱ⿡ "����"�̶�� key�� ���̺� �߰��ϸ鼭 �� Ű���� 10�̶�� Value�� �ֵ��� �Ѵ�. 
		// �ᱹ ��  �޼ҵ带 ���ؼ� ���ϸ鼭 �������� �ʴ� ���� ���´ٸ� �߰����ش�. 
		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );


		System.out.println(map_new.computeIfAbsent("��", i-> 10 ) );	//return 100
		//"��"�̶�� key�� �̹� �����ϱ⿡ �ش� Key�� �����ִ� Value�� 100�� �Ѱ��ش�. 
		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );



		/** ================================================================================
		 * putIfAbsent�� computeIfAbsent�� ������ 
		 * 
		 * // key�� �����Ͽ��� callExpensiveMethodToFindValue()�� ȣ��ȴ�.
		 * Map.putIfAbsent(theKey, callExpensiveMethodToFindValue(theKey)); 
		 * 
		 * // key�� �����Ѵٸ� callExpensiveMethodToFindValue()�� ���� ȣ����� �ʴ´�. 
		 * Map.computeIfAbsent(theKey, key -> callExpensiveMethodToFindValue(key));
		 * 
		 ===================================================================================*/

		System.out.println("computeIfPresent");

		/**============================================================
		 * computeIfPresent() �޼ҵ�� 
		 * key�� �ް� ���� key�� �����ϴ� ��쿡�� �ݺ������� value�� ����ϴ� �Լ��� �ٽ� �����Ѵ�. 
		 * �׷��Ƿ� �ٽ� ������ �Լ��� map�� ���� key�� �����ϴ� ��쿡�� ȣ��ǰ� �� �ݿ��� ��쿡�� ȣ������ �ʴ´�.
		 * �Դٰ�, computeIfPresent() �� ����� �Է°��� ������ compute() �̶�� �ٸ� �޼ҵ尡 �ִ�. 
		 * �׷��� �� �޼ҵ�� ����ε� �Լ��� ����� �ϰ� key�� �̹� �����ϴ��� ���ο� ������� �ʴ´�. 
		 * �ٽ� ���ε� �Լ��� ��°��� �������� map�� value�� �߰��Ѵ�.
		 ==============================================================*/

		// ������
		//Integer value = map.get("����");
		//map_new.put("����", ++value);
		
		
		// �ֽŽ� Java 8
		map_new.computeIfPresent("����", (String a, Integer b) -> ++b );	//(a, b)�� �ص� �ȴ�.
		// (a, b) -> b+1 �̷��� �ص��ȴ�. 
		// �ٸ� ���⼭ b++�� �ȵȴ�. �翬�� �Ҹ������� b++�� �Ѱ��ٶ� b�� ���� ���� �Ѱ��ְ� �� ������ b�� ���� �����ִ� �����ε� �� �޼ҵ� ������ �Ѱ����� ���ϴ� ���°� �Ǵ°ɷ� ���δ�.
		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );

		//����
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("john", 20);
		map3.put("paul", 30);
		map3.put("peter", 40);
		map3.computeIfPresent("kelly", (k,v)->v+10); //{john=20, paul=30, peter=40} // kelly not present
		map3.computeIfPresent("peter", (k,v)->v+10); //{john=20, paul=30, peter=50} // peter present, so increase the value 

		map3.forEach( (k,v) -> System.out.println(k+" "+v) );
	}

}
