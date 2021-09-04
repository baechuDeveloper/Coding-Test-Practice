package Deque;

import java.util.*;
import java.io.*;
// �ð��ʰ��� ���Դ�. Deque��� �ڷ����� ������ �˾Ұ�, �̷��� ��������� �۾��� �ʿ��Ҷ��� �� �ڷ����� ����� pollFirst ���� ������ �ſ� ���� ȿ���� �ش�.
/**----------------------------------------------------------------------
 * �� ���� �Լ� R(������)�� D(������)�� �ִ�.
 * �Լ� R�� �迭�� �ִ� ������ ������ ������ �Լ��̰�, D�� ù ��° ���ڸ� ������ �Լ��̴�. 
 * �迭�� ����ִµ� D�� ����� ��쿡�� ������ �߻��Ѵ�.
 * �Լ��� �����ؼ� �� ���� ����� �� �ִ�. 
 * ���� ���, "AB"�� A�� ������ ������ �ٷ� �̾ B�� �����ϴ� �Լ��̴�. ���� ���, "RDD"�� �迭�� ������ ���� ó�� �� ���ڸ� ������ �Լ��̴�.
 * �迭�� �ʱⰪ�� ������ �Լ��� �־����� ��, ���� ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
  -----------------------------------------------------------------------**/
public class AC {

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
		private LinkedList<Integer> list;
		private int now;
		private boolean r_mode, err;
		//------------------------------------------------
		//������
		My_Deque(String sArr){
			StringTokenizer st = new StringTokenizer( sArr.substring(1, sArr.length()-1), "," );
			//System.out.println(st.countTokens());
			list = new LinkedList<>();
			for(int i=0; i<N; i++)
				list.add(Integer.parseInt(st.nextToken()));
			now = 0;
			r_mode = false;
			err = false;
		}//------------------------------------------------
		// R����
		public void Reverse() {
			if(err==false) 
				if(r_mode==true) {
					r_mode = false;
					now = 0;
				}
				else {
					r_mode = true;
					now = list.size()-1;
				}
		}//------------------------------------------------
		// D����
		public void Delete() {
			if(list.size()==0) 
				err = true;
			else {
				list.remove(now);
				if(now!=0)
					now = now-1;
			}
		}//------------------------------------------------
		// ���
		public void Print() {
			if(err==false) {
				sb.append("[");
				
				if(r_mode==true && list.size()!=0 ) {
					for(int i=list.size()-1; i>0; i--) 
						sb.append(list.get(i)+",");
					sb.append(list.get(0));
				}
				else if(r_mode==false && list.size()!=0){
					for(int i=0; i<list.size()-1; i++) 
						sb.append(list.get(i)+",");
					sb.append(list.get(list.size()-1));
				}
				
				sb.append("]\n");
			}
			else 
				sb.append("error\n");
		}//------------------------------------------------
	}/**================================================================**/
}