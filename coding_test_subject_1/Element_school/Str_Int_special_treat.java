package Element_school;

import java.io.*;
import java.util.*;

// List를 쓰면서 contains를 하는데 내가 예상한 것보다 더 기본적인 클래스에 대한 상세한 부분이 있어서 적어둔다.
public class Str_Int_special_treat {

	public static void main(String[] args) throws IOException{

		List<Integer> list = new ArrayList<>();
		
		list.add(3);
		list.add(3);
		list.forEach(a->System.out.println(a));
		//list.remove(3); 이건 인덱스로 인식 Object형식으로 해야 remove가 객체로 지정해서 삭제
		list.remove(new Integer(3));
		if(list.contains(3))
			System.out.println("wdw");
		list.forEach(a->System.out.println(a)); System.out.println();
		//---------------------------
		List<String> str = new ArrayList<>();
		
		str.add("안녕");
		str.add("안녕");
		str.forEach(a->System.out.println(a));
		str.remove("안녕");
		if(str.contains("안녕"))
			System.out.println("gggg");
		str.remove(new String("안녕"));	//둘다 인식이 되었네... 이사실로 보면 레퍼런스 정보가 중요치 않다 인가..?
		str.forEach(a->System.out.println(a)); System.out.println();
		//---------------------------
		AA a = new AA(1, 2);
		AA b = new AA(1, 2);
		List<AA> aa = new ArrayList<>();
		
		aa.add(a);
		aa.add(b);
		aa.forEach(v->System.out.println(v.x+" "+v.y)); System.out.println("--");
		aa.remove(new AA(1, 2));	//여기서는 제대로 객체에 대한 레퍼런스로서 그 값이 다른 객체이므로 지워지지 않는다.
		aa.forEach(v->System.out.println(v.x+" "+v.y));
		if(aa.contains( new AA(1, 2) ) == true)
			System.out.println("이렇게 클래스 객체들은 같지가 않다.");
		//---------------------------
		
		String q = "안녕";
		String w = "안녕";
		String e = new String("안녕");
		String r = new String(q);
		if( q==w )
			System.out.println("상수로 쓰이는 문자열은 동일한 레퍼런스 값을 제공한다.");
		if( q==e || w==e ) 
			System.out.println("new 연산자로 만든다면 완전 새로운 객체이며 새로운 레퍼런스값을 가진다. 따라서 기존과 다른 레퍼런스 주소값이다.");
		if( q==r || w==r )
			System.out.println("마찬가지로 자신의 문자열로 만들었다 뿐이지 new연산자로 새로운 객체를 만들어 새로운 레퍼런스 값이다.");
		if( e==r )
			System.out.println("당연히 같을 수 없다.");
		if(q.equals(r))
			System.out.println("문자열 자체는 이렇게 비교하는게 국룰이며, 문자열 내용이 같은지 볼수있다.");
		//---------------------------
		
		List<String> str_list = new LinkedList<>();
		str_list.add(q);
		str_list.forEach( v-> System.out.println(v));
		if( str_list.contains(r) == true )
			System.out.println("같은 걸로 취급");
		if( str_list.contains( new String("안녕") ) == true)
			System.out.println("이것도");
		if( e == new String("안녕") )
			System.out.println("그렇다고 레퍼런스가 같은게 아니다.");
		
		
		System.out.println("\n즉! Collections에서 String과 Integer등 기본적인 클래스에 대해서는 '특수취급'을 해주는데\n"
				+ "이와 같은 클래스가 List같은 Collections에서는 레퍼런스로 같은지 여부를 보지 않고 그안의 값이나 문자열을 통해 비교까지 해주도록+\n"
				+ "더 구체적인 경우로 준다. 이점을 참고해보자.");
		
	
	}//===================================================
	
	// 사용자 정의 클래스
	static class AA{
		int x, y;
		AA(int a, int b){
			x=a;
			y=b;
		}
	}//===================================================
}
