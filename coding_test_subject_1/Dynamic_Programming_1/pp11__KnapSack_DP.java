package Dynamic_Programming_1;
// https://st-lab.tistory.com/141
import java.util.*;
import java.io.*;
// 12865�� - ����� �賶
public class pp11__KnapSack_DP {

	static int N, K;
	static Integer[][] dp;
	static int[] W, V;
	//====================================================
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//��ǰ�� ��
		K = Integer.parseInt(st.nextToken());	//��ƿ �� �ִ� ����
		dp = new Integer[N][K+1];				//[��������� 0���� �ش� ��ǰ ��ȣ���� �׿��ִ� �� �ǹ�][�� ���� ����
		W = new int[N];
		V = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println( knapsack(N-1, K) ); 	// Top-Down������� ������ �Ѵ�
	}//====================================================
	
	// i�� ���� ���캸���� �ϴ� ������ '��ȣ', k�� ��� �����ִ� '���� ���Է�' 
	static int knapsack(int i, int k) {	
		// ���� ���̶�� �̷��� ģ���ϰ� ������ ���� �˷��ݴϴ�.
		if(i < 0) return 0; 
		
		//Ž������ ���� ��ġ��� Integer�� ����. �ƴϸ� ��� -1ó�� �ʱ�ȭ �ؼ� �۾��ص� �ȴ�.
		if(dp[i][k] == null) {
			//���� ����(i)�� �߰��� �� ��� ��� (���� i�� Ž��),	��հԵ� Top-Down�̶� ���� ���Է��� ������ ������ ���Կ� ���ϴ� ������ ���´�. 
			if(W[i] > k) {
				dp[i][k] = knapsack(i-1, k);	//���� ������ ���� ���ϰ� ���� �������� ���Է��� �״�� ����ä �Ѿ��.
			}
			//���� ����(i)�� ���� �� �ִ� ���
			else {
				// ���� ������ ���� �ʰ� ���� i������ Ž��, �ƴϸ� ���� ������ ��� ���� ���Է��� �����ϰ� ��ġ������ �÷��� ���� i������ ���� �� ��Ȳ���� Ž��. ��������� ū ��ġ���� �� ����ָ� �ȴ�.  
				dp[i][k] = Math.max( knapsack(i-1, k), V[i] + knapsack(i-1, k-W[i]) );	
			}
		}
		
		return dp[i][k];
	}//====================================================

}
