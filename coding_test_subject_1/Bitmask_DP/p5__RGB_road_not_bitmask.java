package Bitmask_DP;

import java.util.*;
import java.io.*;
public class p5__RGB_road_not_bitmask {
	//��Ʈ����ũ�� ���� �ʾҴ�. �켱 2^1000�� �ش��ϴ� ��Ʈ����ŷ�� �ȵǱ⿡ ū ������ �ٷ��� �� ���� ��Ʈ����ŷ�� ��ƴ�.  
	//�� �ȵǴ��� �˰ھ�. dp�� ������ ���µ��� �� ���� ���� ���� �Ҽ��־�. ������ ���·δ�. �ֳĸ� �ش� dp�� ��Ȯ�� ���� ���� �湮�� ������ dp���� ��������� �ʾƼ� �ܼ��� now�� now_color�� �����ɷ� ���±��� � �湮�� ���� �˼�����. 
	//0:���� , 1:�ʷ� , 2:�Ķ�
	static int INF = 1_000 * 1_000 + 1;
	static int N;
	static int[][] arr;	//  [i��° ��][0,1,2] 	1<=i<=N
	static int[][][] dp;	//	[i��° ��][���� ��][start��] = �ڽ��� �ҷ��� �༮���� �ڱ� ���� ������ �� ��ȣ���� �ڽ��� �����ؿ� ������� ���� ���ش�. �׸��� �޸������̼��� �ȴ�. 
	//=====================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][3];
		//dp = new int[N+1][(1<<N)-1];
		//��Ʈ����ŷ�� �̿��� ��ƴ��� visit�� ��Ʈó�� �湮�� �����ϸ� �Ǵµ�, �̰� �湮�� �� 0, 1, 2�� ��ɷ� �湮�ߴ����ε� �̰� 2�������� �� ��Ʈ�� ǥ���� �ȵǴ���. 
		dp = new int[N+1][3][3];	// [now�� ��ȣ][���� ��][start��]
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

	//�켱 ���� �� ���� �ִ� �༮�� ���ϸ� �ǰ�, ���� ��ġ�� ���� ���� �Ʒ����� �˾Ƽ� �����ִ� ������ �ȴ�. �׸��� �������� N�� ������ ��, ���� ��ó�� �����ߴ� ������ �������� �ľ��ϴ� �͸� �Ű澲�� �ɰ� ����.
	static int run(int now, int now_color, int start_color) {	//now:���� �� ��ȣ, up_color:�� ���� ��� �༮�� ��, start_color:1��° ���� ��
		if(dp[now][now_color][start_color] != INF) 
			return dp[now][now_color][start_color];
		
		if(now==N-1) {	//N�̶�� next�� ���ؼ� ���⼭ �̸� �������ش�.
			for(int next=0; next<3; next++) {
				if(next!=now_color && next!=start_color) 
					dp[now][now_color][start_color] = Math.min(dp[now][now_color][start_color], arr[now][now_color] + arr[N][next]);	
			}
			return dp[now][now_color][start_color];
		}

		//next�� ���� ������ ���Ѵ�.
		for(int next=0; next<3; next++) {
			if(next != now_color) 
				dp[now][now_color][start_color] = Math.min(dp[now][now_color][start_color], arr[now][now_color] + run(now+1, next, start_color) );
		}
		
		return dp[now][now_color][start_color];
	}//=====================================================
}
