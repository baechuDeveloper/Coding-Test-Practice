package ETC;
import java.util.*;
import java.io.*;

// 1022번 - 소용돌이 예쁘게 출력하기
public class soyongdolyee {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());	//행 첫번째
		int c1 = Integer.parseInt(st.nextToken());	//열 첫번째
		int r2 = Integer.parseInt(st.nextToken());	//행 마지막
		int c2 = Integer.parseInt(st.nextToken());	//열 마지막
		//출력은 r1행부터 r2행까지 차례대로 출력한다.

		for(int r=r1; r<=r2; r++) {
			for(int c=c1; c<=c2; c++) 
				sb.append(getNum(r,c)+" ");
			sb.append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
	}//===========================================================

	private static long getNum(int r, int c) {
		int p = Math.max( Math.abs(r), Math.abs(c) ); //현재 자신의 차수를 알아본다. 
		int pn = p;
		long pval = 1;
		if(pn!=0) {
			pn--; //그보다 아래 차수로 진행하면 된다. 
			pval = (long)Math.pow( pn*2-1 , 2 );


			if(r==-p) {
				int pr = p-1;
				int pc = p;
				pval += Math.abs(pr-r) + Math.abs(pc-c);
			}
			else if(r==p) {
				int pr = p-1;
				int pc = p;
				pval += Math.abs(pr-r) + Math.abs(pc-c);
			}
			else if(c==p) {

			}
			else if(c==-p) {

			}
		}

		return pval;
	}//===========================================================

}
