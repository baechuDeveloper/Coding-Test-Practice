package String_ALG__KMP_and_TRI;

import java.io.*;

/**
 * ���λ�� ���̻縦 �̿��ϴ� �� 
 * ������ �������� �������� �־��� N���� ���̸� ���� ���� �ݺ�
 * �ְ�� 1���� ���̸� ���� ���� �ݺ� 
 * �����Լ��� ���ؼ� ���λ�� ���̻��� �̾����� ���踦 Ȯ���ϴµ�
 * ���⼭ �� �����ϸ� ������ 
 * ���ڿ� ���̻��� ������ ���ڴ� �̿� �ش�Ǵ� ���λ��� ���� �� �ڿ� ���ڿ��� �̾��� �� �ִٴ� �������ʴ� ����� �Ҽ����ִ�. 
 * �׷��� ������ ���̻縦 �������� ���λ��� ��� ��ġ���� �˾Ƶθ� �Ƹ� �ش� ���λ縦 �ݺ��ϴ� ����� �ν��Ҽ��� �ְԵȴ�.
 * "abcdab" ��� ���̻�������� ���̻�� ab�� �������ִ�.(���� cdab�� ��Ƶ� ������ �����Լ��� �ٷ� �̿��Ұ��̸� ���ڸ� ������.)
 * �׷��� �Ǹ� N - pi[N-1]���� �� "abcd"�� ���� ª�� ����μ� ��� �ݺ��ǰ� �ִٰ� ����� �Ҽ��ִ�.
 * */
public class p3__ADs {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());	//�������� ũ��, ��� ��� �ǰڴ���.
		String str = br.readLine();	//���� �������� ���̴� ���ڿ� 
		int[] pi = makePi(str);
		for(int i:pi) System.out.println(i);
		System.out.println(L - pi[L-1]);
	}//========================================================
	
	static int[] makePi(String pattern) {
		int M = pattern.length();
		int[] pi = new int[M];
		char[] p = pattern.toCharArray();
		int j=0;
		
		for(int i=1; i<M; i++) {
			while(j>0 && p[i]!=p[j]) 
				j = pi[j-1];
			if(p[i] == p[j]) 
				pi[i] = ++j;	
		}

		return pi;
	}//========================================================
}
