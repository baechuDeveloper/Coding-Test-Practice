package Priority_Queue;
import java.io.*;
import java.util.*;

// 1927¹ø - ÃÖ¼ÒÈü
public class p2__Heap_min {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();	//ÀÌ°Ô ´õ »¡¶ú´Ù. 
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> q = new PriorityQueue<>();
		int input = -1;
		
		for(int z=0; z<N; z++) {
			input = Integer.parseInt(br.readLine());
			
			if(input==0) {
				if(q.isEmpty())
					sb.append(0+"\n");
				else
					sb.append(q.poll()+"\n");
			}
			else {
				q.add(input);
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}//===================================================

}