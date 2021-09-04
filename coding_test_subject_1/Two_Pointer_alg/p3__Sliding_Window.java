package Two_Pointer_alg;

// 백준 - 내려가기 - 2096번
// https://www.acmicpc.net/problem/2096
// https://m.blog.naver.com/kks227/220795165570
/**--------------------------------------------------------------------
 * DP를 응용해서 문제을 풀어본 슬라이딩 윈도우 방식이다. 기존 투포인터 알고리즘은 R과L을 이용해서 진행을 해간다면.. 이건 창문을 이동해가면서 비교한다고 보면 된다.
 * 슬라이딩 윈도우는 어느 순간에도 구간의 넓이가 똑같다는 특징이 있다. 
 * 그 전까지 행에 대한 max와 min에 대해서 알아두면 현재 내가 적용할 위치 now_s에 가장 그전 행에 있는 최대,최소에 가까운 걸 더하여 해당 위치에 적어주고 
 * 쭉 진행하면 된다. 
 ---------------------------------------------------------------------**/
/*--------------------------------------------------------------------
 *  '투 포인터' 알고리즘은 L과 R 두 포인터를 이용한것. 
 *  '슬라이딩 윈도우' 방식은 투 포인터처럼 구간을 흩어가는건 동일하지만 '고정된 크기'를 가진 창문을 진행가는 테크닉이다. 응용된 테크닉이라보면된다.
 *  그러니 서로 구분을 그렇게 두면 되고, 투 포인터는 L과R을 이용한 while문을 진행하고... 
 *  슬라이딩 윈도우는 고정된 크기 dp를 이용해가면 진행을 하기에 서로의 테크닉이 다르다.
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
			
			//왼쪽
			max_table[1][0] = now_s[0] + Math.max(max_table[0][0], max_table[0][1]);
			min_table[1][0] = now_s[0] + Math.min(min_table[0][0], min_table[0][1]);
			
			//가운데
			int max_mid = Math.max(max_table[0][0], max_table[0][1]);
			max_table[1][1] = now_s[1] + Math.max(max_table[0][2], max_mid);
			int min_mid = Math.min(min_table[0][0], min_table[0][1]);
			min_table[1][1] = now_s[1] + Math.min(min_table[0][2], min_mid);
			
			//오른쪽
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
