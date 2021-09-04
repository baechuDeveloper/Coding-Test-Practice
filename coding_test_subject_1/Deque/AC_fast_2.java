package Deque;

import java.util.*;
import java.io.*;

// 어머 진짜 Deque<>라는 Collections가 있었네
// 1212ms - AC_fast에서 Reverse(), Delete호출을 줄여본것 물론 약간 빨라졋지만 시간초과를 위반할 정도가 아니다.
/**----------------------------------------------------------------------
 * 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
 * 함수 R은 배열에 있는 숫자의 순서를 뒤집는 함수이고, D는 첫 번째 숫자를 버리는 함수이다. 
 * 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
 * 함수는 조합해서 한 번에 사용할 수 있다. 
 * 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 숫자를 버리는 함수이다.
 * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오. 
  -----------------------------------------------------------------------**/
public class AC_fast_2 {

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
					if(err==false) 
						if(r_mode==true) 
							r_mode = false;		
						else 
							r_mode = true;
				}
				else if(i.equals("D")){
					if(dq.isEmpty()) 
						err = true;
					else if (r_mode==false)
						dq.pollFirst();
					else if (r_mode==true) 
						dq.pollLast();
				}		
			}
			
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
