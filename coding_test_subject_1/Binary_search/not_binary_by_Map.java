package Binary_search;

import java.io.*;
import java.util.*;

public class not_binary_by_Map {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();	//ÀÌ°Ô ´õ »¡¶ú´Ù. 
		StringTokenizer st1, st2;
	
		int N = Integer.parseInt(br.readLine());
		st1 = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		int ques=-1, temp=-1, val=-1;
	
		for(int i=0; i<N; i++) {
			temp = Integer.parseInt(st1.nextToken());
			if(map.containsKey(temp) == false) {
				map.put(temp, 1);
			}
			else {
				val = map.get(temp);
				map.put(temp, val+1);
			}
		}
	
		for(int i=0; i<M; i++) {
			ques = Integer.parseInt(st2.nextToken());
			if(map.containsKey(ques) == true) 
				sb.append(map.get(ques)+" ");
			else 
				sb.append(0+" ");
		}
		bw.write(sb.toString());
		bw.flush();
	}//===================================================

}
