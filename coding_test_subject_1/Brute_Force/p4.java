package Brute_Force;

import java.io.*;

/**

 666
 1666
 2666
 3666
 ��Ʈ������ �ϳ��� ������ Ǯ��°���  ���߿�������ȴ٤� 
 
 **/

// ��ȭ���� ��
public class p4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		int title = 666;
		String s = "";
		
		while(true) {
			s = title+"";
			if(s.contains("666") == true) 
				++count;
			
			if(count == N) {
				System.out.println(title);
				break;
			}
			++title;
		}
		
		bw.flush();
		bw.close();
		br.close();
	}//==================================================

}
