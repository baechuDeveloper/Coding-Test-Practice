package Dynamic_Programing_2;

// ���� - RGB�Ÿ� - 1149�� 
// https://www.acmicpc.net/problem/1149

/**================================================================
 * RGB�Ÿ����� ���� N�� �ִ�. �Ÿ��� �������� ��Ÿ�� �� �ְ�, 1�� ������ N�� ���� ������� �ִ�.
 * ���� ����, �ʷ�, �Ķ� �� �ϳ��� ������ ĥ�ؾ� �Ѵ�. 
 * ������ ���� ����, �ʷ�, �Ķ����� ĥ�ϴ� ����� �־����� ��, �Ʒ� ��Ģ�� �����ϸ鼭 ��� ���� ĥ�ϴ� ����� �ּڰ��� ���غ���.
 
 1)  1�� ���� ���� 2�� ���� ���� ���� �ʾƾ� �Ѵ�.
 2)  N�� ���� ���� N-1�� ���� ���� ���� �ʾƾ� �Ѵ�.		
 3)  i�� ���� ���� i-1��, i+1�� ���� ���� ���� �ʾƾ� �Ѵ�.  (2 �� i �� N-1) 
 	(�� �翷�� ���� ���� ���� �޶�� �Ѵ�.)
 ==================================================================**/

import java.util.StringTokenizer;
import java.io.*;
// dp����� '�� ������' �˰����� Ư������ '������ ������'�� ����  '�����̵� ������' ������� �����غ� ����.
public class RGB_Slide_Window {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		//----------------------------------------------------
		int N = Integer.parseInt(br.readLine());
		int[][] rgb = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) 
				rgb[i][j] = Integer.parseInt(st.nextToken());
		}
		//----------------------------------------------------
		int[][] dp = new int[2][3]; //�����̵� ������ ���
		
		for(int i=0; i<3; i++) 
			dp[0][i] = rgb[0][i];
				
		for(int i=1; i<N; i++) {
			
			//������ i��° ���� ���������� ĥ�� ���
			dp[1][0] = rgb[i][0] + Math.min(dp[0][1], dp[0][2]);
			
			//������ i��° ���� �ʷϻ����� ĥ�� ���
			dp[1][1] = rgb[i][1] + Math.min(dp[0][0], dp[0][2]);
			
			//������ i��° ���� �Ķ������� ĥ�� ���
			dp[1][2] = rgb[i][2] + Math.min(dp[0][0], dp[0][1]);
			
			for(int j=0; j<3; j++)
				dp[0][j] = dp[1][j];
		}
		
		int min = Math.min(dp[1][0], dp[1][1]);
		min = Math.min(dp[1][2], min);
		//----------------------------------------------------
		bw.write(min+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
