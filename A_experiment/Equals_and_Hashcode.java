package A_experiment;
import java.util.*;

/*
 * 이렇게 하는 이유는 내게 있어서 x랑 y가 같으면 나는 같다고 표현하고싶은데 일반적으로 해당 해시코드값이 다르기에 
 * */

// 이클립스 source에서 generate hashcode() and equals()라는 항목을 Node클래스위의 커서를 둔다음 실행하면 완성시켜주는 기능도 있다.
// 보통 '=='은 레퍼런스 타입이라면 레퍼런스 주소값이 같은지를 확인한다. new로 각각 만들어졌다면 당연히 각각의 주소 레퍼런스 값이 달라붙어서 같은 객체는 절대 아니다.
// 다만 그 '내용물'이 같은 것만 알고싶다면 equals()로 내용물이 같은지를 보는데 어떤 레퍼런스 타입은 hashcode()로 같은지를 비교하기도 한다.
// 따라서 레퍼런스 타입에서 내용물이 같은지를 확인하기위해서 equals()와 hashcode()를 '같이' 바꿔야만 완벽히 내용물이 같은걸 표현해줄 수 있다. 
public class Equals_and_Hashcode {

	
	/** 내가 코딩테스트를 위해 사용을 하기위해서 **/
	//내부클래스로 접해진 static클래스 - Equals_and_Hashcode 클래스로부터 독립적으로 존재 가능
	static class ForTest{
		int x, y;
		ForTest(int x, int y){
			this.x=x; this.y=y;
		}
			
		/** 근데 진짜 신기하게도 static class에서 IDE에서 제공해주는 equals와 hashcode생성을 해보니 
		 * 자동으로 내가 아래에 언급한 것들이 지워진다.
		 * 또한 생각해보면 아마 static은 그자체로 독립적으로 존재하는 것 같다. 내부클래스 인것에 영향없이.**/
		
		/* ----------------------------------------------------
		 * IDE가 만들어준 x와 y에 대해 getOuterType()을 없애고 hashcode()와 equals()에서 들어가는 부분을 주석체크했다.
		 * 코딩테스트에서 static으로 내부클래스를 만드는 일이 많은데 static은 외부클래스의 인스턴스를 제공해주는데 문제가 있기에 getOuterType()이 먹히질 않는다.
		 * 결국 내가 하나의 클래스 Equals_and_Hashcode에서만 문제를 풀기위해 작업을 하니 
		 * 외부 인스턴스는 항상 동일하다는 가정이면서 실제로도 항상 동일하며 equals()에서 저 작업을 숨겨도 외부 인스턴스 같기에 넘어가도 된다.
		 * 또한 외부 인스턴스는 항상 같기에 내용물을 통해 해시코드를 동일하게 주는것도 해당 부분이 없어도 동일한 해시코드를 생성해 줄 수 있다.
		 * 모두 여기 클래스에서만 작업을 한다는 가정하에 모두 성립이 된다. 
		 * 따라서 코딩테스트에서는 이렇게 equals와 hashcode를 설정하면 될 것이다. 
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

		// 또한 이곳에서만 사용할거라면 매개변수를 Node obj로 해서 제작해도 된다. 당연히 오버라이딩이니깐.
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())	//같은 클래스인지
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
	
	/** 일반적으로 개발을 할때 IDE에서 만들어준 내용이면 충분하다. **/
	//내부클래스
	class Node {
		int x, y;
		Node(int x, int y){
			this.x=x; this.y=y;
		}
		
		// IDE가 만들어준 x와 y에 대해 hashcode()와 equals()
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
			if (getClass() != obj.getClass())	//같은 클래스인지
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
		//이것도 IDE에서 만들어준 것이다. 
		//내부클래스에 equals와 hashcode를 만든다면 정확히 해당 내부클래스를 둘러싼 인스턴스(외부클래스, 이걸 외부클래스.this로 인스턴스로서 제공)도 동일한지를 봐야한다. 
		//즉 풀네임을 적는것이다.
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
		System.out.println("내부클래스 Node");
		Equals_and_Hashcode obj = new Equals_and_Hashcode();
		Node a = obj.new Node(1, 1);
		Node b = obj.new Node(1, 1);
		//여기서 알았는데 조금 독특해진건 원래 print로 a를 하는건 a.toString()하는것과 동일하다. 
		//이 String정보로는 어떤 클래스에서 왔는지 알려주고 그 뒤에 'hashcode'값을 알려줄뿐이다. 
		//정말 신기하게 우리가 수정을 하여 이미 내용물이 같은게 있어서 신기하게도 같은 해시코드값을 서로 갖고있다.
		//즉 우리에게 레퍼런스 주소값은 알려주지 않았다. 그럼에도 당연히 a와 b의 두 레퍼런스 주소값은 다르다. 각 new연산을 했으니.
		System.out.println("레퍼런스 주소값이 아닌 해시코드를 알려주네\n"+a.toString()+"\n"+b);
		//다만 '=='은 같은 레퍼런스인지를 주소값으로 알아내기에, 당연히 같은 레퍼런스 주소값이 아니라서 같지 않다.
		if(a==b) System.out.println("레퍼런스 값은 같다.");
		else System.out.println("레퍼런스 값은 당연히 다르다.");
		if(a.equals(b)) System.out.println("같은 내용물이다.");
		else System.out.println("다른 내용물이다.");
		//set은 해시코드로 비교하여 중복을 체크한다.
		Set<Node> s1 = new HashSet<>();
		s1.add(a);
		s1.add(b);
		System.out.println(s1.size()); System.out.println();
		
		System.out.println("static클래스 A - equals와 hashcode 생성 안함");
		A aa = new A(1, 1);
		A bb = new A(1, 1);
		//여기서도 마지막에 hashcode값을 알려주는데 내가 따로 작업을 안해서 각각 다른 해시코드 값을 알려준다.
		System.out.println("레퍼런스 주소값이 아닌 해시코드를 알려주네\n"+aa+"\n"+bb);
		if(aa==bb) System.out.println("레퍼런스 값은 같다.");
		else System.out.println("레퍼런스 값은 당연히 다르다.");
		if(aa.equals(bb)) System.out.println("같은 내용물이다.");
		else System.out.println("다른 내용물이다.");
		//set은 해시코드로 비교하여 중복을 체크한다.
		Set<A> s2 = new HashSet<>();
		s2.add(aa);
		s2.add(bb);
		System.out.println(s2.size()); System.out.println();
	
		System.out.println("static클래스 ForTest - equals와 hashcode 생성 함");
		ForTest ta = new ForTest(1, 1);
		ForTest tb = new ForTest(1, 1);
		//여기서도 마지막에 hashcode값을 알려주는데 내가 따로 작업을 안해서 각각 다른 해시코드 값을 알려준다.
		System.out.println("레퍼런스 주소값이 아닌 해시코드를 알려주네\n"+ta+"\n"+tb);
		if(ta==tb) System.out.println("레퍼런스 값은 같다.");
		else System.out.println("레퍼런스 값은 당연히 다르다.");
		if(ta.equals(tb)) System.out.println("같은 내용물이다.");
		else System.out.println("다른 내용물이다.");
		//set은 해시코드로 비교하여 중복을 체크한다.
		Set<ForTest> s3 = new HashSet<>();
		s3.add(ta);
		s3.add(tb);
		System.out.println(s3.size()); System.out.println();
		
		System.out.println("map도 가능하다.");
		Map<ForTest, Integer> map = new HashMap<>();
		map.put(ta, 1);
		map.put(ta, 123);
		System.out.println("같은 key라면 덮어씌어진다.");
		System.out.println(map.size());
		System.out.println(map.get(new ForTest(1, 1)));	// 해시코드로 같음을 표현하기에 보여준다. 
	}//=====================================================

}
