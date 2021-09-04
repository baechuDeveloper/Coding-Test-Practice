package String_ALG__KMP_and_TRI;
import java.util.*;
import java.io.*;

/**==========================================================================
 * ȯ�����ڿ��̶�� �����̴�. 
 * �� ��� ABCDEF�� CDEFAB �� ���� �������� ���� ���� ���ڿ��� �����ϴ� ���̴�. 
 * Ǫ�� ����� ������ �� �̰� ��� KMP�� Ȱ������ �����ߴµ�, �� ������� ��¥ �Ӹ��� ����.
 * ȯ�� ���ڿ��� KMP�� �ذ��ҷ��� �˻��� ����� �Ǵ� ���ڿ� str�� 2��� �÷��� pattern�� ���� ���� �ִ��� Ȯ���ϴ� ���̴�.
 * �̷����ϸ� ������ �ٸ� ȯ�� ���ڿ����� 2���� str���� ������ �Ǿ����⿡ ȯ�� ���ڿ��̸� �ݵ�� kmp���� ��Ÿ����.
  
 * str = "ABCDEF" �� pattern = "CDEFAB" ��� 
 * "ABCDEFABCDEF" ���� "CDEFAB"�� �ִ��� KMP�� �ϸ� �Ǵ� ���̴�. 

 * �� ���������� �ð�ٴÿ� ���߾� �� �ð��� ������ 1 ������ 0�� �ִ� ���·� �ϰڴ�.
 * �� ���¸� �Ϸ��� ���ڿ�ó�� 10101101101 ���� ���̴� ���� ���ڿ� Ž���� �ϴ� �Ͱ� �����ϰ� ����� �ִ� ���̴�.
 =============================================================================*/

// 10266�� - �ð� ����
public class p4__Watch_Picture_KMP {
	
	static int[] A_clock, B_clock;
	//========================================================
	//main�Լ�
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer A = new StringTokenizer(br.readLine());
		StringTokenizer B = new StringTokenizer(br.readLine());
		A_clock = new int[2*360_000];
		B_clock = new int[360_000];
		for(int i=0; i<N; i++) {
			int a = Integer.parseInt(A.nextToken());
			int b = Integer.parseInt(B.nextToken());
			A_clock[a]   = 1;
			A_clock[a+360_000] = 1;
			B_clock[b]   = 1;
		}
		
		if( kmp() ) 
			System.out.println("possible");
		else 
			System.out.println("impossible");
		
	}//========================================================
	//�����Լ� �̰��� �°�
	static int[] getPi() {
		int lenB = B_clock.length;
		int[] pi = new int[lenB];
		int j = 0;
		
		for(int i=1; i<lenB; i++) {
			while(j>0 && B_clock[i]!=B_clock[j]) 
				j = pi[j-1];			
			if(B_clock[i]==B_clock[j]) 
				pi[i] = ++j;			
		}
		return pi;
	}//========================================================
	//KMP�˰��� �̰��� �°�
	static boolean kmp() {
		int[] pi = getPi();
		int lenA = A_clock.length;
		int lenB = B_clock.length;
		int j = 0;

		for(int i=0; i<lenA; i++) {
			while(j>0 && A_clock[i]!=B_clock[j]) {
				j = pi[j-1];
			}
			if(A_clock[i] == B_clock[j]) {
				if(j == lenB-1) {
					return true;
				}
				else {
					j++;
				}
			}
		}
		return false;
	}//========================================================

}
