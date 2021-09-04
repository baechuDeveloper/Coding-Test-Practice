package Brute_Force;

import java.io.*;

// ºÐÇØÇÕ
public class p2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		String str = "";
		int i=1, len, find_sum=0;
		
		while(true) {
			str = i+"";	//System.out.print(str+" ");
			len = str.length();
			find_sum = i;
			for(int j=0; j<len; j++) 
				find_sum += (str.charAt(j)-48);
			//System.out.println(find_sum);
			
			if(find_sum == N) {
				bw.write(str);
				break;
			}
			else if(i>N) {
				bw.write(0+"");
				break;
			}
			++i;
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
