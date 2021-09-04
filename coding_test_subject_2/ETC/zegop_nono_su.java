package ETC;

import java.util.*;
import java.io.*;

// 1016번 - 제곱 ㄴㄴ 수
public class zegop_nono_su {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		// 1보다 큰 제곱수 4, 9, 16, 25, 36인데 
		// 잘 보면 그저 min과 max사이에 몇개의 제곱수가 있는지 확인하고, 전체 개수에서 제곱수의 개수만큼 뺴면 된다.
		// 이때 min과 max사이에 1이 포함되어있다면 1은 어떠한 제곱수로서 활용못한다고 한다.
		// 제곱수 4는 4, 8, 12, 16, 20...으로 이어질수있다.	
		
		Set<Long> set = new HashSet<>();
		
		long now = 2;
		long pow = now*now;
		while(pow<=max) {
			long d = min/pow;
			long c = min%pow;
			long val = d*pow;
			if(c>0) 
				val += pow;
			while(val <= max) {
				set.add(val);
				val += pow;
			}
			
			now++;
			pow = now*now;		
		}
		System.out.println(max-min+1-(set.size()));
	}

}
