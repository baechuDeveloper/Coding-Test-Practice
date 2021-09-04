package Backtracking;
import java.util.*;
import java.io.*;
//14889�� ��ŸƮ�� ��ũ
// ��Ʈ��ŷ�� ���� ��Ʈ����ũ Ȱ�� �ð��� 336->288ms�� �ٿ���. ���ÿ� ������ ��귮�� ���̷��� �� ���ǹ��� �ִµ� �̰� ũ�� ���̰� �ȳ���.
public class p4_improve {
	
	static int N, answer;
	static int[][] arr;
	static int visit=0; // 20���� ��Ʈ�� ������ ���.
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

	static void make_team(int index, int count) {	//index�� �̹� ����, count�� ������� ���� �ο� ��
		if(answer == 0) return;	//���̻� ����Ұ� ���� �ְ��� ���� ����
		if(count == N/2) {
			lets_score();
			return;
		}
		
		for(int i=index; i<N; i++) {	
			//if(count==0 && i==N/2) break;	//�ܼ��� �� ù��° ���� N/2�� ���ĺ��ʹ� count�� ���� 'N/2'�� ���� �������� ���ϹǷ� N/2������ ���صд�. ��� �ð��� ���̷��� ���� ����
												//�ٵ� ����� �̰� �־ ����� �� �Լ��� ������ �Լ�ȣ���� ������ �ص� ���ص� ��� ũ�� ���̰� ���� �ʾҴ�.
			if( (visit & (1<<i)) == 0 ) {	//�ش� ��Ʈ����ũ�� ������������. �� �湮�� ���� �ʾ���
				visit = visit | (1<<i);
				make_team(i+1, count+1); //i+1�� N�� ���� ����Լ��� �ٸ� ����Լ����� �ݺ����� ����� �Ǿ� ����
				visit = visit & ~(1<<i);
			}	
		}
	}//=================================================
	
	static void lets_score() {
		int a_score=0, b_score=0, sum;
		
		for(int i=0; i<N-1; i++) 
			for(int j=i+1; j<N; j++) 
				if( (visit & (1<<i)) == (1<<i)  
					&& (visit & (1<<j)) == (1<<j) ) {	//�ش� i�� j�� �� �� ���� �ϴ°�
					a_score += (arr[i][j] + arr[j][i]);
				}
				else if( (visit & (1<<i)) == 0  
						&& (visit & (1<<j)) == 0 ) {	// �ش� i�� j�� �� �� ���� ���ϴ°�
					b_score += (arr[i][j] + arr[j][i]);
				}
			
		sum = Math.abs(a_score - b_score);
		if(answer>sum)
			answer = sum;
	}//=================================================
}
