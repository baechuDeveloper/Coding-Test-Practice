package Backtracking;
import java.util.*;
import java.io.*;
//14889번 스타트와 링크
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

	static void make_team(int index, int count) {	//index는 이번 순서, count는 현재까지 뽑힌 인원 수
		if(answer == 0) return;	//더이상 계산할거 없이 최고의 값이 나옴
		if(count == N/2) {
			lets_score();
			return;
		}
		
		for(int i=index; i<N; i++) {
			if(visit[i]==false) {
				visit[i] = true;
				make_team(i+1, count+1); //i+1에 N을 받은 재귀함수는 다른 재귀함수에서 반복문을 벗어나게 되어 안전
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
