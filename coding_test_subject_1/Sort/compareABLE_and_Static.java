package Sort;

import java.io.*;
import java.util.Arrays;

public class compareABLE_and_Static {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		Most_view[] qq = new Most_view[N];

		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			qq[i] = new Most_view(arr[i]);
		}	

		Arrays.sort(arr);
		Arrays.sort(qq);

		for(int i=0; i<arr.length; i++) {
			System.out.print(qq[i].val);
		}System.out.println();

		for(int i:arr) 
			bw.write(i+" ");
		bw.flush();
	}//=================================================

	static class Most_view implements Comparable<Most_view>{
		int val=0;
		int count=0;
		Most_view(int a){
			val = a;
			count = 1;
		}
		@Override
		public int compareTo(Most_view o) {	// sort�� �̰��� �������� �ϸ� �⺻sort�Լ��� ���켼���� ���� �ͺ��� ���켼���� ū ������ �������ش�. 
			if(this.val < o.val) 
				return -1;	// ��뿡�� �� ū���� �ο�
			else if(this.val > o.val) 
				return 1;	// ���� �� ���켼���� �ο� 
			return 0;	//�ƹ��� ��ȭ�� ����. 
		}
	}//=================================================
}
