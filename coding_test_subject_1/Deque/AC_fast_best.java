package Deque;

import java.util.*;
import java.io.*;

// 644ms 내가 시간초과를 했던 문제에서 LinkedList 자료형을 Deque 자료형으로 바꾸었을 때 더욱 빠른 효과를 줄수있다. 
// 정확히는 pollFirst같이 연산이 적은 형태로 접근을 해서 더욱더욱더욱 빨라지는 것같다.
// 또한 재밌게도 다른이의 split 방식을 적용하는 형태는 배열을 만들어두고 또 작업을 하는것인데, 그것보다 여태 내가 했던 방식이 더 빨랐다. 
// 그것도 2배가 빠르다. 메모리도 약 35% 더 좋다.
/**----------------------------------------------------------------------
 * 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 * 함수 R은 배열에 있는 숫자의 순서를 뒤집는 함수이고, D는 첫 번째 숫자를 버리는 함수이다. 
 * 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 * 함수는 조합해서 한 번에 사용할 수 있다. 
 * 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 숫자를 버리는 함수이다.
 * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오. 
  -----------------------------------------------------------------------**/
public class AC_fast_best {

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
		private Deque<Integer> dq;
		private boolean r_mode, err;
		//------------------------------------------------
		//생성자
		My_Deque(String sArr){
			StringTokenizer st = new StringTokenizer( sArr.substring(1, sArr.length()-1), "," );
			dq = new LinkedList<>();
			for(int i=0; i<N; i++)
				dq.add(Integer.parseInt(st.nextToken()));
		
			r_mode = false;
			err = false;
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
			else {
				if(r_mode==false)
					dq.pollFirst();
				else dq.pollLast();
			}
		}//------------------------------------------------
		// 출력
		public void Print() {
			if(err==false) {
				sb.append("[");

				int len = dq.size();	//이렇게 안하면 poll을 해서 점점 dq의 사이즈가 작아져서 원래의 크기 모르고 출력을 하게된다.
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