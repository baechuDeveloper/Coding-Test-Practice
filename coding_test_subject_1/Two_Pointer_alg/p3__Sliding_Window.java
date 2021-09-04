package Two_Pointer_alg;

// ���� - �������� - 2096��
// https://www.acmicpc.net/problem/2096
// https://m.blog.naver.com/kks227/220795165570
/**--------------------------------------------------------------------
 * DP�� �����ؼ� ������ Ǯ� �����̵� ������ ����̴�. ���� �������� �˰����� R��L�� �̿��ؼ� ������ �ذ��ٸ�.. �̰� â���� �̵��ذ��鼭 ���Ѵٰ� ���� �ȴ�.
 * �����̵� ������� ��� �������� ������ ���̰� �Ȱ��ٴ� Ư¡�� �ִ�. 
 * �� ������ �࿡ ���� max�� min�� ���ؼ� �˾Ƶθ� ���� ���� ������ ��ġ now_s�� ���� ���� �࿡ �ִ� �ִ�,�ּҿ� ����� �� ���Ͽ� �ش� ��ġ�� �����ְ� 
 * �� �����ϸ� �ȴ�. 
 ---------------------------------------------------------------------**/
/*--------------------------------------------------------------------
 *  '�� ������' �˰����� L�� R �� �����͸� �̿��Ѱ�. 
 *  '�����̵� ������' ����� �� ������ó�� ������ ���°� ���������� '������ ũ��'�� ���� â���� ���డ�� ��ũ���̴�. ����� ��ũ���̶󺸸�ȴ�.
 *  �׷��� ���� ������ �׷��� �θ� �ǰ�, �� �����ʹ� L��R�� �̿��� while���� �����ϰ�... 
 *  �����̵� ������� ������ ũ�� dp�� �̿��ذ��� ������ �ϱ⿡ ������ ��ũ���� �ٸ���.
 *--------------------------------------------------------------------*/
import java.util.StringTokenizer;
import java.io.*;

public class p3__Sliding_Window {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] max_table = new int[2][3];
		int[][] min_table = new int[2][3];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {
			max_table[1][i] = max_table[0][i] = Integer.parseInt(st.nextToken());
			min_table[1][i] = min_table[0][i] = max_table[0][i];
		}
		int[] now_s = new int[3];
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) 
				now_s[j] =  Integer.parseInt(st.nextToken());
			
			//����
			max_table[1][0] = now_s[0] + Math.max(max_table[0][0], max_table[0][1]);
			min_table[1][0] = now_s[0] + Math.min(min_table[0][0], min_table[0][1]);
			
			//���
			int max_mid = Math.max(max_table[0][0], max_table[0][1]);
			max_table[1][1] = now_s[1] + Math.max(max_table[0][2], max_mid);
			int min_mid = Math.min(min_table[0][0], min_table[0][1]);
			min_table[1][1] = now_s[1] + Math.min(min_table[0][2], min_mid);
			
			//������
			max_table[1][2] = now_s[2] + Math.max(max_table[0][1], max_table[0][2]);
			min_table[1][2] = now_s[2] + Math.min(min_table[0][1], min_table[0][2]);
			
			
			for(int j=0; j<3; j++) {
				max_table[0][j] = max_table[1][j];
				min_table[0][j] = min_table[1][j];
			}
		}
		
		int max = Math.max(max_table[1][0], max_table[1][1]);
		max = Math.max(max_table[1][2], max);
		int min = Math.min(min_table[1][0], min_table[1][1]);
		min = Math.min(min_table[1][2], min);
	
		bw.write(max+" "+min);
		bw.flush();
		bw.close();
		br.close();
	}

}
