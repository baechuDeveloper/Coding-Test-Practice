package ETC;
import java.util.*;
import java.io.*;

// 1015번 - 수열 정렬 
public class Sequence_Sort {

	static class Node implements Comparable<Node> {
		int idx;
		int a;
		
		Node(int i, int a){
			idx = i; this.a=a; 
		}
		
		public int compareTo(Node o) {
			if(a < o.a) {
				return -1;
			}
			else if(a > o.a) {
				return 1;
			}
			else {
				if(idx < o.idx) 
					return -1;	
				else 
					return 1;
			}
		}
	}//=========================================
	
	
	//=========================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Node> list = new ArrayList<>();
		for(int i=0; i<N; i++) 
			list.add( new Node( i, Integer.parseInt(st.nextToken()) ) );
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		int[] p = new int[N];
		int i=0;
		for(Node now : list) 
			p[now.idx] = i++;
			
		for(int z:p) 
			sb.append(z+" ");

		bw.write(sb.toString());
		bw.flush();
	}//=========================================

}
