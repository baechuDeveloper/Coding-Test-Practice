package Deque;

import java.util.*;
import java.io.*;
// 시간초과가 나왔다. Deque라는 자료형이 있음을 알았고, 이렇게 양방향으로 작업이 필요할때는 이 자료형을 사용해 pollFirst 같은 연산이 매우 빠른 효과를 준다.
/**----------------------------------------------------------------------
 * 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 * 함수 R은 배열에 있는 숫자의 순서를 뒤집는 함수이고, D는 첫 번째 숫자를 버리는 함수이다. 
 * 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 * 함수는 조합해서 한 번에 사용할 수 있다. 
 * 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 숫자를 버리는 함수이다.
 * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오. 
  -----------------------------------------------------------------------**/
public class AC {

	static private int N;
	static private StringBuilder sb = new StringBuilder();
	/**================================================================**/
	//main함수
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
	//내부 클래스 Deque
	static class My_Deque {
		//멤버변수
		private LinkedList<Integer> list;
		private int now;
		private boolean r_mode, err;
		//------------------------------------------------
		//생성자
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
		// R연산
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
		// D연산
		public void Delete() {
			if(list.size()==0) 
				err = true;
			else {
				list.remove(now);
				if(now!=0)
					now = now-1;
			}
		}//------------------------------------------------
		// 출력
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