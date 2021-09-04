package Binary_search;

import java.io.*;
import java.util.*;

public class not_binary_by_Set {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();	//ÀÌ°Ô ´õ »¡¶ú´Ù. 
		StringTokenizer st1, st2;
	
		int N = Integer.parseInt(br.readLine());
		st1 = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<>();
		int ques = -1;
		
		for(int i=0; i<N; i++) 
			set.add(Integer.parseInt(st1.nextToken()));
		for(int i=0; i<M; i++) {
			ques = Integer.parseInt(st2.nextToken());
			if(set.contains(ques)) 
				sb.append(1+"\n");
			else 
				sb.append(0+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}//===================================================

}
