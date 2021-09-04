package Brute_Force;

import java.util.*;
import java.io.*;

// 블랙잭 : 카드 3장을 잡아서 M에 최대한 가까운 값을 가지거라, 단 M을 넘어서는 안된다.
public class p1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			cards[i] = Integer.parseInt(st.nextToken());
		
		int c1, c2, c3, max_sum=Integer.MIN_VALUE, mid_sum, temp_sum;
		for(int i=0; i<N-2; i++) {
			c1 = cards[i];
			for(int j=i+1; j<N-1; j++) {
				c2 = cards[j];
				mid_sum = c1+c2;
				for(int k=j+1; k<N; k++) {
					c3= cards[k];
					temp_sum = mid_sum+c3;
					if(max_sum < temp_sum && temp_sum<=M)
						max_sum = temp_sum;
				}
			}
		}
		bw.write(max_sum+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
