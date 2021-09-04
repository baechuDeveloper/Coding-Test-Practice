package A_experiment;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/* 컬렉션(Collections)들의 배열(arr)중 특히 Integer를 이용 할 떄 유용하게 사용하는 방법을 설명한다. 다른 컬렉션들도 충분히 이용가능하다. */

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
		
		
		/**---Integer[]을 인스턴스로 바로 이용하는 방법은 무엇이 있을까.---*/
		//가능
		Integer[] iarr1 = {1, 2};
		Integer[] iarr2 = new Integer[] {3,4};	//이걸 이용하면 된다. 
		
		ArrayList<Integer[]> al1 = new ArrayList<>();
		
		al1.add(iarr1);
		al1.add(iarr2);
		al1.add(new Integer[] {5, 6});
		
		/*a.add({2,3}); 이게 불가능하다. */ 
		/**=========================================================================*/
		
		
		/**---참고로 int[]를 인스턴스로 바로 이용하는 방법도 이렇게 해주면 된다. 이 방법은 모든 배열을 인스턴스로 만들어서 주게할 때 사용하는 방법이다.---*/
		System.out.println("일반 primative에서 함수에서 들어갈때 인스턴스로 들어가는걸 설명");
		int[] ar1 = {10, 20};	   //원래는 이걸로 인스턴스 공간을 선언하고 ar1배열을 넣으며 인스턴스를 주곤했다.
		int[] ar2 = new int[] {30, 40};	//그런데 ar2에서 나온 new int[]{} 형태로 이름없는 레퍼런스 주소값을 설정한 인스턴스를 줄수 있다.

		testF1(ar1);
		testF1(ar2);
		
		//testF1({50, 60}); 불가능
		testF1(new int[]{50, 60}); //이렇게 가능
		//testF1(new Integer[] {50, 60});  배열상태에서는 int와 Integer는 서로 구분을 해줘야한다.
		
		//testF2({1,2});	불가능
		//testF2(new int[]{1, 2});	역시 불가능
		testF2(new Integer[] {1,2});
		
		//클래스 배열은 위에 처럼 인스턴스 형태로 만드는 방법을 활용해야한다.
		
		testOneString("ABC");	//String은 클래스에서 독특하게 문자열 자체가 하나의 인스턴스(이자 힙에 레퍼런스 주소값을 가짐)여서 곧바로 인스턴스 형태로 취급이 가능하다.
								//그리고 다른 클래스도 new Node(1,2)이처럼 하면 Node의 클래스로서 인스턴스를 만든것이다.
		
		//물론 String배열에서는 역시 안된다.	
		//testF3({"abc", "def"}); 불가능 역시 배열 자체를 하나의 인스턴스로 볼려면 아래같은 방법으로 진행해야한다. 
		testF3(new String[]{"abc", "def"});	
		/**=========================================================================*/
		
		
		/**---Integer[]을 인스턴스로 사용하면 for문에 바로 int를 이용한걸 잠시 생각해야한다.---*/
		ArrayList<Integer> al2 = new ArrayList<>();
		al2.add(1);
		al2.add(100);
		al2.add(1000);
		// 이건 가능 - 자체 적으로 Integer로 나오는 값들이 int에게 자체 캐스팅을 해주어서 잘들어간다.
		for(int i : al2) 
			System.out.print(i+" "); System.out.println();
		
		//이건 불가능 일반적으로 Integer 자체가 int를 받는건 어느 경우에서 자체 캐스팅을 해주는 방식이 있어서 바로 사용했지만
		/* for(int[] i : al1) 
			System.out.println(i[0]+" "+i[1]); */
		
		for(Integer[] i : al1) 
			System.out.println(i[0]+" "+i[1]);
		/**=========================================================================*/
		
		
		/**---자바의 Stream을 배워본다.---*/
		
		/* 어떠한 것이라도 'Stream 인스턴스'를 내놓아야지 '람다를 활용해서 이용'할 수 있다.
		 * 단순한 String[] str 배열도 Arrays.stream(str)   이것까지가 str변수를 Arrays.stream()안에 지정해서 Stream 인스턴스로 나타내는 방법이다.
		 * 이렇게 인스턴스를 만들었다면 람다를 이용할 수 있는 함수(예로 forEach)로 호출이 가능하다.
		 * Arrays.stream(str).forEach( s -> System.out.println(s) ); 
		 * 람다를 활용해서 함수형으로 처리하고 병렬로 표현할수있고 
		 * */
		
		/*
		 * 스트림(stream)은 자바8에서 추가되어 람다를 활용할 수 있는 기술 중 하나입니다. 
		 * 
		 * https://wikidocs.net/264
		 * 자바8 이전에는 배열 또는 컬렉션 인스턴스를 다루는 방법은 'for문' 또는 'for each문'(확장for문)을 돌면서 요소 하나씩을 꺼내서 다루는 방법이었습니다.
		 * for(int i=0; i<N; i++) 이거나 for(int i : list) 확장for문으로 이렇게 작업을 했지요. 이 둘은 요소를 하나씩 꺼내서 다루는 방법입니다.
		 * 간단한 경우라면 상관없어도 로직이 '복잡'해질수록 코드의 양이 많아져 로직이 섞이게 되고, 메소드를 나눌 경우 루프를 여러번 도는 경우가 발생합니다.
		 * 
		 * 여기서 스트림은 '데이터의 흐름'입니다. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있습니다. 
		 * 또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있습니다. 즉, '배열과 컬렉션을 함수형'으로 처리할 수 있습니다. 
		 * 
		 * '->'가 들어간다면 '람다식을 사용'한다고 보면 됩니다. forEach 함수는 forEach( key -> 함수 )의 구조로 컬렉션에서 제공해주는 map.forEach를 쓰는 것에는 람다가 들어갑니다. 
		 * 그렇지 않고 list.foreach(System.out::println);으로 한다면 람다식을 생략한 것으로 볼수있습니다.
		 * 
		 * */
		Map<Integer, Integer> map1 = new HashMap<>();
		map1.forEach( (a,b)-> System.out.println() );
		al2.forEach(System.out::println);
		
	
		/**람다에 대하여**/
		
		
		
		/**=========================================================================*/
		
		
		
		/**---람다식에 사용되는 '::' (이중 콜론 연산자, 메소드 참조 표현식) ---**/
		/* http://yoonbumtae.com/?p=2776
		 * '::'은 메소드 참조 표현식이며  람다식에서 파리미터를 중복으로 사용하기 싫을 때 사용합니다. 
		 * 람다 표현식에서만 사용 가능하고, 사용 방법은 [인스턴스]::[메소드명(또는 new)] 로 사용하는데, 예제를 통해 보는 것이 이해가 빠릅니다. 
		 * static 메소드인 경우 '인스턴스 대신' static을 포함하는'클래스 이름'으로 사용할 수 있습니다.
		 * */
		List<String> names = Arrays.asList("김갑순", "이갑돌");
		names.forEach(x -> System.out.println(x));	// x(파라미터)를 건네고 받는 과정에서 x를 두 번 적게된다.
		
		/**람다식이 '건네는' 파라미터와 '받는' 쪽의 파리미터가 '동일'할 때, 아래와 같이 줄여 사용가능하다. 그러면 줄여도 메소드명에게도 해당 파라미터로 들어간다라고 인식할수있다.*/
		//참고로 System.out은 PrintStream 인스턴스를 반환합니다. 그리고 그 '인스턴스의 메소드 중 하나'인 println이 메소드명으로 사용되었습니다.
		names.forEach(System.out::print);			// 아예 x들을 뺴버리고 아래와 같이 작성할 수 있다. 참고로 System.out은 PrintStream 인스턴스를 반환합니다. 
		System.out.println();
		
		//여기 클래스 이름으로 객체 생성
		Stream_and_Collections_arrays obj = new Stream_and_Collections_arrays();
		//
		names.stream().map(x -> obj.addNim(x)).forEach(System.out::println);	//적용 전 
		names.stream().map(obj::addNim).forEach(System.out::println);			//적용 후 x->dct.addNim(x) 가 dct::addNim으로 바꿀 수 있다.
		names.stream().map(Stream_and_Collections_arrays :: stataddNim).forEach(System.out::println);	//static 메소드는 클래스:static메소드명으로 사용가능하다. 
		
		//그리고 만약 파라미터를 통한 메소드를 호출한 람다식인 경우에 그 '해당 파라미터의 클래스 이름'을 적어 메소드를 표현할수있다.
		//이 역시 건네고 받아내는 파라미터가 일치할때만 가능하다. toLowerCase()안에 해당 파라미터가 들어간다는 가정이 필요된다.
		names.stream().map(x -> x.toLowerCase());	
		names.stream().map(String::toLowerCase);
		/**=========================================================================*/
		
		
		/** Stream에서 배열의 원소를 가공하는 방법*/
		/* https://dpdpwl.tistory.com/81
		 * 대표적으로 map(), filter(), sorted() 등이 있습니다. 
		 * ArrayList<String> names2 = new ArrayList<>(Arrays.asList("Apple", "Banana", "Melon", "Grape"));
		 * ---------------------
		 * map은 요소들을 특정조건에 해당하는 값으로 변환해줍니다. 
		 * 요소들을 대,소문자 변형등의 작업이나 아니면 람다식을 이용해서 파라미터를 받아 파라미터를 주어 해당 자료형으로 내주는 메소드를 통해 가공합니다. 
		 * names2.stream().map(String::toUpperCase); // s->s.toUpperCase() 로도 가능
		 * 
		 * 가공한 요소들은 스트림으로만 남아있지 않도록 'collect'를 이용해서 결과로서 리턴도 받을 수 있습니다.
		 * 
		 * Collectors.joining을 이용하면 리스트를 조인의 기준으로 배치 할 수 있습니다.
		 * names2.stream().map(String::toUpperCase).collect(Collectors.joining(" "))); // APPLE BANANA MELON GRAPE -문자열 하나로
		 * 
		 * Collectors.toList를 이용해서 리스트로 리턴 받을 수 있습니다.
		 * names2.stream().map(String::toUpperCase).collect(Collectors.toList())));    // [APPLE, BANANA, MELON, GRAPE]	-리스트 형태로
		 * 
		 * stream의 상태이니 forEach를 통해 각각 작업도 할수있습니다.
		 * ---------------------
		 * filter는 요소들을 조건에 따라 걸러내는 작업을 해줍니다. 
		 * names2.stream().filter(t->t.length()<=5).collect(Collectors.joining(" "))); 	// APPLE MELON GRAPE
		 * names2.stream().filter(t->t.length()<=5).collect(Collectors.toList()));		// [APPLE, MELON, GRAPE]
		 * 
		 * ---------------------
		 * sorted는 리스트의 요소를 정렬합니다. 
		 * 스트림으로 되었다는건 배열이나 컬렉션 인스턴스를 통해 만들어진것이니 스트림이 상태에서 각 요소에 따른 정렬을 해줍니다.
		 * names2.stream().sorted(); 
		 * names2.stream().sorted().collect(Collectors.toList()));	//정렬된 스트림을 리스트로 반환하기.
		 * */
		

		/**=========================================================================*/
		
		/**배열을 리스트로 바꾸어주는 법 - 리스트를 인스턴스로 바로 넣어주는 방법**/
		
		//클래스 배열 인스턴스라면 모두 Arrays.asList()로 list에 코드를 줄여 넣을 수 있다. 
		String[] StoL1 = {"abc", "def"};
		List<String> all1 = Arrays.asList(StoL1);
		all1 = Arrays.asList("ghi", "jkl");			//즉석으로 가능
		
		//Wrapper클래스(Integer와 Character 같은)도 당연히 가능하다.  
		Integer[] ItoL1 = {1, 2, 3};
		List<Integer> all2 = Arrays.asList(ItoL1);	//가능
		all2 = Arrays.asList(new Integer[] {4,5,6});//인스턴스 가능
		all2 = Arrays.asList(7, 8, 9);				//즉석으로 가능.
		
		
		// int[]은 primitive타입이라서 List에 들어갈려고 해도 Arrays.asList()로 이용될 수 없다. 
		// 그렇기에 Stream이 있다.
		int [] itoL1 = {1, 2, 3};

		List<Integer> all3 = Arrays.stream(itoL1).boxed().collect(Collectors.toList());
		/* 스트림 인스턴스로 만들어 두었다면, boxed() 메소드를 사용할 수 있습니다.
		 * boxed()는 primitive Stream 값들을 Wrapper Class Stream으로 바꿔줍니다. 
		 * Wrapper클래스의 Stream으로 바뀌어다면 collect()로 그 안에 Collectors.toList()를 통해 
		 * Stream을 List형태로 변경해 줍니다.*/
		
		//물론 그냥 반복문으로 진행해도 된다. 시간은 나중에 너가 비교해보자.
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
		return s+"님";
	}
	
	static String stataddNim(String s) {
		return s+"님";
	}

}
