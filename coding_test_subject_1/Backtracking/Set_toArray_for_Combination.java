package Backtracking;

import java.util.*;
import java.io.*; 

// 백트래킹으로 하는 것이 이 방법인데... 이를 생각말고 DFS로 진행하는것도 있으며 더 빠를것이다. 

// 연산자 끼어넣기
// 아래의 말은 틀렸다.
// 진행하다가 그만두었는데 이 아이디어는 모든 연산의 경우의 수를 구해두어 중복되는것을 set으로 제거하고 '고정되어있는 숫자'를 연산으로 하나씩 얻어가는건데.
// 연산자 우선순위가 없다는 것은 알았지만. 이를 더 생각해보면 ... 그저 연산자를 가만히 놔두고 숫자만 움직이는 발상이 가능하다. 이러한 건 연산자의 우선순위가 서로 동일하기 때문이다.
public class Set_toArray_for_Combination {

	private static int N, max, min;
	private static int[] num;
	private static char[] cal, sentence;
	private static boolean[] visit;
	private static Set<String> set;
	private static StringBuilder sb;
	//--------------------------------------------------------

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		cal = new char[N-1];	// ( +, -, *, / )
		sentence = new char[N-1];
		visit = new boolean[N-1];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) num[i]=Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int t=0, old_t=0;
		for(int i=0; i<4; i++) {
			t = Integer.parseInt(st.nextToken());
			if(i==0) 
				for(int j=0; j<t; j++)				cal[j] = '+';
			else if(i==1) 
				for(int j=old_t; j<t+old_t; j++)	cal[j] = '-';
			else if(i==2) 
				for(int j=old_t; j<t+old_t; j++)	cal[j] = '*';
			else 
				for(int j=old_t; j<N-1; j++)		cal[j] = '/';
			old_t += t;
		}
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		set = new HashSet<>();
		
		combination(0);	//조합을 만들기

		String[] str = new String[set.size()];
		str = set.toArray(str);
		
		cal_Max_Min(str);	//계산을 하기
		
		bw.write(max+"\n"+min);
		bw.flush();
	}//--------------------------------------------------------

	public static void combination(int k) {
		if(k==N-1) {
			sb = new StringBuilder();
			for(char i:sentence)
				sb.append(i);
			set.add(sb.toString());
			return;
		}
		for(int i=0; i<N-1; i++) {
			if(visit[i] == false) {
				visit[i] = true;
				sentence[k] = cal[i];
				combination(k+1);
				visit[i] = false;
			}
		}
	}//--------------------------------------------------------

	public static void cal_Max_Min(String[] str) {
		int len = str.length;	// 연산모음의 개수
		int result, R;
		char c;
		for(int i=0; i<len; i++) {
			result = num[0];
			for(int j=0; j<N-1; j++) {
				R = num[j+1];
				c = str[i].charAt(j);
				if(c=='+') 
					result += R;
				else if(c=='-') 
					result -= R;
				else if(c=='*') 
					result *= R;
				else if(c=='/') 
					result /= R;
			}
			if(min > result) min = result;
			if(max < result) max = result;
		}
		
	}//--------------------------------------------------------
}
