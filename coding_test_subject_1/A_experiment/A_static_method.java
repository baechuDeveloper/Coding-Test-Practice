package A_experiment;

public class A_static_method {
	
	int Q;
	static int N;
	static int H = 5;
	//==========================================
	// static�� �ƴ� �޼ҵ�
	void AA() {
		//�⺻ ��� - �ʱ�ȭ ���� �ʾƵ� ������������ 0���� �ʱ�ȭ �Ǿ�����
		System.out.println(Q+" "+N+" "+H);
		//���������̿�
		Q = 100;
		N = 100;
		H = 100;
		System.out.println(Q+" "+N+" "+H);
		//this�̿�
		this.Q = 10;
		this.N = 10;	// �ٵ� ��Ŭ������ ������ "�� ǥ�����ٴ� static_method.N�� ����ϴ°� �����ϴ�" ��� �˷���.
		this.H = 10;	// �̷��� �����ϰ� �����Ѱǵ� ���� Ȯ���� �˷��� ����
		System.out.println(Q+" "+N+" "+H);
		
		//�������� ����
		int Q=1, N=1, H=1;
		System.out.println(Q+" "+N+" "+H);
		
		//���������� ���������� �̸��� ������ this�� �̿��ϸ� �ȴ�. �׷��Ƿ� ���������� �����Ӱ� ����� �� �ִ�. 
	}//==========================================
	// static�� �޼ҵ�
	static void BB() {
		// static�� �ƴ� Q�� ��� �Ұ���
		//System.out.println(Q+" "+N+" "+H);
		
		//���������̿�
		//Q = 100;	
		N = 100;
		H = 100;
		System.out.println(N+" "+H);
		//this�� ����� �� �� ����.
		/**
		this.Q = 10;
		this.N = 10;
		this.H = 10;
		**/	

		//�������� ����
		int Q=1, N=1, H=1;
		System.out.println(Q+" "+N+" "+H);
		
		//���������� ���������� �̸��� ������ this�� �̿��� �� ������. 
		// �׷��Ƿ�  static_method�� �ڽ��� Ŭ���� �̸��� Ȱ�� 
		// ���⼭ static���� ������ �ȵ� Q��ü������ Ŭ����.Q�� ��� �Ұ���. ���� �ռ� this.Q���� static �޼ҵ�� �̶� �Ұ���
		// �츮�� ����� ���� static �޼ҵ� �ȿ��� ���������� ����Ϸ��� ���� static�� ���� ���������� ��� �����ϴ�.
		//static_method.Q = 250;
		A_static_method.N = 250;
		A_static_method.H = 250;
		
		System.out.println(A_static_method.N+" "+A_static_method.H);
	}//==========================================
	// main �Լ� - �� ���� static �޼ҵ�
	public static void main(String[] args) {
		A_static_method obj = new A_static_method();
		obj.AA(); System.out.println("---------------------------");
		obj.BB();
	}

}
