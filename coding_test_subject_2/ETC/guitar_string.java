package ETC;
import java.util.*;
import java.io.*;
public class guitar_string {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] pakage = new int[M];
		int[] one = new int[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			pakage[i]  = Integer.parseInt(st.nextToken());	//6개가 들어가 있는 패키지 줄 가격  
			one[i] = Integer.parseInt(st.nextToken());		//낱개로 들어가는 가격
		}

		Arrays.sort(pakage);
		Arrays.sort(one);

		int money_pakage = Integer.MAX_VALUE;
		int money_one = Integer.MAX_VALUE;
		int money_temp = Integer.MAX_VALUE;
		if(pakage[0] < one[0]*6) {
			money_pakage = 0;
			money_temp = 0;
			while(N>6) {
				money_temp += pakage[0];
				N-=6;
			}
			money_pakage += (money_temp + pakage[0]);
			money_temp += (one[0]*N);
			money_pakage = Math.min(money_pakage, money_temp);
		}
		else {
			money_one = 0;
			money_one += one[0]*N;
		}

		System.out.println(Math.min(money_pakage, money_one));
	}
}
