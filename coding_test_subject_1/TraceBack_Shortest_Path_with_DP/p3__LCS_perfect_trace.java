package TraceBack_Shortest_Path_with_DP;

import java.util.*;
import java.io.*;
/*------------
ABCDEF
QAWCDF

FGHABCDE
ABCDEFGH
--------------*/

/* =======================================================================
 * �ش� ��ε� ���غ���. �̶� ������ dp�迭�� ���� �� �ڿ����� �����ؼ� 
 * ���� ū �������� ���� ���� ���� �������� i�� j�� ���ؼ� �� ���簢���� ������ ���·� �����մϴ�.
 =========================================================================*/
//9252�� - LCS 2
public class p3__LCS_perfect_trace {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String A = br.readLine();
		String B = br.readLine();
		int len_A = A.length();
		int len_B = B.length();
		int[][] dp = new int[len_A+1][len_B+1]; // index 0�� �ش��ϴ� �κе� �ʱ� ���ϴ� �κп� ���̰� �ȴ�.

		for(int i=1; i<=len_A; i++){
			char a = A.charAt(i-1);
			for(int j=1; j<=len_B; j++){
				char b = B.charAt(j-1);
				if(a==b)
					dp[i][j] = dp[i-1][j-1] + 1;
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}

		/*for(int i=1; i<=len_A; i++) {
			for (int j = 1; j <= len_B; j++)
				System.out.print(dp[i][j] + " ");
			System.out.println();
		}*/

		Stack<Character> stack = new Stack<>();
		int count = dp[len_A][len_B];
		int A_cut = len_A;
		int B_cut = len_B;

		// ���� ���غó�. ���س��� dp�迭�� ���� �� �ڿ����� �����ؼ� ���� ū �������� ���� ���� ���� �������� i�� j�� ���ؼ� �� ���簢���� ������ ���·� �����մϴ�.
		while(count>0) {
			boolean check = false;
			for (int i = A_cut; i > 0; i--) {
				for (int j = B_cut; j > 0; j--) {
					if(dp[i][j]==count && dp[i-1][j]==count-1 && dp[i][j-1]==count-1) { //���� ������ �Ѱ��� �۴ٸ� �翬�� �밢������ ���Ե� ����.
						//System.out.println(i+" "+j);
						stack.push(A.charAt(i-1));
						count--;
						A_cut = i-1;
						B_cut = j-1;
						check = true;
						break;
					}
				}
				if(check) break;
			}
		}
		//���
		bw.write(dp[len_A][len_B]+"\n");
		while(!stack.isEmpty())
			bw.write(stack.pop()+"");
		bw.flush();

	}//======================================================
}