package Math;

import java.util.*;
import java.io.*;

// 5086�� - ����� ���
// factor - ���, multiple - ���, neither - �� �� �ƴ�
public class p1 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); // �� ���� ���� ���� ����.
			if(a==0 && b==0) break;
			
			if( a<b && b%a==0 ) {	//a�� b�� ���
				sb.append("factor\n");
			}
			else if( a>b && a%b==0 ) {	//a�� b�� ���
				sb.append("multiple\n");
			}
			else {	// a�� b�� �ƹ��͵� �ƴϴ�.
				sb.append("neither\n");
			}	
		}
		
		bw.write(sb.toString());
		bw.flush();
		
	}

}
