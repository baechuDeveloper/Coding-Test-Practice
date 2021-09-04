package Backtracking;

import java.util.*;
import java.io.*; 

// ��Ʈ��ŷ���� �ϴ� ���� �� ����ε�... �̸� �������� DFS�� �����ϴ°͵� ������ �� �������̴�. 

// ������ ����ֱ�
// �Ʒ��� ���� Ʋ�ȴ�.
// �����ϴٰ� �׸��ξ��µ� �� ���̵��� ��� ������ ����� ���� ���صξ� �ߺ��Ǵ°��� set���� �����ϰ� '�����Ǿ��ִ� ����'�� �������� �ϳ��� ���°ǵ�.
// ������ �켱������ ���ٴ� ���� �˾�����. �̸� �� �����غ��� ... ���� �����ڸ� ������ ���ΰ� ���ڸ� �����̴� �߻��� �����ϴ�. �̷��� �� �������� �켱������ ���� �����ϱ� �����̴�.
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
		
		combination(0);	//������ �����

		String[] str = new String[set.size()];
		str = set.toArray(str);
		
		cal_Max_Min(str);	//����� �ϱ�
		
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
		int len = str.length;	// ��������� ����
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
