package Binary_search;

import java.io.*;
import java.util.*;

// 1654��-�����ڸ��� 
// �̺�Ž���� ������ ����.
// ��:�� "N������ ���� ����� �͵� N���� ����� �Ϳ� ���Եȴ�."
public class basic_Parametric_Search {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
	
		long[] line = new long[k];
		for(int i=0; i<k; i++) 
			line[i] = Long.parseLong(br.readLine());
		
		System.out.println( cut_line(n, line) );
	}
	
	public static long cut_line(int n, long[] line) {
		Arrays.sort(line);
		int k = line.length;
		// �� ó������ ����... ��� �ִ밡 �� �� �ִ� ���� ���̴� �� k���� ������ ���� �������� �ִ밡 �ɼ��ִ� �ڸ��� ���� ���̰� �ȴ�.
		long left = 1; //�׷��� ���� ���̴� 0�� �ƴѰ� ���Ƽ� 1���� ����. ��� ���������� 0�� �ּҰ� �ɼ����⿡ �����صε����Ѵ�. 
		long right = line[k-1]; //�ּұ��̰� �ڸ��� ���� ���ؼ��� �ִ���̴�.
		long mid = (left+right)/2;
		long line_count = 0;
		long answer = 0;
	
		while(left<=right) {
			
			mid = (left+right)/2;
			line_count = 0;
			for(int i=0; i<k; i++) 
				line_count += ( line[i]/mid ); //�ڸ� ���� ������ �����ش�.

			if(line_count > n) {	// ���󺸴� ���� ���� �߶��� -> �ڸ��� ���� ���̰� �ʹ� ª�Ƽ� ���� ���� ���Դ�.
				left = mid+1;  //���̸� �ø���. 
				//��;�� �Ʒ� ���ǹ��� ���� ��Ȯ�� n��ŭ�� ���ϴ°Ÿ� �ʿ������ �� �̻��� ���� �Ǵ� ������ �ȴ�.
				if(mid > answer) { //������ ���� ���� �亸�� �� ũ�ٸ� �� ū ������ �����ش�.
					answer = mid;
				}
			}
			else if(line_count < n) { //���� ���� ���Դ� -> �ڸ��� ���� ���̰� �������� �� ���� ���Դ�.
				right = mid-1; //���̸� ���δ�. 
			}
			else if(line_count == n) {	//��Ȯ�� ���ϴ� ������ �������� �ִ���� �������.
				left = mid+1;
				if(mid > answer) { //������ ���� ���� �亸�� �� ũ�ٸ� �� ū ������ �����ش�.
					answer = mid;
				}
			}	
			
		}
		return answer;
	}

}
