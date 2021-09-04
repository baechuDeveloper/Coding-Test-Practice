package Deque;

import java.util.*;
import java.io.*;

// 644ms ���� �ð��ʰ��� �ߴ� �������� LinkedList �ڷ����� Deque �ڷ������� �ٲپ��� �� ���� ���� ȿ���� �ټ��ִ�. 
// ��Ȯ���� pollFirst���� ������ ���� ���·� ������ �ؼ� ���������� �������� �Ͱ���.
// ���� ��հԵ� �ٸ����� split ����� �����ϴ� ���´� �迭�� �����ΰ� �� �۾��� �ϴ°��ε�, �װͺ��� ���� ���� �ߴ� ����� �� ������. 
// �װ͵� 2�谡 ������. �޸𸮵� �� 35% �� ����.
/**----------------------------------------------------------------------
 * �� ���� �Լ� R(������)�� D(������)�� �ִ�.
 * �Լ� R�� �迭�� �ִ� ������ ������ ������ �Լ��̰�, D�� ù ��° ���ڸ� ������ �Լ��̴�. 
 * �迭�� ����ִµ� D�� ����� ��쿡�� ������ �߻��Ѵ�.
 * �Լ��� �����ؼ� �� ���� ����� �� �ִ�. 
 * ���� ���, "AB"�� A�� ������ ������ �ٷ� �̾ B�� �����ϴ� �Լ��̴�. ���� ���, "RDD"�� �迭�� ������ ���� ó�� �� ���ڸ� ������ �Լ��̴�.
 * �迭�� �ʱⰪ�� ������ �Լ��� �־����� ��, ���� ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
  -----------------------------------------------------------------------**/
public class AC_fast_best {

	static private int N;
	static private StringBuilder sb = new StringBuilder();
	/**================================================================**/
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_N = Integer.parseInt(br.readLine());
		My_Deque dq;

		for(int z=0; z<test_N; z++) {
			String cmd = br.readLine();
			N = Integer.parseInt(br.readLine());
			String sArr = br.readLine();
			dq = new My_Deque(sArr);
			
			char c;
			int len = cmd.length();
			for(int i=0; i<len; i++) {
				c = cmd.charAt(i);
				if(c=='R')
					dq.Reverse();
				else if(c=='D')
					dq.Delete();
			}
			dq.Print();
		}
		bw.write(sb.toString());
		bw.flush();
	}/**================================================================**/
	//���� Ŭ���� Deque
	static class My_Deque {
		//�������
		private Deque<Integer> dq;
		private boolean r_mode, err;
		//------------------------------------------------
		//������
		My_Deque(String sArr){
			StringTokenizer st = new StringTokenizer( sArr.substring(1, sArr.length()-1), "," );
			dq = new LinkedList<>();
			for(int i=0; i<N; i++)
				dq.add(Integer.parseInt(st.nextToken()));
		
			r_mode = false;
			err = false;
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
			else {
				if(r_mode==false)
					dq.pollFirst();
				else dq.pollLast();
			}
		}//------------------------------------------------
		// ���
		public void Print() {
			if(err==false) {
				sb.append("[");

				int len = dq.size();	//�̷��� ���ϸ� poll�� �ؼ� ���� dq�� ����� �۾����� ������ ũ�� �𸣰� ����� �ϰԵȴ�.
				if(r_mode==true &&  len!=0 ) {
					for(int i=len-1; i>0; i--) 
						sb.append(dq.pollLast()+",");
					sb.append(dq.pollLast());
				}
				else if(r_mode==false && len!=0){
					for(int i=0; i<len-1; i++) 
						sb.append(dq.pollFirst()+",");
					sb.append(dq.pollFirst());
				}
				
				sb.append("]\n");
			}
			else 
				sb.append("error\n");
		}//------------------------------------------------
	}/**================================================================**/
}