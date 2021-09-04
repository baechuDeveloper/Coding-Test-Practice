package Deque;

import java.util.*;
import java.io.*;

// 어머 진짜 Deque<>라는 Collections가 있었네
// 1252ms

// 가장 큰 특징이 Deque를 쓰는냐 이다.
public class AC_fast_other_stat { 

	static private int N;
	/**================================================================**/
	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_N = Integer.parseInt(br.readLine());
		MyDeque dq;
		
		for(int z=0; z<test_N; z++) {
			// 놀랍다 이 표현
			String[] cmd = br.readLine().split("");	//StringTokenzier말고 split함수를 쓰는것. 이렇게 하나씩 원소로 둔다. 
			N = Integer.parseInt(br.readLine());
			String[] x = br.readLine().replace("[", "").replace("]", "").split(",");	
			dq = new MyDeque(cmd, x);
			bw.write(dq.Print());
		}
		
		bw.flush();
	}/**================================================================**/
	//내부 클래스 Deque
	static class MyDeque {
		//멤버변수
		private Deque<Integer> dq;
		private boolean r_mode, err;
		//------------------------------------------------
		//생성자
		MyDeque(String[] cmd, String[] x){
			dq = new LinkedList<>();
			r_mode = false;
			err = false;

			for(int i=0; i<N; i++)
				dq.add(Integer.parseInt(x[i]));
			
			for(String i : cmd) {
				if(i.equals("R")) {
					Reverse();
				}
				else if(i.equals("D")){
					Delete();
				}		
			}
			
		}//------------------------------------------------
		// R연산
		public void Reverse() {
			if(err==false) 
				if(r_mode==true) 
					r_mode = false;		
				else 
					r_mode = true;
				
		}//------------------------------------------------
		// D연산
		public void Delete() {
			if(dq.isEmpty()) 
				err = true;
			else if (r_mode==false)
				dq.pollFirst();
			else if (r_mode==true) 
				dq.pollLast();
		}//------------------------------------------------
		// 출력
		public String Print() {
			StringBuilder sb = new StringBuilder();
			if(err==false) {
				int len = dq.size();
				sb.append("[");
				
				if(r_mode==false && len!=0 ) {
					for(int i=0; i<len-1; i++) 
						sb.append(dq.pollFirst()+",");
					sb.append(dq.pollFirst());
				}
				else if(r_mode==true && len!=0){
					for(int i=0; i<len-1; i++) 
						sb.append(dq.pollLast()+",");
					sb.append(dq.pollLast());
				}
				
				sb.append("]\n");
			}
			else 
				sb.append("error\n");
			
			return sb.toString();
		}//------------------------------------------------
	}/**================================================================**/
}
