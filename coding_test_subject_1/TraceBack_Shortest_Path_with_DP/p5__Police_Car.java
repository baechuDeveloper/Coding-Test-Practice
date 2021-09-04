package TraceBack_Shortest_Path_with_DP;
import java.util.*;
import java.io.*;

// 2���� �������� ó���� ù��° ������(1,1)�� �ι�° ������(N,N)�� ��ġ�Ѵ�. 

// 2618�� - ������
public class p5__Police_Car { 

	static int N, W; 		// ������ ����, ó���ؾ� �� ��� ����		5 <= N <= 1000	,	1 <= W <= 1000
	static int[][] dp;		// dp[a][b] = ù ��° �������� ��ġ�� a��° ����̰�, �� ��° �������� ��ġ�� b��° ��ǿ� ���� ��, 
							// ���� ��ġ���� ����� ������ �ذ��� ������ �̵��ϴ� �Ÿ��� ���� �ּڰ�. �� �����Ǿ� ���� ���� ���� ���� dp[0][0]�� ���� �ȴ�.
							// �׷��⿡ a����� ù��° ������, b����� �ι�° �������� 
	static int[][] place;	// [����� ��ȣ][2] = �ش� ��� ��ȣ�� �´� x��ġ�� y��ġ
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
		
		int sum = police(1, 0, 0);	//���� ��ǹ�ȣ 1���� ������ ���̰�, ���� ���� �ƹ��� ����� ���� �ʾҴ�. 
		bw.write(sum+"\n");
		
		//�� ����� ���� �����ߴ��� �м�.
		int one_index=0, two_index=0;
		
		for(int index=1; index<=W; index++) {	//index:������� ������ ��� ��ȣ
			int one_remain = distance(1, one_index, index);	//������ 1�� ������ �ξ���. ���� 2�� ������ �������� �ؼ� ���� ���� �����Ͽ� �����ص� �ȴ�.
		
			//�̰� ���� ���̳�! �� ó������ �������� ���� ���ذ� �ȴ�. ù��° ������ ����� �������� ��, 
			//�̹� ������ ������ ��� ��ȣ���� ���������� ������ dp[one][two]�� 
			//�־��� ����� ù��° ������ �����ϰ� �� ����� ����� ���·� ���������� ������ dp[index][two]�� ���� ��� ���� ���ؼ�
			//���� ���ٸ� �� �� ����� 1�� �������� ������ �߱��� �˼��ִ�.
			//���� ������� �۰ų� ���Ƽ� ���� �ٸ��ٸ�, �� 1�� �������� �ش� ����� ���� ���� ���߱���. �׷��� 2�� �������� �ش� ����� �����߱��� �˼��ִ�.
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
	// ��ͱ����� ��ǿ� ���� ���� ������� �����Ѵ�.
	static int police(int count, int one, int two) {	// count:���� ó���غ� ��� ��ȣ, one:1�� �������� ���� ���� ��ǹ�ȣ, two:2�� �������� ���� ���� ��ǹ�ȣ
		if(count > W) return 0;
		
		if(dp[one][two] != 0) return dp[one][two];
		
		//ù��° �������� �� ����� ���� ��
		//ù��° ���������� �ش� ��� ��ȣ�� �ο��ϰ�, �ι�° �������� �״�� ����. police(count+1, count, two)
		//�׸��� ù��° �ڵ����� �ش� ����� ���� ��ġ���� �ش� ������� ���� �� �Ÿ��� �����ؼ� ������ �信 �����ش�. 
		int one_move = police(count+1, count, two) + distance(1, one, count);
		//�ι�° �������� �� ����� ���� ��
		//�ι�° ���������� �ش� ��� ��ȣ�� �ο��ϰ�, ù��° �������� �״�� ����. police(count+1, one, count)
		//�׸��� �ι�° �ڵ����� �ش� ����� ���� ��ġ���� �ش� ������� ���� �� �Ÿ��� �����ؼ� ������ �信 �����ش�. 
		int two_move = police(count+1, one, count) + distance(2, two, count);
		//2���� �����Ǿ�� �Ÿ��� ���� �ּ��� ������ �������ش�.
		dp[one][two] = Math.min(one_move, two_move);
		
		return dp[one][two];
	}//===================================================
	// start��ǹ�ȣ���� end��ǹ�ȣ ���� �Ÿ��� �˷��ش�. 
	static int distance(int car_num, int start, int end) {
		int x_start = place[start][0],	y_start = place[start][1];
		int x_end = place[end][0],		y_end = place[end][1];
		
		if(start==0) {	//���� ��ġ�� ���� ��� ��ǵ� ó������ ���� ��â�� ���¶�� ��
			if(car_num==1)  //ù��° ���������
				x_start = y_start = 1;
			else 			//�ι�° ���������
				x_start = y_start = N;	
		}
		return Math.abs(x_start - x_end) + Math.abs(y_start - y_end);
	}//===================================================
}
