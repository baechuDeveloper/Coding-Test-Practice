package TraceBack_Shortest_Path_with_DP;
import java.util.*;
import java.io.*;

// 2대의 경찰차는 처음에 첫번째 경찰차(1,1)과 두번째 경찰차(N,N)에 위치한다. 

// 2618번 - 경찰차
public class p5__Police_Car { 

	static int N, W; 		// 도로의 개수, 처리해야 할 사건 개수		5 <= N <= 1000	,	1 <= W <= 1000
	static int[][] dp;		// dp[a][b] = 첫 번째 경찰차의 위치가 a번째 사건이고, 두 번째 경찰차의 위치가 b번째 사건에 있을 때, 
							// 현재 위치에서 사건을 끝까지 해결할 때까지 이동하는 거리의 합의 최솟값. 즉 누적되어 들어가며 최종 누적 값은 dp[0][0]에 들어가게 된다.
							// 그렇기에 a사건의 첫번째 경찰차, b사건의 두번째 경찰차가 
	static int[][] place;	// [사건의 번호][2] = 해당 사건 번호에 맞는 x위치와 y위치
	//===================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		W = Integer.parseInt(br.readLine());
		dp= new int[W+1][W+1];
		place = new int[W+1][2];
		for(int i=1; i<=W; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			place[i][0] = x;
			place[i][1] = y;
		}
		
		int sum = police(1, 0, 0);	//이제 사건번호 1부터 시작할 것이고, 아직 서로 아무런 사건을 맡지 않았다. 
		bw.write(sum+"\n");
		
		//각 사건을 누가 진행했는지 분석.
		int one_index=0, two_index=0;
		
		for(int index=1; index<=W; index++) {	//index:순서대로 나오는 사건 번호
			int one_remain = distance(1, one_index, index);	//기준을 1번 경찰로 두었다. 물론 2번 경찰을 기준으로 해서 식을 조금 변경하여 진행해도 된다.
		
			//이게 무슨 말이냐! 맨 처음부터 차근차근 보면 이해가 된다. 첫번째 경찰로 사건을 진행했을 때, 
			//이미 정해진 현재의 사건 번호에서 마지막까지 진행한 dp[one][two]가 
			//주어진 사건을 첫번째 경찰이 진행하고 그 사건을 진행된 상태로 마지막까지 진행한 dp[index][two]한 합의 결과 값과 비교해서
			//서로 같다면 아 이 사건은 1번 경찰차가 진행을 했구나 알수있다.
			//만약 결과값이 작거나 같아서 값이 다르다면, 아 1번 경찰차가 해당 사건을 진행 하지 못했구나. 그래서 2번 경찰차가 해당 사건을 진행했구나 알수있다.
			if(dp[one_index][two_index] == one_remain + dp[index][two_index]) {		
				one_index = index;
				bw.write("1\n");
			}
			else {
				two_index = index;
				bw.write("2\n");
			}
		}
		
		bw.flush();
	}//===================================================
	// 재귀구조로 사건에 대해 누가 취급할지 진행한다.
	static int police(int count, int one, int two) {	// count:현재 처리해볼 사건 번호, one:1번 경찰차가 현재 맡은 사건번호, two:2번 경찰차가 현재 맡은 사건번호
		if(count > W) return 0;
		
		if(dp[one][two] != 0) return dp[one][two];
		
		//첫번째 경찰차가 이 사건을 맡을 때
		//첫번째 경찰차에게 해당 사건 번호를 부여하고, 두번째 경찰차는 그대로 간다. police(count+1, count, two)
		//그리고 첫번째 자동차가 해당 사건을 현재 위치에서 해당 사건으로 갔을 때 거리를 누적해서 가져온 답에 합해준다. 
		int one_move = police(count+1, count, two) + distance(1, one, count);
		//두번째 경찰차가 이 사건을 맡을 때
		//두번째 경찰차에게 해당 사건 번호를 부여하고, 첫번째 경찰차는 그대로 간다. police(count+1, one, count)
		//그리고 두번째 자동차가 해당 사건을 현재 위치에서 해당 사건으로 갔을 때 거리를 누적해서 가져온 답에 합해준다. 
		int two_move = police(count+1, one, count) + distance(2, two, count);
		//2대중 누적되어온 거리의 합이 최소인 것으로 갱신해준다.
		dp[one][two] = Math.min(one_move, two_move);
		
		return dp[one][two];
	}//===================================================
	// start사건번호에서 end사건번호 까지 거리를 알려준다. 
	static int distance(int car_num, int start, int end) {
		int x_start = place[start][0],	y_start = place[start][1];
		int x_end = place[end][0],		y_end = place[end][1];
		
		if(start==0) {	//현재 위치가 아직 어떠한 사건도 처리하지 않은 초창기 상태라는 뜻
			if(car_num==1)  //첫번째 경찰차라면
				x_start = y_start = 1;
			else 			//두번째 경찰차라면
				x_start = y_start = N;	
		}
		return Math.abs(x_start - x_end) + Math.abs(y_start - y_end);
	}//===================================================
}
