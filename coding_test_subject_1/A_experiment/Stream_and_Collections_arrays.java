package A_experiment;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/* �÷���(Collections)���� �迭(arr)�� Ư�� Integer�� �̿� �� �� �����ϰ� ����ϴ� ����� �����Ѵ�. �ٸ� �÷��ǵ鵵 ����� �̿밡���ϴ�. */

/**
 * https://wakestand.tistory.com/420
 * https://futurecreator.github.io/2018/08/26/java-8-streams/
 * https://ponyozzang.tistory.com/406
 * https://khj93.tistory.com/entry/JAVA-%EB%9E%8C%EB%8B%A4%EC%8B%9DRambda%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B4%EA%B3%A0-%EC%82%AC%EC%9A%A9%EB%B2%95
 * http://yoonbumtae.com/?p=2776
 * https://hianna.tistory.com/552
 * */
public class Stream_and_Collections_arrays {

	public static void main(String[] args) {
		
		
		/**---Integer[]�� �ν��Ͻ��� �ٷ� �̿��ϴ� ����� ������ ������.---*/
		//����
		Integer[] iarr1 = {1, 2};
		Integer[] iarr2 = new Integer[] {3,4};	//�̰� �̿��ϸ� �ȴ�. 
		
		ArrayList<Integer[]> al1 = new ArrayList<>();
		
		al1.add(iarr1);
		al1.add(iarr2);
		al1.add(new Integer[] {5, 6});
		
		/*a.add({2,3}); �̰� �Ұ����ϴ�. */ 
		/**=========================================================================*/
		
		
		/**---����� int[]�� �ν��Ͻ��� �ٷ� �̿��ϴ� ����� �̷��� ���ָ� �ȴ�. �� ����� ��� �迭�� �ν��Ͻ��� ���� �ְ��� �� ����ϴ� ����̴�.---*/
		System.out.println("�Ϲ� primative���� �Լ����� ���� �ν��Ͻ��� ���°� ����");
		int[] ar1 = {10, 20};	   //������ �̰ɷ� �ν��Ͻ� ������ �����ϰ� ar1�迭�� ������ �ν��Ͻ��� �ְ��ߴ�.
		int[] ar2 = new int[] {30, 40};	//�׷��� ar2���� ���� new int[]{} ���·� �̸����� ���۷��� �ּҰ��� ������ �ν��Ͻ��� �ټ� �ִ�.

		testF1(ar1);
		testF1(ar2);
		
		//testF1({50, 60}); �Ұ���
		testF1(new int[]{50, 60}); //�̷��� ����
		//testF1(new Integer[] {50, 60});  �迭���¿����� int�� Integer�� ���� ������ ������Ѵ�.
		
		//testF2({1,2});	�Ұ���
		//testF2(new int[]{1, 2});	���� �Ұ���
		testF2(new Integer[] {1,2});
		
		//Ŭ���� �迭�� ���� ó�� �ν��Ͻ� ���·� ����� ����� Ȱ���ؾ��Ѵ�.
		
		testOneString("ABC");	//String�� Ŭ�������� ��Ư�ϰ� ���ڿ� ��ü�� �ϳ��� �ν��Ͻ�(���� ���� ���۷��� �ּҰ��� ����)���� ��ٷ� �ν��Ͻ� ���·� ����� �����ϴ�.
								//�׸��� �ٸ� Ŭ������ new Node(1,2)��ó�� �ϸ� Node�� Ŭ�����μ� �ν��Ͻ��� ������̴�.
		
		//���� String�迭������ ���� �ȵȴ�.	
		//testF3({"abc", "def"}); �Ұ��� ���� �迭 ��ü�� �ϳ��� �ν��Ͻ��� ������ �Ʒ����� ������� �����ؾ��Ѵ�. 
		testF3(new String[]{"abc", "def"});	
		/**=========================================================================*/
		
		
		/**---Integer[]�� �ν��Ͻ��� ����ϸ� for���� �ٷ� int�� �̿��Ѱ� ��� �����ؾ��Ѵ�.---*/
		ArrayList<Integer> al2 = new ArrayList<>();
		al2.add(1);
		al2.add(100);
		al2.add(1000);
		// �̰� ���� - ��ü ������ Integer�� ������ ������ int���� ��ü ĳ������ ���־ �ߵ���.
		for(int i : al2) 
			System.out.print(i+" "); System.out.println();
		
		//�̰� �Ұ��� �Ϲ������� Integer ��ü�� int�� �޴°� ��� ��쿡�� ��ü ĳ������ ���ִ� ����� �־ �ٷ� ���������
		/* for(int[] i : al1) 
			System.out.println(i[0]+" "+i[1]); */
		
		for(Integer[] i : al1) 
			System.out.println(i[0]+" "+i[1]);
		/**=========================================================================*/
		
		
		/**---�ڹ��� Stream�� �������.---*/
		
		/* ��� ���̶� 'Stream �ν��Ͻ�'�� �����ƾ��� '���ٸ� Ȱ���ؼ� �̿�'�� �� �ִ�.
		 * �ܼ��� String[] str �迭�� Arrays.stream(str)   �̰ͱ����� str������ Arrays.stream()�ȿ� �����ؼ� Stream �ν��Ͻ��� ��Ÿ���� ����̴�.
		 * �̷��� �ν��Ͻ��� ������ٸ� ���ٸ� �̿��� �� �ִ� �Լ�(���� forEach)�� ȣ���� �����ϴ�.
		 * Arrays.stream(str).forEach( s -> System.out.println(s) ); 
		 * ���ٸ� Ȱ���ؼ� �Լ������� ó���ϰ� ���ķ� ǥ���Ҽ��ְ� 
		 * */
		
		/*
		 * ��Ʈ��(stream)�� �ڹ�8���� �߰��Ǿ� ���ٸ� Ȱ���� �� �ִ� ��� �� �ϳ��Դϴ�. 
		 * 
		 * https://wikidocs.net/264
		 * �ڹ�8 �������� �迭 �Ǵ� �÷��� �ν��Ͻ��� �ٷ�� ����� 'for��' �Ǵ� 'for each��'(Ȯ��for��)�� ���鼭 ��� �ϳ����� ������ �ٷ�� ����̾����ϴ�.
		 * for(int i=0; i<N; i++) �̰ų� for(int i : list) Ȯ��for������ �̷��� �۾��� ������. �� ���� ��Ҹ� �ϳ��� ������ �ٷ�� ����Դϴ�.
		 * ������ ����� ������ ������ '����'�������� �ڵ��� ���� ������ ������ ���̰� �ǰ�, �޼ҵ带 ���� ��� ������ ������ ���� ��찡 �߻��մϴ�.
		 * 
		 * ���⼭ ��Ʈ���� '�������� �帧'�Դϴ�. �迭 �Ǵ� �÷��� �ν��Ͻ��� �Լ� ���� ���� �����ؼ� ���ϴ� ����� ���͸��ϰ� ������ ����� ���� �� �ֽ��ϴ�. 
		 * ���� ���ٸ� �̿��ؼ� �ڵ��� ���� ���̰� �����ϰ� ǥ���� �� �ֽ��ϴ�. ��, '�迭�� �÷����� �Լ���'���� ó���� �� �ֽ��ϴ�. 
		 * 
		 * '->'�� ���ٸ� '���ٽ��� ���'�Ѵٰ� ���� �˴ϴ�. forEach �Լ��� forEach( key -> �Լ� )�� ������ �÷��ǿ��� �������ִ� map.forEach�� ���� �Ϳ��� ���ٰ� ���ϴ�. 
		 * �׷��� �ʰ� list.foreach(System.out::println);���� �Ѵٸ� ���ٽ��� ������ ������ �����ֽ��ϴ�.
		 * 
		 * */
		Map<Integer, Integer> map1 = new HashMap<>();
		map1.forEach( (a,b)-> System.out.println() );
		al2.forEach(System.out::println);
		
	
		/**���ٿ� ���Ͽ�**/
		
		
		
		/**=========================================================================*/
		
		
		
		/**---���ٽĿ� ���Ǵ� '::' (���� �ݷ� ������, �޼ҵ� ���� ǥ����) ---**/
		/* http://yoonbumtae.com/?p=2776
		 * '::'�� �޼ҵ� ���� ǥ�����̸�  ���ٽĿ��� �ĸ����͸� �ߺ����� ����ϱ� ���� �� ����մϴ�. 
		 * ���� ǥ���Ŀ����� ��� �����ϰ�, ��� ����� [�ν��Ͻ�]::[�޼ҵ��(�Ǵ� new)] �� ����ϴµ�, ������ ���� ���� ���� ���ذ� �����ϴ�. 
		 * static �޼ҵ��� ��� '�ν��Ͻ� ���' static�� �����ϴ�'Ŭ���� �̸�'���� ����� �� �ֽ��ϴ�.
		 * */
		List<String> names = Arrays.asList("�谩��", "�̰���");
		names.forEach(x -> System.out.println(x));	// x(�Ķ����)�� �ǳװ� �޴� �������� x�� �� �� ���Եȴ�.
		
		/**���ٽ��� '�ǳ״�' �Ķ���Ϳ� '�޴�' ���� �ĸ����Ͱ� '����'�� ��, �Ʒ��� ���� �ٿ� ��밡���ϴ�. �׷��� �ٿ��� �޼ҵ���Ե� �ش� �Ķ���ͷ� ���ٶ�� �ν��Ҽ��ִ�.*/
		//����� System.out�� PrintStream �ν��Ͻ��� ��ȯ�մϴ�. �׸��� �� '�ν��Ͻ��� �޼ҵ� �� �ϳ�'�� println�� �޼ҵ������ ���Ǿ����ϴ�.
		names.forEach(System.out::print);			// �ƿ� x���� �������� �Ʒ��� ���� �ۼ��� �� �ִ�. ����� System.out�� PrintStream �ν��Ͻ��� ��ȯ�մϴ�. 
		System.out.println();
		
		//���� Ŭ���� �̸����� ��ü ����
		Stream_and_Collections_arrays obj = new Stream_and_Collections_arrays();
		//
		names.stream().map(x -> obj.addNim(x)).forEach(System.out::println);	//���� �� 
		names.stream().map(obj::addNim).forEach(System.out::println);			//���� �� x->dct.addNim(x) �� dct::addNim���� �ٲ� �� �ִ�.
		names.stream().map(Stream_and_Collections_arrays :: stataddNim).forEach(System.out::println);	//static �޼ҵ�� Ŭ����:static�޼ҵ������ ��밡���ϴ�. 
		
		//�׸��� ���� �Ķ���͸� ���� �޼ҵ带 ȣ���� ���ٽ��� ��쿡 �� '�ش� �Ķ������ Ŭ���� �̸�'�� ���� �޼ҵ带 ǥ���Ҽ��ִ�.
		//�� ���� �ǳװ� �޾Ƴ��� �Ķ���Ͱ� ��ġ�Ҷ��� �����ϴ�. toLowerCase()�ȿ� �ش� �Ķ���Ͱ� ���ٴ� ������ �ʿ�ȴ�.
		names.stream().map(x -> x.toLowerCase());	
		names.stream().map(String::toLowerCase);
		/**=========================================================================*/
		
		
		/** Stream���� �迭�� ���Ҹ� �����ϴ� ���*/
		/* https://dpdpwl.tistory.com/81
		 * ��ǥ������ map(), filter(), sorted() ���� �ֽ��ϴ�. 
		 * ArrayList<String> names2 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Melon", "Grape"));
		 * ---------------------
		 * map�� ��ҵ��� Ư�����ǿ� �ش��ϴ� ������ ��ȯ���ݴϴ�. 
		 * ��ҵ��� ��,�ҹ��� �������� �۾��̳� �ƴϸ� ���ٽ��� �̿��ؼ� �Ķ���͸� �޾� �Ķ���͸� �־� �ش� �ڷ������� ���ִ� �޼ҵ带 ���� �����մϴ�. 
		 * names2.stream().map(String::toUpperCase); // s->s.toUpperCase() �ε� ����
		 * 
		 * ������ ��ҵ��� ��Ʈ�����θ� �������� �ʵ��� 'collect'�� �̿��ؼ� ����μ� ���ϵ� ���� �� �ֽ��ϴ�.
		 * 
		 * Collectors.joining�� �̿��ϸ� ����Ʈ�� ������ �������� ��ġ �� �� �ֽ��ϴ�.
		 * names2.stream().map(String::toUpperCase).collect(Collectors.joining(" "))); // APPLE BANANA MELON GRAPE -���ڿ� �ϳ���
		 * 
		 * Collectors.toList�� �̿��ؼ� ����Ʈ�� ���� ���� �� �ֽ��ϴ�.
		 * names2.stream().map(String::toUpperCase).collect(Collectors.toList())));    // [APPLE, BANANA, MELON, GRAPE]	-����Ʈ ���·�
		 * 
		 * stream�� �����̴� forEach�� ���� ���� �۾��� �Ҽ��ֽ��ϴ�.
		 * ---------------------
		 * filter�� ��ҵ��� ���ǿ� ���� �ɷ����� �۾��� ���ݴϴ�. 
		 * names2.stream().filter(t->t.length()<=5).collect(Collectors.joining(" "))); 	// APPLE MELON GRAPE
		 * names2.stream().filter(t->t.length()<=5).collect(Collectors.toList()));		// [APPLE, MELON, GRAPE]
		 * 
		 * ---------------------
		 * sorted�� ����Ʈ�� ��Ҹ� �����մϴ�. 
		 * ��Ʈ������ �Ǿ��ٴ°� �迭�̳� �÷��� �ν��Ͻ��� ���� ����������̴� ��Ʈ���� ���¿��� �� ��ҿ� ���� ������ ���ݴϴ�.
		 * names2.stream().sorted(); 
		 * names2.stream().sorted().collect(Collectors.toList()));	//���ĵ� ��Ʈ���� ����Ʈ�� ��ȯ�ϱ�.
		 * */
		

		/**=========================================================================*/
		
		/**�迭�� ����Ʈ�� �ٲپ��ִ� �� - ����Ʈ�� �ν��Ͻ��� �ٷ� �־��ִ� ���**/
		
		//Ŭ���� �迭 �ν��Ͻ���� ��� Arrays.asList()�� list�� �ڵ带 �ٿ� ���� �� �ִ�. 
		String[] StoL1 = {"abc", "def"};
		List<String> all1 = Arrays.asList(StoL1);
		all1 = Arrays.asList("ghi", "jkl");			//�Ｎ���� ����
		
		//WrapperŬ����(Integer�� Character ����)�� �翬�� �����ϴ�.  
		Integer[] ItoL1 = {1, 2, 3};
		List<Integer> all2 = Arrays.asList(ItoL1);	//����
		all2 = Arrays.asList(new Integer[] {4,5,6});//�ν��Ͻ� ����
		all2 = Arrays.asList(7, 8, 9);				//�Ｎ���� ����.
		
		
		// int[]�� primitiveŸ���̶� List�� ������ �ص� Arrays.asList()�� �̿�� �� ����. 
		// �׷��⿡ Stream�� �ִ�.
		int [] itoL1 = {1, 2, 3};

		List<Integer> all3 = Arrays.stream(itoL1).boxed().collect(Collectors.toList());
		/* ��Ʈ�� �ν��Ͻ��� ����� �ξ��ٸ�, boxed() �޼ҵ带 ����� �� �ֽ��ϴ�.
		 * boxed()�� primitive Stream ������ Wrapper Class Stream���� �ٲ��ݴϴ�. 
		 * WrapperŬ������ Stream���� �ٲ��ٸ� collect()�� �� �ȿ� Collectors.toList()�� ���� 
		 * Stream�� List���·� ������ �ݴϴ�.*/
		
		//���� �׳� �ݺ������� �����ص� �ȴ�. �ð��� ���߿� �ʰ� ���غ���.
		/**=========================================================================*/
	}//=========================================================================
	
	static void testF1(int[] a) {
		for(int i : a)
			System.out.print(i+" "); System.out.println();
	}
	
	static void testF2(Integer[] a) {
		for(int i : a)
			System.out.print(i+" "); System.out.println();
	}
	
	static void testF3(String[] a) {
		for(String i : a)
			System.out.println(i);
	}
	
	static void testOneString(String a) {
		System.out.println(a);
	}
	
	String addNim(String s) {
		return s+"��";
	}
	
	static String stataddNim(String s) {
		return s+"��";
	}

}
