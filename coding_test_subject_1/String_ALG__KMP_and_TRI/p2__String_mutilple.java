package String_ALG__KMP_and_TRI;

import java.io.*;
/**----------------------------------------------------------
 * �����Լ��� ���ϴ� ����� �����غ��Ҵ�.
 * �ֳ��ϸ� ���λ翡 ���ؼ� �� ���λ簡 ��ŭ �ݺ��ǰ� �ִ��� �ľ��ϴµ� �����Լ���ŭ �����Ѱ� ����. 
 * ���λ縦 ���� �ݺ��� ����Ǵ°� �´��� Ȯ��. �׶� �������� �ش��ϴ� pi[len-1]�� ���� �������̶�� ���λ��� �������� �ǹ����״�, �̸� �̿��ؼ� ���غ���. 
 * �׷� ���� �� 1�� ó���ϸ� �ɰ��̴�.
 -----------------------------------------------------------*/
// 4354�� - ���ڿ� ���� 
public class p2__String_mutilple {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = "";
		while((str=br.readLine())!=null && str.length()!=0) {
			if(str.length()==1 && str.charAt(0)=='.') {
				break;
			}
			int[] pi = makePi(str);
			int len = str.length();
			//for(int i:pi) System.out.print(i+" "); System.out.println();
			
			int cycleStr = len - pi[len-1];	//�ݺ� �� �� �ִ� �ִ����
			if(len/2>=cycleStr && len%cycleStr==0) 	//len�� ���ݺ��� ũ�� �ʰ�, �ݺ��� �� �ִ� �ִ���̰� len���� ������ ������ �� �ִٸ�.
				bw.write(len/cycleStr+"\n");
			else
				bw.write(1+"\n");
		}
		bw.flush();

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
