package Binary_search;

import java.io.*;
import java.util.*;

//https://www.crocus.co.kr/625
// ¹éÁØ 11279¹ø Áß¾Ó°ª Èü
public class PriorityQueue_Heap {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();	//ÀÌ°Ô ´õ »¡¶ú´Ù. 
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<A> q = new PriorityQueue<>();
		int input = -1;
		
	
		for(int z=0; z<N; z++) {
			input = Integer.parseInt(br.readLine());
			
			if(input==0) {
				if(q.isEmpty())
					sb.append(0+"\n");
				else
					sb.append(q.poll().origin+"\n");
			}
			else {
				q.add(new A(input));
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}//===================================================
	
	static class A implements Comparable<A>{
		int origin;
		int absolute;
		A(int a){
			origin = a;
			if(a<0) 
				absolute = -a;
			else 
				absolute= a;
		}
		public int compareTo(A o) {
			if(absolute < o.absolute) 
				return -1;
			else if(absolute> o.absolute) 
				return 1;
			else 
				if(origin > o.origin) return 1;
				else return -1;	
		}
	}//===================================================
}
