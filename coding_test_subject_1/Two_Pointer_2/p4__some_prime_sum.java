package Two_Pointer_2;
import java.io.*;
// 1644�� - �Ҽ��� ������
// �����佺�׳׽��� ü�� �Ҽ��� ���ϰ�, �� ������ �˰����� �̿��ؼ� �������� ���Ѵ�.
public class p4__some_prime_sum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1]; //�Ҽ��� �� �迭
		int len = 0; //�Ҽ� ����
		/**�����佺�׳׽��� ü**/
		boolean[] Pri = new boolean[N+1];    //false �ΰ��� ��� �Ҽ� 
		Pri[0] = Pri[1] = true; //true�� �Ȱ��� prime�Ҽ��� '�� �� ���� ��'���̴�.
	
		//2 ���� ���ڸ� Ű������ ������� ����
		for(int i=2; i*i<=N; i++) {	//i�� 1�� �����ϸ鼭 ���� ��ġ�� ����� �����ִ� ��Ȱ
			for(int j=i*i; j<=N; j+=i) { //j�� �ش� i�� �������� �����ŭ �ö󰣴�. (������ ����� ���� ���´� ���������� �Ҽ��� �ƴ��� �����ְ� �Ǿ�����ī���)
				Pri[j] = true;        //2�� ������ 2�� ��� true
			}
		}
		
		for(int i=0; i<=N; i++) {
			if(false == Pri[i]) {
				arr[len++] = i; // �Ҽ��� ��
			}
		}

		//�������� arr�� �ִ� ������ len-1������ �۾��Ѵ�. ���̸� len���� Ȱ������.  
		/** �� ������ �˰��� ���� **/
		int left=-1, right=-1, sum=0, pos_count=0;
		
		while(true) {	//p3Ŭ�������� ���ߵ� ��� left<=right�� ���̵� �� �����տ����� ������.
			
			if(sum > N) {	//���� �� ũ�ٸ� left�� �÷��� ���� �����. 
				left++;
				sum -= arr[left];
			}
			else {	//���� �۰ų� ������ right�� �÷��� ���� �ø���. 
				if(sum==N) pos_count++;
				right++;
				if(right>=len) break;
				sum += arr[right];
			}
		}
		
		System.out.println(pos_count);
	}

}
