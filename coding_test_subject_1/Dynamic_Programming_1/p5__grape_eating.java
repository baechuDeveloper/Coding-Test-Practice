package Dynamic_Programming_1;

import java.io.*;

// 2156�� - ������ �ý�
// �� �� ������ �ϳ��� �����ְ� ���̱� �����Ѵ�. ��� �ϸ� ����. ���ʿ��� �����ϴµ�  i=1�̸� ������ �ϳ��� �����ַθ� ���̳� i=2�̸� 
// ���� 2���� �����ְ� ���̳� �׋��� �ִ��... ���� ���� n���� ���̳� �̷��� 
public class p5__grape_eating {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] podo = new int[N+1];
		int[] score = new int[N+1];	//�������ִ� �ִ� ���� 
		for(int i=1; i<=N; i++)
			podo[i] = Integer.parseInt(br.readLine());
		
		score[0] = 0;	//score[3]�� ���ؼ��� ������
		score[1] = podo[1]; //����1���� ����
		if(N==1) {System.out.println(score[1]); return;}
		score[2] = podo[2] + podo[1];	//���� 2���� ����
		if(N==2) {System.out.println(score[2]); return;}
		
		for(int i=3; i<=N; i++) {
			score[i] = podo[i] + Math.max(score[i-3]+podo[i-1], score[i-2]);
			//���⼭ ������ �߿��� ������ �ִµ�
			// ���� �������� �����ֱ��� ���캸�� ���߿� �����ɷ� �����ϴ°��ε�.
			// �̸� ��� i=k���� ������ ���� �� k-1�� �񱳸� �ؼ� ������ ���� �����Ҵٸ� k���� �ٶ� �������� �ִ밪�� k-1���� �� �ִ밪�� �� �����ϱ⿡ �� ���� �̾�� 
			// k���� �� �������� ���� �׷��� �������� ���°� �����ϴ�.
			score[i] = Integer.max(score[i], score[i-1]);
		}
		//System.out.println(Integer.max(score[N], score[N-1]));
		System.out.println(score[N]);
	}

}
