package Math;

import java.util.*;
import java.io.*;

// 1037�� ���
// �ش� N�̶�� ���� �̷�� ��� ����� �־����ٴ� ����. (1�� �ڽ�N�� ������)
public class p2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		int len = arr.length;
		if(len==1) 
			bw.write(arr[0]*arr[0]+"\n");
		else 
			bw.write(arr[0]*arr[len-1]+"\n");
		
		bw.flush();
		
	}

}
