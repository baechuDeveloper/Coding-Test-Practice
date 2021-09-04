package Bitmask_DP;

import java.util.*;
import java.io.*;

// 11723번 백준 - 집합
public class p1__not_bit {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int num = -1;
			if(st.hasMoreTokens())
				num = Integer.parseInt(st.nextToken());

			if(cmd.equals("add")) {
				if( !set.contains(num) ) 
					set.add(num);
			}
			else if(cmd.equals("remove")) {
				if( set.contains(num) ) 
					set.remove(num);
			}
			else if(cmd.equals("check")) {
				if( set.contains(num) ) 
					sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			else if(cmd.equals("toggle")) {
				if( set.contains(num) ) 
					set.remove(num);
				else
					set.add(num);
			}
			else if(cmd.equals("all")) {
				set = new HashSet<>();
				for(int i=1; i<=20; i++)
					set.add(i);
			}
			else if(cmd.equals("empty")) {
				set = new HashSet<>();
			}

		}
		
		bw.write(sb.toString());
		bw.flush();
	}

}
