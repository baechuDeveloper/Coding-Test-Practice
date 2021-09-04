package ETC;

import java.io.*;

/*
 * ���� DFA�� ���� �����ߴ�. start�� �ѹ��� ������ �ؼ� ó������ ���ƿö��� �� ��Ȱ ���� state�� �߰����ְų�. ��Ȳ�� ���� �ٸ� �б⸦ ���ο� state�� �غ���. 
 * */

// 1013�� - Contact
public class Contact_DFA {

	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));	
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			char[] c = br.readLine().toCharArray();
			int len = c.length;
			int i = 0;
			boolean check = false;
			int state = 0; //0�� start�� �ǹ��ϴ� ����, -1�� fail�� �ǹ��ϴ� ����, �� ���� �ڿ����� ��� ������ state�� �ִ�.   
			while(i<len) {
				if(check) break;
				
				switch(state) {
				case -1:	//fail
					check = true;
					break;
				case 0:		//start
					if(c[i]=='0') state=1; else state=3; break;
				case 1:
					if(c[i]=='0') state=-1; else state=2; break;
				case 2:
					if(c[i]=='0') state=1; else state=3; break;
				case 3:
					if(c[i]=='0') state=4; else state=-1; break;
				case 4:
					if(c[i]=='0') state=5; else state=-1; break;
				case 5:
					if(c[i]=='0') state=5; else state=6; break;
				case 6:
					if(c[i]=='0') state=1; else state=7; break;
				case 7:
					if(c[i]=='0') state=8; else state=7; break;
				case 8:
					if(c[i]=='0') state=5; else state=2; break;
					
				}
				
				i++;	
			} // �� ������ ����.
			
			//System.out.println(state);
			if(state==2 || state==6 || state==7)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
