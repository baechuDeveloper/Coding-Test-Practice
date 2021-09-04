package ETC;

import java.io.*;

/*
 * 실제 DFA를 만들어서 구성했다. start를 한번만 나오게 해서 처음으로 돌아올때는 그 역활 해줄 state를 추가해주거나. 상황에 따라 다른 분기를 새로운 state로 해본다. 
 * */

// 1013번 - Contact
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
			int state = 0; //0은 start를 의미하는 상태, -1은 fail을 의미하는 상태, 그 외의 자연수는 모두 각자의 state가 있다.   
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
			} // 한 문장을 끝냄.
			
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
