package Greedy;
import java.io.*;
import java.util.*;

// 13305번 - 주유소
public class p5__Oil_Station {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] road = new int[N-1];
		int[] oil = new int[N];
		
		for(int i=0; i<N-1; i++) 
			road[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			oil[i] = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = N-1;
		long max = 0;
		for(int i=start; i<end; i++) {
			int now = i;
			long now_oil = oil[i];
			int len = road[i];
			
			for(int j=i+1; j<end; j++) {
				if(now_oil > oil[j]) 
					break;
				else {
					now = j;
					len += road[j];
				}
			}
			
			max += (long)now_oil*len;
			i = now;
		}
		
		System.out.println(max);
	}

}
