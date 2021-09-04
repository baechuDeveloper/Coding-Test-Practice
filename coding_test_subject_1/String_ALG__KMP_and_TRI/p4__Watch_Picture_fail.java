package String_ALG__KMP_and_TRI;
import java.util.*;
import java.io.*;

/**
 * ȯ�����ڿ��̶�� �����̴�. 
 * �� ��� ABCDEF�� CDEFAB �� ���� �������� ���� ���� ���ڿ��� �����ϴ� ���̴�. 
 * 
 * ���� Ǭ �� ������ ȯ�����ڿ��� ���� ������ ������ ����ϰ� ���־�����, �� ���� �����Ѱ���
 * �Ʒ��� �����־���.
 * */
// 10266�� - �ð� ����
public class p4__Watch_Picture_fail {

	static int[] clock_A, clock_B;	//�ð� �ٴ��� ����Ű�� �� 0<= <360_000 , 360�� ���� ��ġ�� ����ȴ�.
	static int[] among_A, among_B;	//���� �ź��� �ٷ� �ڱ� ���� ū �ð�ٴÿ��� �� ���̻��� ������ ���غ� '���̰���'
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer A = new StringTokenizer(br.readLine());
		StringTokenizer B = new StringTokenizer(br.readLine());
		clock_A = new int[N]; clock_B = new int[N];
		among_A = new int[N]; among_B = new int[N];
		
		for(int i=0; i<N; i++) {
			clock_A[i] = Integer.parseInt(A.nextToken());
			clock_B[i] = Integer.parseInt(B.nextToken());
		}
		//�켱 ������ ���ؼ� �ð�ٴ� ���̻��� ������ ������ ������ ���������� ������ ���ϴµ� ���δ�.
		Arrays.sort(clock_A);
		Arrays.sort(clock_B);
		
		//���̰����� ��� ���صд�. ��, �������� ù���� �ٴÿ��� �������ٴ��� ���� ���̶� ������ ���̳ʽ��� ���´�. ���� ������ �����ؾ� ������ ȯ���� �����̹Ƿ� �����μ� 360_000�� ���� ����� �ϼ����ش�.	
		for(int i=0; i<N; i++) {
			if(i==N-1) {
				among_A[i] = (clock_A[0]-clock_A[i]) + 360_000;
				among_B[i] = (clock_B[0]-clock_B[i]) + 360_000;
			}
			else {
				among_A[i] = clock_A[i+1]-clock_A[i];
				among_B[i] = clock_B[i+1]-clock_B[i];
			}
		}
		
		for(int i:among_A) System.out.print(i+" "); System.out.println(); for(int i:among_B) System.out.print(i+" "); 
		
		// ���� ������ �����״� ������ ������ ���������� ������ ������ üũ���ִ�. 
		// �̰��� �ϳ��� ���ڿ����� � ���ں��� ���ͼ� ���ڸ� ���ƿ����� �ϴ� ���̴�.
		// ABCDEF �� CDEFAB �ΰ� ���� ������ ������ ������ ó�� ���� ������ �ٸ� ���̴�.
		// �� �ð蹮���� �̿� ����. ���� ���̻��� ������ �ٴ��� ������� ���س��Ұ�, 
		// �̸� ���� ���� �ð������� ABCDEF�� CDEFAB�� ó�� Ȯ���ϴ� ���̴�.
		
		
		/*--------------------------------------------------------------------
		 * �Ʒ� ����� �ƽ��� �κ��� 
		 * 4
		 * 1000 4000 2000 3000
		 * 358000 359000 0 1000
		 * 
		 * �̷��� �ϸ� ��ġ�ؾ������� ��ġ���� �ʴٰ� ���´�. Ʋ�� ���� ���̰����� ���� ������ ������ �� �־,
		 * �̷��� ���� ���ΰ͸� ã���� �װ� �̰��� ���ϴ��� ������ ���ϴ��� ��Ȯ�� �Ѽ��� ��� �׻� �´� ���� ������ �� ����.
		 * �׷��⿡ �� ����� ������ �� ���� ������ ���� ���� ���� ��밡������ �̷� ��Ȳ������ �ȵȴ�.
		  --------------------------------------------------------------------
		int i = 0;	//A�� ������ 
		int j = 0;	//B�� ������ 
		boolean check = false;	//���� ������ ������ ���ٸ� �ƿ� ���� �ȵȴ�.
		
		// A�� B�� ���� �������� ã�ƺ���. A�� ù��° ���ڿ� ���� B�� ���ڸ� ã�ƺ���.
		for(j=0; j<N; j++) {
			if(among_A[0] == among_B[j]) {
				check = true;
				break;
			}
		}
		if(check==false) {
			System.out.println("impossible");
			return;
		}
		
		// ���� ���� ���� ���ڿ��� �Ϸķ� �����ϸ� ���� ���� ���ڿ����� �ľ��Ѵ�. 
		for(i=0; i<N; i++,j++) {
			if(j==N) j=0;
			if(among_A[i] != among_B[j]) {
				check = false;
				break;
			}
		}

		if(check)
			System.out.println("possible");
		else
			System.out.println("impossible");
		
		*/

	}//========================================================

}
