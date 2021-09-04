package String_ALG__KMP_and_TRI;

import java.util.*;
import java.io.*;
//https://blog.naver.com/kks227/220917078260
/*=========================================================================
 * ���ڿ� A�� ���ڿ� B �߰��� �� ��, ��� ��ġ���� ��Ÿ������ �˾Ƴ��� ���ڿ� ��Ī ����

 * �̸� ���ؼ� KMP �˰������� ��� ���̴�. � ���� O(N+M)�� ã���ϴ�. (N�� A�� ����, M�� B�� ����)
 * 
 * KMP������ B�� �� ��ġ���� ������ �����Լ�(failure function)���� �����մϴ�.
 * �� ���� ����ġ�� �߻����� �� j�� ���� �̵��ؾ� �ϴ����� ��Ÿ���� ���Դϴ�. 
 * i�� ������� Ž���Ѱ����� �հ� �ݺ��� �Ǵ� ��ġ�� ���۵Ǵ� ���� ���ο� �������� ���,
 * j�� 0���� �ٽ� ���۾��ϰ� �����Լ��� ���ؼ� �ݺ��Ǵ� ��ġ�� ���� ��ġ���� j�� ���� �ٷ� �Ű� ���ʿ��� �ߺ�Ž���� ���� ���� �ֽ��ϴ�.
 * �����Լ��� ���ؼ� �츮�� �ߺ��� ���Ҽ��ְ� �˴ϴ�.  
 --------------------------------------------------------------------------------
 * �����Լ� ����
 * fail(x) = B�� x��°���� ���ڿ��� ����� ��  ���λ�/���̻� �� �ִ� ����
 * ------------------------
 * x(������)   B	   fail(x)
 * ------------------------
 * 0	  a				0
 * 1	  ab			0
 * 2	  abc			0		
 * 3	  abcd			0
 * 4	  abcda			1
 * 5	  abcdab		2
 * 6	  abcdabd		0
 * ------------------------

 * ���� ���� 6��°���� ���� ���ڰ� �ȳ��Դٸ�, fail(5)�� �̷��� ���λ�� ���̻翡�� ��ġ�ϴ� �� 2�� ���ݴϴ�. �׷� ���� i�� ��ġ�� j=2�̶�� ��Ȳ���� ���ؼ� ������ �ϸ� �˴ϴ�.
 * �����Լ��� ���� �ش� ������ġ���� �����ߴٸ� �� ���� i������ pattern�� ���(j)���� �����ϴ°� ������ �˷��ش�. 
 * �����Լ��� ���ϰ��� ���� ���� ��ġ������ '���λ�'�� ���ۿ��� ��ŭ�� �ݺ��� �ִ��� �� ���λ��� ��� ���۵Ǵ� �Ͱ� �������� �˷��ش�.
 * j��°�� �����ߴٸ� j-1�� �����Լ��� ������ �� �������� ���λ�� ���̻翡�� �ݺ��� ������ �̵��Ͽ� �� ������ �Ѵ�. 
 * 
 * ���⼭�� �� �����Լ��� ���� �迭�� �ξ� ���� �־��ִ� ���·� �ߴ�.
 * �� �迭�� pi�� ���صξ��� �� �迭�� ���Ұ��� �����Լ��� �Ű������� ����� �ǰ�, �迭 ���� �����Լ��� ������ ���� �˴ϴ�.  
 ==========================================================================*/

// 1786�� - ã��
public class p1__KMP {
	
	/**pattern�� �����Լ� �迭 ����-------------------------------------------------------
	 * ��ġ |	0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17	
	 * ���� |	a  b  c  d  a  b  c  e  a  b  c  d  f  a  b  c  d  g
	 * pi��|	0  0  0  0  1  2  3  0  1  2  3  4  0  1  2  3  4  0 
	 -----------------------------------------------------------*/
	/*�����Լ��� ����� �迭�� ��Ƶα�*/ 
	static int[] getPi(String pattern) {
		int m = pattern.length();
		int j = 0;	//���λ� ������. ���ÿ� �� ���� ������ ���̻�� ������ ���λ翡�� �ݺ��Ǵ°� ����� �˷��ش�. �׸��� ���� i������ pattern�� � ��ġ�� '�������� �ϸ�' i�� ���ϴµ� ���� �� �ִ��� �˷��ش�.
		//j�� ���� ����Ǵ� ��Ȳ�̴�. �ٽ� ����, ����� ���� ���λ��� ���° �ش��ϴ� ������ ���� ��Ī�� ���������� �̾�� ������ �˷��ݴϴ�.
		char[] p = pattern.toCharArray();
		int[] pi = new int[m];	//0�� ���� �׳� ���λ��� �� ó���� ����. pattern�� ���λ� ó������ �ٽ� ���ϼ��� ��� �˷��ִ� ���̴�.
		//i�� ���̻��� ������ //1���� �����ϴ°� ���λ�/���̻�� ��� 2���� ���ں��� ������ �Ǿ.
		for(int i=1; i<m; i++) {
			while(j>0 && p[i] != p[j])	//���̻簡 �����Ҷ�, �ش� ���̻�� ���λ��� ���� ������ �ٸ��ٸ� ���� �ܰ��� ���λ� ���¸� �����ͼ� �����Ͽ� ���������� �ݺ��Ѵ�.  
				j = pi[j-1];
			if(p[i] == p[j])	//��ø�� �Ǵ°� �ִٸ� 
				pi[i] = ++j;	//�����Լ� �̿�� ���λ翡�� ������ ��ġ�� �˷��ش�. �� ���λ翡�� ������� �ݺ��� ������ �����ش�.
		}
		return pi;
	}//=======================================================
	/**--------------------------------------------------------------
	 * str - ��ü ���ڿ�
	 * pattern - ã�� ���ڿ�
	 * j - ã�� ���ڿ�(pattern)�� �� �ε���. pattern������ j��ġ�� ���ڸ� ����ǰ�, str������ i�� �������� j��ŭ ������ ��ġ�� ���ڸ� ������.
	 * i - ��ü ���ڿ�(str)�� ���� �ε����̱� ������ 1�� �����ϱ⸸ ��. ���� �ұ�Ģ������ ���� ���Ѵ�.
	 * i�� str���� �� ��° ���ڸ� '������'���� ���� pattern�� ���ϰ� �ִ��� �˷��ָ�, �̹� Ž���� ���� str������ ���۹�ȣ (ó���� 0����)
	 * j�� pattern�� �� ��° ���ڸ� str�� ���ϰ��ִ��� �˷��ִ� B���� ��ȣ (���� ó���� 0����) 
	 ---------------------------------------------------------------**/
	/*KMP �˰�����*/
	public static ArrayList<Integer> kmp(String str, String pattern) {
		ArrayList<Integer> ans = new ArrayList<>();
		int[] pi = getPi(pattern);
		int N = str.length();
		int M = pattern.length();
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int j = 0;

		for(int i:pi) System.out.print(i+" "); System.out.println();

		/*��ü ���ڿ��� ���ڴ� �ϳ��� �þƺ����Ѵ�.*/
		for(int i=0; i<N; i++) {
			System.out.println("���� i:"+i+",  j:"+j+"  s[i]:"+s[i]+"  p[j]"+p[j]);
			/*���� ���ڰ� ����ġ�ϸ� fail���� ���󰡸� j���� ������*/
			while(j>0 && s[i] != p[j]) { //���� ũ���� ���ڰ� ���� �ٸ��ٸ� pi�迭�� ���ؼ� j�� �����Ų��.
				//�߰� �ܰ� �پ�ѱ�. �̹� ������ �� �Ѿ��.
				j = pi[j-1]; //���� j�� ���� ���� j-1�� �����Լ� ���� ��ٷ� �������� �ϼ��� ���λ縦 Ȯ���ϰ� 
							 //�� �������±��� �̹� üũ�� �Ǿ ���ν����ϴ� ������ j=0���� ���� �ʰ� j=fail(j-1)�� ��ġ���� �����ϸ� �˴ϴ�. 
							 //������ġ�� �׻� �ϳ��� �����ϴ� i�� ��ġ�����̸� �� i�� ��ġ�������� j=fail(j-1)�� ��ġ���� '�����ϸ� �ȴٴ� ��'�� �˷��ݴϴ�. 
							 //�� ���� �����ϸ� �Ǵ� ��ġ�� ���ڿ� i�� ���ڰ� ���ٸ� ������ ���� �Ѿ ����� �����ϰ� �ٷ� ������ �� �� �ִ� ���̴�. 
				System.out.println("--�ݺ��� ����  j:"+j+"  p[j]:"+p[j]);
				//�׶� ���� ������ ��ġ�� �˷��־�����, �� ��ġ�� �ƴϸ� �ٽ� fail�Լ��� ���� �̰͵� �ƴϸ� �� ������ �̻��·� �����ϼ���!, �� �̰͸����� �ȵǸ� �׷� �� ���� �̰�����..! �ϴٰ� �� �ȵǸ� 0���� ���Եȴ� ��
			}

			// P�� S[i : i+M-1]���� ã��
			if(s[i] == p[j]) {	
				if(j == M-1) { //j�� �� �ε����ν�, �ε����� ã�� ���ڿ��� ũ�⿡ �����ϸ� ���ڿ� ã��.
					ans.add(i-(M-1)); //������ġ�� �˷��ش�. //i=0���� �����Ѵٸ� (i - len_B + 1). ���� ���ǿ� ���� 1�� ���� //���� ���� ã�� ���ڿ��� ���� �� �ֱ� ����
					j = pi[j];	//������ pi[j-1]�� ������ j���� ������ ��ġ�� �˷��ذ��� ������ i�� ���ؼ� ������ ��ġ�� ��Ī�ϱ� ���ؼ���. 
								//���⼭  pi[j]�� ���� ���� i+1�� ���� ���� ������ ��ġ�� ��Ī�ϱ� ���ؼ� �� ������ �����Լ����� ���� �����°��̴�.
								//�� �̾ ������ �ɼ��ִ��� �ľ��ϴ� ���̴�. 
				}
				else {
					j++;
				}
			}
		}		

		return ans;
	}//=======================================================
	/*main�Լ�*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String A = br.readLine();
		String B = br.readLine();
		
		ArrayList<Integer> ans = kmp(A, B);
		
		bw.write(ans.size()+"\n");	//�� ��Ī�� ����
		for(int i : ans) 
			bw.write( (i+1) +" ");	//���������� i�� 1���� �����ϴ� ���� �۵��Ѵ�. �츮�� Ǯ�̴� i�� 0���� �����ϴ� ������ �۾��ߴ�. �׷��� +1
		bw.flush();
	}//=======================================================

}