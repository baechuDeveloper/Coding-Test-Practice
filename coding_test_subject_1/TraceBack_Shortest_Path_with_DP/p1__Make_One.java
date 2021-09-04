package TraceBack_Shortest_Path_with_DP;

import java.io.*;
/** ���� ��ȹ���� �ִܰŸ� ������ - ���ݱ����� �ּڰ�, �ִ�, �ִܰŸ��� ã�ҽ��ϴ�. �̹����� ���� �����ؿ� �ִܰ�θ� ã�� ���ô�. */

/*==============================================================
 * ���� ������ ������ 3����
   1) X�� 3���� ������ ��������, 3���� ������.
   2) X�� 2�� ������ ��������, 2�� ������.
   3) 1�� ����.

 * ���� N�� �־����� ��, ���� ���� ���� 3������ ������ ����ؼ� '1'�� ������� �Ѵ�.
 * ������ ����ϴ� Ƚ���� �ּڰ��� ������� ����� �������� ������ ������� ����غ���.
 ==============================================================*/

/**============================================================
 * ���� ������ ����� DP�� ����ϰڴٸ� �� ������ '1'���� �����ؼ� ���� ������ ����� �ݴ�� ���ͼ� ������ N�� �����ϴ� �������� ���� �غ����Ѵ�.
 * 1) 3�� ���Ѵ�.
 * 2) 2�� ���Ѵ�.
 * 3) 1�� ���Ѵ�.  
 =============================================================**/
// 12852�� - 1�� ����� 2
public class p1__Make_One {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		//�ݴ�� �����ؼ� +1�� �ϰų�, 2�� ���ϰų�, 3�� ���ϴ� ������ �ؼ� N�� �����ϴ°��� �����غ���.
		int[] dp = new int[N+1];    //dp���� �ڽ��� ���� �༮�� �������� ����ִ�.
		int[] cnt = new int[N+1];
		int now = 1;	//���� now���� ���� ���� ���ؼ��� Ȯ���� �Ǿ��.
		while(true) {
			if(now == N) break;

			int[] cal = {now+1, 2*now, 3*now};
			for(int i=0; i<3; i++) {
				int next = cal[i];
				if(next <= N) {
					if(dp[next]==0 || cnt[now] < cnt[next]) {	//ó������ ���°� Ȥ�� ������ count�� ���� ���� �� �ִٸ�(���� count�� 1���̴� ������Ʈ ���ص� ������ �ϵ��� �ߴ�.) 
						dp[next] = now;  
						cnt[next] = 1 + cnt[now];
					}
				}
			}
			now++;
		}

		//���س��� dp�� ������
		StringBuilder sb = new StringBuilder();
		int next = N;
		while(true) {
			sb.append(next+" ");
			next = dp[next];
			if(next==0)
				break;
		}
		bw.write(cnt[N]+"\n");
		bw.write(sb.toString());
		bw.flush();
	}
}

