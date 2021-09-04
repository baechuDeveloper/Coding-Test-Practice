package Two_Pointer_alg;

/**==========================================================
 * ���� - ������ �� 2 - https://www.acmicpc.net/problem/2003
 * N���� ���� �� ���� A[1], A[2], ��, A[N] �� �ִ�. 
 * �� ������ i��° ������ j��° �������� �� A[i]+A[i+1]+��+A[j-1]+A[j]�� 
 * M�� �Ǵ� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
 ============================================================**/

import java.util.*;
import java.io.*;

public class p1 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st =  new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// N , �迭�� ���� ���� 
		int M = Integer.parseInt(st.nextToken());	// M , ��ü ������ ��
		
		st = new StringTokenizer(br.readLine());
		int len = st.countTokens();					// �迭�� ���� ���� , ��� N�� ���� 
		/*int[] arr = new int[len];					// �迭
		for(int i=0; i<len; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		*/
		int[] arr = new int[len+1];
		for(int i=1; i<=len; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		// ������ sum�� ���� ������ �ϰ�. ������ sum�� ���� ������ ������ L�� R�� ������. 
		// if���ǿ� ���� sum�� ���� �ٲ�� �׿� ���� LȤ�� R�� ���� �ٲ�Ե�. 
		int L=0;		// ���� ���� �ε���
		int R=0;		// ���� ������ �ε���
		long sum=0;		// ������� sum
		int count = 0;	// ����� ��
		/*
		// L���� �ʰ� �����غ� �����־�, R���� �ʰ� ���غ� ���� �־� ��� �˷��ִ� ���°� �ȴ�. R�� N-1���� �����ϸ� N�� �����ϻ� ���� ���� �ʴ´�.
		while(L<=R) {	// �� ������ �˰����� �⺻���� ���� ��� , ������ ��Ŀ� �°� ������ �ǰų� �ٲپ� �� �˸��� ���������� �����.
						// ��, �̷��� R�� L�� �¹������� ���� ���ᰡ �ɼ� �ֵ��� ������ ������ �Ұ��̴�.
						// L�ʰ� R������ ������ ��	
						// �˼��ֵ��� L�� R�� �������� �ش� ��ȣ���� ���߾� '�ƹ��͵� ������ ������ �ʾƼ� ���� 0 �λ���'�̴�. 
						// �׷��⿡ L�� R�� �Ѵ´ٴ°��� �츮�� ���� ��쿡 ���� �Ͼ�� ������ �׷��� �ǹ̻� �ο����ִ� ���̵ȴ�.
						// �׷��⿡ true�� ������ ���߾ ����� ����. 
			if(sum >= M) {			// �̰��� ������ L�� �þ�� ������ �پ��� sum�� �۾�����.
				if(sum == M) 
					count++;
				sum -= arr[L];
				L++;		//���� �ε��� �̵�
			}	
			else{					// �̰��� ������ R�� �þ�� ������ �þ�� sum�� Ŀ����.
				if(R>=N) 	//�� �ٸ� ���������� ����
					break;
				sum += arr[R];
				R++;		//������ �ε��� �̵� 
			}
			
		}*/
		
		// L <  <= R  (L�ʰ� R������ ������ ��)
		// ������ �� ���� ���� �̷� �������� �غ���. �̰�
		// �̰� ���������� ������ ������ �˷��ָ� ������ �տ� ���� ������ ���������� �޾Ƶ��δ�. ���� �Ѵ� ����. ��� �ε��� 0���� �����ϴ��� �ε��� 1���� �����ϰ� �ϴ��ķ� ���������� ���ϰ� ���������ڷ� �ٽ� while�� ���ƿö� ���������� ���ش�.  
		while(true) {	
			if(sum>=M) {
				if(sum==M)
					count++;
				sum -= arr[++L];
			}
			else {
				if(R>=N)
					break;
				sum += arr[++R];
			}
		}
		
		bw.write(count+"");
		bw.flush();
		
		br.close();
		bw.close();
	}

}
