package ETC;

import java.util.*;
import java.io.*;

// 1016�� - ���� ���� ��
public class zegop_nono_su {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		
		// 1���� ū ������ 4, 9, 16, 25, 36�ε� 
		// �� ���� ���� min�� max���̿� ��� �������� �ִ��� Ȯ���ϰ�, ��ü �������� �������� ������ŭ ���� �ȴ�.
		// �̶� min�� max���̿� 1�� ���ԵǾ��ִٸ� 1�� ��� �������μ� Ȱ����Ѵٰ� �Ѵ�.
		// ������ 4�� 4, 8, 12, 16, 20...���� �̾������ִ�.	
		
		Set<Long> set = new HashSet<>();
		
		long now = 2;
		long pow = now*now;
		while(pow<=max) {
			long d = min/pow;
			long c = min%pow;
			long val = d*pow;
			if(c>0) 
				val += pow;
			while(val <= max) {
				set.add(val);
				val += pow;
			}
			
			now++;
			pow = now*now;		
		}
		System.out.println(max-min+1-(set.size()));
	}

}
