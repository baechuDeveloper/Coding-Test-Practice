package Dynamic_Programming_1;

import java.io.*;

// 2579�� - ��ܿ����� 
// ���� �ִ��������� ��� �����ϴ°� ������ ����ȴ�. ������������ ����ϳ� ��� ���簡. ������������ �ٷ� �������ϴ���.
// �������� �����غ��� ��ȭ���� ��������.
public class p4__climbing_stairs {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stair = new int[N+1];
		int[] score = new int[N+1];	//�������ִ� �ִ� ���� 
		for(int i=1; i<=N; i++)
			stair[i] = Integer.parseInt(br.readLine());
		
		score[0] = 0;	//score[3]�� ���ؼ��� ������
		score[1] = stair[1];
		if(N==1) {System.out.println(score[1]); return;}
		score[2] = stair[2] + stair[1];
		if(N==2) {System.out.println(score[2]); return;}
		
		for(int i=3; i<=N; i++) 
			score[i] = stair[i] + Math.max(score[i-3]+stair[i-1], score[i-2]);
		
		System.out.println(score[N]);
	}

}
