package String_ALG__KMP_and_TRI;

import java.util.*;

public class Lambda_for_HashMap {

	// Map 이라는 테이블
	// 그 테이블을 이루는 각 Entry들
	// Entry는 Key와 Value의 한 묶음으로 표현되며, 하나의 Entry가 Map의 한 원소이다. 
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 12 );
		map.put("b", 123);
		map.put("c", 1234);

		/**===================== 오리지널과 lambda =====================**/

		/*===================================================
		 * 모든 Key를 print 하는 방법			
		=====================================================*/
		Set<String> keys = map.keySet();

		for(String i : keys) {
			System.out.println(i);
		}
		// or

		keys.forEach( i -> System.out.println(i) );		
		//이것은 람다 표현식, 한번 쓰기위해 만들어진다. 
		//'i'는 원래 존재하는게 아닌 내가 정해둔 임의의 변수. 저 변수에 각 key의 값들이 들어가게된다. 
		keys.forEach(( k -> System.out.println( map.get(k) ))); //value를 뽑아보기

		/*====================================================
		 * 모든 Value를 print 하는 방법
		=====================================================*/
		Collection<Integer> values = map.values();
		values.forEach( i -> System.out.print(i+" ") );
		System.out.println();


		/*====================================================
		 * Map의 근본인 Entry Set을 이용하는 법 			Entry는 key와 value의 묶음을 뜻함
		=====================================================*/

		Set< Map.Entry<String, Integer> > entries = map.entrySet();
		//클래스 형태가 Map의 엔트리임을 알리수있도록 그 클래스 형태로 내주는 " Map.Entry<String, Integer> "라는 클래스로 보면 된다. 저 메소드가 클래스로 제공해준다.
		for(Map.Entry<String, Integer> i : entries) {
			System.out.print("key: "+ i.getKey());
			System.out.println(", val: "+ i.getValue());
		}

		/*====================================================
		 * Map객체를 이용해서 print 하는 방법
		=====================================================*/

		map.forEach( (k,v) -> System.out.println("키와 벨류 :"+k+" "+v) );

		map.forEach( (k,v) -> {
			System.out.print("key: "+k);
			System.out.println(", value: "+v);
			} 
		);
		// 람다식으로 여러 코드줄을 작성하고 싶으면 중괄호로 묶어서 사용하며 된다.
		//====================================================

		System.out.println("computeIfAbsent");

		//computeIfAbsent() 메소드
		/**============================================================
		 * Key가 존재하는지 알고 싶은 경우
		 * 종종, key가 존재하는지 아닌지 확인하고 싶은 경우가 생긴다. 
		 * 이 정보를 파당으로 분기를 만들어야 한다. 
		 * 예를 들어 동적프로그램에서 메모이제이션(Memoization, 오타아님) 
		 * 은 가장 자주 사용되는 기술 중에 하나이다. 
		 * 
		 * containsKey() 대신에 
		 * computeIfAbsent() 를 사용한다면 우리는 이것을 좀 더 짧고 간결하게 만들 수 있다.
		 * 
		 * 이 computeIfAbsent() 메소드는 두개의 입력값을 받는다. 
		 * 첫번째는 key이고 두번째는 key를 사용하여 차례데로 value를 반환하는 함수형 인터페이스이다. 
		 * map에 key가 있으면 값을 반환한다는 아이디어다. 
		 * 그렇지 않다면, value를 계산하고 map에 추가를 한후에 value를 돌려줄것이다. 
		 * 이렇게 하면 코드가 보다 간단해 지고 짧아진다.
		 ==============================================================*/

		Map<String, Integer> map_new = new HashMap<>();
		map_new.put("희영", 100);
		map_new.put("세주", 80);
		map_new.put("기덩", 75);

		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );

		System.out.println(map_new.computeIfAbsent("은영", i-> 10 ) );	//return 10
		//"은영"이라는 문자열은 map_new 테이블에서 Key가 아니다.
		// 해당 키가 존재 하지 않기에 "은영"이라는 key를 테이블에 추가하면서 그 키에게 10이라는 Value를 주도록 한다. 
		// 결국 이  메소드를 통해서 비교하면서 존재하지 않는 값이 나온다면 추가해준다. 
		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );


		System.out.println(map_new.computeIfAbsent("희영", i-> 10 ) );	//return 100
		//"희영"이라는 key는 이미 존재하기에 해당 Key가 갖고있는 Value인 100을 넘겨준다. 
		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );



		/** ================================================================================
		 * putIfAbsent와 computeIfAbsent의 차이점 
		 * 
		 * // key가 존재하여도 callExpensiveMethodToFindValue()가 호출된다.
		 * Map.putIfAbsent(theKey, callExpensiveMethodToFindValue(theKey)); 
		 * 
		 * // key가 존재한다면 callExpensiveMethodToFindValue()가 결코 호출되지 않는다. 
		 * Map.computeIfAbsent(theKey, key -> callExpensiveMethodToFindValue(key));
		 * 
		 ===================================================================================*/

		System.out.println("computeIfPresent");

		/**============================================================
		 * computeIfPresent() 메소드는 
		 * key를 받고 만일 key가 존재하는 경우에만 반복적으로 value를 계산하는 함수로 다시 매핑한다. 
		 * 그러므로 다시 매핑한 함수는 map에 오직 key가 존재하는 경우에만 호출되고 그 반에의 경우에는 호출하지 않는다.
		 * 게다가, computeIfPresent() 와 비슷한 입력값을 가지는 compute() 이라는 다른 메소드가 있다. 
		 * 그러나 이 메소드는 재매핑된 함수로 계산을 하고 key가 이미 존재하는지 여부에 상관하지 않는다. 
		 * 다시 매핑된 함수의 출력값을 바탕으로 map에 value를 추가한다.
		 ==============================================================*/

		// 예전건
		//Integer value = map.get("은영");
		//map_new.put("은영", ++value);
		
		
		// 최신식 Java 8
		map_new.computeIfPresent("은영", (String a, Integer b) -> ++b );	//(a, b)로 해도 된다.
		// (a, b) -> b+1 이렇게 해도된다. 
		// 다만 여기서 b++는 안된다. 당연한 소리겠지만 b++는 넘겨줄때 b의 원래 값만 넘겨주고 다 끝나고 b의 값을 더해주는 형식인데 이 메소드 구조상 넘겨주지 못하는 형태가 되는걸로 보인다.
		map_new.forEach( (k,v) -> System.out.println(k+" "+v) );

		//예제
		Map<String, Integer> map3 = new HashMap<String, Integer>();
		map3.put("john", 20);
		map3.put("paul", 30);
		map3.put("peter", 40);
		map3.computeIfPresent("kelly", (k,v)->v+10); //{john=20, paul=30, peter=40} // kelly not present
		map3.computeIfPresent("peter", (k,v)->v+10); //{john=20, paul=30, peter=50} // peter present, so increase the value 

		map3.forEach( (k,v) -> System.out.println(k+" "+v) );
	}

}
