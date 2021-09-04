package A_experiment;

public class A_static_method {
	
	int Q;
	static int N;
	static int H = 5;
	//==========================================
	// static이 아닌 메소드
	void AA() {
		//기본 출력 - 초기화 하지 않아도 전역변수들이 0으로 초기화 되어있음
		System.out.println(Q+" "+N+" "+H);
		//전역변수이용
		Q = 100;
		N = 100;
		H = 100;
		System.out.println(Q+" "+N+" "+H);
		//this이용
		this.Q = 10;
		this.N = 10;	// 근데 이클립스가 나에게 "이 표현보다는 static_method.N을 사용하는게 좋습니다" 라고 알려줌.
		this.H = 10;	// 이렇게 생각하고 실험한건데 답을 확실히 알려줌 ㅋㅋ
		System.out.println(Q+" "+N+" "+H);
		
		//지역변수 선언
		int Q=1, N=1, H=1;
		System.out.println(Q+" "+N+" "+H);
		
		//지역변수와 전역변수의 이름이 같을때 this를 이용하면 된다. 그러므로 전역변수도 자유롭게 사용할 수 있다. 
	}//==========================================
	// static인 메소드
	static void BB() {
		// static이 아닌 Q는 사용 불가능
		//System.out.println(Q+" "+N+" "+H);
		
		//전역변수이용
		//Q = 100;	
		N = 100;
		H = 100;
		System.out.println(N+" "+H);
		//this를 사용을 할 수 없다.
		/**
		this.Q = 10;
		this.N = 10;
		this.H = 10;
		**/	

		//지역변수 선언
		int Q=1, N=1, H=1;
		System.out.println(Q+" "+N+" "+H);
		
		//지역변수와 전역변수의 이름이 같을때 this를 이용할 수 없었다. 
		// 그러므로  static_method를 자신의 클래스 이름을 활용 
		// 여기서 static으로 선언이 안된 Q객체변수는 클래스.Q로 사용 불가능. 또한 앞선 this.Q역시 static 메소드안 이라서 불가능
		// 우리가 배운대로 역시 static 메소드 안에서 전역변수를 사용하려면 오직 static이 붙은 전역변수만 사용 가능하다.
		//static_method.Q = 250;
		A_static_method.N = 250;
		A_static_method.H = 250;
		
		System.out.println(A_static_method.N+" "+A_static_method.H);
	}//==========================================
	// main 함수 - 이 또한 static 메소드
	public static void main(String[] args) {
		A_static_method obj = new A_static_method();
		obj.AA(); System.out.println("---------------------------");
		obj.BB();
	}

}
