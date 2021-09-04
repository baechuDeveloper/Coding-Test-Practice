package Backtracking;
import java.util.*;
import java.io.*;
//14889�� ��ŸƮ�� ��ũ
public class p4 {
	
	static int N, answer;
	static int[][] arr;
	static boolean[] visit;
	//=================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];	
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		visit = new boolean[N];
		answer = Integer.MAX_VALUE;
		
		make_team(0, 0);
		System.out.println(answer);
	}//=================================================

	static void make_team(int index, int count) {	//index�� �̹� ����, count�� ������� ���� �ο� ��
		if(answer == 0) return;	//���̻� ����Ұ� ���� �ְ��� ���� ����
		if(count == N/2) {
			lets_score();
			return;
		}
		
		for(int i=index; i<N; i++) {
			if(visit[i]==false) {
				visit[i] = true;
				make_team(i+1, count+1); //i+1�� N�� ���� ����Լ��� �ٸ� ����Լ����� �ݺ����� ����� �Ǿ� ����
				visit[i] = false;
			}	
		}
	}//=================================================
	
	static void lets_score() {
		int a_score=0, b_score=0, sum;
		
		for(int i=0; i<N-1; i++) 
			for(int j=i+1; j<N; j++) 
				if(visit[i]==true && visit[j]==true) {
					a_score += (arr[i][j] + arr[j][i]);
				}
				else if(visit[i]==false && visit[j]==false) {
					b_score += (arr[i][j] + arr[j][i]);
				}
			
		sum = Math.abs(a_score-b_score);
		if(answer>sum)
			answer = sum;
	}//=================================================
}
