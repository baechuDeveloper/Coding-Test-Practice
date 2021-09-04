package Deque;

import java.util.*;
import java.io.*;

// ��� ��¥ Deque<>��� Collections�� �־���
// 1216ms StringBuilder�� ��������� �۾��صξ �ѹ��� bw.write�� �ϴ°� �� ������ ���Դ�. ���� �ð��ʰ��� �մ��� ȿ���� �ƴϴ�. 
/**----------------------------------------------------------------------
 * �� ���� �Լ� R(������)�� D(������)�� �ִ�.
 * �Լ� R�� �迭�� �ִ� ������ ������ ������ �Լ��̰�, D�� ù ��° ���ڸ� ������ �Լ��̴�. 
 * �迭�� ����ִµ� D�� ����� ��쿡�� ������ �߻��Ѵ�.
 * �Լ��� �����ؼ� �� ���� ����� �� �ִ�. 
 * ���� ���, "AB"�� A�� ������ ������ �ٷ� �̾ B�� �����ϴ� �Լ��̴�. ���� ���, "RDD"�� �迭�� ������ ���� ó�� �� ���ڸ� ������ �Լ��̴�.
 * �迭�� �ʱⰪ�� ������ �Լ��� �־����� ��, ���� ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
  -----------------------------------------------------------------------**/
public class AC_fast_3 {

	static private StringBuilder sb = new StringBuilder();
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
			dq.Print();
		}
		bw.write(sb.toString());
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
		public void Print() {
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
			
		}//------------------------------------------------
	}/**================================================================**/
}