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
		
		// ccc���Դ� click_for_A �żҵ带 ������ ������ ����. A��� Ŭ�������� click_for_A()��� �޼ҵ� ��ü�� �����.
		// ��� �ڱⰡ �����ִ� click()�� ���� �������� �ڽ��� �ٽ� �������̵� �Ѱ��� �ִٸ� 
		// �ڽ��� ���� �ƴ� �ڽ��� ������ �۾��� �Ѵ�. ���� ���� ������� �ƴ����� �Ѵ� �Ǿ��ִٸ� ���Ŀ� �ۼ��� �ڽ��� ������ �۾��� �ȴ�. 
		
	}//=====================================

}
