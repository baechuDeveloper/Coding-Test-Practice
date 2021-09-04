package Greedy;

import java.io.*;

//1541�� - �Ҿ���� ��ȣ 
/* =================================================================
 * �ٷ� ������ �Դµ� �̰� �׸��� ������...���ڿ� ó������. 
 * �ᱹ +,- ������� ������ ��� �ϳĿ� ���̷� �ּ� ���� ���϶�� �Ѵٸ� 
 * 
 * �ٽ����� ���̴� ��ȣ�� ���� �� ���� ��ȣ�� ������ �ȴٴ� ���� �̿��ϴ� ���̴�. 
 * 
 *  -(50 + 40) ���� ��ȣ�� ġ�� -90�̶�� ����� ������ �տ� -�� �̿��ؼ� �ڿ� +�� ������ -�� �ٲپ� �ָ� �ȴ�. 
 * 
 * -40+50-23-45-67+13 = -(40+50)-23-45-(67+13) 
 * 50+40+50+30+50-34-45+23+24+54 = (50+40+50+30+50)-34-(45+23+24+54)
 * 
 * '-'�� �ѹ��̶� ������ �� �ڿ� ������ ��� ���ڴ� -�� �ٲپ �ȴ�.
 * ���� 10-40+30-45+23 �̶��ص� �տ� -�� �ڿ� -�� ���� ó�� -�� ���� ���ķ� ��� -�� �ȴ�. 
 *=================================================================== */
public class p4__Lost_Parenthesis {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(br.readLine());
		if(sb.charAt(0)=='-') sb.insert(0, 0);
		int len = sb.length();
		int ans = 0;
		int start = 0;
		boolean minus = false;
		
		for(int i=0; i<len; i++) {
			char c = sb.charAt(i);
			if(c=='+') {
				if(minus==false) 
					ans += Integer.parseInt(sb.substring(start, i));	//start���� i����(������)
				else 
					ans -= Integer.parseInt(sb.substring(start, i));	//start���� i����(������)
				start = i+1;
			}
			else if(c=='-') {
				if(minus==false) 
					ans += Integer.parseInt(sb.substring(start, i));	//start���� i����(������)
				else 
					ans -= Integer.parseInt(sb.substring(start, i));	//start���� i����(������)
				minus = true;
				start = i+1;
			}
		}
		
		if(minus==false) 
			ans+=Integer.parseInt(sb.substring(start, len));	//start���� ����������(������)
		else 
			ans-=Integer.parseInt(sb.substring(start, len));	//start���� ����������(������)
		
		System.out.println(ans);
	}

}
