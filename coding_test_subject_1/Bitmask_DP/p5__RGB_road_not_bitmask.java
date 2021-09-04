package Bitmask_DP;

import java.util.*;
import java.io.*;
public class p5__RGB_road_not_bitmask {
	//비트마스크를 쓰지 않았다. 우선 2^1000에 해당하는 비트마스킹은 안되기에 큰 개수를 다루어야 할 때는 비트마스킹이 어렵다.  
	//왜 안되는지 알겠어. dp를 갱신을 헀는데도 더 낮은 값이 존재 할수있어. 지금의 상태로는. 왜냐면 해당 dp가 정확히 내가 여태 방문한 상태의 dp임을 명시해주지 않아서 단순히 now와 now_color만 같은걸로 여태까지 어떤 방문을 한지 알수없다. 
	//0:빨강 , 1:초록 , 2:파랑
	static int INF = 1_000 * 1_000 + 1;
	static int N;
	static int[][] arr;	//  [i번째 집][0,1,2] 	1<=i<=N
	static int[][][] dp;	//	[i번째 집][현재 색][start색] = 자신을 불러온 녀석에게 자기 부터 마지막 집 번호까지 자신이 누적해온 결과값을 전달 해준다. 그리고 메모이제이션이 된다. 
	//=====================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][3];
		//dp = new int[N+1][(1<<N)-1];
		//비트마스킹을 이용이 어렵던게 visit면 비트처럼 방문만 생각하면 되는데, 이건 방문할 때 0, 1, 2중 어떤걸로 방문했는지인데 이걸 2진법으로 한 비트에 표현이 안되더라. 
		dp = new int[N+1][3][3];	// [now집 번호][현재 색][start색]
		for(int i=0; i<=N; i++)
			for(int j=0; j<3; j++)
				Arrays.fill(dp[i][j], INF);
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		int start_red   = run(1, 0, 0); //System.out.println();
		int start_green = run(1, 1, 1); //System.out.println();
		int start_blue  = run(1, 2, 2); //System.out.println();
		//System.out.println(start_red+" "+start_green+" "+start_blue);
		int result = Math.min(start_red , Math.min(start_green, start_blue) );
		System.out.println(result);
	}//=====================================================

	//우선 나는 내 위에 있는 녀석만 피하면 되고, 내가 위치한 곳에 따라 아랫놈이 알아서 피해주는 구조가 된다. 그리고 마지막에 N에 도달할 때, 내가 맨처음 시작했던 색깔이 무엇인지 파악하는 것만 신경쓰면 될것 같다.
	static int run(int now, int now_color, int start_color) {	//now:현재 집 번호, up_color:딱 위에 사는 녀석의 색, start_color:1번째 집의 색
		if(dp[now][now_color][start_color] != INF) 
			return dp[now][now_color][start_color];
		
		if(now==N-1) {	//N이라는 next를 위해서 여기서 미리 점지해준다.
			for(int next=0; next<3; next++) {
				if(next!=now_color && next!=start_color) 
					dp[now][now_color][start_color] = Math.min(dp[now][now_color][start_color], arr[now][now_color] + arr[N][next]);	
			}
			return dp[now][now_color][start_color];
		}

		//next는 다음 색깔을 뜻한다.
		for(int next=0; next<3; next++) {
			if(next != now_color) 
				dp[now][now_color][start_color] = Math.min(dp[now][now_color][start_color], arr[now][now_color] + run(now+1, next, start_color) );
		}
		
		return dp[now][now_color][start_color];
	}//=====================================================
}
