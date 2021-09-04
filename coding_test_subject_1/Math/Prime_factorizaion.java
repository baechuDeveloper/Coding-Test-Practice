package Math;

import java.util.*;
import java.io.*;

// 11653�� - ���μ� ����
// N�� ���μ����� ����� �� �ٿ� �ϳ��� ������������ ����ض�.
// �׷��� ���� ���μ��� ������ ������ ���� �Ҽ�prime���� ��Ÿ����. 
public class Prime_factorizaion {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int now = N;
		ArrayList<Integer> list = new ArrayList<>();
	
		while(now!=1) {
			
			for(int div=2; div<=now; div+=1) {
				if(now%div==0) {
					//System.out.println("now:"+now+" div:"+div);
					list.add(div);
					now = now/div;
					div = 2;
					break;
				}
			}
		}
		list.sort(null);
		for(int i:list)
			bw.write(i+"\n");
		bw.flush();
	}

}
