package Priority_Queue;
import java.io.*;
import java.util.*;

// 11286¹ø - Àý´ë°ª Èü
public class p3__Heap_absolute {

	static class Node implements Comparable<Node>{
		int origin, absolute;
		
		Node(int a){
			origin = a;
			absolute = Math.abs(a);
		}
		public int compareTo(Node o) {
			if(absolute < o.absolute) 
				return -1;
			else if(absolute> o.absolute) 
				return 1;
			else 
				if(origin > o.origin) return 1;
				else return -1;	
		}
	}//===================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();	//ÀÌ°Ô ´õ »¡¶ú´Ù. 
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Node> q = new PriorityQueue<>();
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
				q.add(new Node(input));
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}//===================================================
	
}