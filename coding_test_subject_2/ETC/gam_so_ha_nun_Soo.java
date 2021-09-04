package ETC;

import java.util.*;
import java.io.*;

/**
 * 	   j 1  2  3  4  5  6  7  8  9		sum
 *   i
 * 	 1   1  1  1  1  1  1  1  1  1		9
 *   2   1  2  3  4  5  6  7  8  9		45
 *   3   0  1  3  6 10 15 21 28 36		117
 *   4   0  0  1  4 10 20 35 56 84 	
 *   ...
 *   
 *   이렇게 직접 해보니 점화식이 나왔다. 
 *   arr[i][j] = arr[i][j-1] + arr[i-1][j-1];
 *   
 *   arr[i][j-1]이라는 건 사실 arr[i-1][j-2, j-3, ... 2, 1] 까지의 합이 들어있다. 
 *   그래서 사실 i는 2일때 부터 
 *   arr[i-1][j-1, j-2, j-3, ... , 2, 1] 까지의 합이 arr[i][j]를 나타낸다. 
 *   앞자리수가 한단게 낮은것은 결국 해당 j값보다 작은 것으로 구성될것이고, 그 안에서 모든 경우의 수는 이렇게 나타날 것이기에 구해줄 수 있다.
 *   
 *   한가지 의심되지만 확실히 말해줄건 0이라는 존재로 인해 1자리수에서 '0'번째로 존재하기에 [1][0]=0 이 존재한다고 여길수 있고, [2][1]은 역시 [1][0]+[1][1]의 값이라고 해도 된다. 
 *   테이블에서는 [1][0]이 없는 것처럼 나왔지만 실제로는 있는것으로 보면 된다. 두번째 자리부터 0이라는 특수성이 있기에 실제 연속합의 경우는 i=2에서는 0이라는 특수성을 고려해서 들어간다. i=3부터는 그대로 보일것이다.
 *   아무튼 점화식 그자체에는 문제가 없다. 
 *   
 *   
 *   뺼셈을 통해서 해당 자리수를 구해둔다면 그 다음 자리수는 남은 값에 대해 새로운 자리수를 찾는 것이 좋은 방식이다. 연속적인 숫자와 자리수에 대해 해당 자리수에 개수를 담아둔 arr에서 해당 값에 도달했다면 
 *   그 자리수가 4이고 해당하는 값이 예를들어 5일때, 5210 ~ 5432 까지 범위를 확정 시킨 거라 볼수있다. 
 *   그리고 뺄셈을 통해 남온 값에서 210 ~ 432에서 해당 자리 값을 찾아본다 예를들어  자리수 3에 해당하는 값이 1이면 5210 ~ 5210 으로 될것이다. 
 *   나머지 뺼셈은 0이므로 이제 연속적인 해당하는 값으로 5210이 확정되게 된다.
 *   
 * **/

//1038번  - 감소하는 수
public class gam_so_ha_nun_Soo {

	static int[][] arr; //[맨앞 자리수][맨앞에 해당하는 수] = 해당 조건에서 만들어내는 개수 , 이를 통해서 아래로 아래로 내려가서 정확한 숫자도 알수 있을것이다 .이는 알고싶은 번호에 해당하는 숫자를 점점 배열에서 나온값으로 지우면서 0에 도달할때 나온 값을 적어주면 된다. 
	//와 이걸 내가 생각하다니 천재인가. 와 개 쩐다. 아무튼 아래에 절차를 코드로 적어두겠다. 
	static int[] sum;	// sum[i] = arr[i][1] + ... + arr[i][9];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N<=9) {
			System.out.println(N);
			return;
		}
		arr = new int[11][10];
		sum = new int[11];	//
		Arrays.fill(arr[1], 1); //자리수가 1인 상태에서 모두 1개씩 갖는다. 바로 자기자신 .
		sum[1] = 9;
		for(int i=2; i<=10; i++) 
			arr[i][i-1] = 1;	//계산을 더 효율적으로 0인 것부터 하지않도록 미리 설정해서 작업할려고한다. 큰 차이는 없지만.
		
		for(int i=2; i<=10; i++) {
			for(int j=i-1; j<=9; j++) {
				arr[i][j] = arr[i][j-1] + arr[i-1][j-1];
				sum[i] += arr[i][j];
			}
		}
		
		//for(int i=1; i<=10; i++) {for(int j=1; j<=9; j++) {System.out.print(arr[i][j]+" ");}System.out.println("  "+sum[i]);}
		
		//이제 구해야 할 N은 10보다 같거나 큰 값들이다. 여기서 sum을 통해 나의 첫 앞자리는 어디인지 파악하면서 진행을 하고, 뺼셈을 통해 값이 0보다 크다면 다음 자리에서 진행을 계속 한다.
		//반드시 마지막 뺼셈에서 0이 나온다면 그때 자리수의 값 기준으로 연속적으로 남은 자리를 채운다. 
		// 예를들어 3번째 자리에 7이 들어오고, 뺼셈으로 이제 0이 되었다면 2번째 자리에 6, 1번째 자리에 5가 들어와서 765가된다. 
		
		int t = N;
		int first = -1;
		for(int i=1; i<=10; i++) {
			if(t>sum[i]) {
				t -= sum[i];
			}
			else {
				first = i;
				break;
			}
		}
		//System.out.println("first "+first);
		//System.out.println("t "+t);
		int now_val = -1;
		long answer = 0;
		for(int i=first; i>=1; i--) {
			if(t==0) {
				now_val--;
				answer *= 10;
				answer += now_val;
				continue;
			}
			
			for(int j=i-1; j<=9; j++) {
				if(t > arr[i][j]) {
					t -= arr[i][j];
				}
				else {
					answer *= 10;
					answer += j;
					now_val = j;
					break;
				}
			}
		}
		
		if(first==-1) //범위를 넘어간다면 -1을 선언부터 유지한 상태로 온다.
			System.out.println(-1);
		else
			System.out.println(answer);
		
	}

}
