package ETC;
import java.util.*;
import java.io.*;

// 1022�� - �ҿ뵹�� ���ڰ� ����ϱ�
public class soyongdolyee {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());	//�� ù��°
		int c1 = Integer.parseInt(st.nextToken());	//�� ù��°
		int r2 = Integer.parseInt(st.nextToken());	//�� ������
		int c2 = Integer.parseInt(st.nextToken());	//�� ������
		//����� r1����� r2����� ���ʴ�� ����Ѵ�.

		for(int r=r1; r<=r2; r++) {
			for(int c=c1; c<=c2; c++) 
				sb.append(getNum(r,c)+" ");
			sb.append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
	}//===========================================================

	private static long getNum(int r, int c) {
		int p = Math.max( Math.abs(r), Math.abs(c) ); //���� �ڽ��� ������ �˾ƺ���. 
		int pn = p;
		long pval = 1;
		if(pn!=0) {
			pn--; //�׺��� �Ʒ� ������ �����ϸ� �ȴ�. 
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
