package Math;

import java.io.*;

// �����佺�׳׽��� ü ( �Ҽ� ������ ���ϴ� �˰��� )
public class Eratostenes {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		//--------------------------------------
		// ���� ȿ�� ���� ���

		boolean[] arr = new boolean[num+1];    //false �ΰ��� ��� �Ҽ� 
		arr[0] = arr[1] = true; //true�� �Ȱ��� prime�Ҽ��� '�� �� ���� ��'���̴�.
	
		//2 ���� ���ڸ� Ű������ ������� ����
		for(int i=2; i*i<=num; i++) {	//i�� 1�� �����ϸ鼭 ���� ��ġ�� ����� �����ִ� ��Ȱ
			for(int j=i*i; j<=num; j+=i) { //j�� �ش� i�� �������� �����ŭ �ö󰣴�. (������ ����� ���� ���´� ���������� �Ҽ��� �ƴ��� �����ְ� �Ǿ�����ī���)
				arr[j] = true;        //2�� ������ 2�� ��� true
			}
		}
		
		for(int i=0; i<=num; i++) {
			if(false == arr[i]) {
				System.out.print(i + " "); // �Ҽ��� ��
			}
		}

System.out.println();
		//-------------------------------------- �� �������� ��� 2 ������ ȿ�������� ���� 2�� �ξ� ����.
		boolean[] primeNum = new boolean[num+1];
		primeNum[1] = true;

		for(int i= 2; i <= num; i++) {
			for(int j = 2; i*j <= num; j++) {
				primeNum[i*j] = true;
			}
		}
		for(int i=1; i <= num; i++) {
			if(!primeNum[i]) System.out.print(i+" ");
		}
System.out.println();

		//-------------------------------------------

	}

}
