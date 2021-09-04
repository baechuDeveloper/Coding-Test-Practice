package ETC;

import java.io.*;

/*
 * (100+1+ | 01)+   으로 작업을 했는가.
 * 
 *	+라는 특징이 중요하다. 가장 중요한 점은 '적어도 한번은 사용'이 있어야 한다는 말이다.
 *  or의 특징은 둘중에 하나가 나올것이다. 이므로 이 특징을 살렷거 문제가 잘 나온것 같다.
 *  
 *  먼저 맨 앞이 1로 시작하냐 0으로 시작하냐가 or로 나뉘걸 것이다. 맨 처음이 0이면 01로 작업이 배치되고 대신에 01에서 이어지는 +는 없으니 다시 or로 넘어간다. 0은 바로 or로 넘어가는 중요한 수단이다.
 *  
 *  만약 맨 처음이 1이면 +라는 조건상 100은 적어도 한번은 먼저 앞에 나와야 한다. 그 이후에 0이 몇번이나 반복 될지를 파악하고 반복이 끝났다면 1이 몇번되는지 체크하면 된다. 마찬가지로 1은 반드시 한번은 나와야한다.
 *  여기서 0의 개수가 2개씩 늘어나는지는 01과 구분할 수 있는 좋은 정보다.
 * */

// 1013번 - Contact
public class Contact {

	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));	
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			char[] c = br.readLine().toCharArray();
			int len = c.length;
			int i = 0;
			boolean check = true;

			while(i<len) {
				//해당 if-else조건문은 가장 큰 ( | )+ 에 해당하는 파트로 or를 가른다. 
				
				if(c[i]=='0') {
					int next = i+1;
					if(next<len && c[next]=='1') {
						i = next+1;
					}
					else if(next<len && c[next]=='0' && i-2>=0 && c[i-1]=='1' && c[i-2]=='1') {	//어 나 지금 '00' 인것처럼 나왔는데 이미 틀린 길로 왔네... 어 근데 마지막 우회로서 너 혹시... 1+에서 추가 1을 포함하고있니? 그렇다면 내가 100+루트로 넘어가도 되겠다!
						i--; // 100+ 파트로 넘어가서 활용해볼수있다. 	
					}
					else { //모든 기회를 소진했으니 굳바이...
						check = false;
						break; //있어야 하는 파트이므로 없다면 바로 종료
					}
				}
				else {
					/** 0+ 가 몇번 되는지 확인을 한다. **/
					int next_1 = i+1, next_2 = i+2;
					//적어도 한번 있어야하는 100
					if(next_1<len && next_2<len && c[next_1]=='0' && c[next_2]=='0') {
						i = next_2 + 1;
					}
					else {
						check = false;
						break; //있어야 하는 파트이므로 없다면 바로 종료
					}
					//앞으로 0이 몇번이 나올지 체크해본다. 
					while(i<len && c[i]=='0') {		
						i++; // 추가로 확인을 더하는 구조라서 안된다면 넘어가면 된다..	
					}
	
					/** 1+ 가 몇번 되는지 확인한다. **/ 
					//적어도 한번 나와야하는 1
					if(i<len && c[i]=='1') {
						i++;
					}
					else {
						check = false;
						break; //있어야 하는 파트이므로 없다면 바로 종료
					}
					//앞으로 1이 몇번이 나올지 체크해본다. 
					while(i<len && c[i]=='1') {
						i++; // 추가로 확인을 더하는 구조라서 안된다면 넘어가면 된다.
					}
					
				}
	
			} // 한 문장을 끝냄.
			sb.append( check ? "YES\n" : "NO\n" );

		}
		bw.write(sb.toString());
		bw.flush();
	}

}
