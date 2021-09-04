package Backtracking;
import java.util.*;
import java.io.*;
//14889번 스타트와 링크
// 백트래킹을 위한 비트마스크 활용 시간을 336->288ms로 줄여둠. 동시에 자잘한 계산량을 줄이려고 안 조건문이 있는데 이건 크게 차이가 안난다.
public class p4_improve {
	
	static int N, answer;
	static int[][] arr;
	static int visit=0; // 20개의 비트가 있으면 충분.
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
			//if(count==0 && i==N/2) break;	//단순히 맨 첫번째 값이 N/2번 이후부터는 count의 값이 'N/2'의 값을 도달하지 못하므로 N/2번까지 정해둔다. 계산 시간을 줄이려는 작은 정성
												//근데 웃긴건 이게 있어도 충분히 이 함수는 가볍고 함수호출을 여러번 해도 안해도 사실 크게 차이가 나지 않았다.
			if( (visit & (1<<i)) == 0 ) {	//해당 비트마스크를 갖고있지않음. 즉 방문을 하지 않았음
				visit = visit | (1<<i);
				make_team(i+1, count+1); //i+1에 N을 받은 재귀함수는 다른 재귀함수에서 반복문을 벗어나게 되어 안전
				visit = visit & ~(1<<i);
			}	
		}
	}//=================================================
	
	static void lets_score() {
		int a_score=0, b_score=0, sum;
		
		for(int i=0; i<N-1; i++) 
			for(int j=i+1; j<N; j++) 
				if( (visit & (1<<i)) == (1<<i)  
					&& (visit & (1<<j)) == (1<<j) ) {	//해당 i와 j가 둘 다 존재 하는가
					a_score += (arr[i][j] + arr[j][i]);
				}
				else if( (visit & (1<<i)) == 0  
						&& (visit & (1<<j)) == 0 ) {	// 해당 i와 j가 둘 다 존재 안하는가
					b_score += (arr[i][j] + arr[j][i]);
				}
			
		sum = Math.abs(a_score - b_score);
		if(answer>sum)
			answer = sum;
	}//=================================================
}
