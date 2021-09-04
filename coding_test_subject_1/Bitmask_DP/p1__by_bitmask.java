package Bitmask_DP;

import java.util.*;
import java.io.*;

// 11723번 백준 - 집합
// 비트마스크를 사용해서 더욱 메모리를 효율적으로 사용. 
/**---------------------------------------------------------
 * int 자료형 a를 선언하면 4바이트(4 * 8bit)를 메모리에 할당받아
 * 총 32개에 대에 참, 거짓을 판단할 수 있게 된다.
 * a = 00000000 00000000 00000000 00000000(2)로 메모리에 할당받는다.
 * 따라서, 총 0~31까지 수의 집합을 나타낼 수 있다.
 * 2^0자리 ▶ 0의 true, false
   2^1자리 ▶ 1의 true, false
   2^2자리 ▶ 2의 true, false
   ...
   2^30자리 ▶ 30의 true, false
   2^31자리 ▶ 31의 true, false
   
   이 형태를 비트마스크 DP 형태라고 보는 모양이다.
============================================================**/
public class p1__by_bitmask {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int bitMask = 0;	//현재 32비트에 아무것도 들어있지 않은 상태 
		
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int num = -1;
			if(st.hasMoreTokens())
				num = Integer.parseInt(st.nextToken());
		
			switch (cmd) {	//swtich문에서는 String에 대해서 equals로 처리 된다. '=='으로 진행되지 않았다. 즉 cmd 안에 있는 내용물의 값이 무엇이니? 라고 물어보는것 같다. swtich에서 다른 자료형에 대해서도 주소값이 아닌 내용물을 보고 싶어 할것이다.
				case "add":
					bitMask = bitMask | 1<<(num-1); break;	//현재 비트마스크에 해당 번호의 비트를 1로 넣어주도록 | 비트or연산을 해준다.
				
				case "remove":
					bitMask = bitMask & ~(1<<(num-1)); break; // ~을 이용해서 해당 부분만 비트값을 0으로 바꾸어 & 비트and연산을 적용해주어 없애준다. 
				
				case "check":
					if( (bitMask & 1<<(num-1))  ==  1<<(num-1) )	//(오른쪽)해당 부분의 비트가 왼쪽에도 있는가를 &연산자로 나머지 비트는 모두 0으로 바꿔 비교를 할 수 있다. 
						sb.append(1+"\n");
					else
						sb.append(0+"\n");
					break;
				
				case "toggle":
					bitMask = bitMask ^ 1<<(num-1);	break;	// ^을 이용해서 해당 부분 비트만 1인 XOR비트연산을 진행한다. 
					// 그렇게하면 나머지 비트들은 모두 0으로만 ^연산이 진행되므로 원래 1이면 서로달라서 1로 나오고, 0이면 서로 같아서 0으로 연산이 되어진다. 
					// 해당 비트 부분은 1로 진행을 하는데 만약 원래 1로 가지고있다면 서로 같아서 0으로 변경이 되고, 0을 가지고있다면 달라서 1을 가지도록 변경된다.
					// 직관적으로 말하자면 오른쪽에 제공해주는 비트에서 0비트로 보내주는건 왼족의 각 비트에게 영향을 안주겠다 이며, 오른쪽에서 1비트로 주는 부분만 체크해서 있다면 없애고 없다면 있게 연산이 되도록 하는 방식이다. 
				
				case "all":
					bitMask = ~0; break;	// 매우 유용한 방법 자료형에 모든 비트를 1로 바꾸어 집어 넣기
				
				case "empty":
					bitMask = 0; break;
			}
		}//반복문 종료 
		
		bw.write(sb.toString());
		bw.flush();
	}

}
