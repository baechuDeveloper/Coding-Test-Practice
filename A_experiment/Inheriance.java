package A_experiment;


public class Inheriance {
	
	//=====================================
	
	static class A {
		int a;
		A(int a){
			this.a =a;
		}
		public void click() {
			System.out.println("A "+a);
		}
	}//=====================================
	
	static class B extends A{
		int b;
		B(){
			super(-100);
			b=10;
		}
		
		public void click() {
			System.out.println("B "+b);
		}
		
		public void click_for_A() {
			super.click();
		}
	}//=====================================
	
	public static void main(String[] args) {
		A aaa = new A(100);
		B bbb = new B();
		A ccc = new B();
		aaa.click();
		bbb.click();
		ccc.click();
		
		bbb.click_for_A();
		
		// ccc에게는 click_for_A 매소드를 실행할 권한이 없다. A라는 클래스에는 click_for_A()라는 메소드 자체가 없어서다.
		// 대신 자기가 갖고있는 click()은 실제 공간에서 자식이 다시 오버라이딩 한것이 있다면 
		// 자신의 것이 아닌 자식의 것으로 작업을 한다. 원래 것이 사라진건 아니지만 둘다 되어있다면 이후에 작성된 자식의 것으로 작업이 된다. 
		
	}//=====================================

}
