package Deque;

import java.util.*;
import java.io.*;

// ��� ��¥ Deque<>��� Collections�� �־���
// 1252ms

// ���� ū Ư¡�� Deque�� ���³� �̴�.
public class AC_fast_other_stat { 

	static private int N;
	/**================================================================**/
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_N = Integer.parseInt(br.readLine());
		MyDeque dq;
		
		for(int z=0; z<test_N; z++) {
			// ����� �� ǥ��
			String[] cmd = br.readLine().split("");	//StringTokenzier���� split�Լ��� ���°�. �̷��� �ϳ��� ���ҷ� �д�. 
			N = Integer.parseInt(br.readLine());
			String[] x = br.readLine().replace("[", "").replace("]", "").split(",");	
			dq = new MyDeque(cmd, x);
			bw.write(dq.Print());
		}
		
		bw.flush();
	}/**================================================================**/
	//���� Ŭ���� Deque
	static class MyDeque {
		//�������
		private Deque<Integer> dq;
		private boolean r_mode, err;
		//------------------------------------------------
		//������
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
		// R����
		public void Reverse() {
			if(err==false) 
				if(r_mode==true) 
					r_mode = false;		
				else 
					r_mode = true;
				
		}//------------------------------------------------
		// D����
		public void Delete() {
			if(dq.isEmpty()) 
				err = true;
			else if (r_mode==false)
				dq.pollFirst();
			else if (r_mode==true) 
				dq.pollLast();
		}//------------------------------------------------
		// ���
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
